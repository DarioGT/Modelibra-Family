package modelibra.wicket.component;

import java.util.ArrayList;
import java.util.List;

import modelibra.wicket.component.util.PropertyPanelList;
import modelibra.wicket.component.widget.EntityPropertyLabelPanel;
import modelibra.wicket.model.EntityModel;
import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.wicket.util.LocalizedText;

import dmeduc.wicket.app.home.HomePage;

@SuppressWarnings("serial")
public class EntityForm extends Form {

	public EntityForm(String id, EntityModel entityModel) {
		super(id);
		setModel(entityModel);
		addTitle(entityModel);
		addProperties(entityModel);
		addCancel();
	}

	protected void addTitle(EntityModel entityModel) {
		add(new Label("conceptName", LocalizedText.getConceptName(this,
				entityModel.getEntity())));
	}

	protected void addProperties(EntityModel entityModel) {
		List<Panel> propertyPanels = new ArrayList<Panel>();
		for (EntityPropertyModel entityPropertyModel : entityModel
				.getEntityPropertyModelList()) {
			propertyPanels.add(new EntityPropertyLabelPanel("propertyValue",
					entityPropertyModel));
		}
		add(new PropertyPanelList("propertyPanelList", propertyPanels));
	}

	@Override
	protected void onSubmit() {
		EntityModel entityModel = (EntityModel) getModel();
		IEntities entities = entityModel.getEntities();
		if (entities != null) {
			if (entityModel.isAdd()) {
				IEntity<?> addEntity = entityModel.getEntity();
				if (entities.add(addEntity)) {
					setResponsePage(HomePage.class);
				} else {
					addErrorsByKeys(entities);
				}
			} else {
				IEntity<?> updateEntity = entityModel.getEntity();
				IEntity<?> entity = entities.retrieveByOid(updateEntity
						.getOid());
				if (entities.update(entity, updateEntity)) {
					setResponsePage(HomePage.class);
				} else {
					addErrorsByKeys(entities);
				}
			}
		} else {
			throw new ConfigRuntimeException(
					"Entities are not used in a construction of the entity model.");
		}
	}

	protected void addCancel() {
		add(new Button("cancel") {
			@Override
			public void onSubmit() {
				onCancel();
			}
		}.setDefaultFormProcessing(false));
	}

	protected void onCancel() {
		
	}

	protected void addErrorsByKeys(IEntities<?> entities) {
		List<String> errorKeys = entities.getErrors().getKeyList();
		for (String errorKey : errorKeys) {
			String errorMsg = LocalizedText.getErrorMessage(this, errorKey);
			error(errorMsg);
		}
	}

}
