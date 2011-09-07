package chapter06.section07;

import java.io.Serializable;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * @author dashorst
 */
public class FeedbackPage extends WebPage {
	private FeedbackPanel panel;

	public FeedbackPage() {
		/* Section 7.7.1 */
		Form form = new Form("form");
		form.add(new TextField("name").setRequired(true));
		form.add(new PasswordTextField("password").setRequired(true));
		form.add(new TextField("phonenumber").setRequired(true));
		form.add(new FeedbackPanel("feedback",
				new ContainerFeedbackMessageFilter(form)));
		add(form);

		/* Section 7.7.2 */
		class Person implements Serializable {
			private String name;

			public void save() {
				System.out.println("Saved person " + name);
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
		}

		Person person = new Person();

		form = new Form("form2", new Model(person)) {
			@Override
			protected void onSubmit() {
				Person p = (Person) getModelObject();
				try {
					p.save();

					// generate flash message
					getSession().info("Person " + p.getName() + " was saved.");

					setResponsePage(new Page2(FeedbackPage.this));
				} catch (Exception e) {
					error(p.getName() + " was not saved: " + e.getMessage());
					// do something to rollback the transaction
				}
			}
		};
		add(form);
		form.add(new TextField("name", new PropertyModel(person, "name")));
		form.add(new FeedbackPanel("feedback",
				new ContainerFeedbackMessageFilter(form)));

		panel = new FeedbackPanel("feedback");
		panel.setFilter(new ComponentFeedbackMessageFilter(panel));

		// added the messages in onBeforeRender because they will be
		// cleared once rendered. This way they keep getting added.
		add(panel);
	}

	@Override
	protected void onBeforeRender() {
		panel.info("Info level message");
		panel.warn("Warning level message");
		panel.error("Error level message");
		super.onBeforeRender();
	}
}
