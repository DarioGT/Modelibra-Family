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
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Member specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class Member extends GenMember {

	private static final long serialVersionUID = 1233251560118L;

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

	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
		notifyObservers(getConceptConfig().getPropertyConfig("firstName"));
	}

	public void setLastName(String lastName) {
		super.setLastName(lastName);
		notifyObservers(getConceptConfig().getPropertyConfig("lastName"));
	}

	public void setEmail(String email) {
		super.setEmail(email);
		notifyObservers(getConceptConfig().getPropertyConfig("email"));
	}

	public void setOnTime(Boolean onTime) {
		super.setOnTime(onTime);
		notifyObservers(getConceptConfig().getPropertyConfig("onTime"));
	}

	public void setMaxNumberOfBooks(Integer maxNumberOfBooks) {
		super.setMaxNumberOfBooks(maxNumberOfBooks);
		notifyObservers(getConceptConfig()
				.getPropertyConfig("maxNumberOfBooks"));
	}

	public void setReturnDate(Date returnDate) {
		super.setReturnDate(returnDate);
		notifyObservers(getConceptConfig().getPropertyConfig("returnDate"));
	}

}