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
package org.modelibra.wicket.app.domain.model.entry;

import org.apache.wicket.markup.html.basic.Label;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Domain model entry table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-28
 */
public class EntryDisplayTablePanel extends DmPanel {

	private static final long serialVersionUID = 105030L;

	/**
	 * Constructs an entry display table panel.
	 * 
	 * @param viewModel
	 *            viewModel
	 * @param view
	 *            view
	 */
	public EntryDisplayTablePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		String modelName = LocalizedText.getModelName(this, viewModel
				.getModel());
		add(new Label("modelName", modelName));

		ViewModel entryDisplayTableListViewModel = new ViewModel();
		entryDisplayTableListViewModel.copyPropertiesFrom(viewModel);

		View entryDisplayTableListView = new View();
		entryDisplayTableListView.copyPropertiesFrom(view);
		entryDisplayTableListView.setWicketId("entryDisplayTableListView");

		add(new EntryDisplayTableListView(entryDisplayTableListViewModel,
				entryDisplayTableListView));
	}

}
