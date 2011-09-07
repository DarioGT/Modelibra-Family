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
 * Interest specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-07
 */
public class Interests extends GenInterests {

	private static final long serialVersionUID = 1194454872535L;

	private static Log log = LogFactory.getLog(Interests.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs interests within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Interests(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs interests for the member parent.
	 * 
	 * @param member
	 *            member
	 */
	public Interests(Member member) {
		super(member);
	}

	/**
	 * Constructs interests for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public Interests(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Gets interests ordered by categoryOid.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered interests
	 */
	public Interests getInterestsOrderedByCategoryOid(boolean ascending) {
		return getInterests("categoryOid", ascending);
	}

	/**
	 * Creates interest.
	 * 
	 * @param memberParent
	 *            member parent
	 * @param categoryParent
	 *            category parent
	 * @param description
	 *            description
	 * @return interest
	 */
	public Interest createInterest(Member memberParent,
			Category categoryParent, String description) {
		Interest interest = new Interest(getModel());
		interest.setMember(memberParent);
		interest.setCategory(categoryParent);
		interest.setDescription(description);
		if (!add(interest)) {
			interest = null;
		}
		return interest;
	}

}