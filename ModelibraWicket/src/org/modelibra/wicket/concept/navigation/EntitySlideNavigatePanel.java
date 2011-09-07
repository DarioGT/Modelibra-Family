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
package org.modelibra.wicket.concept.navigation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity slide navigate panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-19
 */
public class EntitySlideNavigatePanel extends DmPanel {

	private static final long serialVersionUID = 102320L;

	private static Log log = LogFactory.getLog(EntitySlideNavigatePanel.class);

	public EntitySlideNavigatePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		final DomainApp app = (DomainApp) getApplication();
		final String modelCode = viewModel.getModel().getModelConfig()
				.getCode();

		final IEntities<?> entities = viewModel.getEntities();
		final IEntity<?> entity = viewModel.getEntity();
		ConceptConfig conceptConfig = entity.getConceptConfig();

		Link firstLink = new Link("first") {
			static final long serialVersionUID = 200581L;

			public void onClick() {
				IEntity<?> firstEntity = (IEntity<?>) entities.first();
				ViewModel firstEntityModel = new ViewModel();
				firstEntityModel.copyPropertiesFrom(viewModel);
				firstEntityModel.setEntity(firstEntity);

				View firstEntityView = new View();
				firstEntityView.copyPropertiesFrom(view);

				WebPage firstWebPage = app.getViewMeta(modelCode).getPage(
						"EntityDisplaySlidePage", firstEntityModel,
						firstEntityView);
				setResponsePage(firstWebPage);
			}
		};
		add(firstLink);

		Link nextLink = new Link("next") {
			static final long serialVersionUID = 200582L;

			public void onClick() {
				IEntity<?> nextEntity = findNextEntity(entities, entity);

				ViewModel nextEntityModel = new ViewModel();
				nextEntityModel.copyPropertiesFrom(viewModel);
				nextEntityModel.setEntity(nextEntity);

				View nextEntityView = new View();
				nextEntityView.copyPropertiesFrom(view);

				WebPage nextWebPage = app.getViewMeta(modelCode).getPage(
						"EntityDisplaySlidePage", nextEntityModel,
						nextEntityView);
				setResponsePage(nextWebPage);
			}
		};
		add(nextLink);

		Link priorLink = new Link("prior") {
			static final long serialVersionUID = 200583L;

			public void onClick() {
				IEntity<?> priorEntity = findPriorEntity(entities, entity);

				ViewModel priorEntityModel = new ViewModel();
				priorEntityModel.copyPropertiesFrom(viewModel);
				priorEntityModel.setEntity(priorEntity);

				View priorEntityView = new View();
				priorEntityView.copyPropertiesFrom(view);

				WebPage priorWebPage = app.getViewMeta(modelCode).getPage(
						"EntityDisplaySlidePage", priorEntityModel,
						priorEntityView);
				setResponsePage(priorWebPage);
			}
		};
		add(priorLink);

		Link lastLink = new Link("last") {
			static final long serialVersionUID = 200584L;

			public void onClick() {
				IEntity<?> lastEntity = (IEntity<?>) entities.last();

				ViewModel lastEntityModel = new ViewModel();
				lastEntityModel.copyPropertiesFrom(viewModel);
				lastEntityModel.setEntity(lastEntity);

				View lastEntityView = new View();
				lastEntityView.copyPropertiesFrom(view);

				WebPage lastWebPage = app.getViewMeta(modelCode).getPage(
						"EntityDisplaySlidePage", lastEntityModel,
						lastEntityView);
				setResponsePage(lastWebPage);
			}
		};
		add(lastLink);

		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			firstLink.setVisible(false);
			nextLink.setVisible(false);
			priorLink.setVisible(false);
			lastLink.setVisible(false);
		}
	}

	/**
	 * Finds a next entity.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 */
	private IEntity<?> findNextEntity(final IEntities entities,
			final IEntity<?> entity) {
		IEntity<?> nextEntity = null;
		nextEntity = (IEntity<?>) entities.next(entity);
		if (nextEntity == null) {
			nextEntity = (IEntity<?>) entities.last();
		}
		return nextEntity;
	}

	/**
	 * Finds a prior entity.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 */
	private IEntity<?> findPriorEntity(final IEntities entities,
			final IEntity<?> entity) {
		IEntity<?> priorEntity = null;
		priorEntity = (IEntity<?>) entities.prior(entity);
		if (priorEntity == null) {
			priorEntity = (IEntity<?>) entities.first();
		}
		return priorEntity;
	}

}
