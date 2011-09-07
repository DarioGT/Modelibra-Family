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
package dmeduc.weblink.question;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import dmeduc.weblink.category.Category;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Question generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public abstract class GenQuestions extends Entities<Question> {

	private static final long serialVersionUID = 1171896744339L;

	private static Log log = LogFactory.getLog(GenQuestions.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Category category;

	/* ======= base constructor ======= */

	/**
	 * Constructs questions within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQuestions(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs questions for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public GenQuestions(Category category) {
		this(category.getModel());
		// parent
		setCategory(category);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the question with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return question
	 */
	public Question getQuestion(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the question with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return question
	 */
	public Question getQuestion(Long oidUniqueNumber) {
		return getQuestion(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first question whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return question
	 */
	public Question getQuestion(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects questions whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return questions
	 */
	public Questions getQuestions(String propertyCode, Object property) {
		return (Questions) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets questions ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered questions
	 */
	public Questions getQuestions(String propertyCode, boolean ascending) {
		return (Questions) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets questions selected by a selector. Returns empty questions if there
	 * are no questions that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected questions
	 */
	public Questions getQuestions(ISelector selector) {
		return (Questions) selectBySelector(selector);
	}

	/**
	 * Gets questions ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered questions
	 */
	public Questions getQuestions(Comparator comparator, boolean ascending) {
		return (Questions) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets text questions.
	 * 
	 * @param text
	 *            text
	 * @return text questions
	 */
	public Questions getTextQuestions(String text) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineEqual(text);
		return getQuestions(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets number question.
	 * 
	 * @param number
	 *            number
	 * @return number question
	 */
	public Question getNumberQuestion(Integer number) {
		PropertySelector propertySelector = new PropertySelector("number");
		propertySelector.defineEqual(number);
		List<Question> list = getQuestions(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets category questions.
	 * 
	 * @param category
	 *            category oid unique number
	 * @return category questions
	 */
	public Questions getCategoryQuestions(Long category) {
		PropertySelector propertySelector = new PropertySelector("categoryOid");
		propertySelector.defineEqual(category);
		return getQuestions(propertySelector);
	}

	/**
	 * Gets category questions.
	 * 
	 * @param category
	 *            category oid
	 * @return category questions
	 */
	public Questions getCategoryQuestions(Oid category) {
		return getCategoryQuestions(category.getUniqueNumber());
	}

	/**
	 * Gets category questions.
	 * 
	 * @param category
	 *            category
	 * @return category questions
	 */
	public Questions getCategoryQuestions(Category category) {
		return getCategoryQuestions(category.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets questions ordered by number.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered questions
	 */
	public Questions getQuestionsOrderedByNumber(boolean ascending) {
		return getQuestions("number", ascending);
	}

	/**
	 * Gets questions ordered by text.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered questions
	 */
	public Questions getQuestionsOrderedByText(boolean ascending) {
		return getQuestions("text", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Question question) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(question)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Category category = getCategory();
				if (category == null) {
					Category questionCategory = question.getCategory();
					if (questionCategory != null) {
						if (!questionCategory.getQuestions().contain(question)) {
							questionCategory.getQuestions()
									.setPropagateToSource(false);
							post = questionCategory.getQuestions()
									.add(question);
							questionCategory.getQuestions()
									.setPropagateToSource(true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	protected boolean postRemove(Question question) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(question)) {
			Category category = getCategory();
			if (category == null) {
				Category questionCategory = question.getCategory();
				if (questionCategory != null) {
					if (questionCategory.getQuestions().contain(question)) {
						questionCategory.getQuestions().setPropagateToSource(
								false);
						post = questionCategory.getQuestions().remove(question);
						questionCategory.getQuestions().setPropagateToSource(
								true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	protected boolean postUpdate(Question beforeQuestion, Question afterQuestion) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeQuestion, afterQuestion)) {
			Category category = getCategory();
			if (category == null) {
				Category beforeQuestionCategory = beforeQuestion.getCategory();
				Category afterQuestionCategory = afterQuestion.getCategory();
				if (beforeQuestionCategory == null
						&& afterQuestionCategory != null) {
					// attach
					if (!afterQuestionCategory.getQuestions().contain(
							afterQuestion)) {
						afterQuestionCategory.getQuestions()
								.setPropagateToSource(false);
						post = afterQuestionCategory.getQuestions().add(
								afterQuestion);
						afterQuestionCategory.getQuestions()
								.setPropagateToSource(true);
					}
				} else if (beforeQuestionCategory != null
						&& afterQuestionCategory == null) {
					// detach
					if (beforeQuestionCategory.getQuestions().contain(
							beforeQuestion)) {
						beforeQuestionCategory.getQuestions()
								.setPropagateToSource(false);
						post = beforeQuestionCategory.getQuestions().remove(
								beforeQuestion);
						beforeQuestionCategory.getQuestions()
								.setPropagateToSource(true);
					}
				} else if (beforeQuestionCategory != null
						&& afterQuestionCategory != null
						&& beforeQuestionCategory != afterQuestionCategory) {
					// detach
					if (beforeQuestionCategory.getQuestions().contain(
							beforeQuestion)) {
						beforeQuestionCategory.getQuestions()
								.setPropagateToSource(false);
						post = beforeQuestionCategory.getQuestions().remove(
								beforeQuestion);
						beforeQuestionCategory.getQuestions()
								.setPropagateToSource(true);
					}
					// attach
					if (!afterQuestionCategory.getQuestions().contain(
							afterQuestion)) {
						afterQuestionCategory.getQuestions()
								.setPropagateToSource(false);
						post = afterQuestionCategory.getQuestions().add(
								afterQuestion);
						afterQuestionCategory.getQuestions()
								.setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= create entity method ======= */

	/**
	 * Creates question.
	 * 
	 * @param categoryParent
	 *            category parent
	 * @param text
	 *            text
	 * @return question
	 */
	public Question createQuestion(Category categoryParent, String text) {
		Question question = new Question(getModel());
		question.setCategory(categoryParent);
		question.setText(text);
		if (!add(question)) {
			question = null;
		}
		return question;
	}

}