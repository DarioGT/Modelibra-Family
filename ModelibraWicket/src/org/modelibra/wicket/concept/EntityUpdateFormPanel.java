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

import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity update form panel. Used only for markup inheritance.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-05-11
 */
public abstract class EntityUpdateFormPanel extends DmPanel {

	private static final long serialVersionUID = 102340L;

	/**
	 * Constructs an entity update form.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityUpdateFormPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
	}

}
