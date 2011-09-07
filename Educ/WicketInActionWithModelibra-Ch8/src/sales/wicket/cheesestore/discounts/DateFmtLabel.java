/**
 * 
 */
package sales.wicket.cheesestore.discounts;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

public class DateFmtLabel extends Label {

	public DateFmtLabel(String id) {
		super(id);
	}

	public DateFmtLabel(String id, IModel model) {
		super(id, model);
	}

	@Override
	public final IConverter getConverter(Class type) {
		return new StyleDateConverter("M-", true);
	}
	
}
