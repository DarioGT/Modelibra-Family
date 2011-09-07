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
package dmeduc.weblink.interest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import dmeduc.weblink.category.Category;
import dmeduc.weblink.member.Member;

/**
 * Interest specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-07
 */
public class Interest extends GenInterest {

	private static final long serialVersionUID = 1194454872534L;

	private static Log log = LogFactory.getLog(Interest.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs interest within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Interest(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs interest within its parent(s).
	 * 
	 * @param Member
	 *            member
	 * @param Category
	 *            category
	 */
	public Interest(Member member, Category category) {
		super(member, category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}