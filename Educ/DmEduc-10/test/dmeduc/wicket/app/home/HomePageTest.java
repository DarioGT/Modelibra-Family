package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.wicket.concept.EntityPropertyDisplayListPanel;
import org.modelibra.wicket.neighbor.tree.AjaxEntityTreePanel;
import org.modelibra.wicket.security.SigninPanel;

import dmeduc.wicket.app.DmEducApp;

/**
 * Test if root components are rendered.
 * 
 * @author Vedad Kirlic
 * @author Dzenan Ridjanovic
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
	public void containTreeImage() {
		tester.assertComponent("treeImage", Image.class);
	}

	@Test
	public void containCategoryTree() {
		tester.assertComponent("categoryTree", AjaxEntityTreePanel.class);
	}

	@Test
	public void containSignin() {
		tester.assertComponent("signIn", SigninPanel.class);
	}

	@Test
	public void containSigninFeedback() {
		tester.assertComponent("signInFeedback", FeedbackPanel.class);
	}

}
