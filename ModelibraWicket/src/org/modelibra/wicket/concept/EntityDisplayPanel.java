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
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.container.PropertyNameLabelValuePanelListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.util.PropertyNameLabelValuePanelPair;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.CheckBoxPanel;
import org.modelibra.wicket.widget.ExternalLinkPanel;
import org.modelibra.wicket.widget.LabelPanel;
import org.modelibra.wicket.widget.MultiLineLabelPanel;

/**
 * Entity displaypanel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityDisplayPanel extends DmPanel {

	private static final long serialVersionUID = 102150L;

	private static Log log = LogFactory.getLog(EntityDisplayPanel.class);

	/**
	 * Constructs an entity display panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		DomainApp app = (DomainApp) getApplication();

		IEntity<?> entity = viewModel.getEntity();

		ConceptConfig conceptConfig = entity.getConceptConfig();
		String conceptName = LocalizedText.getConceptName(this, entity);
		add(new Label("conceptName", conceptName));

		ViewModel entityModel = new ViewModel();
		entityModel.copyPropertiesFrom(viewModel);

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

								ViewModel parentViewModel = new ViewModel();
								parentViewModel.copyPropertiesFrom(viewModel);
								parentViewModel.setEntity(parentEntity);
								parentViewModel
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
								}
								if (!app
										.getAccessPoint()
										.isNeighborDisplayAllowed(
												getAppSession(), neighborConfig)) {
									parentPropertyNameLabel.setVisible(false);
									parentPropertyPanel.setVisible(false);
								} else if (!app.getAccessPoint()
										.isPropertyDisplayAllowed(
												getAppSession(),
												parentConceptPropertyConfig)) {
									parentPropertyNameLabel.setVisible(false);
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
			if (!propertyConfig.isReference()) {
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

				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())
						&& propertyConfig.getDisplayLengthInt() > DomainApp.MIN_LONG_TEXT_LENGTH) {
					propertyValuePanel = new MultiLineLabelPanel(entityModel,
							propertiesView);

				} else {
					propertyValuePanel = new LabelPanel(entityModel,
							propertiesView);
				} // end if
				if (!app.getAccessPoint().isPropertyDisplayAllowed(
						getAppSession(), propertyConfig)) {
					propertyNameLabel.setVisible(false);
					propertyValuePanel.setVisible(false);
				}

				propertyNameLabelValuePanelPair
						.setPropertyValuePanel(propertyValuePanel);
				propertyNameLabelValuePanelPairs
						.add(propertyNameLabelValuePanelPair);
			} // end if (!propertyConfig.isReference()) {
		} // end for

		ListView propertyNameLabelValuePanelListView = new PropertyNameLabelValuePanelListView(
				"propertyNameLabelValuePanelListView",
				propertyNameLabelValuePanelPairs);
		add(propertyNameLabelValuePanelListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			propertyNameLabelValuePanelListView.setVisible(false);
		}
	}

}
