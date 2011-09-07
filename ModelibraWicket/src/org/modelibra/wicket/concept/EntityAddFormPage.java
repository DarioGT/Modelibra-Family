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
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmUpdatePage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity add form page.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-11-28
 */
public class EntityAddFormPage extends DmUpdatePage {

	private static final long serialVersionUID = 102050L;

	private static Log log = LogFactory.getLog(EntityAddFormPage.class);

	/**
	 * Constructs an entity add form page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityAddFormPage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		add(new FeedbackPanel("feedbackPanel"));

		ViewModel entityAddFormPanelModel = new ViewModel();
		entityAddFormPanelModel.copyPropertiesFrom(viewModel);

		View entityAddFormPanelView = new View();
		entityAddFormPanelView.copyPropertiesFrom(view);
		entityAddFormPanelView.setPage(this);
		entityAddFormPanelView.setWicketId("entityAddFormPanel");

		add(app.getViewMeta(modelCode).getPanel("EntityAddFormPanel",
				entityAddFormPanelModel, entityAddFormPanelView));
	}

	/**
	 * Constructs a page of this class at the time of the link click.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public static PageLink link(final ViewModel viewModel, final View view) {
		PageLink link = new PageLink(view.getWicketId(), new IPageLink() {
			static final long serialVersionUID = 200651L;

			public Page getPage() {
				return new EntityAddFormPage(viewModel, view);
			}

			public Class getPageIdentity() {
				return EntityAddFormPage.class;
			}
		});
		return link;
	}

}
