package dmeduc.wicket.weblink.member;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.concept.EntityDisplayMinPanel;
import org.modelibra.wicket.container.DmUpdatePage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.interest.Interests;
import dmeduc.weblink.member.Member;
import dmeduc.wicket.weblink.interest.EntityUpdateTablePanel;

/**
 * Member interests list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
@SuppressWarnings("serial")
public class MemberPage extends DmUpdatePage {

	/**
	 * Constructs the Member page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public MemberPage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		ViewModel memberPageModel = new ViewModel();
		memberPageModel.copyPropertiesFrom(viewModel);
		View memberPageView = new View();
		memberPageView.copyPropertiesFrom(view);
		memberPageView.setContextView(view);
		memberPageView.setPage(this);

		// Member
		ViewModel memberModel = new ViewModel();
		memberModel.copyPropertiesFrom(memberPageModel);
		View memberView = new View();
		memberView.copyPropertiesFrom(memberPageView);
		memberView.setWicketId("minMemberSection");
		add(new EntityDisplayMinPanel(memberModel, memberView));

		// Member Interests
		ViewModel membernterestsModel = new ViewModel();
		membernterestsModel.copyPropertiesFrom(memberPageModel);
		Member member = (Member) viewModel.getEntity();
		Interests memberInterests = member.getInterests();
		membernterestsModel.setEntities(memberInterests);
		membernterestsModel.setEntity(null);
		View memberInterestsView = new View();
		memberInterestsView.copyPropertiesFrom(memberPageView);
		memberInterestsView.setContextView(memberPageView);
		memberInterestsView.setUpdate(true);
		memberInterestsView.setWicketId("memberInterestsSection");
		add(new EntityUpdateTablePanel(membernterestsModel, memberInterestsView));
	}

	/**
	 * Constructs a link to this page.
	 * 
	 * @param linkId
	 *            link Wicket id
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public static PageLink link(final String linkId, final ViewModel viewModel,
			final View view) {
		PageLink link = new PageLink(linkId, new IPageLink() {
			public Page getPage() {
				return new MemberPage(viewModel, view);
			}

			public Class<? extends Page> getPageIdentity() {
				return MemberPage.class;
			}
		});
		return link;
	}

}
