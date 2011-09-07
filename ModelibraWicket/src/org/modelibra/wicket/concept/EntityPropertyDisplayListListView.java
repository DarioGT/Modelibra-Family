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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.ExternalLinkPanel;
import org.modelibra.wicket.widget.LabelPanel;

/**
 * Displays entity property list list view.
 * 
 * @author Vensada Okanovic
 * @version 2007-11-28
 */
public class EntityPropertyDisplayListListView extends DmListView {

	private static final long serialVersionUID = 102280L;

	private static Log log = LogFactory
			.getLog(EntityPropertyDisplayListListView.class);

	private ViewModel viewModel;

	private View view;

	public EntityPropertyDisplayListListView(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
	}

	protected void populateItem(final ListItem item) {
		IEntity entity = (IEntity) item.getModelObject();

		DomainApp app = (DomainApp) getApplication();

		ViewModel entityModel = new ViewModel();
		entityModel.copyPropertiesFrom(viewModel);
		entityModel.setEntity(entity);

		View entityView = new View();
		entityView.copyPropertiesFrom(view);
		entityView.setWicketId("entityProperty");
		entityView.getUserProperties().addUserProperty("shortText",
				Boolean.TRUE);

		PropertyConfig propertyConfig = entityModel.getPropertyConfig();
		Panel entityPropertyPanel;

		String propertyClass = propertyConfig.getPropertyClass();
		if (propertyClass.equals(PropertyClass.getUrl())
				|| propertyClass.equals(PropertyClass.getEmail())) {
			entityPropertyPanel = new ExternalLinkPanel(entityModel, entityView);
			item.add(entityPropertyPanel);
		} else if (propertyConfig.getPropertyClass().equals(
				PropertyClass.getString())
				&& propertyConfig.isValidateType()) {
			if (propertyConfig.getValidationType().equals(
					ValidationType.getUrl())
					|| propertyConfig.getValidationType().equals(
							ValidationType.getEmail())) {
				entityPropertyPanel = new ExternalLinkPanel(entityModel,
						entityView);
				item.add(entityPropertyPanel);
			} else {
				entityPropertyPanel = new LabelPanel(entityModel, entityView);
				item.add(entityPropertyPanel);
			}
		} else {
			entityPropertyPanel = new LabelPanel(entityModel, entityView);
			item.add(entityPropertyPanel);
		}
		if (!app.getAccessPoint().isPropertyDisplayAllowed(getAppSession(),
				propertyConfig)) {
			entityPropertyPanel.setVisible(false);
		}
	}

}
