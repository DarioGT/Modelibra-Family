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
package twoadw.website.question;

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


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.product.Product;	
	import twoadw.website.product.Products;	

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    
	import twoadw.website.qqcategory.QQCategory;	
	import twoadw.website.qqcategory.QQCategories;	
			import twoadw.website.questioncategory.QuestionCategory;	
	
/**
 * Question generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-04-08
 */
public abstract class GenQuestions extends Entities<Question> {
	
	private static final long serialVersionUID = 1236739001411L;

	private static Log log = LogFactory.getLog(GenQuestions.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	
	private Product product;
	

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
		 * Constructs questions for the product parent.
		 * 
		 * @param product
		 *            product
		 */
		public GenQuestions(Product product) {
			this(product.getModel());
			// parent
			setProduct(product);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the question with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return question
	 */
public Question getQuestion(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the question with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return question
	 */
	public Question getQuestion(Long oidUniqueNumber) {
		return getQuestion(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first question whose property with a  
 * property code is equal to a property object. Null if not found. 
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
	 * Selects questions whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
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
	 * Gets questions selected by a selector. Returns empty questions if there are no
	 * questions that satisfy the selector.
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
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets questionText questions.
		 * 
		 * @param questionText 
		 *            questionText
		 * @return questionText questions
		 */
		public Questions getQuestionTextQuestions(String questionText) {
			PropertySelector propertySelector = new PropertySelector("questionText");
			propertySelector.defineEqual(questionText);
			return getQuestions(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
						/**
		 	* Gets product questions.
		 	* 
		 	* @param product 
		 	*            product oid unique number
		 	* @return product questions
		 	*/
			public Questions getProductQuestions(Long product) {
						PropertySelector propertySelector = new PropertySelector("productOid");
						propertySelector.defineEqual(product);
				return getQuestions(						propertySelector);
			}
			
			/**
		 	* Gets product questions.
		 	* 
		 	* @param product 
		 	*            product oid
		 	* @return product questions
		 	*/
			public Questions getProductQuestions(Oid product) {
				return getProductQuestions(product.getUniqueNumber());
			}
			
			/**
		 	* Gets product questions.
		 	* 
		 	* @param product 
		 	*            product
		 	* @return product questions
		 	*/
			public Questions getProductQuestions(Product product) {
				return getProductQuestions(product.getOid());
			}
				
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets questions ordered by questionText.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered questions
		 */
		public Questions getQuestionsOrderedByQuestionText(boolean ascending) {			
			return getQuestions("questionText", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
						/**
			 * Gets questionCategory qQCategories.
			 * 
			 * @return questionCategory qQCategories
			 */
			public QQCategories getQuestionCategoryQQCategories(QuestionCategory questionCategory) {
				QQCategories qQCategories = new QQCategories(questionCategory);
				qQCategories.setPersistent(false);
				for (Question question : this) {
					QQCategory qQCategory = question.getQQCategories()
						.getQQCategory(question, questionCategory);
					if (qQCategory != null) {
						qQCategories.add(qQCategory);
					}
				}
				return qQCategories;
			}
				
		
	/* ======= internal parent set and get methods ======= */
    
	
	/* ======= external parent set and get methods ======= */
    
/**
		 * Sets product.
		 * 
		 * @param product
		 *            product
		 */
public void setProduct(Product product) {
			this.product = product;
		}

		/**
		 * Gets product.
		 * 
		 * @return product
		 */
		public Product getProduct() {
			return product;
		}
		
		
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
			protected boolean postAdd(Question question) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postAdd(question)) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {			
											Product product = getProduct();
						if (product == null) {
							Product questionProduct = question.getProduct();
							if (questionProduct != null) {
								if (!questionProduct.getQuestions().contain(question)) {
									questionProduct.getQuestions().setPropagateToSource(false);
									post = questionProduct.getQuestions().add(question);
									questionProduct.getQuestions().setPropagateToSource(true);
								}
							}
						} 
									}
			} else {
				post = false;
			}
		return post;
	}
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
			protected boolean postRemove(Question question) {
			if (!isPost()) {
				return true;
			}
			boolean post = true;
			if (super.postRemove(question)) {			
									Product product = getProduct();
					if (product == null) {
						Product questionProduct = question.getProduct();
						if (questionProduct != null) {
							if (questionProduct.getQuestions().contain(question)) {
								questionProduct.getQuestions().setPropagateToSource(false);
								post = questionProduct.getQuestions().remove(question);
								questionProduct.getQuestions().setPropagateToSource(true);
							}
						}
					} 
							} else {
				post = false;
			}
		return post;
	}
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
			protected boolean postUpdate(Question beforeQuestion, Question afterQuestion) {
			if (!isPost()) {
				return true;
			}			
			boolean post = true;
			if (super.postUpdate(beforeQuestion, afterQuestion)) {			
									Product product = getProduct();					
					if (product == null) {	
						Product beforeQuestionProduct = beforeQuestion.getProduct();
						Product afterQuestionProduct = afterQuestion.getProduct();
						if (beforeQuestionProduct == null && afterQuestionProduct != null) {
							// attach
							if (!afterQuestionProduct.getQuestions().contain(afterQuestion)) {
								afterQuestionProduct.getQuestions().setPropagateToSource(false);
								post = afterQuestionProduct.getQuestions().add(afterQuestion);
								afterQuestionProduct.getQuestions().setPropagateToSource(true);
							}
						} else if (beforeQuestionProduct != null && afterQuestionProduct == null) {
							// detach
							if (beforeQuestionProduct.getQuestions().contain(beforeQuestion)) {
								beforeQuestionProduct.getQuestions().setPropagateToSource(false);
								post = beforeQuestionProduct.getQuestions().remove(beforeQuestion);
								beforeQuestionProduct.getQuestions().setPropagateToSource(true);
							}
						} else if (beforeQuestionProduct != null && afterQuestionProduct != null
								&& beforeQuestionProduct != afterQuestionProduct) {
							// detach
							if (beforeQuestionProduct.getQuestions().contain(beforeQuestion)) {
								beforeQuestionProduct.getQuestions().setPropagateToSource(false);
								post = beforeQuestionProduct.getQuestions().remove(beforeQuestion);
								beforeQuestionProduct.getQuestions().setPropagateToSource(true);
							}
							// attach
							if (!afterQuestionProduct.getQuestions().contain(afterQuestion)) {
								afterQuestionProduct.getQuestions().setPropagateToSource(false);
								post = afterQuestionProduct.getQuestions().add(afterQuestion);
								afterQuestionProduct.getQuestions().setPropagateToSource(true);
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
			 * @param productParent product parent
		 * @param questionText questionText 
		 * @return question
	 */
	public Question createQuestion(
																	Product productParent,
																	String questionText 
				) {
		Question question = new Question(getModel());
					question.setProduct(productParent);
						question.setQuestionText(questionText);
				if (!add(question)) {
			question = null;
		}
		return question;
	}

}