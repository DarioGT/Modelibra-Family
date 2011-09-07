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
package dmeduc.wicket.weblink.url;

import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.url.Urls;

/**
 * Entity display list page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-10
 */
public class EntityDisplayListPage extends
		org.modelibra.wicket.concept.EntityDisplayListPage {

	private static final long serialVersionUID = 1171894920495L;

	/**
	 * Constructs an entity display list page.
	 * 
	 * @param viewModel
	 *            model context
	 * @param view
	 *            view context
	 */
	public EntityDisplayListPage(final ViewModel viewModel, final View view) {
		super(getNewViewModel(viewModel), view);
	}

	/**
	 * Gets a new view model.
	 * 
	 * @param viewModel
	 *            view model
	 * @return new view model
	 */
	private static ViewModel getNewViewModel(final ViewModel viewModel) {
		ViewModel newViewModel = new ViewModel();
		newViewModel.copyPropertiesFrom(viewModel);
		Urls urls = (Urls) viewModel.getEntities();
		urls = urls.getApprovedUrls().getUrlsOrderedByName();
		newViewModel.setEntities(urls);
		return newViewModel;
	}

}
