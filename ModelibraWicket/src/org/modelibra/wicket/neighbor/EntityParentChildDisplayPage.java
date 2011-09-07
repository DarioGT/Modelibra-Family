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
package org.modelibra.wicket.neighbor;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.container.DmDisplayPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity Parent-Child display page.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
@SuppressWarnings("serial")
public class EntityParentChildDisplayPage extends DmDisplayPage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs the Entity Parent-Child display page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityParentChildDisplayPage(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		ViewModel parentChildPageModel = new ViewModel();
		parentChildPageModel.copyPropertiesFrom(viewModel);

		View parentChildPageView = new View();
		parentChildPageView.copyPropertiesFrom(view);
		parentChildPageView.setWicketId("parentChildDisplaySection");
		parentChildPageView.setContextView(view);
		parentChildPageView.setPage(this);

		add(new EntityParentChildDisplayPanel(parentChildPageModel,
				parentChildPageView));
	}

	/**
	 * Constructs a link to this page.
	 * 
	 * @param linkId
	 *            link Wicket id
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public static PageLink link(final String linkId, final ViewModel viewModel,
			final View view) {
		PageLink link = new PageLink(linkId, new IPageLink() {
			public Page getPage() {
				return new EntityParentChildDisplayPage(viewModel, view);
			}

			public Class<? extends Page> getPageIdentity() {
				return EntityParentChildDisplayPage.class;
			}
		});
		return link;
	}

}
