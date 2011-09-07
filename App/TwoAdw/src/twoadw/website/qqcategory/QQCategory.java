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
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */

import twoadw.website.question.Question;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.questioncategory.QuestionCategory;
	import twoadw.website.questioncategory.QuestionCategories;	

/**
 * QQCategory specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class QQCategory extends GenQQCategory {

	private static final long serialVersionUID = 1236975992801L;

	private static Log log = LogFactory.getLog(QQCategory.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs qQCategory within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public QQCategory(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs qQCategory within its parent(s).
	     * 
	        		* @param Question question
		    		* @param QuestionCategory questionCategory
			     */
	    public QQCategory(
	    		    							Question question,
	    			    		    							QuestionCategory questionCategory  
	    			    		    ) {
			super(
					    									question,
	    				    			    									questionCategory  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}