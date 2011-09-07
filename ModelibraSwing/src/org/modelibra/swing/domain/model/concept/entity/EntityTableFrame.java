package org.modelibra.swing.domain.model.concept.entity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import org.modelibra.IEntities;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.swing.app.App;
import org.modelibra.swing.widget.ModelibraFrame;

@SuppressWarnings("serial")
public class EntityTableFrame extends ModelibraFrame {

	public EntityTableFrame(App app, boolean internalContext,
			boolean displayOnly, IEntities<?> entities,
			List<PropertyConfig> propertyConfigList,
			List<NeighborConfig> neighborConfigList) {
		super(app);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		addTitle(entities);
		addEntityTable(internalContext, displayOnly, entities,
				propertyConfigList, neighborConfigList);
		pack();
	}

	protected void addTitle(IEntities<?> entities) {
		setTitle(this.getApp().getNatLang().getText(
				entities.getConceptConfig().getEntitiesCode()));
	}

	protected void addEntityTable(boolean internalContext, boolean displayOnly,
			IEntities<?> entities, List<PropertyConfig> propertyConfigList,
			List<NeighborConfig> neighborConfigList) {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		EntityTablePanel entityTablePanel = new EntityTablePanel(this,
				internalContext, displayOnly, entities, propertyConfigList,
				neighborConfigList);
		container.add(entityTablePanel);
	}

}
