package chapter06.section05;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

/**
 * @author dashorst
 */
public class SubmitComponentsPage extends WebPage {
	public SubmitComponentsPage() {
		/* Section 7.5.1 */
		Form form = new Form("form751") {
			@Override
			protected void onSubmit() {
				// called when one of the buttons is clicked, but after the
				// button�s onSubmit call
				System.out.println("Form onSubmit is called");
			}
		};
		add(form);
		form.add(new Button("button1", new Model("Pressing matters")) {
			@Override
			public void onSubmit() {
				System.out.println("Button 1�s onSubmit is called");
			}
		});
		form.add(new Button("button2") {
			@Override
			public void onSubmit() {
				System.out.println("Button 2�s onSubmit is called");
			}
		});

		/* Section 7.5.2 */
		form = new Form("form752") {
			@Override
			protected void onSubmit() {
				// called when one of the links is clicked, but after the
				// link�s onSubmit call
				System.out.println("Form onSubmit is called");
			}
		};
		add(form);
		form.add(new SubmitLink("inside") {
			@Override
			public void onSubmit() {
				System.out.println("Inside link�s onSubmit is called");
			}
		});
		add(new SubmitLink("outside", form) {
			@Override
			public void onSubmit() {
				System.out.println("Outside link�s onSubmit is called");
			}
		});

		/* Section 7.5.3 */
		final List<String> comments = new ArrayList<String>();
		final WebMarkupContainer parent = new WebMarkupContainer("comments");
		parent.setOutputMarkupId(true);
		add(parent);
		parent.add(new ListView("list", comments) {
			@Override
			protected void populateItem(ListItem item) {
				item.add(new Label("comment", item.getModel()));
			}
		});

		form = new Form("form753");
		final TextArea editor = new TextArea("editor", new Model(""));
		editor.setOutputMarkupId(true);
		form.add(editor);
		form.add(new AjaxSubmitLink("save") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				comments.add(editor.getModelObjectAsString());
				editor.setModel(new Model(""));
				target.addComponent(parent);
				target.focusComponent(editor);
			}
		});
		parent.add(form);

		/* Section 7.5.4 */
		form = new Form("form");
		add(form);
		form.add(new TextField("q", new Model(), Integer.class));// #1
		Button b = new Button("do") {
			@Override
			public void onSubmit() {
				setResponsePage(new Page2(SubmitComponentsPage.this));// #2
			}
		};
		b.setDefaultFormProcessing(false); // #3
		form.add(b);
		form.add(new FeedbackPanel("feedback"));// #4

	}
}
