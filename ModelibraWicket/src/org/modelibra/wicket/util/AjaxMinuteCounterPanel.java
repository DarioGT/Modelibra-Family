package org.modelibra.wicket.util;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.util.time.Duration;
import org.modelibra.util.Transformer;

/**
 * Ajax minute counter panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-19
 */
public class AjaxMinuteCounterPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Label minuteCounter;

	/**
	 * Constructs a minute counter panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 */
	public AjaxMinuteCounterPanel(final String wicketId) {
		super(wicketId);
		minuteCounter = new Label("minuteCounter", new MinuteCounterModel());
		minuteCounter.add(new AjaxSelfUpdatingTimerBehavior(Duration
				.seconds(60)));
		add(minuteCounter);
	}

	/**
	 * A model that returns the current minute where the starting point is 0.
	 */
	private class MinuteCounterModel extends AbstractReadOnlyModel {

		private static final long serialVersionUID = 1L;

		private double startTime;

		public MinuteCounterModel() {
			startTime = System.currentTimeMillis();
		}

		public Object getObject() {
			double currentTime = System.currentTimeMillis();
			double timeIntervalInSeconds = (currentTime - startTime) / 1000.0;
			double timeIntervalInMinutes = timeIntervalInSeconds / 60.0;
			Double timeIntervalInMinutesDouble = Transformer
					.doubleDecimal(timeIntervalInMinutes);
			int timeIntervalInMinutesInt = timeIntervalInMinutesDouble
					.intValue();
			return Transformer.string(timeIntervalInMinutesInt);
		}

	}

}
