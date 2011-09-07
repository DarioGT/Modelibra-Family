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
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.NeighborNameLabelLinkListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.util.NeighborNameLabelLinkPair;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity display table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityDisplayTableListView extends
		EntityPropertiesDisplayTableListView {

	private static final long serialVersionUID = 102180L;

	private static Log log = LogFactory
			.getLog(EntityDisplayTableListView.class);

	private ViewModel viewModel;

	private View view;

	private String modelCode;

	/**
	 * Constructs an entity display table list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayTableListView(final ViewModel viewModel, final View view) {
		super(viewModel, view);
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
		super.populateItem(item);
		IEntity entity = (IEntity) item.getModelObject();

		final DomainApp app = (DomainApp) getApplication();

		IEntities entities = viewModel.getEntities();

		ConceptConfig conceptConfig = entity.getConceptConfig();

		// Neighbor display links
		List<NeighborNameLabelLinkPair> neighborNameLabelLinkPairs = new ArrayList<NeighborNameLabelLinkPair>();
		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();
		for (IEntity neighborConfigEntity : neighborsConfig) {
			NeighborConfig neighborConfig = (NeighborConfig) neighborConfigEntity;
			if (neighborConfig.getType().equals("child")) {
				String neighborConcept = neighborConfig.getDestinationConcept();
				ConceptConfig neighborConceptConfig = neighborConfig
						.getConceptConfig().getModelConfig().getConceptConfig(
								neighborConcept);

				final String neighborDisplayType = neighborConceptConfig
						.getDisplayType();
				NeighborNameLabelLinkPair neighborNameLabelLinkPair = new NeighborNameLabelLinkPair();
				String neighborName = LocalizedText.getNeighborName(this,
						entity, neighborConfig);
				Label neighborNameLabel = new Label("neighborName",
						neighborName);
				neighborNameLabelLinkPair
						.setNeighborNameLabel(neighborNameLabel);
				Link neighborLink;
				DomainModel model = (DomainModel) viewModel.getModel();
				IEntities neighborEntities = model.getModelMeta()
						.getChildNeighbor(entity, neighborConfig.getCode());

				final ViewModel neighborModel = new ViewModel();
				neighborModel.copyPropertiesFrom(viewModel);
				if (neighborModel.getLookupEntities() != null
						&& neighborModel.getLookupEntities().getConceptConfig()
								.getCode().equals(
										neighborModel.getEntities()
												.getConceptConfig().getCode())) {
					neighborModel.setLookupEntities(neighborEntities);
				} else {
					ViewModel contextModel = new ViewModel();
					contextModel.copyPropertiesFrom(viewModel);
					contextModel.setEntity(entity);
					contextModel.setEntities(entities);
					neighborModel.setContextViewModel(contextModel);
				}
				neighborModel.setEntities(neighborEntities);

				final View neighborView = new View();
				neighborView.copyPropertiesFrom(view);

				neighborLink = new PageLink("neighbor", new IPageLink() {
					static final long serialVersionUID = 200471L;

					WebPage neighborPage;

					public Page getPage() {
						if (neighborModel.getLookupEntities() != null
								&& neighborModel.getLookupEntities()
										.getConceptConfig().getCode().equals(
												neighborModel.getEntities()
														.getConceptConfig()
														.getCode())) {
							neighborPage = app.getViewMeta(modelCode)
									.getLookupPage("EntityLookupTablePage",
											neighborModel, neighborView);
						} else if (neighborDisplayType.equals("table")) {
							neighborPage = app.getViewMeta(modelCode).getPage(
									"EntityDisplayTablePage", neighborModel,
									neighborView);
						} else if (neighborDisplayType.equals("list")) {
							neighborPage = app.getViewMeta(modelCode).getPage(
									"EntityDisplayListPage", neighborModel,
									neighborView);
						} else if (neighborDisplayType.equals("slide")) {
							neighborPage = app.getViewMeta(modelCode).getPage(
									"EntityDisplaySlidePage", neighborModel,
									neighborView);
						}
						return neighborPage;
					}

					Class neighborPageClass;

					public Class getPageIdentity() {
						if (neighborDisplayType.equals("table")) {
							neighborPageClass = app.getViewMeta(modelCode)
									.getPageClass("EntityDisplayTablePage",
											neighborModel, neighborView);
						} else if (neighborDisplayType.equals("list")) {
							neighborPageClass = app.getViewMeta(modelCode)
									.getPageClass("EntityDisplayListPage",
											neighborModel, neighborView);
						} else if (neighborDisplayType.equals("slide")) {
							neighborPageClass = app.getViewMeta(modelCode)
									.getPageClass("EntityDisplaySlidePage",
											neighborModel, neighborView);
						}
						return neighborPageClass;
					}
				});
				if (!app.getAccessPoint().isNeighborDisplayAllowed(
						getAppSession(), neighborConfig)) {
					neighborLink.setVisible(false);
				}

				neighborNameLabelLinkPair.setNeighborLink(neighborLink);
				neighborNameLabelLinkPairs.add(neighborNameLabelLinkPair);
			} // end if child
		} // end for

		item.add(new NeighborNameLabelLinkListView("neighborNameLinkListView",
				neighborNameLabelLinkPairs));
	}

}
