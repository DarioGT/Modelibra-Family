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

import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity update table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-27
 */
@SuppressWarnings("serial")
public class EntityUpdateTablePanel extends
		org.modelibra.wicket.concept.EntityUpdateTablePanel {

	/**
	 * Constructs an entity update table list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityUpdateTablePanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
	}

}
