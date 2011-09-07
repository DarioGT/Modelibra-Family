package twoadw.wicket.app.twoadw.generic.reusable;

import java.util.Calendar;
import java.util.Date;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.NumberValidator;

public class DateTimeField extends FormComponentPanel {

	private Date date;

	private final DateTextField dateField;

	private Integer hours;

	private final TextField hoursField;

	private Integer minutes;

	private final TextField minutesField;

	public DateTimeField(String id) {
		this(id, null);
	}

	public DateTimeField(String id, IModel model) {
		super(id, model);
		setType(Date.class);
		PropertyModel dateFieldModel = new PropertyModel(this, "date");
		add(dateField = newDateTextField("date", dateFieldModel));
		dateField.add(new DatePicker());
		add(hoursField = new TextField("hours",
				new PropertyModel(this, "hours"), Integer.class));
		hoursField.add(NumberValidator.range(0, 24));
		hoursField.setLabel(new Model("hours"));
		add(minutesField = new TextField("minutes", new PropertyModel(this,
				"minutes"), Integer.class));
		minutesField.add(NumberValidator.range(0, 59));
		minutesField.setLabel(new Model("minutes"));
	}

	@Override
	public String getInput() {
		return dateField.getInput() + ", " + hoursField.getInput() + ":"
				+ minutesField.getInput();
	}

	@Override
	protected void convertInput() {
		Date date = (Date) dateField.getConvertedInput();
		if (date != null) {
			Calendar calendar = Calendar.getInstance(getLocale());
			calendar.setTime(date);
			Integer hours = (Integer) hoursField.getConvertedInput();
			Integer minutes = (Integer) minutesField.getConvertedInput();
			if (hours != null) {
				calendar.set(Calendar.HOUR_OF_DAY, hours % 12);
				calendar.set(Calendar.MINUTE, (minutes != null) ? minutes : 0);
			}
			setConvertedInput(calendar.getTime());
		} else {
			setConvertedInput(null);
		}
	}

	protected DateTextField newDateTextField(String id,
			PropertyModel dateFieldModel) {
		return new DateTextField(id, dateFieldModel);
	}

	@Override
	protected void onBeforeRender() {
		date = (Date) getModelObject();
		if (date != null) {
			Calendar calendar = Calendar.getInstance(getLocale());
			calendar.setTime(date);
			hours = calendar.get(Calendar.HOUR_OF_DAY);
			minutes = calendar.get(Calendar.MINUTE);
		}
		dateField.setRequired(isRequired());
		super.onBeforeRender();
	}
	
}
