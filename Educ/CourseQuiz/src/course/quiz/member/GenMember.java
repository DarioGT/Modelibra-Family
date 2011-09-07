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

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.quiz.Quiz;
import course.quiz.document.Documents;
import course.quiz.question.Questions;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Member generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenMember extends Entity<Member> {

	private static final long serialVersionUID = 1176743255484L;

	private static Log log = LogFactory.getLog(GenMember.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String password;

	private String lastName;

	private String firstName;

	private String email;

	private Boolean receiveEmail;

	private String role;

	private Date startDate;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	private Documents documents;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Questions questions;

	/* ======= base constructor ======= */

	/**
	 * Constructs member within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMember(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setDocuments(new Documents((Member) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets password.
	 * 
	 * @param password
	 *            password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets lastName.
	 * 
	 * @param lastName
	 *            lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets lastName.
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets firstName.
	 * 
	 * @param firstName
	 *            firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets firstName.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets email.
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets receiveEmail.
	 * 
	 * @param receiveEmail
	 *            receiveEmail
	 */
	public void setReceiveEmail(Boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	/**
	 * Gets receiveEmail.
	 * 
	 * @return receiveEmail
	 */
	public Boolean getReceiveEmail() {
		return receiveEmail;
	}

	/**
	 * Sets receiveEmail.
	 * 
	 * @param receiveEmail
	 *            receiveEmail
	 */
	public void setReceiveEmail(boolean receiveEmail) {
		setReceiveEmail(new Boolean(receiveEmail));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isReceiveEmail() {
		return getReceiveEmail().booleanValue();
	}

	/**
	 * Sets role.
	 * 
	 * @param role
	 *            role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets role.
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets startDate.
	 * 
	 * @param startDate
	 *            startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets startDate.
	 * 
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets documents.
	 * 
	 * @param documents
	 *            documents
	 */
	public void setDocuments(Documents documents) {
		this.documents = documents;
		if (documents != null) {
			documents.setMember((Member) this);
		}
	}

	/**
	 * Gets documents.
	 * 
	 * @return documents
	 */
	public Documents getDocuments() {
		return documents;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets questions.
	 * 
	 * @param questions
	 *            questions
	 */
	public void setQuestions(Questions questions) {
		this.questions = questions;
		if (questions != null) {
			questions.setMember((Member) this);
		}
	}

	/**
	 * Gets questions.
	 * 
	 * @return questions
	 */
	public Questions getQuestions() {
		if (questions == null) {
			Quiz quiz = (Quiz) getModel();
			Questions questions = quiz.getQuestions();
			setQuestions(questions.getMemberQuestions((Member) this));
		}
		return questions;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}