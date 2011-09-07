package dmeduc.wicket.weblink.member;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.weblink.interest.InterestListPanel;

/**
 * MembeInterestsrListPanel tests.
 * 
 * @author Vedad Kirlic
 * @version 2008-09-30
 */
@SuppressWarnings("serial")
public class MemberInterestsListPanelTest {

	private static WicketTester tester;

	private static Members orderedMembers;

	@BeforeClass
	public static void prepareTester() {
		DmEducApp dmEducApp = new DmEducApp();
		final WebLink webLink = dmEducApp.getDmEduc().getWebLink();
		orderedMembers = webLink.getMembers().getMembersOrderedByLastFirstName(
				true);

		tester = new WicketTester(dmEducApp);
		tester.startPanel(new TestPanelSource() {
			@Override
			public Panel getTestPanel(String panelId) {
				ViewModel membersModel = new ViewModel();
				membersModel.setModel(webLink);
				membersModel.setEntities(orderedMembers);

				View membersView = new View();
				membersView.setWicketId(panelId);

				return new MemberInterestsListPanel(membersModel, membersView);
			}
		});
	}

	@Test
	public void containMemberInterestsList() {
		tester.assertComponent("panel:memberInterestsList",
				MemberInterestsList.class);
	}

	@Test
	public void useMemberListModel() {
		List<Member> expectedList = orderedMembers.getList();
		tester.assertListView("panel:memberInterestsList", expectedList);
	}

	@Test
	public void containMemberNameComponent() {
		tester.assertComponent("panel:memberInterestsList:0:memberName",
				Label.class);
	}

	@Test
	public void containMemberNameLabel() {
		Member member = orderedMembers.first();
		String lastName = member.getLastName().toUpperCase();
		String firstName = member.getFirstName();
		String name = lastName + ", " + firstName;

		tester.assertLabel("panel:memberInterestsList:0:memberName", name);
	}

	@Test
	public void containInterestList() {
		tester.assertComponent("panel:memberInterestsList:0:interestListPanel",
				InterestListPanel.class);
	}

}
