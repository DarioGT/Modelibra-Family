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
package org.modelibra.wicket.neighbor.tree;

import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.tree.LinkTree;
import org.apache.wicket.model.IModel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.wicket.concept.EntitiesNameLabelAddLinkPanel;
import org.modelibra.wicket.concept.EntityDisplayAddLinksPanel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity tree panel.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class AjaxEntityTreePanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	private String code;

	public AjaxEntityTreePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		final IEntities<?> entities = viewModel.getEntities();
		ConceptConfig conceptConfig = entities.getConceptConfig();
		code = conceptConfig.getEntitiesCodeWithFirstLetterAsLower();

		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();

		boolean hasReflexiveRelationship = false;
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getDestinationConceptConfig() == conceptConfig
					&& neighborConfig.isChild()) {
				hasReflexiveRelationship = true;
				code = neighborConfig.getCodeWithFirstLetterAsLower();
				break;
			}

		}
		if (hasReflexiveRelationship) {
			List<IEntity> entitiesList = (List<IEntity>) entities.getList();

			LinkTree tree = new LinkTree("tree",
					convertToTreeModel(entitiesList)) {

				private static final long serialVersionUID = 1L;

				@Override
				protected Component newNodeComponent(String id, IModel model) {

					View nodeView = new View();
					nodeView.setPage(getPage());
					nodeView.setWicketId(id);
					nodeView.setContextView(nodeView);
					nodeView.setRecreateContext(true);

					ViewModel nodeViewModel = new ViewModel();
					nodeViewModel.copyPropertiesFrom(viewModel);
					nodeViewModel.setEntities(entities);
					nodeViewModel.setContextViewModel(viewModel);

					DefaultMutableTreeNode node = (DefaultMutableTreeNode) model
							.getObject();
					if (node.isRoot()) {
						return getRootNodePanel(nodeViewModel, nodeView);
					} else {
						IEntity entity = (IEntity) node.getUserObject();
						nodeViewModel.setEntity(entity);
						return getNodePanel(nodeViewModel, nodeView);
					}
				}
			};
			add(tree);
			tree.getTreeState().collapseAll();
		} else {
			throw new ModelibraRuntimeException(
					"EntitiesTreePanel can be used only with entities of a concept that has reflexive relationship!");
		}
	}

	/**
	 * Converts to tree model.
	 * 
	 * @param list
	 *            category list
	 */
	private DefaultTreeModel convertToTreeModel(List<IEntity> list) {
		DefaultTreeModel model = null;
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		add(rootNode, list);
		model = new DefaultTreeModel(rootNode);
		return model;

	}

	/**
	 * Adds a child to a parent.
	 * 
	 * @param parent
	 *            parent
	 * @param child
	 *            child
	 */
	private void add(DefaultMutableTreeNode parent, List<IEntity> sub) {
		Iterator<IEntity> i = sub.iterator();
		for (IEntity entity : sub) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(entity);
			parent.add(child);
			List<IEntity> subEntitiesList = entity.getChildNeighbor(code)
					.getList();
			if (!subEntitiesList.isEmpty()) {
				add(child, subEntitiesList);
			}
		}
	}

	/**
	 * Gets panel for tree root node. Override this method to provide your own
	 * panel for root node.
	 * 
	 * @param viewModel
	 * @param view
	 * @return Panel
	 */
	protected Panel getRootNodePanel(ViewModel viewModel, View view) {
		return new EntitiesNameLabelAddLinkPanel(viewModel, view);
	}

	/**
	 * Gets panel for tree node. Override this method to provide your own panel
	 * for tree node.
	 * 
	 * @param viewModel
	 * @param view
	 * @return Panel
	 */
	protected Panel getNodePanel(ViewModel viewModel, View view) {
		return new EntityDisplayAddLinksPanel(viewModel, view);
	}

}
