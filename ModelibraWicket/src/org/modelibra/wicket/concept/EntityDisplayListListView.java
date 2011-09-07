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

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.container.PanelListView;
import org.modelibra.wicket.container.PropertyNameLabelValuePanelListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.util.PropertyNameLabelValuePanelPair;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.CheckBoxPanel;
import org.modelibra.wicket.widget.ExternalLinkPanel;
import org.modelibra.wicket.widget.LabelPanel;

/**
 * Entity display list list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-28
 */
public class EntityDisplayListListView extends DmListView {

	private static final long serialVersionUID = 102090L;

	private ViewModel viewModel;

	private View view;

	private String modelCode;

	/**
	 * Constructs an entity display list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayListListView(final ViewModel viewModel, final View view) {
		super(view.getWicketId(), viewModel.getEntities());
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
		IEntity<?> entity = (IEntity<?>) item.getModelObject();

		DomainApp app = (DomainApp) getApplication();

		ConceptConfig conceptConfig = entity.getConceptConfig();
		String conceptCode = conceptConfig.getCode();

		ViewModel entityModel = new ViewModel();
		entityModel.copyPropertiesFrom(viewModel);
		entityModel.setEntity(entity);

		List<PropertyNameLabelValuePanelPair> propertyNameLabelValuePanelPairs = new ArrayList<PropertyNameLabelValuePanelPair>();

		// Properties absorbed from parents.
		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();
		for (IEntity<?> neighborConfigEntity : neighborsConfig) {
			NeighborConfig neighborConfig = (NeighborConfig) neighborConfigEntity;
			if (neighborConfig.getType().equals("parent")
					&& neighborConfig.getMax().equals("1")
					&& neighborConfig.isAbsorb()) {
				ConceptConfig parentConceptConfig = neighborConfig
						.getDestinationConceptConfig();
				if (parentConceptConfig != null) {
					if (conceptCode.equals(neighborConfig.getConceptConfig()
							.getCode())
							&& parentConceptConfig.getDisplayType().equals(
									"slide")) {
						// Do not absorb the context parent properties when
						// the parent is displayed as a slide.
						continue;
					}
					PropertiesConfig parentConceptPropertiesConfig = parentConceptConfig
							.getPropertiesConfig();
					for (IEntity<?> parentPropertyConfigEntity : parentConceptPropertiesConfig) {
						PropertyConfig parentConceptPropertyConfig = (PropertyConfig) parentPropertyConfigEntity;
						if (parentConceptPropertyConfig.isEssential()) {
							IEntity<?> parentEntity = entity
									.getParentNeighbor(neighborConfig.getCode());
							if (parentEntity != null) {
								String parentPropertyName = LocalizedText
										.getPropertyName(this, parentEntity,
												parentConceptPropertyConfig);
								Label parentPropertyNameLabel = new Label(
										"propertyName", parentPropertyName);
								PropertyNameLabelValuePanelPair propertyNameLabelValuePanelPair = new PropertyNameLabelValuePanelPair();
								propertyNameLabelValuePanelPair
										.setPropertyNameLabel(parentPropertyNameLabel);

								ViewModel parentModel = new ViewModel();
								parentModel.copyPropertiesFrom(viewModel);
								parentModel.setEntity(parentEntity);
								parentModel
										.setPropertyConfig(parentConceptPropertyConfig);

								View parentView = new View();
								parentView.copyPropertiesFrom(view);
								parentView.setWicketId("valuePanel");
								Panel parentPropertyPanel;
								if (parentConceptPropertyConfig
										.getPropertyClass().equals(
												PropertyClass.getUrl())
										|| parentConceptPropertyConfig
												.getPropertyClass().equals(
														PropertyClass
																.getEmail())) {
									parentPropertyPanel = new ExternalLinkPanel(
											parentModel, parentView);
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
											parentModel, parentView);
								} else if (parentConceptPropertyConfig
										.getPropertyClass().equals(
												PropertyClass.getBoolean())) {
									parentPropertyPanel = new CheckBoxPanel(
											parentModel, parentView);

								} else {
									parentPropertyPanel = new LabelPanel(
											parentModel, parentView);
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

								propertyNameLabelValuePanelPair
										.setPropertyValuePanel(parentPropertyPanel);
								propertyNameLabelValuePanelPairs
										.add(propertyNameLabelValuePanelPair);
							} // if
						} // if
					} // for
				} // if
			} // if
		} // for

		PropertiesConfig propertiesConfig = conceptConfig.getPropertiesConfig();
		for (IEntity<?> propertyConfigEntity : propertiesConfig) {
			PropertyConfig propertyConfig = (PropertyConfig) propertyConfigEntity;
			// if (!propertyConfig.isReference()) {
			if (propertyConfig.isEssential()) {
				String propertyName = LocalizedText.getPropertyName(this,
						entity, propertyConfig);
				Label propertyNameLabel = new Label("propertyName",
						propertyName);
				PropertyNameLabelValuePanelPair propertyNameLabelValuePanelPair = new PropertyNameLabelValuePanelPair();
				propertyNameLabelValuePanelPair
						.setPropertyNameLabel(propertyNameLabel);

				entityModel.setPropertyConfig(propertyConfig);
				View propertiesView = new View();
				propertiesView.copyPropertiesFrom(view);
				propertiesView.setWicketId("valuePanel");
				Panel propertyValuePanel;
				if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getUrl())
						|| propertyConfig.getPropertyClass().equals(
								PropertyClass.getEmail())) {
					propertyValuePanel = new ExternalLinkPanel(entityModel,
							propertiesView);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())
						&& propertyConfig.isValidateType()
						&& (propertyConfig.getValidationType().equals(
								ValidationType.getUrl()) || propertyConfig
								.getValidationType().equals(
										ValidationType.getEmail()))) {
					propertyValuePanel = new ExternalLinkPanel(entityModel,
							propertiesView);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getBoolean())) {
					propertyValuePanel = new CheckBoxPanel(entityModel,
							propertiesView);

				} else {
					propertyValuePanel = new LabelPanel(entityModel,
							propertiesView);
				}
				if (!app.getAccessPoint().isPropertyDisplayAllowed(
						getAppSession(), propertyConfig)) {
					propertyNameLabel.setVisible(false);
					propertyValuePanel.setVisible(false);
				}

				propertyNameLabelValuePanelPair
						.setPropertyValuePanel(propertyValuePanel);
				propertyNameLabelValuePanelPairs
						.add(propertyNameLabelValuePanelPair);
			}
		}

		ListView propertyNameLabelValuePanelListView = new PropertyNameLabelValuePanelListView(
				"propertyNameLabelValuePanelListView",
				propertyNameLabelValuePanelPairs);
		item.add(propertyNameLabelValuePanelListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			propertyNameLabelValuePanelListView.setVisible(false);
		}

		List<Panel> childList = new ArrayList<Panel>();
		for (IEntity<?> neighborConfigEntity : neighborsConfig) {
			NeighborConfig neighborConfig = (NeighborConfig) neighborConfigEntity;
			if (neighborConfig.getType().equals("child")) {
				String childCode = neighborConfig.getCode();
				DomainModel model = (DomainModel) viewModel.getModel();
				IEntities<?> childEntities = model.getModelMeta()
						.getChildNeighbor(entity, childCode);
				ViewModel childEntitiesModel = new ViewModel();
				childEntitiesModel.copyPropertiesFrom(viewModel);
				childEntitiesModel.setEntities(childEntities);
				View entityDisplayListPanelView = new View();
				entityDisplayListPanelView.copyPropertiesFrom(view);
				entityDisplayListPanelView
						.setWicketId("entityDisplayListPanel");
				Panel entityDisplayListPanel = app.getViewMeta(modelCode)
						.getPanel("EntityDisplayListPanel", childEntitiesModel,
								entityDisplayListPanelView);
				childList.add(entityDisplayListPanel);
				if (!app.getAccessPoint().isNeighborDisplayAllowed(
						getAppSession(), neighborConfig)) {
					entityDisplayListPanel.setVisible(false);
				}
			} // if (neighborConfig.getType().equals("child")) {
		} // for

		item.add(new PanelListView("childEntityListPanelListView", childList));
	}
}
