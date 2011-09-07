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

import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.util.Reflector;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Country language choice panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class CountryLanguageChoicePanel extends DmPanel {

	private static final long serialVersionUID = 102030L;

	private static final String GET_LANGUAGE = "getLanguage";

	private static final String GET_LANGUAGE_LIST = "getLanguageList";

	private static final String GET_LANGUAGE_CODE = "getLanguageCode";

	private static Log log = LogFactory
			.getLog(CountryLanguageChoicePanel.class);

	private ViewModel viewModel;

	private View view;

	/**
	 * Constructs a country language choice panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public CountryLanguageChoicePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		this.viewModel = viewModel;
		this.view = view;
		DomainApp app = (DomainApp) getApplication();

		IEntity countryLanguage = viewModel.getEntity();
		IEntities countryLanguages = viewModel.getEntities();
		ConceptConfig countryLanguageConceptConfig = countryLanguages
				.getConceptConfig();
		String getLanguageMethod = (String) viewModel.getUserProperties()
				.getUserProperty("getLanguageMethod");
		if (getLanguageMethod == null) {
			getLanguageMethod = GET_LANGUAGE;
		}
		String currentLanguage = (String) Reflector.executeMethod(
				countryLanguage, getLanguageMethod);
		if (currentLanguage == null) {
			log.error("Error in CountryLanguageChoicePanel: "
					+ "There is no get language method for the entity");
		}
		String getLanguageListMethod = (String) viewModel.getUserProperties()
				.getUserProperty("getLanguageListMethod");
		if (getLanguageListMethod == null) {
			getLanguageListMethod = GET_LANGUAGE_LIST;
		}
		List languageList = (List) Reflector.executeMethod(countryLanguages,
				getLanguageListMethod);
		if (languageList == null) {
			log.error("Error in CountryLanguageChoicePanel: "
					+ "There is no get language list method for the entities");
		}

		IModel choiceModel = new Model(currentLanguage);
		DropDownChoice countryLanguageChoice = new CountryLanguageChoice(
				"countryLanguageChoice", choiceModel, languageList);
		add(countryLanguageChoice);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				countryLanguageConceptConfig)) {
			countryLanguageChoice.setVisible(false);
		}
	}

	private final class CountryLanguageChoice extends DropDownChoice {

		private static final long serialVersionUID = 200371L;

		/**
		 * Constructs a country language choice.
		 * 
		 * @param wicketId
		 *            Wicket id
		 * @param choiceModel
		 *            choice model
		 * @param languageList
		 *            language list
		 */
		public CountryLanguageChoice(final String wicketId,
				final IModel choiceModel, List languageList) {
			super(wicketId, choiceModel, languageList);
		}

		/**
		 * To force a server makes a roundtrip on each selection change.
		 * 
		 * @return <code>true</code> if a server makes a roundtrip on each
		 *         selection change
		 */
		protected boolean wantOnSelectionChangedNotifications() {
			return true;
		}

		/**
		 * To change locale.
		 * 
		 * @param newSelection
		 *            new language selection
		 */
		public void onSelectionChanged(Object newSelection) {
			String selectedLanguage = (String) newSelection;
			changeLocale(selectedLanguage);
			DomainApp app = (DomainApp) getApplication();
			setResponsePage(app.getHomePage());
		}

		/**
		 * To change locale.
		 * 
		 * @param currentLanguage
		 *            current language
		 */
		private void changeLocale(String currentLanguage) {
			IEntities<?> countryLanguages = viewModel.getEntities();
			String getLanguageCodeMethod = (String) viewModel
					.getUserProperties().getUserProperty(
							"getLanguageCodeMethod");
			if (getLanguageCodeMethod == null) {
				getLanguageCodeMethod = GET_LANGUAGE_CODE;
			}
			String currentLanguageCode = (String) Reflector.executeMethod(
					countryLanguages, getLanguageCodeMethod, currentLanguage);
			if (currentLanguageCode == null) {
				currentLanguageCode = "en";
			}
			getAppSession().setLocale(new Locale(currentLanguageCode));
		}
	}

}
