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
import org.apache.wicket.Page;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity edit form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityEditForm extends EntityUpdateForm {

	private static final long serialVersionUID = 102210L;

	private static Log log = LogFactory.getLog(EntityEditForm.class);

	private ViewModel viewModel;

	private View view;

	/**
	 * Constructs an entity edit form.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityEditForm(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
	}

	/**
	 * Submits a user action.
	 */
	@Override
	protected void onSubmit() {
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		IEntities entities = viewModel.getEntities();
		IEntity entity = viewModel.getEntity();
		IEntity updateEntity = viewModel.getUpdateEntity();
		ConceptConfig conceptConfig = entities.getConceptConfig();

		if (app.getAccessPoint().isConceptUpdateAllowed(getAppSession(),
				conceptConfig, entity, "update")) {
			entities.getErrors().empty();
			if (entities.update(entity, updateEntity)) {
				if (!view.isRecreateContext()) {
					ViewModel entityUpdateTablePageModel = new ViewModel();
					entityUpdateTablePageModel.copyPropertiesFrom(viewModel);
					entityUpdateTablePageModel.setUpdateEntity(null);
					View entityUpdateTablePageView = new View();
					entityUpdateTablePageView.copyPropertiesFrom(view);
					setResponsePage(view.getContextView().getPage());
				} else {
					Page contextPage = null;
					ViewModel contextModel = viewModel.getContextViewModel();
					View contextView = view.getContextView();
					if (contextView != null) {
						contextPage = app.getViewMeta(modelCode).getPage(
								contextModel, contextView);
						if (contextPage == null) {
							contextPage = app.getViewMeta(modelCode).getPage(
									contextView.getPage());
						}
					}
					setResponsePage(contextPage);
				}
			} else {
				addErrorsByKeys(entities);
			}
		} else {
			addErrorByKey("entities.config.update");
		}
	}

}
