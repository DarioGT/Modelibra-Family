package dmeduc.wicket.app.home;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.wicket.concept.EntityDisplayTablePanel;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.neighbor.ParentChildPropertyDisplayListPanel;

import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.weblink.member.MemberListPanel;

/**
 * Test if root components are rendered.
 * 
 * @author Vedad Kirlic
 * @version 2008-09-30
 */
public class HomePageTest {

	private static WicketTester tester;

	@BeforeClass
	public static void prepareTester() {
		tester = new WicketTester(new DmEducApp());
		tester.startPage(HomePage.class);
	}

	@Test
	public void containHomeMenu() {
		tester.assertComponent("homeMenu", HomeMenu.class);
	}

	@Test
	public void containQuestionTextList() {
		tester.assertComponent("questionTextList",
				EntityPropertyDisplayListPanel.class);
	}

	@Test
	public void containCommentTable() {
		tester.assertComponent("commentTable", EntityDisplayTablePanel.class);
	}

	@Test
	public void containCategoryNameUrlLinkList() {
		tester.assertComponent("categoryNameUrlLinkList",
				ParentChildPropertyDisplayListPanel.class);
	}

	@Test
	public void containMemberList() {
		tester.assertComponent("memberList", MemberListPanel.class);
	}

}
