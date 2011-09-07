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
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntity;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * EntityDisplayAddLinksPanel
 * 
 * Contains links to EntityDisplayPage and EntityAddFormPage
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 * 
 */
@SuppressWarnings("serial")
public class EntityDisplayAddLinksPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public EntityDisplayAddLinksPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		final DomainApp domainApp = (DomainApp) getApplication();
		final String modelCode = viewModel.getModel().getModelConfig()
				.getCode();

		IEntity<?> entity = (IEntity<?>) viewModel.getEntity();

		// EntityDisplayPage link
		Link entityDisplayPageLink = new Link("entityDisplayPageLink") {
			@Override
			public void onClick() {
				setResponsePage(domainApp.getViewMeta(modelCode).getPage(
						"EntityDisplayPage", viewModel, view));
			}
		};
		entityDisplayPageLink.add(new Label("entityString", new PropertyModel(
				entity, "toString()")));
		add(entityDisplayPageLink);

		// EntityAddFormPage link
		String code = entity.getConceptConfig().getEntitiesCodeInLowerLetters();
		final ViewModel categoriesAddViewModel = new ViewModel();
		categoriesAddViewModel.copyPropertiesFrom(viewModel);
		categoriesAddViewModel.setEntities(entity.getChildNeighbor(code));
		categoriesAddViewModel.setEntity(null);

		add(new Link("entityAddLink") {
			public void onClick() {
				setResponsePage(domainApp.getViewMeta(modelCode).getPage(
						"EntityAddFormPage", viewModel, view));
			}
		});
	}

}
