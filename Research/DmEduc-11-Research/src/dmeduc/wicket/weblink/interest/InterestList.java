package dmeduc.wicket.weblink.interest;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.LabelPanel;

import dmeduc.weblink.category.Category;
import dmeduc.weblink.interest.Interest;

/**
 * Interest list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class InterestList extends DmListView {

	private ViewModel viewModel;

	private View view;

	public InterestList(final ViewModel modelContext, final View viewContext) {
		super(modelContext, viewContext);
		this.viewModel = modelContext;
		this.view = viewContext;
	}

	protected void populateItem(final ListItem item) {
		Interest interest = (Interest) item.getModelObject();

		Category category = interest.getCategory();
		String title = category.getName().toUpperCase();
		Label categoryNameLabel = new Label("categoryName", title);
		item.add(categoryNameLabel);

		ViewModel interestModel = new ViewModel();
		interestModel.copyPropertiesFrom(viewModel);
		interestModel.setEntity(interest);
		interestModel.setPropertyCode("description");

		View interestView = new View();
		interestView.copyPropertiesFrom(view);
		interestView.setWicketId("interestDescription");

		item.add(new LabelPanel(interestModel, interestView));
	}

}
