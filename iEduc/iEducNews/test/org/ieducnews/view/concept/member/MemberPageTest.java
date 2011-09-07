package org.ieducnews.view.concept.member;

import java.util.Locale;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.ieducnews.model.DomainModel;
import org.ieducnews.model.concept.member.Member;
import org.ieducnews.model.concept.member.Members;
import org.ieducnews.model.config.ModelProperties;
import org.ieducnews.view.WebApp;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MemberPageTest {

	private static DomainModel domainModel;

	private static WebApp webApp;

	private static WicketTester tester;

	private static Member member = new Member();

	@BeforeClass
	public static void beforeTests() {
		ModelProperties modelProperties = new ModelProperties(
				MemberPageTest.class);
		domainModel = new DomainModel(modelProperties);
		domainModel = domainModel.load();

		webApp = new WebApp();
		webApp.setDomainModel(domainModel);

		member.setAccount("testaccount");
		member.setPassword("testpassword");
		member.setKarma(1);
		member.setApproved(true);

	}

	@Before
	public void beforeTest() {
		tester = new WicketTester(webApp);
		tester.startPage(new MemberPage(member));
	}

	@Test
	public void renderMemberPage() {
		tester.assertRenderedPage(MemberPage.class);
		tester.assertNoErrorMessage();
	}

	@Test
	public void containComponents() {
		tester.assertComponent("menu", Panel.class);
		tester.assertComponent("form", Form.class);
		tester.assertComponent("form:account", Label.class);
		tester.assertComponent("form:karma", Label.class);
		tester.assertComponent("form:password", PasswordTextField.class);
		tester.assertComponent("form:lastName", TextField.class);
		tester.assertComponent("form:firstName", TextField.class);
		tester.assertComponent("form:email", TextField.class);
		tester.assertComponent("form:update", Button.class);
		tester.assertComponent("feedback", FeedbackPanel.class);
		tester.assertComponent("footer", Panel.class);
	}

	@Test
	public void submitValidUpdate() {
		// given
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("password", "password");
		formTester.setValue("lastName", "lastName_updated");
		formTester.setValue("firstName", "firstName_updated");
		formTester.setValue("email", "email@email.com");

		// submit
		formTester.submit("update");

		// no messages
		tester.assertNoErrorMessage();
		tester.assertNoInfoMessage();
	}

	@Test
	public void passwordRequiredError() {
		// given
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("password", null);

		// submit
		formTester.submit();

		// required password message is displayed
		tester.assertErrorMessages(new String[] { "password is required." });
	}

	@Test
	public void invalidEmailError() {
		// given
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("email", "invalid email");

		// International error string
		String error = "is not a valid Email.";
		if (Locale.getDefault().equals(Locale.CANADA_FRENCH)) {
			error = "n'est pas valide pour le type Email.";
		}

		// submit
		formTester.submit();

		// invalid email message is displayed
		tester.assertErrorMessages(new String[] { "'invalid email' " + error });
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
