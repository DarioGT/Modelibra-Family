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

import java.util.List;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Base panel for Parent-Child panels. Extend this class and implement abstract
 * methods for parent and child panels. See getChildEntities() method for
 * manipulation of child entities collection.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public abstract class EntityParentChildPanel extends DmPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs the EntityParentChildPanel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityParentChildPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		// Parent
		ViewModel parentModel = new ViewModel();
		parentModel.copyPropertiesFrom(viewModel);

		View parentView = new View();
		parentView.copyPropertiesFrom(view);
		parentView.setWicketId("parentSection");

		add(getParentPanel(parentModel, parentView));

		// Child
		IEntity<?> parentEntity = viewModel.getEntity();
		IEntities<?> childEntities = getChildEntities(parentEntity);
		Panel childPanel;
		if (childEntities != null) {
			ViewModel childModel = new ViewModel();
			childModel.copyPropertiesFrom(viewModel);
			childModel.setEntities(childEntities);
			childModel.setEntity(null);

			View childView = new View();
			childView.copyPropertiesFrom(view);
			childView.setContextView(view);
			childView.setWicketId("childSection");

			childPanel = getChildPanel(childModel, childView);
		} else {
			childPanel = new EmptyPanel("childSection");
			childPanel.setVisible(false);
		}
		add(childPanel);
	}

	/**
	 * Gets panel for parent entity. Override this method to provide different
	 * panel then default. Do not set wicketId for view parameter.
	 * 
	 * @param viewModel
	 *            viewModel
	 * @param view
	 *            view
	 * @return Panel
	 */
	protected abstract Panel getParentPanel(ViewModel viewModel, View view);

	/**
	 * Gets panel for child entities. Override this method to provide different
	 * panel then default. Do not set wicketId for view parameter.
	 * 
	 * @param viewModel
	 *            viewModel
	 * @param view
	 *            view
	 * @return Panel
	 */
	protected abstract Panel getChildPanel(ViewModel viewModel, View view);

	/**
	 * Gets child entities for this parent. Derives first child neighbor if
	 * there is one. If parent has more than one child neighbor and you want to
	 * control which one is used then override this method.
	 * 
	 * @param parentEntity
	 *            parent entity - one that is set as entity in viewModel for
	 *            this panel
	 * 
	 * @return IEntities child entities
	 */
	protected IEntities<?> getChildEntities(IEntity<?> parentEntity) {
		ConceptConfig conceptConfig = parentEntity.getConceptConfig();
		List<String> childCodes = conceptConfig.getChildNeighborCodes();
		if (childCodes.size() > 0) {
			return parentEntity.getChildNeighbor(childCodes.get(0));
		}
		return null;
	}

}
