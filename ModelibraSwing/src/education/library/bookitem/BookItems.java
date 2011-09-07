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
package education.library.bookitem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import education.library.book.Book;
import education.library.member.Member;

/**
 * BookItem specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class BookItems extends GenBookItems {

	private static final long serialVersionUID = 1235857282290L;

	private static Log log = LogFactory.getLog(BookItems.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs bookItems within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public BookItems(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs bookItems for the book parent.
	 * 
	 * @param book
	 *            book
	 */
	public BookItems(Book book) {
		super(book);
	}

	/**
	 * Constructs bookItems for the member parent.
	 * 
	 * @param member
	 *            member
	 */
	public BookItems(Member member) {
		super(member);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}