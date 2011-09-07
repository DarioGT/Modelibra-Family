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
package org.modelibra.wicket.concept.selection;

import org.modelibra.IEntities;
import org.modelibra.PropertySelector;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmDisplayPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity keyword select page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-19
 */
public final class EntityPropertyKeywordSelectPage extends DmDisplayPage {

	private static final long serialVersionUID = 102300L;

	/**
	 * Constructs an entity property keyword select page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityPropertyKeywordSelectPage(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		ViewModel selectPageViewModel = new ViewModel();
		selectPageViewModel.copyPropertiesFrom(viewModel);
		View selectPageView = new View();
		selectPageView.copyPropertiesFrom(view);
		selectPageView.setPage(this);
		selectPageView.setContextView(view);

		IEntities<?> entities = selectPageViewModel.getEntities();
		PropertyConfig propertyConfig = selectPageViewModel.getPropertyConfig();

		String keywords = (String) selectPageView.getUserProperties()
				.getUserProperty("keywords");
		if (keywords == null) {
			keywords = "";
			selectPageView.getUserProperties().addUserProperty("keywords",
					keywords);
		}
		Boolean andKeywords = (Boolean) selectPageView.getUserProperties()
				.getUserProperty("andKeywords");
		if (andKeywords == null) {
			andKeywords = Boolean.TRUE;
			selectPageView.getUserProperties().addUserProperty("andKeywords",
					andKeywords);
		}

		IEntities<?> entitiesThatContainKeywords = null;
		keywords.trim();
		if (keywords.equals("")) {
			entitiesThatContainKeywords = entities;
		} else {
			String[] keywordsArray = keywords.split(",");
			if (andKeywords.booleanValue()) {
				PropertySelector propertySelector = new PropertySelector(
						propertyConfig.getCode());
				propertySelector.defineContainAll(keywordsArray);
				entitiesThatContainKeywords = entities
						.selectBySelector(propertySelector);
			} else {
				PropertySelector propertySelector = new PropertySelector(
						propertyConfig.getCode());
				propertySelector.defineContainSome(keywordsArray);
				entitiesThatContainKeywords = entities
						.selectBySelector(propertySelector);
			}
		}

		ViewModel keywordEntitiesModel = new ViewModel();
		keywordEntitiesModel.copyPropertiesFrom(selectPageViewModel);
		keywordEntitiesModel.setEntities(entitiesThatContainKeywords);

		View keywordEntitiesView = new View();
		keywordEntitiesView.copyPropertiesFrom(selectPageView);
		keywordEntitiesView.setContextView(selectPageView);
		keywordEntitiesView.setWicketId("entityPropertyKeywordSelectPanel");

		add(app.getViewMeta(modelCode).getPanel(
				"EntityPropertyKeywordSelectPanel", keywordEntitiesModel,
				keywordEntitiesView));

		ViewModel entityDisplayTablePanelModel = new ViewModel();
		entityDisplayTablePanelModel.copyPropertiesFrom(selectPageViewModel);
		entityDisplayTablePanelModel.setEntities(entitiesThatContainKeywords);

		View entityDisplayTablePanelView = new View();
		entityDisplayTablePanelView.copyPropertiesFrom(selectPageView);
		entityDisplayTablePanelView.setContextView(selectPageView);
		entityDisplayTablePanelView.setWicketId("entityDisplayTablePanel");

		add(app.getViewMeta(modelCode).getPanel("EntityDisplayTablePanel",
				entityDisplayTablePanelModel, entityDisplayTablePanelView));
	}
}
