package org.modelibra.swing.domain.model.concept.entity;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.modelibra.Entity;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.swing.domain.model.concept.entity.neighbor.EntityNeighborButton;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.swing.widget.ModelibraPanel;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class EntityAttributesPanel extends ModelibraPanel implements Observer {

	private ModelibraFrame contentFrame;

	private JLabel messageLabel;

	private IEntity<?> entity;

	private List<NeighborConfig> neighborConfigList;

	public EntityAttributesPanel(ModelibraFrame contentFrame,
			boolean internalContext, boolean displayOnly, boolean add,
			final IEntities<?> entities, IEntity<?> entity,
			List<PropertyConfig> propertyConfigList,
			List<NeighborConfig> neighborConfigList) {
		this.contentFrame = contentFrame;
		this.entity = entity;
		this.neighborConfigList = neighborConfigList;

		entities.getErrors().empty();

		((Entity<?>) entity).addObserver(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		addMessage();
		addProperties(displayOnly, add, entities, entity, propertyConfigList);
		addExternalParentLookups(internalContext, displayOnly, add, entities,
				entity, neighborConfigList);
		addNeighborButtons(displayOnly, entity, neighborConfigList);
		addAbsorbedParents(entity, neighborConfigList);
		if (add) {
			addAction(entities, entity);
		}
	}

	private void addMessage() {
		JPanel messagePanel = new JPanel();
		messageLabel = new JLabel("");
		messagePanel.add(messageLabel);
		add(messagePanel);
	}

	protected void addProperties(boolean displayOnly, boolean add,
			IEntities<?> entities, IEntity<?> entity,
			List<PropertyConfig> propertyConfigList) {
		add(new EntityPropertiesPanel(contentFrame, displayOnly, add, entities,
				entity, propertyConfigList));
	}

	protected void addExternalParentLookups(boolean internalContext,
			boolean displayOnly, boolean add, IEntities<?> entities,
			IEntity<?> entity, List<NeighborConfig> neighborConfigList) {
		if (!displayOnly) {
			List<NeighborConfig> externalParentNeighborConfigList = getExternalParentNeighborConfigList(
					internalContext, entity, neighborConfigList);
			if (externalParentNeighborConfigList.size() > 0) {
				add(new EntityParentLookupsPanel(contentFrame, add, entities,
						entity, externalParentNeighborConfigList));
			}
		}
	}

	private List<NeighborConfig> getParentNeighborConfigList(
			List<NeighborConfig> neighborConfigList) {
		List<NeighborConfig> parentNeighborConfigList = new ArrayList<NeighborConfig>();
		if (neighborConfigList != null && neighborConfigList.size() > 0) {
			for (NeighborConfig neighborConfig : neighborConfigList) {
				if (neighborConfig.isParent()) {
					parentNeighborConfigList.add(neighborConfig);
				}
			}
		}
		return parentNeighborConfigList;
	}

	private List<NeighborConfig> getExternalParentNeighborConfigList(
			boolean internalContext, IEntity<?> entity,
			List<NeighborConfig> neighborConfigList) {
		List<NeighborConfig> externalParentNeighborConfigList = new ArrayList<NeighborConfig>();
		if (neighborConfigList != null && neighborConfigList.size() > 0) {
			for (NeighborConfig neighborConfig : neighborConfigList) {
				if (neighborConfig.isParent() && neighborConfig.isExternal()) {
					if (internalContext) {
						externalParentNeighborConfigList.add(neighborConfig);
					}
				}
			}
		}
		return externalParentNeighborConfigList;
	}

	// for all neighbors
	protected void addNeighborButtons(boolean displayOnly,
			final IEntity<?> entity, List<NeighborConfig> neighborConfigList) {
		if (neighborConfigList != null && neighborConfigList.size() > 0) {
			JPanel neighborButtonsPanel = new JPanel();
			for (NeighborConfig neighborConfig : neighborConfigList) {
				neighborButtonsPanel.add(new EntityNeighborButton(contentFrame,
						displayOnly, neighborConfig) {
					protected IEntity<?> getEntity() {
						return entity;
					}
				});
			}
			add(neighborButtonsPanel);
		}
	}

	// absorptions of essential properties of parent neighbors
	protected void addAbsorbedParents(IEntity<?> entity,
			List<NeighborConfig> neighborConfigList) {
		List<NeighborConfig> parentNeighborConfigList = getParentNeighborConfigList(neighborConfigList);
		if (parentNeighborConfigList.size() > 0) {
			add(new EntityAbsorbedParentsPanel(contentFrame, entity,
					parentNeighborConfigList));
		}
	}

	protected void addAction(final IEntities<?> entities,
			final IEntity<?> entity) {
		JPanel actionPanel = new JPanel();
		add(actionPanel);
		final NatLang natLang = contentFrame.getApp().getNatLang();
		JButton addButton = new JButton(natLang.getText("add"));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMessage(natLang.getText("empty"));
				EntitiesAction action = new AddAction(contentFrame.getApp()
						.getModelSession(), entities, entity);
				action.execute();
				if (!action.isExecuted()) {
					setMessage(natLang.getText("addNot") + " "
							+ getErrorMsgsByKeys(entities, natLang));
				} else {
					contentFrame.exit();
				}
			}
		});
		actionPanel.add(addButton);
	}

	private void setMessage(String message) {
		messageLabel.setText(message);
		contentFrame.pack();
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		if (o == entity) {
			if (arg instanceof PropertyConfig) {
				PropertyConfig propertyConfig = (PropertyConfig) arg;
				if (propertyConfig.isReference()) {
					for (Component component : getComponents()) {
						if (component instanceof EntityAbsorbedParentsPanel) {
							remove(component);
						}
					}
					addAbsorbedParents(entity, neighborConfigList);
					contentFrame.pack();
				}
			}
		}
	}

}
