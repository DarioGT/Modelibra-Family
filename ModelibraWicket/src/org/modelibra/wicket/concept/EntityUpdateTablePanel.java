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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.container.NeighborEmptyNameLabelListView;
import org.modelibra.wicket.container.PropertyNameLabelListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity update table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityUpdateTablePanel extends DmPanel {

	private static final long serialVersionUID = 102370L;

	private static Log log = LogFactory.getLog(EntityUpdateTablePanel.class);

	/**
	 * Constructs an entity update table panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityUpdateTablePanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		final DomainApp app = (DomainApp) getApplication();
		final String modelCode = viewModel.getModel().getModelConfig()
				.getCode();

		final IEntities entities = viewModel.getEntities();
		final ConceptConfig conceptConfig = entities.getConceptConfig();

		// Table title
		String conceptsName = LocalizedText.getConceptsName(this, entities);
		add(new Label("conceptsName", conceptsName));

		// Absorbed parent essential property names
		List<String> parentEssentialPropertyNames = new ArrayList<String>();
		List<String> parentEssentialPropertyCodes = entities.getConceptConfig()
				.getParentCodeEssentialPropertyCodes();
		for (String parentPropertyKey : parentEssentialPropertyCodes) {
			String parentPropertyName = LocalizedText
					.getApplicationPropertiesText(this, parentPropertyKey);
			parentEssentialPropertyNames.add(parentPropertyName);
		}

		// Concept essential property names
		List<String> conceptEssentialPropertyNames = new ArrayList<String>();
		List<String> conceptEssentiaPropertyCodes = entities.getConceptConfig()
				.getConceptCodeEssentialPropertyCodes();
		for (String conceptPropertyKey : conceptEssentiaPropertyCodes) {
			String conceptPropertyName = LocalizedText
					.getApplicationPropertiesText(this, conceptPropertyKey);
			conceptEssentialPropertyNames.add(conceptPropertyName);
		}

		List<String> essentialPropertyNames = parentEssentialPropertyNames;
		essentialPropertyNames.addAll(conceptEssentialPropertyNames);
		add(new PropertyNameLabelListView("propertyNameLabelListView",
				essentialPropertyNames));

		// Neighbor empty labels
		List<String> childInternalNeighborNames = conceptConfig
				.getInternalChildNeighborNames();
		add(new NeighborEmptyNameLabelListView(
				"neighborEmptyNameLabelListViewHead",
				childInternalNeighborNames));

		ViewModel entityUpdateTableModel = new ViewModel();
		entityUpdateTableModel.copyPropertiesFrom(viewModel);

		View entityUpdateTableView = new View();
		entityUpdateTableView.copyPropertiesFrom(view);
		entityUpdateTableView.setWicketId("entityUpdateTableListView");

		PageableListView entityUpdateTableListView = app.getViewMeta(modelCode)
				.getPageableListView("EntityUpdateTableListView",
						entityUpdateTableModel, entityUpdateTableView);
		add(entityUpdateTableListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			entityUpdateTableListView.setVisible(false);
		}

		add(new PagingNavigator("blockNavigator", entityUpdateTableListView));

		final Integer currentBlock = new Integer(entityUpdateTableListView
				.getCurrentPage());
		Link addLink = new Link("add") {
			static final long serialVersionUID = 200761L;

			public void onClick() {
				if (app.getAccessPoint().isConceptUpdateAllowed(
						getAppSession(), conceptConfig, "add")) {
					ViewModel entityAddModel = new ViewModel();
					entityAddModel.copyPropertiesFrom(viewModel);
					entityAddModel.setEntity(null);
					View entityAddView = new View();
					entityAddView.copyPropertiesFrom(view);
					entityAddView.setPageBlock(currentBlock);
					setResponsePage(app.getViewMeta(modelCode).getPage(
							"EntityAddFormPage", entityAddModel, entityAddView));
				} else {
					addErrorByKey("entities.config.add");
				}
			}
		};
		add(addLink);

		final IEntity clipboardEntity = getAppSession().getClipboard()
				.getEntity();
		Link pasteLink = new Link("paste") {
			static final long serialVersionUID = 200762L;

			public void onClick() {
				if (clipboardEntity != null) {
					if (app.getAccessPoint().isConceptUpdateAllowed(
							getAppSession(), conceptConfig, clipboardEntity,
							"add")
							&& clipboardEntity.getConceptConfig() == entities
									.getConceptConfig()) {
						if (entities.add(clipboardEntity)) {
							View entityUpdateView = new View();
							entityUpdateView.copyPropertiesFrom(view);
							entityUpdateView.setPageBlock(currentBlock);
							setResponsePage(app.getViewMeta(modelCode).getPage(
									"EntityUpdateTablePage", viewModel,
									entityUpdateView));
						} else {
							addErrorsByKeys(entities);
						}
					} else {
						String error = LocalizedText.getText(this,
								"entities.config.add");
						error(error);
					}
				}
			}
		};
		if (clipboardEntity == null) {
			pasteLink.setVisible(false);
		}
		add(pasteLink);
	}

}
