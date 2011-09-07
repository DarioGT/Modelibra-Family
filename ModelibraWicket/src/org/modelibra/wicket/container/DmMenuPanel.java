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

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.IEntity;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Dm menu panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-14
 */
@SuppressWarnings("serial")
public class DmMenuPanel extends DmPanel {

	private static final long serialVersionUID = 101010L;

	/**
	 * Constructs a dm menu panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public DmMenuPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		final DomainApp app = (DomainApp) getApplication();

		Link homePageLink = new PageLink("home", app.getHomePageClass());
		homePageLink.setAutoEnable(true);
		add(homePageLink);

		Link domainPageLink = new Link("domain") {
			public void onClick() {
				setResponsePage(app.getDomainPageClass());
			}
		};
		domainPageLink.setAutoEnable(true);
		add(domainPageLink);
		AppSession appSession = getAppSession();
		if (appSession.isUserSignedIn()) {
			IEntity<?> user = appSession.getSignedInUser();
			if (user != null) {
				String role = (String) user.getProperty("role");
				if (role != null) {
					if (!role.equals("admin") && !role.equals("manager")) {
						domainPageLink.setVisible(false);
					}
				}
			}
		} else {
			domainPageLink.setVisible(false);
		}

		Link contextLink = new PageLink("context", app.getHomePageClass());
		View contextView = view.getContextView();
		if (contextView != null) {
			if (!contextView.isRecreateContext()) {
				final Page contextViewPage = contextView.getPage();
				if (contextViewPage != null) {
					contextLink = new Link("context") {
						public void onClick() {
							setResponsePage(contextViewPage);
						}
					};
				}
			} else {
				Page contextPage = null;
				ViewModel contextModel = viewModel.getContextViewModel();
				if (contextModel != null) {
					String modelCode = viewModel.getModel().getModelConfig()
							.getCode();
					contextPage = app.getViewMeta(modelCode).getPage(
							contextModel, contextView);
					if (contextPage != null) {
						final Page recreatedContextPage = contextPage;
						contextLink = new Link("context") {
							public void onClick() {
								setResponsePage(recreatedContextPage);
							}
						};
					} else {
						Page contextViewPage = contextView.getPage();
						if (contextViewPage != null) {
							contextPage = app.getViewMeta(modelCode).getPage(
									contextViewPage);
							if (contextPage != null) {
								final Page recreatedContextPage = contextPage;
								contextLink = new Link("context") {
									public void onClick() {
										setResponsePage(recreatedContextPage);
									}
								};
							}
						}
					} // if (contextPage != null) {
				} // if (contextModel != null) {
			} // if (!contextView.isRecreateContext()) {
		} // if (contextView != null) {

		contextLink.setAutoEnable(true);
		add(contextLink);

		String modelName = LocalizedText.getModelName(this, viewModel
				.getModel());
		add(new Label("modelName", modelName));
	}

}
