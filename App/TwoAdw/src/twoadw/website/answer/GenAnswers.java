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
package twoadw.website.answer;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.question.Question;	

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * Answer generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenAnswers extends Entities<Answer> {
	
	private static final long serialVersionUID = 1236739076033L;

	private static Log log = LogFactory.getLog(GenAnswers.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Question question;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs answers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAnswers(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs answers for the question parent.
		 * 
		 * @param question
		 *            question
		 */
		public GenAnswers(Question question) {
			this(question.getModel());
			// parent
			setQuestion(question);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the answer with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return answer
	 */
public Answer getAnswer(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the answer with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return answer
	 */
	public Answer getAnswer(Long oidUniqueNumber) {
		return getAnswer(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first answer whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return answer
	 */
	public Answer getAnswer(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects answers whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return answers
	 */
	public Answers getAnswers(String propertyCode, Object property) {
		return (Answers) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets answers ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered answers
	 */
	public Answers getAnswers(String propertyCode, boolean ascending) {
		return (Answers) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets answers selected by a selector. Returns empty answers if there are no
	 * answers that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected answers
	 */
	public Answers getAnswers(ISelector selector) {
		return (Answers) selectBySelector(selector);
	}
	
	/**
	 * Gets answers ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered answers
	 */
	public Answers getAnswers(Comparator comparator, boolean ascending) {
		return (Answers) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets answerText answers.
		 * 
		 * @param answerText 
		 *            answerText
		 * @return answerText answers
		 */
		public Answers getAnswerTextAnswers(String answerText) {
			PropertySelector propertySelector = new PropertySelector("answerText");
			propertySelector.defineEqual(answerText);
			return getAnswers(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets answers ordered by answerText.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered answers
		 */
		public Answers getAnswersOrderedByAnswerText(boolean ascending) {			
			return getAnswers("answerText", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
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
		
		
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates answer.
	 *
			 * @param questionParent question parent
		 * @param answerText answerText 
		 * @return answer
	 */
	public Answer createAnswer(
																	Question questionParent,
																	String answerText 
				) {
		Answer answer = new Answer(getModel());
					answer.setQuestion(questionParent);
						answer.setAnswerText(answerText);
				if (!add(answer)) {
			answer = null;
		}
		return answer;
	}

}