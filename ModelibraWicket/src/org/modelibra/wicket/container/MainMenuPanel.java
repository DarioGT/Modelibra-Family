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
package org.modelibra.wicket.container;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.Entities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.concept.CountryLanguageChoicePanel;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.security.SigninPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Main menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-14
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends DmPanel {

	/**
	 * Constructs a main menu panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public MainMenuPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		final DomainApp domainApp = (DomainApp) getApplication();

		Link domainPageLink = new Link("domain") {
			public void onClick() {
				setResponsePage(domainApp.getDomainPageClass());
			}
		};
		domainPageLink.setAutoEnable(true);
		add(domainPageLink);

		// Sign in & out
		final AppSession appSession = getAppSession();

		Link signinLink = new PageLink("signin", SigninPage.class);
		add(signinLink);
		Link signoutLink = new Link("signout") {
			public void onClick() {
				appSession.invalidate();
				setResponsePage(view.getPage().getClass());
			}
		};
		add(signoutLink);
		if (!domainApp.getDomain().getDomainConfig().isSignin()) {
			signinLink.setVisible(false);
			signoutLink.setVisible(false);
		} else if (appSession.isUserSignedIn()) {
			signinLink.setVisible(false);
		} else {
			signoutLink.setVisible(false);
		}

		// I18n
		ViewModel languageViewModel = new ViewModel();
		IDomainModel reference = viewModel.getModel();
		languageViewModel.setModel(reference);
		Entities<?> languages = (Entities<?>) viewModel.getEntities();
		languageViewModel.setEntities(languages);
		String languageCode = null;
		IEntity<?> defaultLanguage = null;
		languageCode = appSession.getLocale().getLanguage();
		defaultLanguage = languages.retrieveByCode(languageCode);
		if (defaultLanguage == null) {
			defaultLanguage = languages.retrieveByCode("en");
		}
		languageViewModel.setEntity(defaultLanguage);

		View languageView = new View();
		languageView.setWicketId("languageChoiceSection");

		Panel languageChoicePanel = new CountryLanguageChoicePanel(
				languageViewModel, languageView);
		add(languageChoicePanel);
		if (!domainApp.getDomain().getDomainConfig().isI18n()) {
			languageChoicePanel.setVisible(false);
		}
	}

}
