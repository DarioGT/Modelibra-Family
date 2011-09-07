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
package education.library.review;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import education.library.book.Book;

/* ======= import external parent entity and entities classes ======= */

/**
 * Review specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class Reviews extends GenReviews {

	private static final long serialVersionUID = 1235857133374L;

	private static Log log = LogFactory.getLog(Reviews.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs reviews within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Reviews(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs reviews for the book parent.
	 * 
	 * @param book
	 *            book
	 */
	public Reviews(Book book) {
		super(book);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}