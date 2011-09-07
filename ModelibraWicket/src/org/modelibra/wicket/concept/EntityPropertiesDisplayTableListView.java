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
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPageableListView;
import org.modelibra.wicket.container.PropertyValuePanelListView;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.CheckBoxPanel;
import org.modelibra.wicket.widget.ExternalLinkPanel;
import org.modelibra.wicket.widget.LabelPanel;

/**
 * Entity properties display table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityPropertiesDisplayTableListView extends DmPageableListView {

	private static final long serialVersionUID = 102270L;

	private static Log log = LogFactory
			.getLog(EntityPropertiesDisplayTableListView.class);

	private ViewModel viewModel;

	private View viewContext;

	private String modelCode;

	/**
	 * Constructs an entity properties display table list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param viewContext
	 *            view
	 */
	public EntityPropertiesDisplayTableListView(final ViewModel viewModel,
			final View viewContext) {
		super(viewModel, viewContext, viewModel.getModel().getModelConfig()
				.getDomainConfig().getPageBlockDefaultSizeInt());
		this.viewModel = viewModel;
		this.viewContext = viewContext;
		modelCode = viewModel.getModel().getModelConfig().getCode();
	}

	/**
	 * Populates a list view line.
	 * 
	 * @param item
	 *            list item (line)
	 */
	protected void populateItem(ListItem item) {
		IEntity entity = (IEntity) item.getModelObject();

		final DomainApp app = (DomainApp) getApplication();

		ConceptConfig conceptConfig = entity.getConceptConfig();

		final ViewModel entityModel = new ViewModel();
		entityModel.copyPropertiesFrom(viewModel);
		entityModel.setEntity(entity);

		ViewModel parentViewModel = new ViewModel();
		parentViewModel.copyPropertiesFrom(viewModel);
		View parentView = new View();
		parentView.copyPropertiesFrom(viewContext);

		List<Panel> propertyValuePanels = new ArrayList<Panel>();

		// Properties absorbed from parents.
		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();
		for (IEntity neighborConfigEntity : neighborsConfig) {
			NeighborConfig neighborConfig = (NeighborConfig) neighborConfigEntity;
			if (neighborConfig.getType().equals("parent")
					&& neighborConfig.getMax().equals("1")
					&& neighborConfig.isAbsorb()) {
				ConceptConfig parentConceptConfig = neighborConfig
						.getDestinationConceptConfig();
				if (parentConceptConfig != null) {
					PropertiesConfig parentConceptPropertiesConfig = parentConceptConfig
							.getPropertiesConfig();
					for (IEntity parentPropertyConfigEntity : parentConceptPropertiesConfig) {
						PropertyConfig parentConceptPropertyConfig = (PropertyConfig) parentPropertyConfigEntity;
						if (parentConceptPropertyConfig.isEssential()) {
							IEntity parentEntity = entity
									.getParentNeighbor(neighborConfig.getCode());
							Panel parentPropertyPanel;
							if (parentEntity != null) {
								parentViewModel.setEntity(parentEntity);
								parentViewModel
										.setPropertyConfig(parentConceptPropertyConfig);
								parentView.setWicketId("valuePanel");
								parentView.getUserProperties().addUserProperty(
										"shortText", Boolean.TRUE);
								if (parentConceptPropertyConfig
										.getPropertyClass().equals(
												PropertyClass.getUrl())
										|| parentConceptPropertyConfig
												.getPropertyClass().equals(
														PropertyClass
																.getEmail())) {
									parentPropertyPanel = new ExternalLinkPanel(
											parentViewModel, parentView);
								} else if (parentConceptPropertyConfig
										.getPropertyClass().equals(
												PropertyClass.getString())
										&& parentConceptPropertyConfig
												.isValidateType()
										&& (parentConceptPropertyConfig
												.getValidationType()
												.equals(ValidationType.getUrl()) || parentConceptPropertyConfig
												.getValidationType().equals(
														ValidationType
																.getEmail()))) {
									parentPropertyPanel = new ExternalLinkPanel(
											parentViewModel, parentView);
								} else if (parentConceptPropertyConfig
										.getPropertyClass().equals(
												PropertyClass.getBoolean())) {
									parentPropertyPanel = new CheckBoxPanel(
											parentViewModel, parentView);

								} else {
									parentPropertyPanel = new LabelPanel(
											parentViewModel, parentView);
								} // if
								if (!app
										.getAccessPoint()
										.isNeighborDisplayAllowed(
												getAppSession(), neighborConfig)) {
									parentPropertyPanel.setVisible(false);
								} else if (!app.getAccessPoint()
										.isPropertyDisplayAllowed(
												getAppSession(),
												parentConceptPropertyConfig)) {
									parentPropertyPanel.setVisible(false);
								}
							} else {
								parentPropertyPanel = new Panel("valuePanel");
								parentPropertyPanel.setVisible(false);
							}
							propertyValuePanels.add(parentPropertyPanel);
						} // if
					} // for
				} // if
			} // if
		} // for

		PropertiesConfig propertiesConfig = conceptConfig.getPropertiesConfig();
		for (IEntity propertyConfigEntity : propertiesConfig) {
			PropertyConfig propertyConfig = (PropertyConfig) propertyConfigEntity;
			if (propertyConfig.isEssential()) {
				entityModel.setPropertyConfig(propertyConfig);
				View propertiesView = new View();
				propertiesView.copyPropertiesFrom(viewContext);
				propertiesView.setWicketId("valuePanel");
				propertiesView.getUserProperties().addUserProperty("shortText",
						Boolean.TRUE);
				Panel essentialPropertyPanel;
				if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getUrl())
						|| propertyConfig.getPropertyClass().equals(
								PropertyClass.getEmail())) {
					essentialPropertyPanel = new ExternalLinkPanel(entityModel,
							propertiesView);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())
						&& propertyConfig.isValidateType()
						&& (propertyConfig.getValidationType().equals(
								ValidationType.getUrl()) || propertyConfig
								.getValidationType().equals(
										ValidationType.getEmail()))) {
					essentialPropertyPanel = new ExternalLinkPanel(entityModel,
							propertiesView);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getBoolean())) {
					essentialPropertyPanel = new CheckBoxPanel(entityModel,
							propertiesView);

				} else {
					essentialPropertyPanel = new LabelPanel(entityModel,
							propertiesView);
				} // end if
				if (!app.getAccessPoint().isPropertyDisplayAllowed(
						getAppSession(), propertyConfig)) {
					essentialPropertyPanel.setVisible(false);
				}

				propertyValuePanels.add(essentialPropertyPanel);
			} // end if (propertyConfig.isEssential()) {
		} // for

		ListView propertyValuePanelListView = new PropertyValuePanelListView(
				"propertyValuePanelListView", propertyValuePanels);
		item.add(propertyValuePanelListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			propertyValuePanelListView.setVisible(false);
		}

		final View entityDisplayPageView = new View();
		entityDisplayPageView.copyPropertiesFrom(viewContext);
		entityDisplayPageView.setUpdate(false);
		Link displayLink = new PageLink("display", new IPageLink() {
			static final long serialVersionUID = 200531L;

			public Page getPage() {
				return app.getViewMeta(modelCode).getPage("EntityDisplayPage",
						entityModel, entityDisplayPageView);
			}

			public Class getPageIdentity() {
				return app.getViewMeta(modelCode)
						.getPageClass("EntityDisplayPage", entityModel,
								entityDisplayPageView);
			}
		});
		item.add(displayLink);
	}

}
