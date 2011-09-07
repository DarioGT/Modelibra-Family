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
 * BookItem specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class BookItem extends GenBookItem {

	private static final long serialVersionUID = 1235857282289L;

	private static Log log = LogFactory.getLog(BookItem.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs bookItem within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public BookItem(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs bookItem within its parent(s).
	 * 
	 * @param Book
	 *            book
	 * @param Member
	 *            member
	 */
	public BookItem(Book book, Member member) {
		super(book, member);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	
	public String getBookName() {
		return getBook().getName();
	}
	
	public void setMemberOid(Long memberOid) {
		super.setMemberOid(memberOid);
		notifyObservers(getConceptConfig().getPropertyConfig("memberOid"));
	}

}