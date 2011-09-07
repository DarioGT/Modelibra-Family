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
package org.modelibra.wicket.neighbor;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
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
 * Displays a child property list as a list view.
 * 
 * @author Vensada Okanovic
 * @version 2007-12-16
 */
public class ChildPropertyDisplayListListView extends DmListView {

	private static final long serialVersionUID = 102010L;

	private ViewModel viewModel;

	private View view;

	/**
	 * Constructs a child property display list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public ChildPropertyDisplayListListView(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
	}

	/**
	 * Populates a list view line.
	 * 
	 * @param item
	 *            list item (line)
	 */
	protected void populateItem(final ListItem item) {
		IEntity<?> childEntity = (IEntity<?>) item.getModelObject();

		DomainApp app = (DomainApp) getApplication();

		ViewModel childModel = new ViewModel();
		childModel.copyPropertiesFrom(viewModel);
		childModel.setEntity(childEntity);

		View childView = new View();
		childView.copyPropertiesFrom(view);
		childView.setWicketId("childProperty");

		String childPropertyCode = (String) viewModel.getUserProperties()
				.getUserProperty("childProperty");
		PropertyConfig childPropertyConfig = childEntity.getConceptConfig()
				.getPropertyConfig(childPropertyCode);
		childModel.setPropertyConfig(childPropertyConfig);
		Panel childPropertyPanel;
		String childPropertyClass = childPropertyConfig.getPropertyClass();

		Object childProperty = childEntity.getProperty(childPropertyCode);
		if (childProperty != null) {
			if (childPropertyClass.equals(PropertyClass.getUrl())
					|| childPropertyClass.equals(PropertyClass.getEmail())) {
				childPropertyPanel = new ExternalLinkPanel(childModel,
						childView);
			} else if (childPropertyConfig.getPropertyClass().equals(
					PropertyClass.getString())
					&& childPropertyConfig.isValidateType()) {
				if (childPropertyConfig.getValidationType().equals(
						ValidationType.getUrl())
						|| childPropertyConfig.getValidationType().equals(
								ValidationType.getEmail())) {
					childPropertyPanel = new ExternalLinkPanel(childModel,
							childView);

				} else {
					childPropertyPanel = new LabelPanel(childModel, childView);
				}
			} else {
				childPropertyPanel = new LabelPanel(childModel, childView);
			}
			if (!app.getAccessPoint().isPropertyDisplayAllowed(getAppSession(),
					childPropertyConfig)) {
				childPropertyPanel.setVisible(false);
			}
		} else {// child property null
			childPropertyPanel = new EmptyPanel(childView.getWicketId());
			childPropertyPanel.setVisible(false);
		}
		item.add(childPropertyPanel);
	}

}
