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
package twoadw.website.qqcategory;

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

	import twoadw.website.questioncategory.QuestionCategory;	
	import twoadw.website.questioncategory.QuestionCategories;	

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * QQCategory generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenQQCategories extends Entities<QQCategory> {
	
	private static final long serialVersionUID = 1236975992800L;

	private static Log log = LogFactory.getLog(GenQQCategories.class);
	
	/* ======= internal parent neighbors ======= */
	
	private Question question;	
    
/* ======= external parent neighbors ======= */
	
	private QuestionCategory questionCategory;
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs qQCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQQCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs qQCategories for the question parent.
		 * 
		 * @param question
		 *            question
		 */
		public GenQQCategories(Question question) {
			this(question.getModel());
			// parent
			setQuestion(question);
		}
	
    	/**
		 * Constructs qQCategories for the questionCategory parent.
		 * 
		 * @param questionCategory
		 *            questionCategory
		 */
		public GenQQCategories(QuestionCategory questionCategory) {
			this(questionCategory.getModel());
			// parent
			setQuestionCategory(questionCategory);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the qQCategory with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return qQCategory
	 */
public QQCategory getQQCategory(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the qQCategory with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return qQCategory
	 */
	public QQCategory getQQCategory(Long oidUniqueNumber) {
		return getQQCategory(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first qQCategory whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return qQCategory
	 */
	public QQCategory getQQCategory(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects qQCategories whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return qQCategories
	 */
	public QQCategories getQQCategories(String propertyCode, Object property) {
		return (QQCategories) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets qQCategories ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered qQCategories
	 */
	public QQCategories getQQCategories(String propertyCode, boolean ascending) {
		return (QQCategories) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets qQCategories selected by a selector. Returns empty qQCategories if there are no
	 * qQCategories that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected qQCategories
	 */
	public QQCategories getQQCategories(ISelector selector) {
		return (QQCategories) selectBySelector(selector);
	}
	
	/**
	 * Gets qQCategories ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered qQCategories
	 */
	public QQCategories getQQCategories(Comparator comparator, boolean ascending) {
		return (QQCategories) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
		
	/* ======= order methods for essential properties ======= */
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
				/**
		 * Gets qQCategory based on many-to-many parents.
		 * 
				 * @param Question question
				 * @param QuestionCategory questionCategory
			 */
		public QQCategory getQQCategory(
									Question question,
										QuestionCategory questionCategory  
						) {
			for (QQCategory qQCategory : this) {
				if (
																	qQCategory.getQuestion() == question &&
																						qQCategory.getQuestionCategory() == questionCategory  
													) {
					return qQCategory;
				}
			}
			return null;
		}
		
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
    
/**
		 * Sets questionCategory.
		 * 
		 * @param questionCategory
		 *            questionCategory
		 */
public void setQuestionCategory(QuestionCategory questionCategory) {
			this.questionCategory = questionCategory;
		}

		/**
		 * Gets questionCategory.
		 * 
		 * @return questionCategory
		 */
		public QuestionCategory getQuestionCategory() {
			return questionCategory;
		}
		
		
	/* ======= for a many-to-many concept: post add propagation ======= */
	
				protected boolean postAdd(QQCategory qQCategory) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(qQCategory)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {
											Question question = getQuestion();	
						if (question == null) {
							Question qQCategoryQuestion = qQCategory.getQuestion();
							if (!qQCategoryQuestion.getQQCategories().contain(qQCategory)) {
								post = qQCategoryQuestion.getQQCategories().add(qQCategory);
							}
						}						
											QuestionCategory questionCategory = getQuestionCategory();	
						if (questionCategory == null) {
							QuestionCategory qQCategoryQuestionCategory = qQCategory.getQuestionCategory();
							if (!qQCategoryQuestionCategory.getQQCategories().contain(qQCategory)) {
								post = qQCategoryQuestionCategory.getQQCategories().add(qQCategory);
							}
						}						
									}
			} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
				protected boolean postRemove(QQCategory qQCategory) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(qQCategory)) {		
									Question question = getQuestion();	
					if (question == null) {
						Question qQCategoryQuestion = qQCategory.getQuestion();
						if (qQCategoryQuestion.getQQCategories().contain(qQCategory)) {
							post = qQCategoryQuestion.getQQCategories().remove(qQCategory);
						}
					}					
									QuestionCategory questionCategory = getQuestionCategory();	
					if (questionCategory == null) {
						QuestionCategory qQCategoryQuestionCategory = qQCategory.getQuestionCategory();
						if (qQCategoryQuestionCategory.getQQCategories().contain(qQCategory)) {
							post = qQCategoryQuestionCategory.getQQCategories().remove(qQCategory);
						}
					}					
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a many-to-many concept: post update propagation ======= */
	
				protected boolean postUpdate(QQCategory beforeQQCategory, QQCategory afterQQCategory) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeQQCategory, afterQQCategory)) {					
									Question beforeQQCategoryQuestion = beforeQQCategory.getQuestion();
					Question afterQQCategoryQuestion = afterQQCategory.getQuestion();
						
					if (beforeQQCategoryQuestion != afterQQCategoryQuestion) {
						post = beforeQQCategoryQuestion.getQQCategories().remove(beforeQQCategory);
						if (post) {
							post = afterQQCategoryQuestion.getQQCategories().add(afterQQCategory);
							if (!post) {
								beforeQQCategoryQuestion.getQQCategories().add(beforeQQCategory);
							}
						}
					}						
									QuestionCategory beforeQQCategoryQuestionCategory = beforeQQCategory.getQuestionCategory();
					QuestionCategory afterQQCategoryQuestionCategory = afterQQCategory.getQuestionCategory();
						
					if (beforeQQCategoryQuestionCategory != afterQQCategoryQuestionCategory) {
						post = beforeQQCategoryQuestionCategory.getQQCategories().remove(beforeQQCategory);
						if (post) {
							post = afterQQCategoryQuestionCategory.getQQCategories().add(afterQQCategory);
							if (!post) {
								beforeQQCategoryQuestionCategory.getQQCategories().add(beforeQQCategory);
							}
						}
					}						
							} else {
				post = false;
			}
			return post;
		}
		
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates qQCategory.
	 *
			 * @param questionParent question parent
			 * @param questionCategoryParent questionCategory parent
			 * @return qQCategory
	 */
	public QQCategory createQQCategory(
											Question questionParent, 
																				QuestionCategory questionCategoryParent
										) {
		QQCategory qQCategory = new QQCategory(getModel());
					qQCategory.setQuestion(questionParent);
					qQCategory.setQuestionCategory(questionCategoryParent);
						if (!add(qQCategory)) {
			qQCategory = null;
		}
		return qQCategory;
	}

}