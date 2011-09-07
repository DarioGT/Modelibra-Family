package chapter06.section06;

import java.util.Map;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.NumberValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.apache.wicket.validation.validator.UrlValidator;

/**
 * @author dashorst
 */
public class ValidationsPage extends WebPage {
	public ValidationsPage() {
		Form form = new Form("form", new CompoundPropertyModel(new ValueMap()));
		add(form);
		form.add(new FeedbackPanel("feedback"));

		/* Section 7.6.1 */
		form.add(new TextField("age1").setRequired(true).setLabel(
				new Model("age")));

		/* Section 7.6.2 */
		form.add(new TextField("age2", new Model(), Integer.class));

		/* Section 7.6.3 */

		form.add(new TextField("age3").add(NumberValidator.minimum(18)));
		form.add(new TextField("handicap").add(NumberValidator.range(0, 3.5)));
		form.add(new TextField("duration").add(NumberValidator.POSITIVE));

		form.add(new TextField("userid").add(StringValidator.lengthBetween(8,
				12)));
		form.add(new TextField("comment").add(StringValidator
				.maximumLength(4000)));

		form.add(new TextField("phone").add(new PatternValidator(
				"^[2-9]\\d{2}-\\d{3}-\\d{4}$")));

		form.add(new TextField("email")
				.add(EmailAddressValidator.getInstance()));
		form.add(new TextField("url").add(new UrlValidator(
				new String[] { "http" })));

		PasswordTextField field1 = new PasswordTextField("password");
		field1.setResetPassword(false);
		form.add(field1);

		PasswordTextField field2 = new PasswordTextField("controlPassword");
		field2.setModel(field1.getModel());
		field2.setResetPassword(false);
		form.add(field2);

		form.add(new EqualPasswordInputValidator(field1, field2));

		/* Section 7.6.4 */
		class DivisibleValidator extends AbstractValidator {
			private final long n;

			public DivisibleValidator(long n) {
				if (n == 0)
					throw new IllegalArgumentException("n can�t be 0");
				this.n = n;
			}

			@Override
			protected void onValidate(IValidatable validatable) {
				Number value = (Number) validatable.getValue();
				// determine whether the input is divisible by n
				if (value.longValue() % n != 0) {
					error(validatable);
				}
			}

			@Override
			protected String resourceKey() {
				return "DivisibleValidator";
			}

			@Override
			protected Map variablesMap(IValidatable validatable) {
				Map map = super.variablesMap(validatable);
				// add keys and values for interpolation in error message
				map.put("divisor", n);
				return map;
			}
		}
		form.add(new TextField("divisible", Integer.class).add(
				new DivisibleValidator(2)).setLabel(new Model("Even number")));
	}
}
