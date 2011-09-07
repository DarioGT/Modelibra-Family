package org.modelibra.wicket.util;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.util.time.Duration;
import org.modelibra.util.Transformer;

/**
 * Ajax second counter panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-19
 */
public class AjaxSecondCounterPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private int upperLimit;

	private Label secondCounter;

	/**
	 * Constructs a second counter panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 */
	public AjaxSecondCounterPanel(final String wicketId, final int upperLimit) {
		super(wicketId);
		this.upperLimit = upperLimit;
		secondCounter = new Label("secondCounter",
				new MinuteSecondCounterModel());
		secondCounter
				.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(1)));
		add(secondCounter);
	}

	/**
	 * A model that returns the number of seconds that starts with 0 and goes
	 * back to 0 when the upper limit is reached.
	 */
	private class MinuteSecondCounterModel extends AbstractReadOnlyModel {

		private static final long serialVersionUID = 1L;

		private int second;

		public MinuteSecondCounterModel() {
			second = 0;
		}

		public Object getObject() {
			second++;
			if (second == upperLimit) {
				second = 0;
			}
			return Transformer.string(second);
		}

	}

}
