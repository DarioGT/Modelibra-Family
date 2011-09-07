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

import modelibra.wicket.component.util.PropertyLabelList;
import modelibra.wicket.component.util.PropertyPanelList;
import modelibra.wicket.component.view.ComponentView;
import modelibra.wicket.component.view.ConceptView;
import modelibra.wicket.component.view.PropertyView;
import modelibra.wicket.component.widget.old.CheckBoxPanel;
import modelibra.wicket.component.widget.old.ExternalLinkPanel;
import modelibra.wicket.component.widget.old.LabelPanel;
import modelibra.wicket.component.widget.old.MultiLineLabelPanel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.util.LocalizedText;

@SuppressWarnings("serial")
public class EntityTableDisplayPanel extends Panel {

	private ComponentView componentView;

	public EntityTableDisplayPanel(final ComponentView componentView) {
		super(componentView.getWicketId());
		this.componentView = componentView;

		DomainApp app = (DomainApp) getApplication();
		ConceptView conceptView = componentView.getRootConceptView();
		IEntities<?> entities = conceptView.getEntities();
		ConceptConfig conceptConfig = entities.getConceptConfig();

		// Table title
		String conceptsName = LocalizedText.getConceptsName(this, entities);
		add(new Label("conceptsName", conceptsName));

		// Property views
		List<PropertyView> propertyViews = conceptView.getPropertyViews();
		if (propertyViews.size() == 0) {
			conceptView.includeEssentialProperties();
			propertyViews = conceptView.getPropertyViews();
		}

		// Property headers
		List<String> propertyHeaders = new ArrayList<String>();
		for (PropertyView propertyView : propertyViews) {
			String propertyKey = conceptConfig.getCode() + "."
					+ propertyView.getHeader();
			String propertyHeader = LocalizedText.getApplicationPropertiesText(
					this, propertyKey);
			propertyHeaders.add(propertyHeader);
		}
		add(new PropertyLabelList("propertyLabelList", propertyHeaders));

		EntityList entityList = new EntityList("entityList", entities.getList());
		add(entityList);

		AppSession appSession = (AppSession) getSession();
		if (!app.getAccessPoint().isConceptDisplayAllowed(appSession,
				conceptConfig)) {
			entityList.setVisible(false);
		}
	}

	protected class EntityList extends ListView {

		public EntityList(final String wicketId, final List<?> entityList) {
			super(wicketId, entityList);
		}

		/**
		 * Populates list view lines.
		 * 
		 * @param item
		 *            list item
		 */
		protected void populateItem(ListItem item) {
			IEntity<?> entity = (IEntity<?>) item.getModelObject();
			ConceptView conceptView = componentView.getRootConceptView();
			conceptView.setEntity(entity);

			List<Panel> propertyPanels = preparePropertyPanels(conceptView
					.getPropertyViews());

			ListView propertyPanelList = new PropertyPanelList(
					"propertyPanelList", propertyPanels);
			item.add(propertyPanelList);
		}

		protected List<Panel> preparePropertyPanels(
				final List<PropertyView> propertyViews) {
			List<Panel> propertyPanels = new ArrayList<Panel>();
			for (PropertyView propertyView : propertyViews) {
				PropertyConfig propertyConfig = propertyView
						.getPropertyConfig();
				propertyView.setWicketId("propertyPanel");
				Panel propertyPanel;
				if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getUrl())
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

}
