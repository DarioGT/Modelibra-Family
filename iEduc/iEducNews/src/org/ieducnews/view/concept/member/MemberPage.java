package org.ieducnews.view.concept.member;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.convert.IConverter;
import org.ieducnews.model.concept.member.Member;
import org.ieducnews.model.type.Email;
import org.ieducnews.view.BasePage;
import org.ieducnews.view.HomePage;
import org.ieducnews.view.type.EmailConverter;

public class MemberPage extends BasePage {

	public MemberPage(final Member member) {
		Form<Member> form = new Form<Member>("form",
				new CompoundPropertyModel<Member>(member));
		form.add(new Label("account"));
		form.add(new PasswordTextField("password").setResetPassword(false));
		form.add(new TextField<String>("lastName"));
		form.add(new TextField<String>("firstName"));
		form.add(new EmailField());
		form.add(new Label("karma"));
		form.add(new UpdateButton());
		add(form);
		add(new FeedbackPanel("feedback"));
	}

	private class EmailField extends TextField<Email> {

		private static final long serialVersionUID = 1;

		private EmailField() {
			super("email", Email.class);
		}

		public IConverter getConverter(final Class<?> type) {
			return new EmailConverter();
		}
	}

	private class UpdateButton extends Button {

		private static final long serialVersionUID = 1;

		private UpdateButton() {
			super("update");
		}

		@Override
		public void onSubmit() {
			getWebApp().getDomainModel().save();
			setResponsePage(HomePage.class);
		}
	}

}