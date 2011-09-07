package dmeduc.wicket.weblink.interest;

import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Interest list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
@SuppressWarnings("serial")
public class InterestListPanel extends DmPanel {

	public InterestListPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		ViewModel interestListModel = new ViewModel();
		interestListModel.copyPropertiesFrom(viewModel);

		View interestListView = new View();
		interestListView.copyPropertiesFrom(view);
		interestListView.setWicketId("interestList");

		InterestList interestList = new InterestList(interestListModel,
				interestListView);
		add(interestList);
	}
}
