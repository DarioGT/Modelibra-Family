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

import modelibra.wicket.component.util.PropertyPanelList;
import modelibra.wicket.component.view.ComponentView;
import modelibra.wicket.component.view.ConceptView;
import modelibra.wicket.component.view.LookupConceptView;
import modelibra.wicket.component.view.PropertyView;
import modelibra.wicket.component.widget.old.CheckBox2Panel;
import modelibra.wicket.component.widget.old.ExternalLink2Panel;
import modelibra.wicket.component.widget.old.Label2Panel;
import modelibra.wicket.component.widget.old.MultiLineLabel2Panel;

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
public class Entity2DisplayPanel extends Panel {

	public Entity2DisplayPanel(final ComponentView componentView) {
		super(componentView.getWicketId());
		DomainApp app = (DomainApp) getApplication();
		ConceptView conceptView = componentView.getRootConceptView();
		IEntity<?> entity = conceptView.getEntity();
		ConceptConfig conceptConfig = entity.getConceptConfig();

		String conceptName = LocalizedText.getConceptName(this, entity);
		add(new Label("conceptName", conceptName));

		List<Panel> property2Panels = new ArrayList<Panel>();

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
				property2Panels = prepareProperty2Panels(lookupConceptPropertyViews);
			}
		}

		List<PropertyView> conceptPropertyViews = conceptView
				.getPropertyViews();
		if (conceptPropertyViews.size() == 0) {
			conceptView.includeAllProperties();
			conceptPropertyViews = conceptView.getPropertyViews();
		}
		List<Panel> conceptProperty2Panels = prepareProperty2Panels(conceptPropertyViews);
		property2Panels.addAll(conceptProperty2Panels);

		ListView property2PanelList = new PropertyPanelList(
				"property2PanelList", property2Panels);
		add(property2PanelList);

		AppSession appSession = (AppSession) getSession();
		if (!app.getAccessPoint().isConceptDisplayAllowed(appSession,
				conceptConfig)) {
			property2PanelList.setVisible(false);
		}
	}

	protected List<Panel> prepareProperty2Panels(
			final List<PropertyView> propertyViews) {
		List<Panel> propertyPanels = new ArrayList<Panel>();
		for (PropertyView propertyView : propertyViews) {
			PropertyConfig propertyConfig = propertyView.getPropertyConfig();

			if (!propertyConfig.isRequired() && !propertyView.isDisplayIfNull()) {
				Object property = propertyView.getConceptView().getEntity()
						.getProperty(propertyView.getPropertyName());
				if (property == null) {
					continue;
				}
			}

			propertyView.setWicketId("property2Panel");
			Panel propertyPanel;
			if (propertyConfig.getPropertyClass()
					.equals(PropertyClass.getUrl())
					|| propertyConfig.getPropertyClass().equals(
							PropertyClass.getEmail())) {
				propertyPanel = new ExternalLink2Panel(propertyView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getString())
					&& propertyConfig.isValidateType()
					&& (propertyConfig.getValidationType().equals(
							ValidationType.getUrl()) || propertyConfig
							.getValidationType().equals(
									ValidationType.getEmail()))) {
				propertyPanel = new ExternalLink2Panel(propertyView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getBoolean())) {
				propertyPanel = new CheckBox2Panel(propertyView);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getString())
					&& propertyConfig.getDisplayLengthInt() > DomainApp.MIN_LONG_TEXT_LENGTH) {
				if (propertyView.isShortDisplay()) {
					propertyPanel = new Label2Panel(propertyView);
				} else {
					propertyPanel = new MultiLineLabel2Panel(propertyView);
				}
			} else {
				propertyPanel = new Label2Panel(propertyView);
			}

			propertyPanels.add(propertyPanel);

			DomainApp app = (DomainApp) getApplication();
			AppSession appSession = (AppSession) getSession();
			if (!app.getAccessPoint().isPropertyDisplayAllowed(appSession,
					propertyView.getPropertyConfig())) {
				propertyPanel.setVisible(false);
			}
		} // end for
		return propertyPanels;
	}

}
