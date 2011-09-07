package org.ieducnews.view.concept.contribution;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.ieducnews.model.DomainModel;
import org.ieducnews.model.concept.contribution.Submissions;
import org.ieducnews.model.config.ModelProperties;
import org.ieducnews.view.HomePage;
import org.ieducnews.view.WebApp;
import org.ieducnews.view.concept.member.SignInPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddSubmissionPageTest {

	private static DomainModel domainModel;

	private static WebApp webApp;

	private static WicketTester tester;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				AddSubmissionPageTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();

		webApp = new WebApp();
		webApp.setDomainModel(domainModel);

		tester = new WicketTester(webApp);
		tester.setupRequestAndResponse();
		tester.startPage(SignInPage.class);
		FormTester formTester = tester.newFormTester("signIn");
		formTester.setValue("account", "pascald");
		formTester.setValue("password", "pd");
		formTester.submit();
	}

	@Before
	public void beforeTest() {
		tester.startPage(AddSubmissionPage.class);
	}

	@Test
	public void renderAddLinkPage() {
		tester.assertRenderedPage(AddSubmissionPage.class);
		tester.assertNoErrorMessage();
	}

	@Test
	public void containComponents() {
		tester.assertComponent("menu", Panel.class);
		tester.assertComponent("form", Form.class);
		tester.assertComponent("form:name", RequiredTextField.class);
		tester.assertComponent("form:link", RequiredTextField.class);
		tester.assertComponent("form:save", Button.class);
		tester.assertComponent("feedback", FeedbackPanel.class);
		tester.assertComponent("footer", Panel.class);
	}

	@Test
	public void nameRequiredError() {
		// given
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("link", "http://www.testlink.com");

		// submit
		formTester.submit("save");

		// required name message is displayed
		tester.assertErrorMessages(new String[] { "name is required." });
	}

	@Test
	public void linkRequiredError() {
		// given
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "This is a test name");

		// submit
		formTester.submit("save");

		// required link message is displayed
		tester.assertErrorMessages(new String[] { "link is required." });
	}

	@Test
	public void submitValidWebLink() {
		// given
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "Test Name");
		formTester.setValue("link", "http://www.testlink.com");

		// submit
		formTester.submit("save");

		// no messages
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();

		// redirection
		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void existingNameError() {
		// given
		FormTester formTester = tester.newFormTester("form");

		// previous entry
		formTester.setValue("name", "Test Name");
		formTester.setValue("link", "http://www.testlink.com");

		// submit
		formTester.submit("save");

		// unique name message is displayed
		tester
				.assertErrorMessages(new String[] { "this name exists already." });

	}

	@AfterClass
	public static void afterTests() {
		Submissions webLinks = domainModel.getSubmissions();
		if (webLinks.contains("Test Name")) {
			webLinks.remove("Test Name");
			domainModel.save();
		}
	}

}
