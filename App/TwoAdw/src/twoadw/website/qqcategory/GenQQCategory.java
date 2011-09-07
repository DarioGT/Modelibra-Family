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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.question.Question;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.questioncategory.QuestionCategory;
	import twoadw.website.questioncategory.QuestionCategories;	

/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * QQCategory generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenQQCategory extends Entity<QQCategory> {

	private static final long serialVersionUID = 1236975992799L;

	private static Log log = LogFactory.getLog(GenQQCategory.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
        
    /* ======= reference properties ======= */
	
    	    private Long questionCategoryOid;
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private Question question;	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    	    private QuestionCategory questionCategory;
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs qQCategory within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQQCategory(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs qQCategory within its parent(s).
	     * 
	        		* @param Question question
		    		* @param QuestionCategory questionCategory
			     */
	    public GenQQCategory(
	    		    							Question question,
	    			    		    							QuestionCategory questionCategory  
	    			    		    ) {
	    				this(questionCategory.getModel());
			// parents
							setQuestion(question);
	    					setQuestionCategory(questionCategory);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
        
    /* ======= reference property set and get methods ======= */
    
    	/**
		 * Sets questionCategoryOid.
		 * 
		 * @param questionCategoryOid
		 *            questionCategoryOid
		 */
		public void setQuestionCategoryOid(Long questionCategoryOid) {
			this.questionCategoryOid = questionCategoryOid;
							questionCategory = null;
			}
		
		/**
		 * Gets questionCategoryOid.
		 * 
		 * @return questionCategoryOid
		 */
		public Long getQuestionCategoryOid() {
			return questionCategoryOid;
		}  
        
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
    
        	/**
		 * Sets questionCategory.
		 * 
		 * @param questionCategory
		 *            questionCategory
		 */
    	public void setQuestionCategory(QuestionCategory questionCategory) {
			this.questionCategory = questionCategory;
			if (questionCategory != null) {
				questionCategoryOid = questionCategory.getOid().getUniqueNumber();
			} else {
				questionCategoryOid = null;
			}
		}

		/**
		 * Gets questionCategory.
		 * 
		 * @return questionCategory
		 */
		public QuestionCategory getQuestionCategory() {
			if (questionCategory == null) {
				Website website = (Website) getModel();
				QuestionCategories questionCategories = website.getQuestionCategories();
				if (questionCategoryOid != null) {
											questionCategory = questionCategories.getReflexiveQuestionCategory(questionCategoryOid);
									}
			}
			return questionCategory;
		}
		
		
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}