package org.modelibra.swing.domain.model.concept.entity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.swing.app.App;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class EntityAttributesFrame extends ModelibraFrame {

	public EntityAttributesFrame(App app, boolean internalContext,
			boolean displayOnly, boolean add, IEntities<?> entities,
			IEntity<?> entity, List<PropertyConfig> propertyConfigList,
			List<NeighborConfig> neighborConfigList) {
		super(app);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		addTitle(entity, app.getNatLang());
		addAttributes(internalContext, displayOnly, add, entities, entity,
				propertyConfigList, neighborConfigList);
		pack();
	}

	protected void addTitle(IEntity<?> entity, NatLang natLang) {
		setTitle(natLang.getText(entity.getConceptConfig().getCode()));
	}

	protected void addAttributes(boolean internalContext, boolean displayOnly,
			boolean add, IEntities<?> entities, IEntity<?> entity,
			List<PropertyConfig> propertyConfigList,
			List<NeighborConfig> neighborConfigList) {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(new EntityAttributesPanel(this, internalContext,
				displayOnly, add, entities, entity, propertyConfigList,
				neighborConfigList));
	}

}
