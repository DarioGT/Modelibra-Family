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

/* ======= import internal parent entity class ======= */

import twoadw.website.question.Question;	

/* ======= import external parent entity and entities classes ======= */

	import twoadw.website.questioncategory.QuestionCategory;	
	import twoadw.website.questioncategory.QuestionCategories;	

/**
 * QQCategory specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class QQCategories extends GenQQCategories {
	
	private static final long serialVersionUID = 1236975992802L;

	private static Log log = LogFactory.getLog(QQCategories.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs qQCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public QQCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs qQCategories for the question parent.
		 * 
		 * @param question
		 *            question
		 */
		public QQCategories(Question question) {
			super(question);
		}
	
    	    /**
		 * Constructs qQCategories for the questionCategory parent.
		 * 
		 * @param questionCategory
		 *            questionCategory
		 */
		public QQCategories(QuestionCategory questionCategory) {
			super(questionCategory);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}