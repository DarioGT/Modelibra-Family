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
 * Entity update table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityUpdateTableListView extends
		EntityPropertiesDisplayTableListView {

	private static final long serialVersionUID = 102350L;

	static Log log = LogFactory.getLog(EntityUpdateTableListView.class);

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
	public EntityUpdateTableListView(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
		modelCode = viewModel.getModel().getModelConfig().getCode();
		setCurrentPage(view.getPageBlock().intValue());
	}

	/**
	 * Populates a list view line.
	 * 
	 * @param item
	 *            list item (line)
	 */
	protected void populateItem(final ListItem item) {
		super.populateItem(item);
		final IEntity entity = (IEntity) item.getModelObject();

		final DomainApp app = (DomainApp) getApplication();

		final IEntities entities = viewModel.getEntities();
		final ConceptConfig conceptConfig = entity.getConceptConfig();

		final Integer currentPageBlock = new Integer(getCurrentPage());

		final ViewModel entityModel = new ViewModel();
		entityModel.copyPropertiesFrom(viewModel);
		entityModel.setEntity(entity);
		Link copyLink = new Link("copy") {
			static final long serialVersionUID = 200741L;

			public void onClick() {
				getAppSession().getClipboard().setEntity(entity);
				View entityCopyPageView = new View();
				entityCopyPageView.copyPropertiesFrom(view);
				entityCopyPageView.setPageBlock(currentPageBlock);
				setResponsePage(app.getViewMeta(modelCode).getPage(
						"EntityUpdateTablePage", entityModel,
						entityCopyPageView));
			}
		};
		item.add(copyLink);
		if (!conceptConfig.isReflexive()) {
			copyLink.setVisible(false);
		}

		final View entityUpdateView = new View();
		entityUpdateView.copyPropertiesFrom(view);
		entityUpdateView.setPageBlock(currentPageBlock);
		Link editLink = new PageLink("edit", new IPageLink() {
			static final long serialVersionUID = 200742L;

			public Page getPage() {
				return app.getViewMeta(modelCode).getPage("EntityEditFormPage",
						entityModel, entityUpdateView);
			}

			public Class getPageIdentity() {
				return app.getViewMeta(modelCode).getPageClass(
						"EntityEditFormPage", entityModel, entityUpdateView);
			}
		});
		item.add(editLink);

		final View entityRemoveView = new View();
		entityRemoveView.copyPropertiesFrom(view);
		entityRemoveView.setPageBlock(currentPageBlock);
		item.add(new Link("remove") {
			static final long serialVersionUID = 200743L;

			public void onClick() {
				if (app.getAccessPoint().isConceptUpdateAllowed(
						getAppSession(), conceptConfig, entity, "remove")) {
					entities.getErrors().empty();
					if (viewModel.getModel().getModelConfig().getDomainConfig()
							.isConfirmRemove()) {
						setResponsePage(app.getViewMeta(modelCode).getPage(
								"EntityConfirmRemovePage", entityModel,
								entityRemoveView));
					} else if (entities.remove(entity)) {
						setResponsePage(app.getViewMeta(modelCode).getPage(
								"EntityUpdateTablePage", entityModel,
								entityRemoveView));
					} else {
						addErrorsByKeys(entities);
					}
				} else {
					addErrorByKey("entities.config.remove");
				}
			}
		});

		// Neighbor update links
		List<NeighborNameLabelLinkPair> neighborNameLabelLinkPairs = new ArrayList<NeighborNameLabelLinkPair>();
		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();
		for (IEntity neighborConfigEntity : neighborsConfig) {
			NeighborConfig neighborConfig = (NeighborConfig) neighborConfigEntity;
			if (neighborConfig.getType().equals("child")
					&& neighborConfig.isInternal()) {
				NeighborNameLabelLinkPair neighborNameLabelLinkPair = new NeighborNameLabelLinkPair();
				String neighborName = LocalizedText.getNeighborName(this,
						entity, neighborConfig);
				Label neighborNameLabel = new Label("neighborName",
						neighborName);
				neighborNameLabelLinkPair
						.setNeighborNameLabel(neighborNameLabel);
				Link neighborLink = null;
				DomainModel model = (DomainModel) viewModel.getModel();
				final IEntities neighborEntities = model.getModelMeta()
						.getChildNeighbor(entity, neighborConfig.getCode());

				final ViewModel neighborModel = new ViewModel();
				neighborModel.copyPropertiesFrom(viewModel);
				ViewModel contextModel = new ViewModel();
				contextModel.copyPropertiesFrom(viewModel);
				contextModel.setEntity(entity);
				contextModel.setEntities(entities);
				neighborModel.setContextViewModel(contextModel);
				neighborModel.setEntities(neighborEntities);
				final View neighborView = new View();
				neighborView.copyPropertiesFrom(view);
				neighborView.setPageBlock(new Integer(0));

				neighborLink = new PageLink("neighbor", new IPageLink() {
					static final long serialVersionUID = 200744L;

					public Page getPage() {
						return app.getViewMeta(modelCode).getPage(
								"EntityUpdateTablePage", neighborModel,
								neighborView);
					}

					public Class getPageIdentity() {
						return app.getViewMeta(modelCode).getPageClass(
								"EntityUpdateTablePage", neighborModel,
								neighborView);
					}
				});
				if (!app.getAccessPoint().isNeighborDisplayAllowed(
						getAppSession(), neighborConfig)) {
					neighborLink.setVisible(false);
				} else if (!app.getAccessPoint().isNeighborUpdateAllowed(
						getAppSession(), neighborConfig)) {
					neighborLink.setVisible(false);
				}

				neighborNameLabelLinkPair.setNeighborLink(neighborLink);
				neighborNameLabelLinkPairs.add(neighborNameLabelLinkPair);
			}
		}
		item.add(new NeighborNameLabelLinkListView("neighborNameLinkListView",
				neighborNameLabelLinkPairs));
	}

}
