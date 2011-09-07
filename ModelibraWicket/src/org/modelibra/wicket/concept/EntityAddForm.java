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
 * Entity add form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityAddForm extends EntityUpdateForm {

	private static final long serialVersionUID = 102040L;

	private static Log log = LogFactory.getLog(EntityAddForm.class);

	private ViewModel viewModel;

	private View view;

	/**
	 * Constructs an entity add form.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityAddForm(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
	}

	/**
	 * Submits a user action.
	 */
	protected void onSubmit() {
		onSubmit(viewModel, view);
	}

	/**
	 * Submits a user action.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	protected void onSubmit(final ViewModel viewModel, final View view) {
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		IEntities entities = viewModel.getEntities();
		IEntity entity = viewModel.getEntity();
		ConceptConfig conceptConfig = entities.getConceptConfig();

		if (app.getAccessPoint().isConceptUpdateAllowed(getAppSession(),
				conceptConfig, entity, "add")) {
			entities.getErrors().empty();
			if (entities.add(entity)) {
				if (!view.isRecreateContext()) {
					ViewModel entityUpdateTablePageModel = new ViewModel();
					entityUpdateTablePageModel.copyPropertiesFrom(viewModel);
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
			addErrorByKey("entities.config.add");
		}
	}

}
