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
package modelibra.wicket.component.old;

import java.util.ArrayList;
import java.util.List;

import modelibra.wicket.component.util.old.PropertyLabelPanelList;
import modelibra.wicket.component.util.old.PropertyLabelPanelPair;
import modelibra.wicket.component.view.ComponentView;
import modelibra.wicket.component.view.ConceptView;
import modelibra.wicket.component.view.LookupConceptView;
import modelibra.wicket.component.view.PropertyView;
import modelibra.wicket.component.widget.old.CheckBoxPanel;
import modelibra.wicket.component.widget.old.ExternalLinkPanel;
import modelibra.wicket.component.widget.old.LabelPanel;
import modelibra.wicket.component.widget.old.MultiLineLabelPanel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.util.LocalizedText;

@SuppressWarnings("serial")
public class EntityDisplayPanel extends Panel {

	public EntityDisplayPanel(final ComponentView componentView) {
		super(componentView.getWicketId());
		DomainApp app = (DomainApp) getApplication();
		ConceptView conceptView = componentView.getRootConceptView();
		IEntity<?> entity = conceptView.getEntity();
		ConceptConfig conceptConfig = entity.getConceptConfig();

		String conceptName = LocalizedText.getConceptName(this, entity);
		add(new Label("conceptName", conceptName));

		List<PropertyLabelPanelPair> propertyLabelPanelPairs = new ArrayList<PropertyLabelPanelPair>();

		if (conceptView.isAbsorptionPermitted()) {
			List<LookupConceptView> lookupConceptViews = conceptView
					.getLookupConceptViews();
			if (lookupConceptViews.size() == 0) {
				conceptView.includeLookupConceptViews();
			}
			for (ConceptView lookupConceptView : conceptView
					.getLookupConceptViews()) {
				List<PropertyView> lookupConceptPropertyViews = lookupConceptView
						.getPropertyViews();
				if (lookupConceptPropertyViews.size() == 0) {
					lookupConceptView.includeEssentialProperties();
					lookupConceptPropertyViews = lookupConceptView
							.getPropertyViews();
				}
				propertyLabelPanelPairs = preparePropertyLabelPanelPairs(lookupConceptPropertyViews);
			}
		}

		List<PropertyView> conceptPropertyViews = conceptView
				.getPropertyViews();
		if (conceptPropertyViews.size() == 0) {
			conceptView.includeAllProperties();
			conceptPropertyViews = conceptView.getPropertyViews();
		}
		List<PropertyLabelPanelPair> conceptPropertyLabelPanelPairs = preparePropertyLabelPanelPairs(conceptPropertyViews);
		propertyLabelPanelPairs.addAll(conceptPropertyLabelPanelPairs);

		ListView propertyLabelPanelList = new PropertyLabelPanelList(
				"propertyLabelPanelList", propertyLabelPanelPairs);
		add(propertyLabelPanelList);

		AppSession appSession = (AppSession) getSession();
		if (!app.getAccessPoint().isConceptDisplayAllowed(appSession,
				conceptConfig)) {
			propertyLabelPanelList.setVisible(false);
		}
	}

	protected List<PropertyLabelPanelPair> preparePropertyLabelPanelPairs(
			final List<PropertyView> propertyViews) {
		List<PropertyLabelPanelPair> propertyLabelPanelPairs = new ArrayList<PropertyLabelPanelPair>();
		for (PropertyView propertyView : propertyViews) {
			PropertyConfig propertyConfig = propertyView.getPropertyConfig();

			if (!propertyConfig.isRequired() && !propertyView.isDisplayIfNull()) {
				Object property = propertyView.getConceptView().getEntity()
						.getProperty(propertyView.getPropertyName());
				if (property == null) {
					continue;
				}
			}

			String propertyKey = propertyConfig.getConceptConfig().getCode()
					+ "." + propertyView.getHeader();
			String propertyHeader = LocalizedText.getApplicationPropertiesText(
					this, propertyKey);
			PropertyLabelPanelPair propertyLabelPanelPair = new PropertyLabelPanelPair();
			Label propertyLabel = new Label("propertyLabel", propertyHeader);
			propertyLabelPanelPair.setPropertyLabel(propertyLabel);
			propertyView.setWicketId("propertyPanel");

			Panel propertyPanel;
			if (propertyConfig.getPropertyClass()
					.equals(PropertyClass.getUrl())
					|| propertyConfig.getPropertyClass().equals(
							PropertyClass.getEmail())) {
				propertyPanel = new ExternalLinkPanel(propertyView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getString())
					&& propertyConfig.isValidateType()
					&& (propertyConfig.getValidationType().equals(
							ValidationType.getUrl()) || propertyConfig
							.getValidationType().equals(
									ValidationType.getEmail()))) {
				propertyPanel = new ExternalLinkPanel(propertyView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getBoolean())) {
				propertyPanel = new CheckBoxPanel(propertyView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getString())
					&& propertyConfig.getDisplayLengthInt() > DomainApp.MIN_LONG_TEXT_LENGTH) {
				if (propertyView.isShortDisplay()) {
					propertyPanel = new LabelPanel(propertyView);
				} else {
					propertyPanel = new MultiLineLabelPanel(propertyView);
				}
			} else {
				propertyPanel = new LabelPanel(propertyView);
			}

			propertyLabelPanelPair.setPropertyPanel(propertyPanel);
			propertyLabelPanelPairs.add(propertyLabelPanelPair);

			DomainApp app = (DomainApp) getApplication();
			AppSession appSession = (AppSession) getSession();
			if (!app.getAccessPoint().isPropertyDisplayAllowed(appSession,
					propertyView.getPropertyConfig())) {
				propertyPanel.setVisible(false);
			}
		} // end for
		return propertyLabelPanelPairs;
	}

}
