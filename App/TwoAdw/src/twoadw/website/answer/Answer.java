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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */

import twoadw.website.question.Question;	

/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Answer specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Answer extends GenAnswer {

	private static final long serialVersionUID = 1236739076034L;

	private static Log log = LogFactory.getLog(Answer.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs answer within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Answer(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs answer within its parent(s).
	     * 
	        		* @param Question question
			     */
	    public Answer(
	    		    							Question question  
	    			    		    ) {
			super(
					    									question  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}