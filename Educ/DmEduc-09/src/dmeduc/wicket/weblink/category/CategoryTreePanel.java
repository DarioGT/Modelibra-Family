package dmeduc.wicket.weblink.category;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.tree.LinkTree;
import org.apache.wicket.model.IModel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;

/**
 * Category tree panel.
 * 
 * @author Vedad Kirlic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class CategoryTreePanel extends Panel {

	/**
	 * Constructs the Category tree panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public CategoryTreePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		final Categories categories = (Categories) viewModel.getEntities();
		List<Category> categoryList = categories.getApprovedCategories()
				.getList();

		LinkTree tree = new LinkTree("tree", convertToTreeModel(categoryList)) {
			@Override
			protected Component newNodeComponent(final String id,
					final IModel model) {
				View nodeView = new View();
				nodeView.setPage(getPage());
				nodeView.setWicketId(id);
				nodeView.setContextView(nodeView);
				// nodeView.setRecreateContext(true);

				ViewModel nodeViewModel = new ViewModel();
				nodeViewModel.copyPropertiesFrom(viewModel);
				nodeViewModel.setEntities(categories);

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) model
						.getObject();
				if (node.isRoot()) {
					return new CategoryRootPanel(nodeViewModel, nodeView);
				} else {
					Category category = (Category) node.getUserObject();
					nodeViewModel.setEntity(category);
					return new CategoryNodePanel(nodeViewModel, nodeView);
				}
			}
		};
		add(tree);
		tree.getTreeState().collapseAll();
	}

	/**
	 * Converts to tree model.
	 * 
	 * @param list
	 *            category list
	 */
	private DefaultTreeModel convertToTreeModel(final List<Category> list) {
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
	private void add(final DefaultMutableTreeNode parent,
			final List<Category> child) {
		for (Category category : child) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
					category);
			parent.add(childNode);
			List<Category> subCategoriesList = category.getCategories()
					.getApprovedCategories().getList();
			if (!subCategoriesList.isEmpty()) {
				add(childNode, subCategoriesList);
			}
		}
	}

}
