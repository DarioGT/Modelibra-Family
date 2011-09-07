package course.wicket.quiz.question;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.modelibra.util.TextHandler;

import course.quiz.item.Item;
import course.quiz.question.Question;

/**
 * User answer panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-08-27
 */
public class UserAnswerPanel extends Panel {

	private static final long serialVersionUID = 282160L;

	private Question question;

	private TextArea shortAnswer;

	private RadioGroup singleChoice;

	private CheckGroup multipleChoice;

	public UserAnswerPanel(String id, final Question question, String answer) {
		super(id);
		this.question = question;
		if (answer == null) {
			answer = "";
		}

		shortAnswer = new TextArea("shortAnswer", new Model(answer));
		add(shortAnswer);

		singleChoice = new RadioGroup("singleChoice", new Model(answer));
		add(singleChoice);
		ListView singleChoiceItems = new ListView("singleChoiceItems", question
				.getItemNumbers()) {
			static final long serialVersionUID = 282161L;

			protected void populateItem(ListItem item) {
				String questionNumberString = (String) item.getModelObject();
				Integer questionNumber = new Integer(questionNumberString);
				item.add(new Radio("radio", item.getModel()));
				item.add(new Label("questionItemNumber", questionNumberString));
				Item questionItem = question.getItems().getNumberItem(
						questionNumber);
				item.add(new Label("questionItemText", questionItem.getText()));
			};
		};
		singleChoice.add(singleChoiceItems);

		List<String> multipleChoiceNumbers = new ArrayList<String>();
		if (question.isMultipleChoice()) {
			if (!answer.equals("")) {
				TextHandler textHandler = new TextHandler();
				multipleChoiceNumbers = textHandler.extractSeparatedSubtrings(
						answer, ",");
			}
		}
		multipleChoice = new CheckGroup("multipleChoice", multipleChoiceNumbers);
		add(multipleChoice);
		ListView multipleChoiceItems = new ListView("multipleChoiceItems",
				question.getItemNumbers()) {
			static final long serialVersionUID = 282162L;

			protected void populateItem(ListItem item) {
				String questionNumberString = (String) item.getModelObject();
				Integer questionNumber = new Integer(questionNumberString);
				item.add(new Check("check", item.getModel()));
				item.add(new Label("questionItemNumber", questionNumberString));
				Item questionItem = question.getItems().getNumberItem(
						questionNumber);
				item.add(new Label("questionItemText", questionItem.getText()));
			};
		};
		multipleChoice.add(multipleChoiceItems);

		if (question.isShortAnswer()) {
			singleChoice.setVisible(false);
			multipleChoice.setVisible(false);
		} else if (question.isSingleChoice() || question.isTrueFalse()) {
			shortAnswer.setVisible(false);
			multipleChoice.setVisible(false);
		} else if (question.isMultipleChoice()) {
			shortAnswer.setVisible(false);
			singleChoice.setVisible(false);
		}
	}

	public String getAnswer() {
		String answer = "";
		if (question.isShortAnswer()) {
			answer = shortAnswer.getModelObjectAsString();
		} else if (question.isSingleChoice() || question.isTrueFalse()) {
			answer = singleChoice.getModelObjectAsString();
		} else if (question.isMultipleChoice()) {
			answer = multipleChoice.getModelObjectAsString();
		}
		return answer;
	}

}
