package dmeduc.wicket.weblink.member;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.interest.Interests;
import dmeduc.weblink.member.Member;
import dmeduc.wicket.weblink.interest.InterestListPanel;

/**
 * Member interests list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
@SuppressWarnings("serial")
public class MemberInterestsList extends DmListView {

	private ViewModel viewModel;

	private View view;

	public MemberInterestsList(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
	}

	protected void populateItem(final ListItem item) {
		Member member = (Member) item.getModelObject();
		String lastName = member.getLastName().toUpperCase();
		String firstName = member.getFirstName();
		String memberName = lastName + ", " + firstName;
		Label memberNameLabel = new Label("memberName", memberName);
		item.add(memberNameLabel);

		ViewModel interestModel = new ViewModel();
		interestModel.copyPropertiesFrom(viewModel);
		Interests interests = member.getInterests();
		Interests orderedInterests = interests
				.getInterestsOrderedByCategoryOid(true);
		interestModel.setEntities(orderedInterests);

		View interestView = new View();
		interestView.copyPropertiesFrom(view);
		interestView.setWicketId("interestListPanel");

		InterestListPanel interestListPanel = new InterestListPanel(
				interestModel, interestView);
		item.add(interestListPanel);
	}

}
