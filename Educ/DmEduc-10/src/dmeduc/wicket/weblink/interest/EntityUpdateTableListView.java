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
package dmeduc.wicket.weblink.interest;

import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.interest.Interest;
import dmeduc.wicket.weblink.category.CategoryUrlsPage;

/**
 * Entity update table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-27
 */
@SuppressWarnings("serial")
public class EntityUpdateTableListView extends
		org.modelibra.wicket.concept.EntityUpdateTableListView {

	private ViewModel viewModel;

	private View view;

	/**
	 * Constructs an entity update table list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityUpdateTableListView(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
	}

	/**
	 * Populates a list view line.
	 * 
	 * @param item
	 *            list item (line)
	 */
	protected void populateItem(final ListItem item) {
		super.populateItem(item);
		Interest memberInterest = (Interest) item.getModelObject();
		Category memberInterestCategory = memberInterest.getCategory();
		ViewModel categoryPageModel = new ViewModel();
		WebLink webLink = (WebLink) viewModel.getModel();
		Categories categories = webLink.getCategories();
		categoryPageModel.copyPropertiesFrom(viewModel);
		categoryPageModel.setEntities(categories);
		categoryPageModel.setEntity(memberInterestCategory);
		item.add(CategoryUrlsPage.link("categoryUrlsLink", categoryPageModel,
				view));
	}

}
