package dmeduc.wicket.weblink.member;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;
import dmeduc.wicket.app.DmEducApp;

/**
 * MemberListPanel tests.
 * 
 * @author Vedad Kirlic
 * @version 2008-09-30
 */
@SuppressWarnings("serial")
public class MemberListPanelTest {

	private static WicketTester tester;

	private static Members orderedMembers;

	@BeforeClass
	public static void prepareTester() {
		DmEducApp dmEducApp = new DmEducApp();
		orderedMembers = dmEducApp.getDmEduc().getWebLink().getMembers()
				.getMembersOrderedByLastFirstName(true);

		tester = new WicketTester(dmEducApp);
		tester.startPanel(new TestPanelSource() {
			@Override
			public Panel getTestPanel(String panelId) {
				return new MemberListPanel(panelId, orderedMembers.getList());
			}
		});
	}

	@Test
	public void useMemberListModel() {
		List<Member> expectedList = orderedMembers.getList();
		tester.assertListView("panel:memberList", expectedList);
	}

	@Test
	public void containMemberNameComponent() {
		tester.assertComponent("panel:memberList:0:memberName", Label.class);
	}

	@Test
	public void containMemberNameLabel() {
		Member member = orderedMembers.first();
		String lastName = member.getLastName().toUpperCase();
		String firstName = member.getFirstName();
		String name = lastName + ", " + firstName;

		tester.assertLabel("panel:memberList:0:memberName", name);
	}

}
