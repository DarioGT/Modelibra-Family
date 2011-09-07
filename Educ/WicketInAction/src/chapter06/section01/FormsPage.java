package chapter06.section01;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 * @author dashorst
 */
public class FormsPage extends WebPage {
	public FormsPage() {
		Form form = new Form("form") {
			@Override
			protected void onSubmit() {
				System.out.println("form was submitted!");
			}
		};
		add(form);
		form.add(new TextField("field", new Model("")));
	}
}
