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
package education.library.member;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import education.library.Library;
import education.library.bookitem.BookItems;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Member generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenMember extends Entity<Member> {

	private static final long serialVersionUID = 1233251560116L;

	private static Log log = LogFactory.getLog(GenMember.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String password;

	private String firstName;

	private String lastName;

	private String email;

	private Boolean onTime;

	private Integer maxNumberOfBooks;

	private Date returnDate;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private BookItems bookItems;

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
	 * Sets onTime.
	 * 
	 * @param onTime
	 *            onTime
	 */
	public void setOnTime(Boolean onTime) {
		this.onTime = onTime;
	}

	/**
	 * Gets onTime.
	 * 
	 * @return onTime
	 */
	public Boolean getOnTime() {
		return onTime;
	}

	/**
	 * Sets onTime.
	 * 
	 * @param onTime
	 *            onTime
	 */
	public void setOnTime(boolean onTime) {
		setOnTime(new Boolean(onTime));
	}

	/**
	 * Checks if it is <code>true</code> or <code>false</code>.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean isOnTime() {
		return getOnTime().booleanValue();
	}

	/**
	 * Sets maxNumberOfBooks.
	 * 
	 * @param maxNumberOfBooks
	 *            maxNumberOfBooks
	 */
	public void setMaxNumberOfBooks(Integer maxNumberOfBooks) {
		this.maxNumberOfBooks = maxNumberOfBooks;
	}

	/**
	 * Gets maxNumberOfBooks.
	 * 
	 * @return maxNumberOfBooks
	 */
	public Integer getMaxNumberOfBooks() {
		return maxNumberOfBooks;
	}

	/**
	 * Sets returnDate.
	 * 
	 * @param returnDate
	 *            returnDate
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * Gets returnDate.
	 * 
	 * @return returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets bookItems.
	 * 
	 * @param bookItems
	 *            bookItems
	 */
	public void setBookItems(BookItems bookItems) {
		this.bookItems = bookItems;
		if (bookItems != null) {
			bookItems.setMember((Member) this);
		}
	}

	/**
	 * Gets bookItems.
	 * 
	 * @return bookItems
	 */
	public BookItems getBookItems() {
		if (bookItems == null) {
			Library library = (Library) getModel();
			BookItems bookItems = library.getBookItems();
			setBookItems(bookItems.getMemberBookItems((Member) this));
		}
		return bookItems;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}