package chapter01;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * @author dashorst
 */
public class Index extends WebPage {
	/** counter of clicks from section 1.3.2 */
	private int counter;

	private int ajaxCounter;

	private Label label;

	public Index() {
		/* Section 1.3.1 */
		add(new Label("message", "Hello, World!"));

		/* Section 1.3.2 */
		add(new Link("link") {

			@Override
			public void onClick() {
				counter++;
			}
		});

		add(new Label("label", new PropertyModel(this, "counter")));
		add(new AjaxFallbackLink("ajaxlink") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				ajaxCounter++;
				if (target != null) {
					target.addComponent(label);
				}
			}
		});

		add(label = new Label("ajaxlabel", new PropertyModel(this,
				"ajaxCounter")));
		label.setOutputMarkupId(true);

		/* Section 1.3.3 */
		Form form = new Form("form");
		final TextField field = new TextField("field", new Model(""));
		final Label label = new Label("echo", new Model(""));
		form.add(field);
		form.add(new Button("button") {
			@Override
			public void onSubmit() {
				String value = (String) field.getModelObject();
				label.setModelObject(value);
				field.setModelObject("");
			}
		});
		add(form);
		add(label);

	}
}
