package course.wicket.quiz.test;

import org.apache.wicket.markup.html.panel.Panel;

import course.quiz.test.Tests;

/**
 * Test display table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class TestDisplayTablePanel extends Panel {

	private static final long serialVersionUID = 282180L;

	public TestDisplayTablePanel(String id, Tests tests) {
		super(id);
		add(new TestDisplayTableListView("testDisplayTableListView", tests));
	}

}
