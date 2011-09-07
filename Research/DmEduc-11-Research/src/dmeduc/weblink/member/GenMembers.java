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
package dmeduc.weblink.member;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import dmeduc.weblink.category.Category;
import dmeduc.weblink.interest.Interest;
import dmeduc.weblink.interest.Interests;

/**
 * Member generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-15
 */
public abstract class GenMembers extends Entities<Member> {

	private static final long serialVersionUID = 1172170727629L;

	private static Log log = LogFactory.getLog(GenMembers.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs members within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMembers(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the member with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return member
	 */
	public Member getMember(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the member with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return member
	 */
	public Member getMember(Long oidUniqueNumber) {
		return getMember(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first member whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return member
	 */
	public Member getMember(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects members whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return members
	 */
	public Members getMembers(String propertyCode, Object property) {
		return (Members) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets members ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered members
	 */
	public Members getMembers(String propertyCode, boolean ascending) {
		return (Members) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets members selected by a selector. Returns empty members if there are
	 * no members that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected members
	 */
	public Members getMembers(ISelector selector) {
		return (Members) selectBySelector(selector);
	}

	/**
	 * Gets members ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered members
	 */
	public Members getMembers(Comparator comparator, boolean ascending) {
		return (Members) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets lastName members.
	 * 
	 * @param lastName
	 *            lastName
	 * @return lastName members
	 */
	public Members getLastNameMembers(String lastName) {
		PropertySelector propertySelector = new PropertySelector("lastName");
		propertySelector.defineEqual(lastName);
		return getMembers(propertySelector);
	}

	/**
	 * Gets firstName members.
	 * 
	 * @param firstName
	 *            firstName
	 * @return firstName members
	 */
	public Members getFirstNameMembers(String firstName) {
		PropertySelector propertySelector = new PropertySelector("firstName");
		propertySelector.defineEqual(firstName);
		return getMembers(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets members ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered members
	 */
	public Members getMembersOrderedByCode(boolean ascending) {
		return getMembers("code", ascending);
	}

	/**
	 * Gets members ordered by lastName.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered members
	 */
	public Members getMembersOrderedByLastName(boolean ascending) {
		return getMembers("lastName", ascending);
	}

	/**
	 * Gets members ordered by firstName.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered members
	 */
	public Members getMembersOrderedByFirstName(boolean ascending) {
		return getMembers("firstName", ascending);
	}

	/**
	 * Gets members ordered by email.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered members
	 */
	public Members getMembersOrderedByEmail(boolean ascending) {
		return getMembers("email", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/**
	 * Gets category interests.
	 * 
	 * @return category interests
	 */
	public Interests getCategoryInterests(Category category) {
		Interests interests = new Interests(category);
		interests.setPersistent(false);
		for (Member member : this) {
			Interest interest = member.getInterests().getInterest(member,
					category);
			if (interest != null) {
				interests.add(interest);
			}
		}
		return interests;
	}

	/* ======= internal parent set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

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
	 * Creates member.
	 * 
	 * @param code
	 *            code
	 * @param password
	 *            password
	 * @param lastName
	 *            lastName
	 * @param firstName
	 *            firstName
	 * @param email
	 *            email
	 * @return member
	 */
	public Member createMember(String code, String password, String lastName,
			String firstName, String email) {
		Member member = new Member(getModel());
		member.setCode(code);
		member.setPassword(password);
		member.setLastName(lastName);
		member.setFirstName(firstName);
		member.setEmail(email);
		if (!add(member)) {
			member = null;
		}
		return member;
	}

}