package dmeduc.wicket.app.home;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.wicket.app.domain.DomainPage;
import org.modelibra.wicket.concept.CountryLanguageChoicePanel;
import org.modelibra.wicket.security.AppSession;

import dmeduc.reference.Reference;
import dmeduc.reference.countrylanguage.CountryLanguage;
import dmeduc.reference.countrylanguage.CountryLanguages;
import dmeduc.weblink.member.Member;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.app.about.AboutPage;

/**
 * HomeMenu tests.
 * 
 * @author Vedad Kirlic
 * @author Dzenan Ridjanovic
 * @version 2008-09-30
 */
public class HomeMenuTest {

	private static WicketTester tester;

	private static DmEducApp dmEducApp;

	private static AppSession appSession;

	@BeforeClass
	public static void prepareTester() {
		dmEducApp = new DmEducApp();
		tester = new WicketTester(dmEducApp);
		tester.setupRequestAndResponse();
		tester.startPage(HomePage.class);

		appSession = (AppSession) tester.getWicketSession();
		appSession.setLocale(new Locale("fr"));
	}

	@Test
	public void containSignupLink() {
		tester.assertComponent("homeMenu:signUpLink", PageLink.class);
	}

	@Test
	public void containSignoutLink() {
		if (appSession.isUserSignedIn()) {
			tester.assertComponent("homeMenu:signOutLink", Link.class);
		} else {
			tester.assertInvisible("homeMenu:signOutLink");
		}
	}

	@Test
	public void containDomainLink() {
		if (appSession.isUserSignedIn()) {
			Member signedInMember = (Member) appSession.getSignedInUser();
			if (signedInMember.hasAdminAccess()) {
				tester.assertComponent("homeMenu:domainLink", Link.class);
			} else {
				tester.assertInvisible("homeMenu:signOutLink");
			}
		} else {
			tester.assertInvisible("homeMenu:signOutLink");
		}
	}

	@Test
	public void navigateToDomainPage() {
		if (appSession.isUserSignedIn()) {
			Member signedInMember = (Member) appSession.getSignedInUser();
			if (signedInMember.hasAdminAccess()) {
				tester.clickLink("homeMenu:domainLink");
				tester.assertRenderedPage(DomainPage.class);
			} else {
				tester.assertInvisible("homeMenu:signOutLink");
			}
		} else {
			tester.assertInvisible("homeMenu:signOutLink");
		}
	}

	@Test
	public void containAboutLink() {
		tester.startPage(HomePage.class);
		tester.assertComponent("homeMenu:aboutLink", Link.class);
	}

	@Test
	public void navigateToAboutPage() {
		tester.clickLink("homeMenu:aboutLink");
		tester.assertRenderedPage(AboutPage.class);
	}

	@Test
	public void containLanguageChoice() {
		tester.startPage(HomePage.class);
		tester.assertComponent("homeMenu:countryLanguageChoice",
				CountryLanguageChoicePanel.class);
	}

	@Test
	public void defaultLanguage() {
		Reference reference = dmEducApp.getDmEduc().getReference();
		CountryLanguages countryLanguages = reference.getCountryLanguages();
		String languageCode = appSession.getLocale().getLanguage();
		CountryLanguage defaultLanguage = countryLanguages
				.getCountryLanguage(languageCode);
		String language = defaultLanguage.getLanguage();
		assertEquals("français", language);
	}

}
