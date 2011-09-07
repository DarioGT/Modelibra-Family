package sales.wicket.cheesestore.discounts;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class RequiredTextField extends TextField {

	public RequiredTextField(String id) {
		super(id);
	}

	public RequiredTextField(String id, Class type) {
		super(id, type);
		setRequired(true);
	}

	public RequiredTextField(String id, IModel model) {
		super(id, model);
		setRequired(true);
	}

	public RequiredTextField(String id, IModel model, Class type) {
		super(id, model, type);
		setRequired(true);
	}
	
}
