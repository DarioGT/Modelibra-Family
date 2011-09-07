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
import org.modelibra.PropertySelector;

import course.quiz.question.Question;

/* ======= import external parent entity and entities classes ======= */

/**
 * Item specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Items extends GenItems {

	private static final long serialVersionUID = 1176743181621L;

	private static Log log = LogFactory.getLog(Items.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs items within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Items(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs items for the question parent.
	 * 
	 * @param question
	 *            question
	 */
	public Items(Question question) {
		super(question);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public Items getKeywordItems(String keyword) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContain(keyword);
		return getItems(propertySelector);
	}

	public Items getSomeKeywordItems(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContainSome(keywords);
		return getItems(propertySelector);
	}

	public Items getAllKeywordItems(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContainAll(keywords);
		return getItems(propertySelector);
	}

}