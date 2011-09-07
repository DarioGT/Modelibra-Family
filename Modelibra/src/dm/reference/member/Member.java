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

import java.util.Date;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/**
 * Member entity.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-08-23
 */
public class Member extends Entity<Member> {

	private static final long serialVersionUID = 110120140L;

	private String lastName;

	private String firstName;

	private String email;

	private String role;

	// code is inherited from Entity
	private String password;

	private Date startDate;

	private Boolean receiveEmail;

	/**
	 * Constructs a member within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Member(IDomainModel domainModel) {
		super(domainModel);
		// internal child neighbors only
	}

	/**
	 * Sets a last name.
	 * 
	 * @param lastName
	 *            last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets a last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets a first name.
	 * 
	 * @param firstName
	 *            first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets a first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets an email.
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets an email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets a role (regular or admin).
	 * 
	 * @param role
	 *            role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets a role.
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets a password.
	 * 
	 * @param password
	 *            password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets a password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets a start date.
	 * 
	 * @param startDate
	 *            start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets a start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets a receive email switch.
	 * 
	 * @param receiveEmail
	 *            <code>true</code> if to receive email
	 */
	public void setReceiveEmail(Boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	/**
	 * Sets a receive email switch.
	 * 
	 * @return receive email switch
	 */
	public Boolean getReceiveEmail() {
		return receiveEmail;
	}

	/**
	 * Checks if to receive email.
	 * 
	 * @return <code>true</code> if to receive email
	 */
	public boolean isReceiveEmail() {
		return getReceiveEmail().booleanValue();
	}

	/**
	 * Selects a member if the member is regular and wants to receive email.
	 * 
	 * @return specific selection switch
	 */
	public boolean isRegularReceiveEmail() {
		boolean selected = false;
		if (getRole().equals("regular") && isReceiveEmail()) {
			selected = true;
		}
		if (selected) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Selects a member if the member is admin.
	 * 
	 * @return specific selection switch
	 */
	public boolean isAdmin() {
		boolean admin = false;
		if (getRole().equals("admin")) {
			admin = true;
		}
		return admin;
	}

	/**
	 * Selects a member if the member has role equal to provided one.
	 * 
	 * @param role
	 *            member role
	 * @return specific selection switch
	 */
	public boolean isRole(String role) {
		boolean selected = false;
		if (getRole().equals(role)) {
			selected = true;
		}
		return selected;
	}

	/**
	 * Selects a member if the member has role equal to provided one and member
	 * last name is longer than length
	 * 
	 * @param role
	 *            member role
	 * @param length
	 *            last name length
	 * @return specific selection switch
	 */
	public boolean isRoleAndLastNameLongerThanLength(String role, int length) {
		if (getRole().equals(role) && getLastName().length() > length) {
			return true;
		}
		return false;
	}
}