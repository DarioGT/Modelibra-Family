package modelibra.wicket.component;

import java.util.ArrayList;
import java.util.List;

import modelibra.wicket.component.util.PropertyPanelList;
import modelibra.wicket.component.widget.EntityPropertyLabelPanel;
import modelibra.wicket.model.EntityModel;
import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.util.LocalizedText;

@SuppressWarnings("serial")
public class EntityPanel extends Panel {

	public EntityPanel(String id, EntityModel entityModel) {
		super(id);
		addTitle(entityModel);
		addPropertyValues(entityModel);
	}

	protected void addTitle(EntityModel entityModel) {
		add(new Label("conceptName", LocalizedText.getConceptName(this,
				entityModel.getEntity())));
	}

	protected void addPropertyValues(EntityModel entityModel) {
		List<Panel> propertyLabelPanels = new ArrayList<Panel>();
		for (EntityPropertyModel entityPropertyModel : entityModel
				.getEntityPropertyModelList()) {
			propertyLabelPanels.add(new EntityPropertyLabelPanel(
					"propertyValue", entityPropertyModel));
		}
		add(new PropertyPanelList("propertyPanelList", propertyLabelPanels));
	}

}
