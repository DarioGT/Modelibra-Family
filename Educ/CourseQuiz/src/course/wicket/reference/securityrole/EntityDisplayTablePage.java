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
package course.wicket.reference.securityrole;

import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.reference.securityrole.SecurityRoles;

/**
 * Entity display table page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class EntityDisplayTablePage extends
		org.modelibra.wicket.concept.EntityDisplayTablePage {

	private static final long serialVersionUID = 1176561884338L;

	/**
	 * Constructs an entity display table page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayTablePage(final ViewModel viewModel, final View view) {
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
		SecurityRoles securityRoles = (SecurityRoles) viewModel.getEntities();
		// securityRoles = securityRoles.getSecurityRolesOrderedBy????(true);
		newViewModel.setEntities(securityRoles);
		return newViewModel;
	}

}
