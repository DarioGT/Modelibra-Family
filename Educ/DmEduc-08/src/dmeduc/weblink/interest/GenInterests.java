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

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

import dmeduc.weblink.category.Category;
import dmeduc.weblink.member.Member;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Interest generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-15
 */
public abstract class GenInterests extends Entities<Interest> {

	private static final long serialVersionUID = 1172170900467L;

	private static Log log = LogFactory.getLog(GenInterests.class);

	/* ======= internal parent neighbors ======= */

	private Member member;

	/* ======= external parent neighbors ======= */

	private Category category;

	/* ======= base constructor ======= */

	/**
	 * Constructs interests within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInterests(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs interests for the member parent.
	 * 
	 * @param member
	 *            member
	 */
	public GenInterests(Member member) {
		this(member.getModel());
		// parent
		setMember(member);
	}

	/**
	 * Constructs interests for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public GenInterests(Category category) {
		this(category.getModel());
		// parent
		setCategory(category);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the interest with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return interest
	 */
	public Interest getInterest(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the interest with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return interest
	 */
	public Interest getInterest(Long oidUniqueNumber) {
		return getInterest(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first interest whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return interest
	 */
	public Interest getInterest(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects interests whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return interests
	 */
	public Interests getInterests(String propertyCode, Object property) {
		return (Interests) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets interests ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered interests
	 */
	public Interests getInterests(String propertyCode, boolean ascending) {
		return (Interests) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets interests selected by a selector. Returns empty interests if there
	 * are no interests that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected interests
	 */
	public Interests getInterests(ISelector selector) {
		return (Interests) selectBySelector(selector);
	}

	/**
	 * Gets interests ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered interests
	 */
	public Interests getInterests(Comparator comparator, boolean ascending) {
		return (Interests) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/**
	 * Gets interest based on many-to-many parents.
	 * 
	 * @param Member
	 *            member
	 * @param Category
	 *            category
	 */
	public Interest getInterest(Member member, Category category) {
		for (Interest interest : this) {
			if (interest.getMember() == member
					&& interest.getCategory() == category) {
				return interest;
			}
		}
		return null;
	}

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets member.
	 * 
	 * @param member
	 *            member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets member.
	 * 
	 * @return member
	 */
	public Member getMember() {
		return member;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	protected boolean postAdd(Interest interest) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(interest)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Member member = getMember();
				if (member == null) {
					Member interestMember = interest.getMember();
					if (!interestMember.getInterests().contain(interest)) {
						post = interestMember.getInterests().add(interest);
					}
				}
				Category category = getCategory();
				if (category == null) {
					Category interestCategory = interest.getCategory();
					if (!interestCategory.getInterests().contain(interest)) {
						post = interestCategory.getInterests().add(interest);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post remove propagation ======= */

	protected boolean postRemove(Interest interest) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(interest)) {
			Member member = getMember();
			if (member == null) {
				Member interestMember = interest.getMember();
				if (interestMember.getInterests().contain(interest)) {
					post = interestMember.getInterests().remove(interest);
				}
			}
			Category category = getCategory();
			if (category == null) {
				Category interestCategory = interest.getCategory();
				if (interestCategory.getInterests().contain(interest)) {
					post = interestCategory.getInterests().remove(interest);
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post update propagation ======= */

	protected boolean postUpdate(Interest beforeInterest, Interest afterInterest) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeInterest, afterInterest)) {
			Member beforeInterestMember = beforeInterest.getMember();
			Member afterInterestMember = afterInterest.getMember();

			if (beforeInterestMember != afterInterestMember) {
				post = beforeInterestMember.getInterests().remove(
						beforeInterest);
				if (post) {
					post = afterInterestMember.getInterests()
							.add(afterInterest);
					if (!post) {
						beforeInterestMember.getInterests().add(beforeInterest);
					}
				}
			}
			Category beforeInterestCategory = beforeInterest.getCategory();
			Category afterInterestCategory = afterInterest.getCategory();

			if (beforeInterestCategory != afterInterestCategory) {
				post = beforeInterestCategory.getInterests().remove(
						beforeInterest);
				if (post) {
					post = afterInterestCategory.getInterests().add(
							afterInterest);
					if (!post) {
						beforeInterestCategory.getInterests().add(
								beforeInterest);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates interest.
	 * 
	 * @param memberParent
	 *            member parent
	 * @param categoryParent
	 *            category parent
	 * @return interest
	 */
	public Interest createInterest(Member memberParent, Category categoryParent) {
		Interest interest = new Interest(getModel());
		interest.setMember(memberParent);
		interest.setCategory(categoryParent);
		if (!add(interest)) {
			interest = null;
		}
		return interest;
	}

}