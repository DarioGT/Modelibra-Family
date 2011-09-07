package dmeduc.wicket.app.home;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.wicket.app.domain.DomainPage;

import dmeduc.wicket.app.DmEducApp;

/**
 * HomeMenu tests.
 * 
 * @author Vedad Kirlic
 * @version 2008-09-30
 */
public class HomeMenuTest {

	private static WicketTester tester;

	@BeforeClass
	public static void prepareTester() {
		tester = new WicketTester(new DmEducApp());
		tester.startPage(HomePage.class);
	}

	@Test
	public void containDomainLink() {
		tester.assertComponent("homeMenu:domainLink", Link.class);
	}

	@Test
	public void navigateToDomainPage() {
		tester.clickLink("homeMenu:domainLink");
		tester.assertRenderedPage(DomainPage.class);
	}

}
