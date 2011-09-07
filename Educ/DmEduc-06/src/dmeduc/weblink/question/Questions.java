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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import dmeduc.weblink.category.Category;

/**
 * Question specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public class Questions extends GenQuestions {

	private static final long serialVersionUID = 1171896744341L;

	private static Log log = LogFactory.getLog(Questions.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs questions within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Questions(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs questions for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public Questions(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Creates question.
	 * 
	 * @param text
	 *            text
	 * @return question
	 */
	public Question createQuestion(String text) {
		Question question = new Question(getModel());
		question.setText(text);
		if (!add(question)) {
			question = null;
		}
		return question;
	}

	/**
	 * Creates question.
	 * 
	 * @param categoryParent
	 *            category parent
	 * @param text
	 *            text
	 * @param type
	 *            type
	 * @return question
	 */
	public Question createQuestion(Category categoryParent, String text,
			String type) {
		Question question = new Question(getModel());
		question.setCategory(categoryParent);
		question.setText(text);
		question.setType(type);
		if (!add(question)) {
			question = null;
		}
		return question;
	}

}