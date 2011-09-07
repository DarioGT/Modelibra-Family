/**
 * 
 */
package twoadw.wicket.app.twoadw.generic.reusable;

import java.text.NumberFormat;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

public class PercentLabel extends WebComponent {

	public PercentLabel(String id) {
		super(id);
	}

	public PercentLabel(String id, IModel model) {
		super(id, model);
	}

	@Override
	protected void onComponentTagBody(MarkupStream markupStream,
			ComponentTag openTag) {
		NumberFormat fmt = NumberFormat.getPercentInstance(getLocale());
		fmt.setMaximumFractionDigits(2);
		fmt.setMinimumFractionDigits(0);
		Double number = (Double) getModelObject();
		String perc = number != null ? fmt.format(number) : "";
		replaceComponentTagBody(markupStream, openTag, perc);
	}
	
}
