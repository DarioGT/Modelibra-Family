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
package org.modelibra.wicket.app.domain.model;

import java.util.Locale;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.IDomainModel;
import org.modelibra.IDomainModels;
import org.modelibra.wicket.app.domain.model.entry.EntryDisplayTablePage;
import org.modelibra.wicket.app.domain.model.entry.EntryUpdateTablePage;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Domain model table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-30
 */
@SuppressWarnings("serial")
public class DomainModelTableListView extends DmListView {

	private static final long serialVersionUID = 103030L;

	private Page domainPage;

	/**
	 * Constructs a domain model table list view.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param domainPage
	 *            domain page
	 * @param domainModels
	 *            list of domain models
	 */
	public DomainModelTableListView(final String wicketId,
			final Page domainPage, final IDomainModels domainModels) {
		super(wicketId, domainModels.getList());
		this.domainPage = domainPage;

	}

	/**
	 * Populates a list view line.
	 * 
	 * @param item
	 *            list item (line)
	 */
	protected void populateItem(ListItem item) {
		IDomainModel model = (IDomainModel) item.getModelObject();
		String modelName = LocalizedText.getModelName(this, model);
		item.add(new Label("modelName", modelName));

		ViewModel modelEntryDisplayPageModel = new ViewModel();
		modelEntryDisplayPageModel.setModel(model);
		modelEntryDisplayPageModel
				.setContextViewModel(modelEntryDisplayPageModel);

		Locale sessionLocale = getSession().getLocale();
		View modelEntryDisplayPageView = new View();
		modelEntryDisplayPageView.setLocale(sessionLocale);
		modelEntryDisplayPageView.setPage(domainPage);
		modelEntryDisplayPageView.setContextView(modelEntryDisplayPageView);

		final EntryDisplayTablePage entryDisplayTablePage = new EntryDisplayTablePage(
				modelEntryDisplayPageModel, modelEntryDisplayPageView);
		item.add(new Link("displayEntries") {
			public void onClick() {
				setResponsePage(entryDisplayTablePage);
			}
		});

		ViewModel modelEntryUpdatePageModel = new ViewModel();
		modelEntryUpdatePageModel
				.copyPropertiesFrom(modelEntryDisplayPageModel);

		View modelEntryUpdatePageView = new View();
		modelEntryUpdatePageView.copyPropertiesFrom(modelEntryDisplayPageView);
		modelEntryUpdatePageView.setUpdate(true);

		final EntryUpdateTablePage entryUpdateTablePage = new EntryUpdateTablePage(
				modelEntryUpdatePageModel, modelEntryUpdatePageView);
		item.add(new Link("updateEntries") {
			public void onClick() {
				setResponsePage(entryUpdateTablePage);
			}
		});
	}

}
