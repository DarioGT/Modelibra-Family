/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra.wicket.concept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity add form panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityAddFormPanel extends EntityUpdateFormPanel {

	private static final long serialVersionUID = 102060L;

	private static Log log = LogFactory.getLog(EntityAddFormPanel.class);

	/**
	 * Constructs an entity add form panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityAddFormPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		IEntities<?> entities = viewModel.getEntities();

		ViewModel entityAddFormModel = new ViewModel();
		entityAddFormModel.copyPropertiesFrom(viewModel);
		entityAddFormModel.setAction("add");
		IEntity<?> newEntity = viewModel.getEntity();
		if (newEntity == null) {
			DomainModel model = (DomainModel) viewModel.getModel();
			newEntity = model.getModelMeta().createEntity(entities);
			entityAddFormModel.setEntity(newEntity);
		}

		View entityAddFormView = new View();
		entityAddFormView.copyPropertiesFrom(view);
		entityAddFormView.setWicketId("entityUpdateForm");
		add(app.getViewMeta(modelCode).getForm("EntityAddForm",
				entityAddFormModel, entityAddFormView));
	}

}
