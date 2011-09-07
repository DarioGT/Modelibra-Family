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
package course.quiz.member;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.exception.ModelibraException;
import org.modelibra.util.EmailConfig;

/* ======= import external parent entity and entities classes ======= */

/**
 * Member specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Member extends GenMember {

	private static final long serialVersionUID = 1176743255486L;

	private static Log log = LogFactory.getLog(Member.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs member within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Member(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Selects a member if the member is regular and wants to receive email.
	 * 
	 * @return <code>true</code> if the member is regular and wants to receive
	 *         email
	 */
	public boolean isRegularReceiveEmail() {
		boolean selected = false;
		if (getRole().equals("regular") && isReceiveEmail()) {
			selected = true;
		}
		return selected;
	}

	/**
	 * Checks if the member can have access to the administration function.
	 * 
	 * @return <code>true</code> if the member can have access to the
	 *         administration function
	 */
	public boolean hasAdminAccess() {
		boolean adminAccess = true;
		if (getRole().equals("regular") || getRole().equals("casual")) {
			adminAccess = false;
		}
		return adminAccess;
	}

	/**
	 * Sends an email message to this member.
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
			emailConfig.send(getEmail().toString(), subject, message);
		} catch (ModelibraException e) {
			log.error("Error in Member.emailMessage: " + e.getMessage());
		}
	}

}