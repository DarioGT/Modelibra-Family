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
package course.quiz.item;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import course.quiz.question.Question;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Item specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Item extends GenItem {

	private static final long serialVersionUID = 1176743181620L;

	private static Log log = LogFactory.getLog(Item.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs item within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Item(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs item within its parent(s).
	 * 
	 * @param Question
	 *            question
	 */
	public Item(Question question) {
		super(question);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}