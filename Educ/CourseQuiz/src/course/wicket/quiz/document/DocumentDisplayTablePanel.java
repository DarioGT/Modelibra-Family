package course.wicket.quiz.document;

import org.apache.wicket.markup.html.panel.Panel;

import course.quiz.document.Documents;

/**
 * Document display table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-16
 */
public class DocumentDisplayTablePanel extends Panel {

	private static final long serialVersionUID = 282030L;

	public DocumentDisplayTablePanel(String id, Documents documents) {
		super(id);
		add(new DocumentDisplayTableListView("documentDisplayTableListView",
				documents));
	}

}
