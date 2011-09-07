package twoadw.wicket.app.twoadw.generic.reusable;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.EmailAddressPatternValidator;

public class EmailTextField extends TextField {

	public EmailTextField(String id) {
		super(id);
	}

	public EmailTextField(String id, Class type) {
		super(id, type);
		setRequired(true);
	}

	public EmailTextField(String id, IModel model) {
		super(id, model);
		setRequired(true);
	}

	public EmailTextField(String id, IModel model, Class type) {
		super(id, model, type);
		add(EmailAddressPatternValidator.getInstance());
	}
	
}
