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
package course.reference.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.CompositeComparator;
import org.modelibra.IDomainModel;
import org.modelibra.PropertyComparator;
import org.modelibra.PropertySelector;
import org.modelibra.util.EmailConfig;

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Member specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Members extends GenMembers {

	private static final long serialVersionUID = 1176743255487L;

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
				new PropertyComparator("lastName"), new PropertyComparator(
						"firstName"));
		return getMembers(compositePropertyComparator, ascending);
	}

	/**
	 * Gets members ordered alphabetically by last name then by first name.
	 * 
	 * @return members ordered alphabetically
	 */
	public Members getMembersOrderedByLastFirstName() {
		CompositeComparator compositePropertyComparator = new CompositeComparator(
				new PropertyComparator("lastName"), new PropertyComparator(
						"firstName"));
		return getMembers(compositePropertyComparator, true);
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
		for (Member member : this) {
			member.emailMessage(emailConfig, subject, message);
		}
	}

}