package org.ieducnews.view.concept.contribution;

import java.net.URL;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.convert.IConverter;
import org.ieducnews.model.concept.contribution.Submissions;
import org.ieducnews.model.concept.contribution.WebLink;
import org.ieducnews.view.BasePage;
import org.ieducnews.view.HomePage;
import org.ieducnews.view.component.SecuredWebPage;
import org.ieducnews.view.type.UrlConverter;

public class AddSubmissionPage extends SecuredWebPage {

	public AddSubmissionPage() {
		WebLink webLink = new WebLink(getMember());
		Form<WebLink> form = new Form<WebLink>("form",
				new CompoundPropertyModel<WebLink>(webLink));
		form.add(new RequiredTextField<String>("name"));
		form.add(new LinkField());
		form.add(new SaveButton(webLink));
		add(form);
		add(new FeedbackPanel("feedback"));
	}

	private class LinkField extends RequiredTextField<URL> {

		private static final long serialVersionUID = 1;

		private LinkField() {
			super("link", URL.class);
		}

		public IConverter getConverter(final Class<?> type) {
			return new UrlConverter();
		}
	}

	private class SaveButton extends Button {

		private static final long serialVersionUID = 1;

		private WebLink webLink;

		private SaveButton(WebLink webLink) {
			super("save");
			this.webLink = webLink;
		}

		@Override
		public void onSubmit() {
			Submissions submissions = getWebApp().getDomainModel()
					.getSubmissions();
			if (submissions.contains(webLink.getName())) {
				error("this name exists already.");
			} else {
				submissions.add(webLink);
				getWebApp().getDomainModel().save();
				setResponsePage(new HomePage());
			}
		}
	}

}