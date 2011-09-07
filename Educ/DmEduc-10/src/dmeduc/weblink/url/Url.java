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
package dmeduc.weblink.url;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.type.EasyDate;

import dmeduc.weblink.category.Category;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Url specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public class Url extends GenUrl {

	private static final long serialVersionUID = 1171894920492L;

	private static Log log = LogFactory.getLog(Url.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs url within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Url(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs url within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public Url(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Checks if it is created today.
	 * 
	 * @return <code>true</code> if it is created today
	 */
	public boolean isCreatedToday() {
		EasyDate easyToday = new EasyDate(new Date());
		if (easyToday.equals(getCreationDate())) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if it is created and approved today.
	 * 
	 * @return <code>true</code> if it is created and approved today
	 */
	public boolean isCreatedAndApprovedToday() {
		if (isApproved() && isCreatedToday()) {
			return true;
		}
		return false;
	}

}