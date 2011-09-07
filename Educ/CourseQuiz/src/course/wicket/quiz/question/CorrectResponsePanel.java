package course.wicket.quiz.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import course.quiz.item.Item;
import course.quiz.item.Items;
import course.quiz.question.Question;

/**
 * Correct response panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-16
 */
public class CorrectResponsePanel extends Panel {

	private static final long serialVersionUID = 282040L;

	public CorrectResponsePanel(String id, Question question,
			boolean showResponse) {
		super(id);

		Label responseLabel;

		if (showResponse) {
			if (question.isShortAnswer()) {
				String response = question.getResponse();
				if (response == null) {
					response = getLocalizer().getString(
							"question.response.none", this);
				}
				responseLabel = new Label("response", response);
				add(responseLabel);
			} else if (question.isSingleChoice()) {
				Integer correctItemNumber = new Integer(0);
				Items items = question.getItems();
				Item item;
				for (Iterator<Item> x = items.iterator(); x.hasNext();) {
					item = (Item) x.next();
					if (item.isCorrect()) {
						correctItemNumber = item.getNumber();
						break;
					}
				}
				String response = getLocalizer().getString(
						"question.response.choice", this);
				responseLabel = new Label("response", response + " "
						+ correctItemNumber.toString() + ".");
				add(responseLabel);
			} else if (question.isTrueFalse()) {
				String correctItemText = "";
				Items items = question.getItems();
				Item item;
				for (Iterator<Item> x = items.iterator(); x.hasNext();) {
					item = (Item) x.next();
					if (item.isCorrect()) {
						correctItemText = item.getText();
						break;
					}
				}
				String response = getLocalizer().getString(
						"question.response.answer", this);
				responseLabel = new Label("response", response + " "
						+ correctItemText);
				add(responseLabel);
			} else if (question.isMultipleChoice()) {
				List<Integer> correctItemNumbers = new ArrayList<Integer>();
				Items items = question.getItems();
				Item item;
				for (Iterator<Item> x = items.iterator(); x.hasNext();) {
					item = (Item) x.next();
					if (item.isCorrect()) {
						correctItemNumbers.add(item.getNumber());
					}
				}
				String response = getLocalizer().getString(
						"question.response.choice", this);
				responseLabel = new Label("response", response + " "
						+ correctItemNumbers.toString() + ".");
				add(responseLabel);
			}
		} else {
			responseLabel = new Label("response", "");
			add(responseLabel);
		}
	}

}
