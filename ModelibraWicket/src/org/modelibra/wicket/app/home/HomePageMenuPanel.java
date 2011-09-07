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
package org.modelibra.wicket.app.home;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IDomain;
import org.modelibra.IDomainModel;
import org.modelibra.config.DomainConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.concept.CountryLanguageChoicePanel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dm.reference.countrylanguage.CountryLanguage;
import dm.reference.countrylanguage.CountryLanguages;

/**
 * Home page main menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-14
 */
@SuppressWarnings("serial")
public class HomePageMenuPanel extends DmPanel {

	private static final long serialVersionUID = 104020L;

	private static Log log = LogFactory.getLog(HomePageMenuPanel.class);

	/**
	 * Constructs the application home page main menu panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 */
	public HomePageMenuPanel(final String wicketId) {
		super(wicketId);
		final DomainApp app = (DomainApp) getApplication();
		IDomain domain = app.getDomain();
		DomainConfig domainConfig = domain.getDomainConfig();
		String referenceModelName = domainConfig.getReferenceModel();
		IDomainModel referenceModel = domain.getModel(referenceModelName);
		if (referenceModel == null) {
			log.info(domainConfig.getCode()
					+ " domain does not have the reference model.");
		}

		Link entryLink = new Link("entry") {
			public void onClick() {
				setResponsePage(app.getDomainPageClass());
			}
		};
		add(entryLink);

		String domainName = LocalizedText.getDomainName(this);
		add(new Label("domainName", domainName));

		Link signinLink = new PageLink("signin", app.getSigninPageClass());
		add(signinLink);
		Link signoutLink = new Link("signout") {
			public void onClick() {
				getAppSession().invalidate();
				setResponsePage(app.getHomePageClass());
			}
		};
		add(signoutLink);
		if (getAppSession().isUserSignedIn()) {
			signinLink.setVisible(false);
		} else {
			signoutLink.setVisible(false);
		}
		if (!app.getDomain().getDomainConfig().isSignin()) {
			signinLink.setVisible(false);
		}

		Panel languageChoicePanel;
		if (domainConfig.isI18n()) {
			ViewModel languageViewModel = new ViewModel();
			languageViewModel.setModel(referenceModel);
			CountryLanguages countryLanguages = (CountryLanguages) referenceModel
					.getEntry("CountryLanguages");
			if (countryLanguages != null) {
				languageViewModel.setEntities(countryLanguages);
				String languageCode = null;
				languageCode = getSession().getLocale().getLanguage();
				CountryLanguage defaultLanguage = (CountryLanguage) countryLanguages
						.retrieveByCode(languageCode);
				if (defaultLanguage == null) {
					defaultLanguage = (CountryLanguage) countryLanguages
							.retrieveByCode("en");
				}
				languageViewModel.setEntity(defaultLanguage);

				View languageView = new View();
				languageView.setWicketId("languageChoicePanel");

				languageChoicePanel = new CountryLanguageChoicePanel(
						languageViewModel, languageView);
			} else {
				languageChoicePanel = new Panel("languageChoicePanel");
				languageChoicePanel.setVisible(false);
				log
						.info("The application is i18n, but there are no country languages.");
			}
		} else {
			languageChoicePanel = new Panel("languageChoicePanel");
			languageChoicePanel.setVisible(false);
		}
		add(languageChoicePanel);
	}

}
