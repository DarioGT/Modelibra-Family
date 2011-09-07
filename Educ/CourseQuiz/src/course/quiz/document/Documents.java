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
package course.quiz.document;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import course.quiz.member.Member;
import course.quiz.test.Test;

/**
 * Document specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Documents extends GenDocuments {

	private static final long serialVersionUID = 1176743350748L;

	private static Log log = LogFactory.getLog(Documents.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs documents within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Documents(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs documents for the member parent.
	 * 
	 * @param member
	 *            member
	 */
	public Documents(Member member) {
		super(member);
	}

	/**
	 * Constructs documents for the test parent.
	 * 
	 * @param test
	 *            test
	 */
	public Documents(Test test) {
		super(test);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}