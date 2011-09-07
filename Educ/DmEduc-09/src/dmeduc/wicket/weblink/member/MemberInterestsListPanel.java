package dmeduc.wicket.weblink.member;

import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Member interests list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
@SuppressWarnings("serial")
public class MemberInterestsListPanel extends DmPanel {

	public MemberInterestsListPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		ViewModel memberInterestsListModel = new ViewModel();
		memberInterestsListModel.copyPropertiesFrom(viewModel);

		View memberInterestsListView = new View();
		memberInterestsListView.copyPropertiesFrom(view);
		memberInterestsListView.setWicketId("memberInterestsList");

		MemberInterestsList memberList = new MemberInterestsList(
				memberInterestsListModel, memberInterestsListView);
		add(memberList);
	}

}
