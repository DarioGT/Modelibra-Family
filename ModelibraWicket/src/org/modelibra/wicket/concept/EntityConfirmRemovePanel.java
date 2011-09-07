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
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity confirm remove panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityConfirmRemovePanel extends DmPanel {

	private static final long serialVersionUID = 102080L;

	static Log log = LogFactory.getLog(EntityConfirmRemovePanel.class);

	private String modelCode;

	/**
	 * Constructs an entity confirm remove panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityConfirmRemovePanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		modelCode = viewModel.getModel().getModelConfig().getCode();
		ViewModel confirmRemoveFormModel = new ViewModel();
		confirmRemoveFormModel.copyPropertiesFrom(viewModel);
		confirmRemoveFormModel.setAction("remove");

		View confirmRemoveFormView = new View();
		confirmRemoveFormView.copyPropertiesFrom(view);
		confirmRemoveFormView.setWicketId("confirmRemoveForm");

		add(new ConfirmRemoveForm(confirmRemoveFormModel, confirmRemoveFormView));
	}

	/**
	 * Confirm remove form.
	 */
	private class ConfirmRemoveForm extends Form {

		static final long serialVersionUID = 200681L;

		private ViewModel viewModel;

		private View view;

		/**
		 * Constructs a confirm remove form.
		 * 
		 * @param viewModel
		 *            view model
		 * @param view
		 * 
		 */
		public ConfirmRemoveForm(final ViewModel viewModel, final View view) {
			super(view.getWicketId());
			this.viewModel = viewModel;
			this.view = view;
			add(new Button("no") {
				static final long serialVersionUID = 200682L;

				public void onSubmit() {
					DomainApp app = (DomainApp) getApplication();
					if (!view.isRecreateContext()) {
						View entityUpdateTablePageView = new View();
						entityUpdateTablePageView.copyPropertiesFrom(view);
						setResponsePage(view.getContextView().getPage());
					} else {
						Page contextPage = null;
						ViewModel contextModel = viewModel
								.getContextViewModel();
						View contextView = view.getContextView();
						if (contextView != null) {
							contextPage = app.getViewMeta(modelCode).getPage(
									contextModel, contextView);
							if (contextPage == null) {
								contextPage = app.getViewMeta(modelCode)
										.getPage(contextView.getPage());
							}
						}
						setResponsePage(contextPage);
					}
				}
			}.setDefaultFormProcessing(false));
		}

		/**
		 * Submits a user action.
		 */
		protected void onSubmit() {
			IEntities entities = viewModel.getEntities();
			IEntity entity = viewModel.getEntity();
			ConceptConfig conceptConfig = entities.getConceptConfig();
			DomainApp app = (DomainApp) getApplication();

			if (app.getAccessPoint().isConceptUpdateAllowed(getAppSession(),
					conceptConfig, entity, "remove")) {
				if (entities.remove(entity)) {
					if (!view.isRecreateContext()) {
						View entityUpdateTablePageView = new View();
						entityUpdateTablePageView.copyPropertiesFrom(view);
						setResponsePage(view.getContextView().getPage());
					} else {
						Page contextPage = null;
						ViewModel contextViewModel = viewModel
								.getContextViewModel();
						View contextView = view.getContextView();
						if (contextView != null) {
							contextPage = app.getViewMeta(modelCode).getPage(
									contextView.getPage(), contextViewModel,
									contextView);
							if (contextPage == null) {
								contextPage = app.getViewMeta(modelCode)
										.getPage(contextView.getPage());
							}
						}
						setResponsePage(contextPage);
					}
				} else {
					addErrorsByKeys(entities);
				}
			} else {
				String error = LocalizedText.getText(this,
						"entities.config.remove");
				error(error);
			}
		}

	}

}
