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
package course.quiz.item;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.quiz.question.Question;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Item generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenItem extends Entity<Item> {

	private static final long serialVersionUID = 1176743181618L;

	private static Log log = LogFactory.getLog(GenItem.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer number;

	private String text;

	private Boolean correct;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Question question;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs item within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenItem(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs item within its parent(s).
	 * 
	 * @param Question
	 *            question
	 */
	public GenItem(Question question) {
		this(question.getModel());
		// parents
		setQuestion(question);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets number.
	 * 
	 * @param number
	 *            number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets number.
	 * 
	 * @return number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Sets text.
	 * 
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets text.
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets correct.
	 * 
	 * @param correct
	 *            correct
	 */
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	/**
	 * Gets correct.
	 * 
	 * @return correct
	 */
	public Boolean getCorrect() {
		return correct;
	}

	/**
	 * Sets correct.
	 * 
	 * @param correct
	 *            correct
	 */
	public void setCorrect(boolean correct) {
		setCorrect(new Boolean(correct));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isCorrect() {
		return getCorrect().booleanValue();
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets question.
	 * 
	 * @param question
	 *            question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Gets question.
	 * 
	 * @return question
	 */
	public Question getQuestion() {
		return question;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}