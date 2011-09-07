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
import org.modelibra.IDomainModel;
import org.modelibra.wicket.container.DmDisplayPage;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Domain model entry display table page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-28
 */
public class EntryDisplayTablePage extends DmDisplayPage {

	private static final long serialVersionUID = 105020L;

	/**
	 * Constructs an entry display table page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntryDisplayTablePage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		IDomainModel domainModel = viewModel.getModel();

		String modelDescription = LocalizedText.getModelDescription(this,
				domainModel);
		add(new Label("modelDescription", modelDescription));

		ViewModel entryDisplayTablePanelModel = new ViewModel();
		entryDisplayTablePanelModel.copyPropertiesFrom(viewModel);

		View entryDisplayTablePanelView = new View();
		entryDisplayTablePanelView.copyPropertiesFrom(view);
		entryDisplayTablePanelView.setContextView(view);
		entryDisplayTablePanelView.setPage(this);
		entryDisplayTablePanelView.setWicketId("entryDisplayTablePanel");

		add(new EntryDisplayTablePanel(entryDisplayTablePanelModel,
				entryDisplayTablePanelView));
	}

}
