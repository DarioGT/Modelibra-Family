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
import org.modelibra.util.Transformer;

import course.quiz.member.Member;
import course.quiz.test.Test;

/**
 * Document specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
public class Document extends GenDocument {

	private static final long serialVersionUID = 1176743350747L;

	private static Log log = LogFactory.getLog(Document.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs document within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Document(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs document within its parent(s).
	 * 
	 * @param Member
	 *            member
	 * @param Test
	 *            test
	 */
	/*
	 * public Document(Member member, Test test) { super(member, test); }
	 */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Constructs document within its parent(s).
	 * 
	 * @param Member
	 *            member
	 * @param Test
	 *            test
	 */
	public Document(Member member, Test test) {
		this(member.getModel());
		// parents
		setMember(member);
		setTest(test);
	}

	public String getUploadDateText() {
		return Transformer.string(getUploadDate());
	}

}