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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.CompositeComparator;
import org.modelibra.IDomainModel;
import org.modelibra.PropertyComparator;
import org.modelibra.PropertySelector;
import org.modelibra.exception.ModelibraException;
import org.modelibra.type.EasyDate;
import org.modelibra.util.EmailConfig;

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Member specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
public class Members extends GenMembers {

	private static final long serialVersionUID = 1194454872530L;

	private static Log log = LogFactory.getLog(Members.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs members within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Members(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Gets the member by a code.
	 * 
	 * @param code
	 *            code
	 * @return member
	 */
	public Member getMemberByCode(String code) {
		return getMember("code", code);
	}

	/**
	 * Gets members that want to receive email.
	 * 
	 * @return members that want to receive email
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
	 *            before recent date
	 * @return recent members
	 */
	public Members getRecentMembers(Date beforeRecentDate) {
		PropertySelector propertySelector = new PropertySelector("startDate");
		propertySelector.defineGreaterThan(beforeRecentDate);
		return getMembers(propertySelector);
	}

	/**
	 * Gets admin members.
	 * 
	 * @return admin members
	 */
	public Members getAdminMembers() {
		PropertySelector propertySelector = new PropertySelector("role");
		propertySelector.defineEqual("admin");
		return getMembers(propertySelector);
	}

	/**
	 * Gets regular members that want to receive email.
	 * 
	 * @return regular members that want to receive email
	 */
	public Members getRegularMembersThatReceiveEmail() {
		return (Members) selectByMethod("isRegularReceiveEmail");
	}

	/**
	 * Gets members ordered by last name then by first name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered members
	 */
	public Members getMembersOrderedByLastFirstName(boolean ascending) {
		CompositeComparator compositePropertyComparator = new CompositeComparator(
				new PropertyComparator<Member>("lastName"),
				new PropertyComparator<Member>("firstName"));
		return getMembers(compositePropertyComparator, ascending);
	}

	/**
	 * Gets a list of member email addresses.
	 * 
	 * @return list of emails
	 */
	public List<String> getEmails() {
		List<String> emails = new ArrayList<String>();
		for (Member member : this) {
			emails.add(member.getEmail().toString());
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
			for (Member member : this) {
				emailConfig
						.send(member.getEmail().toString(), subject, message);
			}
		} catch (ModelibraException e) {
			log.error("Error in Members.emailMessage: " + e.getMessage());
		}
	}

	/**
	 * Checks if a new member has a valid role.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the add postcondition is satisfied
	 */
	protected boolean postAdd(Member member) {
		if (!isPost()) {
			return true;
		}
		boolean validMember = false;
		if (super.postAdd(member)) {
			validMember = member.hasValidRole();
			if (!validMember) {
				getErrors().add("Member.role.validation",
						"Role must be regular or admin.");
			}
		}
		return validMember;
	}

	/**
	 * Checks if an updated member has a valid role.
	 * 
	 * @param beforeMember
	 *            before update member
	 * @param afterMember
	 *            after update member
	 * @return <code>true</code> if the update postcondition is satisfied
	 */
	protected boolean postUpdate(Member beforeMember, Member afterMember) {
		if (!isPost()) {
			return true;
		}
		boolean validMember = false;
		if (super.postUpdate(beforeMember, afterMember)) {
			validMember = afterMember.hasValidRole();
			if (!validMember) {
				getErrors().add("Member.role.validation",
						"Role must be regular or admin.");
			}
		}
		return validMember;
	}

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
	 * @param receiveEmail
	 *            receive Email
	 * @param role
	 *            role
	 * @param startEasyDate
	 *            start easy date
	 * @return member
	 */
	public Member createMember(String code, String password, String lastName,
			String firstName, String email, boolean receiveEmail, String role,
			EasyDate startEasyDate) {
		Member member = new Member(getModel());
		member.setCode(code);
		member.setPassword(password);
		member.setLastName(lastName);
		member.setFirstName(firstName);
		member.setEmail(email);
		member.setReceiveEmail(receiveEmail);
		member.setRole(role);
		member.setStartDate(startEasyDate.getDate());
		if (!add(member)) {
			member = null;
		}
		return member;
	}

}