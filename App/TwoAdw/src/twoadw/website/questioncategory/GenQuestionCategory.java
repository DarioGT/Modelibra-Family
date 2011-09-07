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
package twoadw.website.questioncategory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.questioncategory.QuestionCategory;	

/* ======= import internal child entities classes ======= */

	import twoadw.website.questioncategory.QuestionCategories;	

/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */

	import twoadw.website.qqcategory.QQCategories;	

/* ======= import external many-to-many internal parent entities classes ======= */

			import twoadw.website.question.Questions;
	
/**
 * QuestionCategory generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenQuestionCategory extends Entity<QuestionCategory> {

	private static final long serialVersionUID = 1236738768962L;

	private static Log log = LogFactory.getLog(GenQuestionCategory.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String name;
	
    	    private Boolean published;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    	    private QuestionCategory questionCategory;	
    
	/* ======= internal child neighbors ======= */
	
    	    private QuestionCategories questionCategories;
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
    	    private QQCategories qQCategories;
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs questionCategory within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQuestionCategory(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setQuestionCategories(new QuestionCategories((QuestionCategory) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs questionCategory within its parent(s).
	     * 
	        		* @param QuestionCategory questionCategory
			     */
	    public GenQuestionCategory(
	    		    							QuestionCategory questionCategory  
	    			    		    ) {
	    				this(questionCategory.getModel());
			// parents
							setQuestionCategory(questionCategory);
	    		    }
        
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets name.
		 * 
		 * @param name
		 *            name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Gets name.
		 * 
		 * @return name
		 */
		public String getName() {
			return name;
		}  
		
				    		/**
		 * Sets published.
		 * 
		 * @param published
		 *            published
		 */
		public void setPublished(Boolean published) {
			this.published = published;
		}
		
		/**
		 * Gets published.
		 * 
		 * @return published
		 */
		public Boolean getPublished() {
			return published;
		}  
		
							/**
		     * Sets published.
		     * 
		     * @param published
		     *            published
		     */
			public void setPublished(boolean published) {
				setPublished(new Boolean(published));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isPublished() {
				return getPublished().booleanValue();
			}
			
		        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
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
		
		
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets questionCategories.
		 * 
		 * @param questionCategories
		 *            questionCategories
		 */
    	public void setQuestionCategories(QuestionCategories questionCategories) {
			this.questionCategories = questionCategories;
			if (questionCategories != null) {
				questionCategories.setQuestionCategory((QuestionCategory) this);
			}
		}

		/**
		 * Gets questionCategories.
		 * 
		 * @return questionCategories
		 */
		public QuestionCategories getQuestionCategories() {
			return questionCategories;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
    					/**
			 * Sets qQCategories.
			 * 
			 * @param qQCategories
			 *            qQCategories
			 */
	    	public void setQQCategories(QQCategories qQCategories) {
				this.qQCategories = qQCategories;
				if (qQCategories != null) {
					qQCategories.setQuestionCategory((QuestionCategory) this);
				}
			}
	
			/**
			 * Gets qQCategories.
			 * 
			 * @return qQCategories
			 */
			public QQCategories getQQCategories() {
			    if (qQCategories == null) {
					Website website = (Website) getModel();
					Questions questions = website.getQuestions();
					setQQCategories(questions.getQuestionCategoryQQCategories((QuestionCategory) this));
				}
				return qQCategories;
			}
			
			    
}