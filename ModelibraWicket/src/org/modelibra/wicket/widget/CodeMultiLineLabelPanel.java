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
package org.modelibra.wicket.widget;

import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Code multi line label panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-16
 */
public class CodeMultiLineLabelPanel extends MultiLineLabelPanel {

	private static final long serialVersionUID = 101872L;

	/**
	 * Constructs a multi line label panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public CodeMultiLineLabelPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		setEscapeModelStrings(false);
	}

}
