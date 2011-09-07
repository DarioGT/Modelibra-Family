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
package course.quiz.question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.PropertySelector;

import course.quiz.member.Member;
import course.quiz.test.Test;

/**
 * Question specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Questions extends GenQuestions {

	private static final long serialVersionUID = 1176743111753L;

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
	 * Constructs questions for the test parent.
	 * 
	 * @param test
	 *            test
	 */
	public Questions(Test test) {
		super(test);
	}

	/**
	 * Constructs questions for the member parent.
	 * 
	 * @param member
	 *            member
	 */
	public Questions(Member member) {
		super(member);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public Questions getKeywordQuestions(String keyword) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContain(keyword);
		return getQuestions(propertySelector);
	}

	public Questions getSomeKeywordQuestions(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContainSome(keywords);
		return getQuestions(propertySelector);
	}

	public Questions getAllKeywordQuestions(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContainAll(keywords);
		return getQuestions(propertySelector);
	}

}