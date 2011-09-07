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

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.modelibra.IEntities;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Used as a panel for the root node in the AjaxEntityTreePanel component.
 * 
 * @author Vedad Kirlic
 * @version 2008-05-21
 */
public class EntitiesNameLabelAddLinkPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public EntitiesNameLabelAddLinkPanel(final ViewModel viewModel,
			final View view) {
		super(view.getWicketId());
		final DomainApp domainApp = (DomainApp) getApplication();
		final String modelCode = viewModel.getModel().getModelConfig()
				.getCode();

		// root node label
		IEntities<?> entities = viewModel.getEntities();
		String conceptsName = entities.getConceptConfig().getConceptsName();
		String localizedConceptsName = LocalizedText
				.getApplicationPropertiesText(this, conceptsName);
		add(new Label("rootName", localizedConceptsName));

		// Entity add link
		final ViewModel entityAddViewModel = new ViewModel();
		entityAddViewModel.copyPropertiesFrom(viewModel);
		entityAddViewModel.setEntities(entities);
		entityAddViewModel.setEntity(null);

		add(new Link("entityAddLink") {
			private static final long serialVersionUID = 1L;

			public void onClick() {
				setResponsePage(domainApp.getViewMeta(modelCode).getPage(
						"EntityAddFormPage", entityAddViewModel, view));
			}
		});
	}

}
