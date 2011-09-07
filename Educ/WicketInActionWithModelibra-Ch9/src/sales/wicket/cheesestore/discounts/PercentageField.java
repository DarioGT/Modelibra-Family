package sales.wicket.cheesestore.discounts;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

public class PercentageField extends TextField {

	public PercentageField(String id) {
		super(id, double.class);
	}

	public PercentageField(String id, IModel model) {
		super(id, model, double.class);
	}

	@Override
	public final IConverter getConverter(Class type) {
		return new PercentageConverter();
	}
	
}
