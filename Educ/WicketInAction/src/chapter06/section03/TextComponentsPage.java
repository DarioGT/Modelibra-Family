package chapter06.section03;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * @author dashorst
 */
@SuppressWarnings("unused")
public class TextComponentsPage extends WebPage {
	public TextComponentsPage() {
		/* section 7.3.1 */
		class Person {
			String name;
		}

		Person person = new Person();
		add(new TextField("name0", new PropertyModel(person, "name")));
		add(new TextField("name1", new PropertyModel(person, "name")).add(
				new SimpleAttributeModifier("maxlength", "48")).add(
				new SimpleAttributeModifier("size", "48")));

		/* section 7.3.2 */
		PasswordTextField pw = new PasswordTextField("password", new Model(""));
		add(pw);

		/* section 7.3.3 */
		class Cheese {
			String description;
		}
		Cheese cheese = new Cheese();
		add(new TextArea("description",
				new PropertyModel(cheese, "description")));
	}
}
