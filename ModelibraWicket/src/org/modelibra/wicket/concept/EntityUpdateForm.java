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
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.util.Reflector;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmForm;
import org.modelibra.wicket.container.PropertyNameLabelValuePanelListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.util.PropertyNameLabelValuePanelPair;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.CheckBoxPanel;
import org.modelibra.wicket.widget.ExternalLinkPanel;
import org.modelibra.wicket.widget.LabelPanel;
import org.modelibra.wicket.widget.LookupDropDownChoicePanel;
import org.modelibra.wicket.widget.LookupEmptyPanel;
import org.modelibra.wicket.widget.LookupPanel;
import org.modelibra.wicket.widget.TextAreaPanel;
import org.modelibra.wicket.widget.TextFieldPanel;
import org.modelibra.wicket.widget.TypeValueDropDownChoicePanel;

/**
 * Entity update form.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-11-28
 */
public abstract class EntityUpdateForm extends DmForm {

	private static final long serialVersionUID = 102330L;

	private static Log log = LogFactory.getLog(EntityUpdateForm.class);

	/**
	 * Constructs an entity update form.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityUpdateForm(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		final String modelCode = viewModel.getModel().getModelConfig()
				.getCode();

		final IEntity entity = viewModel.getEntity();
		ConceptConfig conceptConfig = entity.getConceptConfig();
		String conceptCode = conceptConfig.getCode();

		Label titleLabel;
		String titleKey = view.getTitle();
		if (titleKey != null) {
			String title = LocalizedText.getApplicationPropertiesText(this,
					titleKey);
			titleLabel = new Label("formTitle", title);
		} else {
			String conceptName = LocalizedText.getConceptName(this, entity);
			titleLabel = new Label("formTitle", conceptName);
		}
		add(titleLabel);

		List<PropertyNameLabelValuePanelPair> propertyNameLabelValuePanelPairs = new ArrayList<PropertyNameLabelValuePanelPair>();

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
					for (IEntity propertyConfigEntity : parentConceptPropertiesConfig) {
						PropertyConfig parentConceptPropertyConfig = (PropertyConfig) propertyConfigEntity;
						if (parentConceptPropertyConfig.isEssential()) {
							IEntity parentEntity = entity
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
								parentModel.setUpdateEntity(parentEntity);
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
									// disable check box in CheckBoxPanel to
									// prevent editing parent entity
									((CheckBoxPanel) parentPropertyPanel)
											.getCheckBox().setEnabled(false);
								} else {
									parentPropertyPanel = new LabelPanel(
											parentModel, parentView);
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
		for (PropertyConfig config : propertiesConfig) {
			PropertyConfig propertyConfig = (PropertyConfig) config;
			String propertyCode = propertyConfig.getCode();
			String propertyName = LocalizedText.getPropertyName(this,
					conceptConfig, propertyConfig);
			PropertyNameLabelValuePanelPair propertyNameLabelValuePanelPair = new PropertyNameLabelValuePanelPair();
			Label propertyNameLabel = new Label("propertyName", propertyName);
			propertyNameLabelValuePanelPair
					.setPropertyNameLabel(propertyNameLabel);
			ViewModel entityModel = new ViewModel();
			entityModel.copyPropertiesFrom(viewModel);
			entityModel.setPropertyConfig(propertyConfig);
			Panel propertyValuePanel;
			if (propertyConfig.isDerived()) {
				View derivedPanelView = new View();
				derivedPanelView.copyPropertiesFrom(view);
				derivedPanelView.setWicketId("valuePanel");
				propertyValuePanel = new LabelPanel(entityModel,
						derivedPanelView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getBoolean())) {
				View checkBoxPanelView = new View();
				checkBoxPanelView.copyPropertiesFrom(view);
				checkBoxPanelView.setWicketId("valuePanel");
				propertyValuePanel = new CheckBoxPanel(entityModel,
						checkBoxPanelView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getString())
					&& propertyConfig.getDisplayLengthInt() > DomainApp.MIN_LONG_TEXT_LENGTH) {
				View textAreaPanelView = new View();
				textAreaPanelView.copyPropertiesFrom(view);
				textAreaPanelView.setWicketId("valuePanel");
				propertyValuePanel = new TextAreaPanel(entityModel,
						textAreaPanelView);
			} else if (propertyConfig.isReference()) {
				String neighborCode = propertyConfig.getReferenceNeighbor();
				NeighborConfig neighborConfig = conceptConfig
						.getNeighborsConfig().getNeighborConfig(neighborCode);
				String neighborConceptCode = neighborConfig
						.getDestinationConcept();
				IDomainModel model = viewModel.getModel();
				IEntities<?> lookupEntities = model
						.getEntry(neighborConceptCode);
				if (lookupEntities == null) {
					ConceptConfig neighborConceptConfig = model
							.getModelConfig().getConceptConfig(
									neighborConceptCode);
					String lookupEntitiesCode = neighborConceptConfig
							.getEntitiesCode();
					String getLookupEntitiesMethod = "get" + lookupEntitiesCode;
					lookupEntities = (IEntities<?>) Reflector.executeMethod(
							model, getLookupEntitiesMethod);
				}
				if (lookupEntities != null) {
					ViewModel neighborModel = new ViewModel();
					neighborModel.copyPropertiesFrom(viewModel);
					neighborModel.setPropertyConfig(propertyConfig);
					neighborModel.setLookupEntities(lookupEntities);
					View lookupView = new View();
					lookupView.copyPropertiesFrom(view);
					lookupView.setWicketId("valuePanel");
					if (propertyConfig.isReferenceDropDownLookup()) {
						propertyValuePanel = new LookupDropDownChoicePanel(
								neighborModel, lookupView);
					} else {
						if (neighborConfig.getMinInt() == 0) {
							propertyValuePanel = new LookupEmptyPanel(
									neighborModel, lookupView);

						} else {
							propertyValuePanel = new LookupPanel(neighborModel,
									lookupView);
						}
					}
				} else {
					log
							.info(modelCode
									+ "."
									+ conceptCode
									+ "."
									+ propertyCode
									+ " reference property does not have the lookup entities.");
					View textFieldPanelView = new View();
					textFieldPanelView.copyPropertiesFrom(view);
					textFieldPanelView.setWicketId("valuePanel");
					propertyValuePanel = new TextFieldPanel(entityModel,
							textFieldPanelView);
				}
			} else if (propertyConfig.isValidateType()) {
				if (propertyConfig.isValidateClassType()) {
					View textFieldPanelView = new View();
					textFieldPanelView.copyPropertiesFrom(view);
					textFieldPanelView.setWicketId("valuePanel");
					propertyValuePanel = new TextFieldPanel(entityModel,
							textFieldPanelView);
				} else {
					View dropDownChoicePanelView = new View();
					dropDownChoicePanelView.copyPropertiesFrom(view);
					dropDownChoicePanelView.setWicketId("valuePanel");
					propertyValuePanel = new TypeValueDropDownChoicePanel(
							entityModel, dropDownChoicePanelView);
				}
			} else {
				View textFieldPanelView = new View();
				textFieldPanelView.copyPropertiesFrom(view);
				textFieldPanelView.setWicketId("valuePanel");
				propertyValuePanel = new TextFieldPanel(entityModel,
						textFieldPanelView);
			} // end if
			if (!app.getAccessPoint().isPropertyDisplayAllowed(getAppSession(),
					propertyConfig)) {
				propertyNameLabel.setVisible(false);
				propertyValuePanel.setVisible(false);
			} else if (!app.getAccessPoint().isPropertyUpdateAllowed(
					getAppSession(), propertyConfig)) {
				View labelPanelView = new View();
				labelPanelView.copyPropertiesFrom(view);
				labelPanelView.setWicketId("valuePanel");
				propertyValuePanel = new LabelPanel(entityModel, labelPanelView);
			}

			propertyNameLabelValuePanelPair
					.setPropertyValuePanel(propertyValuePanel);
			propertyNameLabelValuePanelPairs
					.add(propertyNameLabelValuePanelPair);
		} // end for

		ListView propertyNameLabelValuePanelListView = new PropertyNameLabelValuePanelListView(
				"propertyNameLabelValuePanelListView",
				propertyNameLabelValuePanelPairs);
		add(propertyNameLabelValuePanelListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			propertyNameLabelValuePanelListView.setVisible(false);
		}

		add(new Button("cancel") {
			static final long serialVersionUID = 200721L;

			public void onSubmit() {
				onCancel(viewModel, view);
			}
		}.setDefaultFormProcessing(false));
	}

	/**
	 * Cancels a user action.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	protected void onCancel(final ViewModel viewModel, final View view) {
		if (!view.isRecreateContext()) {
			ViewModel entityUpdateTablePageModel = new ViewModel();
			entityUpdateTablePageModel.copyPropertiesFrom(viewModel);
			entityUpdateTablePageModel.setUpdateEntity(null);
			View entityUpdateTablePageView = new View();
			entityUpdateTablePageView.copyPropertiesFrom(view);
			setResponsePage(view.getContextView().getPage());
		} else {
			Page contextPage = null;
			ViewModel contextModel = viewModel.getContextViewModel();
			View contextView = view.getContextView();
			if (contextView != null) {
				DomainApp app = (DomainApp) getApplication();
				String modelCode = viewModel.getModel().getModelConfig()
						.getCode();
				contextPage = app.getViewMeta(modelCode).getPage(contextModel,
						contextView);
				if (contextPage == null) {
					contextPage = app.getViewMeta(modelCode).getPage(
							contextView.getPage());
				}
			}
			setResponsePage(contextPage);
		}
	}

}
