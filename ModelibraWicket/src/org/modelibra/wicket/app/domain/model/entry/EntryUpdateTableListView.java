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

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.IEntities;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Domain model entry update table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-30
 */
@SuppressWarnings("serial")
public class EntryUpdateTableListView extends DmListView {

	private static final long serialVersionUID = 105040L;

	private ViewModel viewModel;

	private View view;

	private String modelCode;

	/**
	 * Constructs an entity update table list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntryUpdateTableListView(final ViewModel viewModel, final View view) {
		super(view.getWicketId(), viewModel.getModel().getEntryList());
		this.viewModel = viewModel;
		this.view = view;
		modelCode = viewModel.getModel().getModelConfig().getCode();
	}

	/**
	 * Populates a list view line.
	 * 
	 * @param item
	 *            list item (line)
	 */
	protected void populateItem(ListItem item) {
		IEntities<?> entry = (IEntities<?>) item.getModelObject();
		ConceptConfig entryConceptConfig = entry.getConceptConfig();
		DomainApp app = (DomainApp) getApplication();

		String conceptName = LocalizedText.getConceptName(this, entry);
		Label entryConceptName = new Label("entryName", conceptName);
		item.add(entryConceptName);

		ViewModel entryModel = new ViewModel();
		entryModel.copyPropertiesFrom(viewModel);
		entryModel.setEntities(entry);
		View entryView = new View();
		entryView.copyPropertiesFrom(view);
		final WebPage entryConceptPage = app.getViewMeta(modelCode).getPage(
				"EntityUpdateTablePage", entryModel, entryView);
		Link entryConceptLink = new Link("entryUpdateTable") {
			public void onClick() {
				setResponsePage(entryConceptPage);
			}
		};
		item.add(entryConceptLink);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				entryConceptConfig)) {
			entryConceptLink.setVisible(false);
		} else if (!app.getAccessPoint().isConceptUpdateAllowed(
				getAppSession(), entryConceptConfig)) {
			entryConceptLink.setVisible(false);
		}
	}

}
