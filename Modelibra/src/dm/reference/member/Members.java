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
package dm.reference.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.CompositeComparator;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.PropertyComparator;
import org.modelibra.PropertySelector;
import org.modelibra.exception.ModelibraException;
import org.modelibra.util.EmailConfig;

/**
 * Member entities.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-08-23
 */
public class Members extends Entities<Member> {

	private static final long serialVersionUID = 110120141L;

	private static Log log = LogFactory.getLog(Members.class);

	/**
	 * Constructs members within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Members(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Gets the member given a member code.
	 * 
	 * @param code
	 *            code
	 * @return member
	 */
	public Member getMember(String code) {
		return retrieveByCode(code);
	}

	/**
	 * Gets members selected by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return selected members
	 */
	public Members getMembers(String propertyCode, Object property) {
		return (Members) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets members selected by a property selector.
	 * 
	 * @param propertySelector
	 *            property selector
	 * @return selected members
	 */
	public Members getMembers(PropertySelector propertySelector) {
		return (Members) selectBySelector(propertySelector);
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

	/**
	 * Gets members that will receive email.
	 * 
	 * @return selected members
	 */
	public Members getReceiveEmailMembers() {
		PropertySelector propertySelector = new PropertySelector("receiveEmail");
		propertySelector.defineEqual(Boolean.TRUE);
		return getMembers(propertySelector);
	}

	/**
	 * Gets recent members.
	 * 
	 * @param beforeRecentDate
	 *            before this date
	 * @return selected members
	 */
	public Members getRecentMembers(Date beforeRecentDate) {
		PropertySelector propertySelector = new PropertySelector("startDate");
		propertySelector.defineGreaterThan(beforeRecentDate);
		return getMembers(propertySelector);
	}

	/**
	 * Gets administrators.
	 * 
	 * @return selected members
	 */
	public Members getAdminMembers() {
		PropertySelector propertySelector = new PropertySelector("role");
		propertySelector.defineEqual("admin");
		return getMembers(propertySelector);
	}

	/**
	 * Gets regular members that receive email.
	 * 
	 * @return regular members that receive email
	 */
	public Members getRegularMembersThatReceiveEmail() {
		return (Members) selectByMethod("isRegularReceiveEmail", null);
	}

	/**
	 * Gets members ordered by last name then by first name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered members
	 */
	public Members getMembersOrderedByLastNameFirstName(boolean ascending) {
		CompositeComparator compositeComparator = new CompositeComparator(
				new PropertyComparator("lastName"), new PropertyComparator(
						"firstName"));
		return (Members) orderByComparator(compositeComparator, ascending);
	}

	/**
	 * Gets a list of member email addresses.
	 * 
	 * @return list of emails
	 */
	public List<String> getEmails() {
		List<String> emails = new ArrayList<String>();
		synchronized (this) {
			for (Member member : this) {				
				emails.add(member.getEmail().toString());
			}
		}
		return emails;
	}

	/**
	 * Sends an email message to members.
	 * 
	 * @param emailConfig
	 *            email configuration
	 * @param subject
	 *            subject
	 * @param message
	 *            message
	 */
	public void emailMessage(EmailConfig emailConfig, String subject,
			String message) {
		try {
			synchronized (this) {
				for (Member member : this) {					
					emailConfig.send(member.getEmail().toString(), subject,
							message);
				}
			}
		} catch (ModelibraException e) {
			log.error("Error in Members.emailMessage: " + e.getMessage());
		}
	}

	/**
	 * Gets admin members using select by method.
	 * 
	 * @return admin members
	 */
	public Members getAdmins() {
		return (Members) selectByMethod("isAdmin", null);
	}

	/**
	 * Gets members with member role equal to provided one, using select by
	 * method.
	 * 
	 * @param role
	 *            role
	 * @return role members
	 */
	public Members getRoleMembers(String role) {
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(role);
		return (Members) selectByMethod("isRole", params);
	}

	/**
	 * Gets members with member role equal to provided one and last name longer
	 * than length, using select by method.
	 * 
	 * @param role
	 *            role
	 * @return role members
	 */
	public Members getRoleMembersWithLastNameLongerThanLength(String role,
			int length) {
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(role);
		params.add(length);
		return (Members) selectByMethod("isRoleAndLastNameLongerThanLength",
				params);
	}

	/**
	 * Gets members with member first name containing provided string. Selection
	 * may be case sensitive or not.
	 * 
	 * @param string
	 * @param caseSensitive
	 * @return selected members
	 */
	public Members getMembersWithFirstNameContainingString(String string,
			boolean caseSensitive) {
		PropertySelector propertySelector = new PropertySelector("firstName");
		propertySelector.defineContain(string);
		propertySelector.setCaseSensitive(caseSensitive);
		return getMembers(propertySelector);
	}

	/**
	 * Gets members with member first name containing some of the provided
	 * strings. Selection may be case sensitive or not.
	 * 
	 * @param strings
	 * @param caseSensitive
	 * @return selected members
	 */
	public Members getMembersWithFirstNameContainingSomeStrings(
			String[] strings, boolean caseSensitive) {
		PropertySelector propertySelector = new PropertySelector("firstName");
		propertySelector.defineContainSome(strings);
		propertySelector.setCaseSensitive(caseSensitive);
		return getMembers(propertySelector);
	}

	/**
	 * Gets members with member first name containing all of the provided
	 * strings. Selection may be case sensitive or not.
	 * 
	 * @param strings
	 * @param caseSensitive
	 * @return selected members
	 */
	public Members getMembersWithFirstNameContainingAllStrings(
			String[] strings, boolean caseSensitive) {
		PropertySelector propertySelector = new PropertySelector("firstName");
		propertySelector.defineContainAll(strings);
		propertySelector.setCaseSensitive(caseSensitive);
		return getMembers(propertySelector);
	}

	/**
	 * Creates a member.
	 * 
	 * @param firstName
	 *            first name
	 * @param lastName
	 *            last name
	 * @param email
	 *            email
	 * @param code
	 *            code
	 * @param password
	 *            password
	 * @return member
	 */
	public Member createMember(String firstName, String lastName, String email,
			String code, String password) {
		Member member = new Member(getModel());
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setEmail(email);
		member.setCode(code);
		member.setPassword(password);
		if (!add(member)) {
			member = null;
		}
		return member;
	}

	/**
	 * Creates a member.
	 * 
	 * @param firstName
	 *            first name
	 * @param lastName
	 *            last name
	 * @param email
	 *            email
	 * @param code
	 *            code
	 * @param password
	 *            password
	 * @param role
	 *            role
	 * @return member
	 */
	public Member createMember(String firstName, String lastName, String email,
			String code, String password, String role) {
		Member beforeMember = createMember(firstName, lastName, email, code,
				password);
		if (beforeMember != null) {
			Member afterMember = (Member) beforeMember.copy();
			afterMember.setRole(role);
			if (update(beforeMember, afterMember)) {
				return afterMember;
			}
		}
		return null;
	}
}