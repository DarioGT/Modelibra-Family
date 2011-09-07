package org.ieducnews.view.concept.member;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.ieducnews.model.DomainModel;
import org.ieducnews.model.concept.member.Members;
import org.ieducnews.model.config.ModelProperties;
import org.ieducnews.view.HomePage;
import org.ieducnews.view.WebApp;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SignInPageTest {

	private static DomainModel domainModel;

	private static WebApp webApp;

	private static WicketTester tester;

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				SignInPageTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();

		webApp = new WebApp();
		webApp.setDomainModel(domainModel);

	}

	@Before
	public void beforeTest() {
		tester = new WicketTester(webApp);
		tester.startPage(SignInPage.class);
	}

	@Test
	public void renderSignInPage() {
		tester.assertRenderedPage(SignInPage.class);
		tester.assertNoErrorMessage();
	}

	@Test
	public void containComponents() {
		tester.assertComponent("menu", Panel.class);
		tester.assertComponent("signIn", Form.class);
		tester.assertComponent("signIn:account", RequiredTextField.class);
		tester.assertComponent("signIn:password", PasswordTextField.class);
		tester.assertComponent("signUp", Form.class);
		tester.assertComponent("signUp:account", RequiredTextField.class);
		tester.assertComponent("signUp:password", PasswordTextField.class);
		tester.assertComponent("feedback", FeedbackPanel.class);
		tester.assertComponent("footer", Panel.class);
	}

	@Test
	public void submitValidSignUp() {
		// given
		FormTester formTester = tester.newFormTester("signUp");
		formTester.setValue("account", "testaccount");
		formTester.setValue("password", "testpassword");

		// submit
		formTester.submit();

		// no messages
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();

		// redirection
		tester.assertRenderedPage(MemberPage.class);
	}

	@Test
	public void existingAccountError() {
		// given
		FormTester formTester = tester.newFormTester("signUp");
		formTester.setValue("account", "testaccount");
		formTester.setValue("password", "testpassword");

		// submit
		formTester.submit();

		// existing account message is displayed
		tester
				.assertErrorMessages(new String[] { "This account exists already. Please choose another name." });
	}

	@Test
	public void signUpAccountRequiredError() {
		// given
		FormTester formTester = tester.newFormTester("signUp");
		formTester.setValue("password", "passwordTest");

		// submit
		formTester.submit();

		// required account message is displayed
		tester.assertErrorMessages(new String[] { "account is required." });
	}

	@Test
	public void signUpPasswordRequiredError() {
		// given
		FormTester formTester = tester.newFormTester("signUp");
		formTester.setValue("account", "accountTest");

		// submit
		formTester.submit();

		// required password message is displayed
		tester.assertErrorMessages(new String[] { "password is required." });
	}

	@Test
	public void signInAccountRequiredError() {
		// given
		FormTester formTester = tester.newFormTester("signIn");
		formTester.setValue("password", "passwordTest");

		// submit
		formTester.submit();

		// required account message is displayed
		tester.assertErrorMessages(new String[] { "account is required." });
	}

	@Test
	public void signInPasswordRequiredError() {
		// given
		FormTester formTester = tester.newFormTester("signIn");
		formTester.setValue("account", "accountTest");

		// submit
		formTester.submit();

		// required password message is displayed
		tester.assertErrorMessages(new String[] { "password is required." });
	}

	@Test
	public void unknownUsernameError() {
		// given
		FormTester formTester = tester.newFormTester("signIn");
		formTester.setValue("account", "!!!....phonyUsername...!!!");
		formTester.setValue("password", "testpassword");

		// submit
		formTester.submit();

		// required password message is displayed
		tester
				.assertErrorMessages(new String[] { "Unknown username/password" });
	}

	@Test
	public void unknownPasswordError() {
		// given
		FormTester formTester = tester.newFormTester("signIn");
		formTester.setValue("account", "testaccount");
		formTester.setValue("password", "!!!!---PhonyPassword----!!!");

		// submit
		formTester.submit();

		// required password message is displayed
		tester
				.assertErrorMessages(new String[] { "Unknown username/password" });
	}

	@Test
	public void submitValidSignIn() {
		// given
		FormTester formTester = tester.newFormTester("signIn");
		formTester.setValue("account", "testaccount");
		formTester.setValue("password", "testpassword");

		// submit
		formTester.submit();

		// no messages
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();

		// redirection
		tester.assertRenderedPage(HomePage.class);
	}

	@AfterClass
	public static void afterTests() {
		Members members = domainModel.getMembers();
		if (members.contains("testaccount")) {
			members.remove("testaccount");
			domainModel.save();
		}
	}

}
