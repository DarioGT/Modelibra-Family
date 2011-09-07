/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package course.quiz.question;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import course.quiz.item.Item;
import course.quiz.item.Items;
import course.quiz.member.Member;
import course.quiz.test.Test;

/**
 * Question specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-26
 */
public class Question extends GenQuestion {

	private static final long serialVersionUID = 1176743111752L;

	private static final String SHORT_ANSWER = "short answer";

	private static final String SINGLE_CHOICE = "single choice";

	private static final String TRUE_FALSE = "true or false";

	private static final String MULTIPLE_CHOICE = "multiple choice";

	private static Log log = LogFactory.getLog(Question.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs question within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Question(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs question within its parent(s).
	 * 
	 * @param Test
	 *            test
	 * @param Member
	 *            member
	 */
	public Question(Test test, Member member) {
		super(test, member);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public boolean isShortAnswer() {
		boolean shortAnswer = false;
		if (getType().equals(SHORT_ANSWER)) {
			shortAnswer = true;
		}
		return shortAnswer;
	}

	public boolean isSingleChoice() {
		boolean singleChoice = false;
		if (getType().equals(SINGLE_CHOICE)) {
			singleChoice = true;
		}
		return singleChoice;
	}

	public boolean isTrueFalse() {
		boolean trueFalse = false;
		if (getType().equals(TRUE_FALSE)) {
			trueFalse = true;
		}
		return trueFalse;
	}

	public boolean isMultipleChoice() {
		boolean multipleChoice = false;
		if (getType().equals(MULTIPLE_CHOICE)) {
			multipleChoice = true;
		}
		return multipleChoice;
	}

	public List<String> getItemNumbers() {
		List<String> itemNumbers = new ArrayList<String>();
		Items items = getItems();
		for (Item item : items) {
			itemNumbers.add(item.getNumber().toString());
		}
		return itemNumbers;
	}

}