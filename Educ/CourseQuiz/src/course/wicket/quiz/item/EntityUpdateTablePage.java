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
package course.wicket.quiz.item;

import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.quiz.item.Items;

/**
 * Entity update table page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class EntityUpdateTablePage extends
		org.modelibra.wicket.concept.EntityUpdateTablePage {

	private static final long serialVersionUID = 1176743181623L;

	/**
	 * Constructs an entity update table page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityUpdateTablePage(final ViewModel viewModel, final View view) {
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
		Items items = (Items) viewModel.getEntities();
		// items = items.getItemsOrderedBy????(true);
		newViewModel.setEntities(items);
		return newViewModel;
	}

}
