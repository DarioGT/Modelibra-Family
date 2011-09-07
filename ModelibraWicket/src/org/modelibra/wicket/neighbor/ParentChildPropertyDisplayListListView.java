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
 * Displays a parent child property list as a list view.
 * 
 * @author Vensada Okanovic
 * @version 2007-11-28
 */
public class ParentChildPropertyDisplayListListView extends DmListView {

	private static final long serialVersionUID = 102380L;

	private ViewModel viewModel;

	private View view;

	private String modelCode;

	public ParentChildPropertyDisplayListListView(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
		modelCode = viewModel.getModel().getModelConfig().getCode();
	}

	protected void populateItem(final ListItem item) {
		IEntity<?> parentEntity = (IEntity<?>) item.getModelObject();

		DomainApp app = (DomainApp) getApplication();

		ViewModel parentModel = new ViewModel();
		parentModel.copyPropertiesFrom(viewModel);
		parentModel.setEntity(parentEntity);

		View parentView = new View();
		parentView.copyPropertiesFrom(view);
		parentView.setWicketId("parentProperty");

		PropertyConfig parentPropertyConfig = parentModel.getPropertyConfig();
		Panel parentPropertyPanel;
		String parentPropertyClass = parentPropertyConfig.getPropertyClass();
		if (parentPropertyClass.equals(PropertyClass.getUrl())
				|| parentPropertyClass.equals(PropertyClass.getEmail())) {
			parentPropertyPanel = new ExternalLinkPanel(parentModel, parentView);
			item.add(parentPropertyPanel);
		} else if (parentPropertyConfig.getPropertyClass().equals(
				PropertyClass.getString())
				&& parentPropertyConfig.isValidateType()) {
			if (parentPropertyConfig.getValidationType().equals(
					ValidationType.getUrl())
					|| parentPropertyConfig.getValidationType().equals(
							ValidationType.getEmail())) {
				parentPropertyPanel = new ExternalLinkPanel(parentModel,
						parentView);
				item.add(parentPropertyPanel);
			} else {
				parentPropertyPanel = new LabelPanel(parentModel, parentView);
				item.add(parentPropertyPanel);
			}
		} else {
			parentPropertyPanel = new LabelPanel(parentModel, parentView);
			item.add(parentPropertyPanel);
		}
		if (!app.getAccessPoint().isPropertyDisplayAllowed(getAppSession(),
				parentPropertyConfig)) {
			parentPropertyPanel.setVisible(false);
		}

		ViewModel childModel = new ViewModel();
		childModel.copyPropertiesFrom(viewModel);
		childModel.setEntity(parentEntity);

		View childView = new View();
		childView.copyPropertiesFrom(view);
		childView.setWicketId("childPropertyList");

		item.add(app.getViewMeta(modelCode).getPanel(
				"ChildPropertyDisplayListPanel", childModel, childView));
	}

}
