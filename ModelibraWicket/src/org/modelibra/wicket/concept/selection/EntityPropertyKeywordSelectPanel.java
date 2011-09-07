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

import org.apache.wicket.markup.html.form.Form;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.KeywordSelectionForm;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity property keyword select panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-19
 */
public class EntityPropertyKeywordSelectPanel extends DmPanel {

	private static final long serialVersionUID = 102310L;

	private ViewModel viewModel;

	private View view;

	private String modelCode;

	/**
	 * Constructs an entity property keyword select panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityPropertyKeywordSelectPanel(final ViewModel viewModel,
			final View view) {
		super(view.getWicketId());
		this.viewModel = viewModel;
		this.view = view;
		modelCode = viewModel.getModel().getModelConfig().getCode();
		DomainApp app = (DomainApp) getApplication();

		Form keywordSelectionForm = new Form("keywordSelectionForm");
		PropertyConfig propertyConfig = viewModel.getPropertyConfig();
		if (propertyConfig != null) {
			String propertyName = LocalizedText.getPropertyName(this, viewModel
					.getEntities(), viewModel.getPropertyConfig());
			String select = LocalizedText.getApplicationPropertiesText(this,
					"select");
			String title = propertyName + ": " + select;

			String keywords = (String) view.getUserProperties()
					.getUserProperty("keywords");
			Boolean andKeywords = (Boolean) view.getUserProperties()
					.getUserProperty("andKeywords");
			keywordSelectionForm = new KeywordSelectionPanelForm(
					"keywordSelectionForm", title, keywords, andKeywords);
			if (!app.getAccessPoint().isPropertyDisplayAllowed(getAppSession(),
					propertyConfig)) {
				keywordSelectionForm.setVisible(false);
			}
		} else {
			keywordSelectionForm.setVisible(false);
		}
		add(keywordSelectionForm);
	}

	/**
	 * Keyword selection panel form.
	 */
	private class KeywordSelectionPanelForm extends KeywordSelectionForm {

		static final long serialVersionUID = 200571L;

		/**
		 * Constructs a keyword selection panel form.
		 * 
		 * @param wicketId
		 *            Wicket id
		 * @param title
		 *            title
		 * @param keywords
		 *            keywords
		 * @param andKeywords
		 *            <code>true</code> if to AND keywords
		 */
		public KeywordSelectionPanelForm(final String wicketId,
				final String title, final String keywords,
				final Boolean andKeywords) {
			super(wicketId, title, keywords, andKeywords);
		}

		/**
		 * Submits a user action.
		 */
		protected void onSubmit() {
			String keywords = keywordsField.getModelObjectAsString();
			Boolean andKeywords = new Boolean(andKeywordsBox
					.getModelObjectAsString());

			ViewModel keywordSelectionModel = new ViewModel();
			keywordSelectionModel.copyPropertiesFrom(viewModel);

			View keywordSelectionView = new View();
			keywordSelectionView.copyPropertiesFrom(view);
			keywordSelectionView.getUserProperties().addUserProperty(
					"keywords", keywords);
			keywordSelectionView.getUserProperties().addUserProperty(
					"andKeywords", andKeywords);

			DomainApp app = (DomainApp) getApplication();
			setResponsePage(app.getViewMeta(modelCode).getPage(
					"EntityPropertyKeywordSelectPage", keywordSelectionModel,
					keywordSelectionView));
		}
	}

}
