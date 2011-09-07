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
package dmeduc.wicket.weblink.comment;

import org.modelibra.wicket.security.AccessPoint;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.member.Member;
import dmeduc.wicket.app.home.HomePage;

/**
 * Entity add form for Contact Us.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-23
 */
public class EntityAddForm extends org.modelibra.wicket.concept.EntityAddForm {

	private static final long serialVersionUID = 1171894920506L;

	/**
	 * Constructs an entity add form.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityAddForm(final ViewModel viewModel, final View view) {
		super(viewModel, view);
	}

	/**
	 * Submits a user action.
	 */
	protected void onSubmit() {
		super.onSubmit();
		if (!hasError()) {
			signOutGest();
			setResponsePage(HomePage.class);			
		}
	}

	/**
	 * Cancels a user action.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	protected void onCancel(final ViewModel viewModel, final View view) {
		super.onCancel(viewModel, view);
		signOutGest();
		setResponsePage(HomePage.class);
	}

	/**
	 * Signs out a guest user.
	 */
	private void signOutGest() {
		if (getAppSession().isUserSignedIn()) {
			Member user = (Member) getAppSession().getSignedInUser();
			if (user.getRole().equals(AccessPoint.CASUAL)) {
				if (user.getCode().equals(AccessPoint.GUEST)
						&& user.getPassword().equals(AccessPoint.GUEST)) {
					getAppSession().signOutUser();
				}
			}
		}
	}

}
