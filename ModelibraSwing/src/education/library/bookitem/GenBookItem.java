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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import education.library.Library;
import education.library.book.Book;
import education.library.member.Member;
import education.library.member.Members;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * BookItem generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenBookItem extends Entity<BookItem> {

	private static final long serialVersionUID = 1235857282287L;

	private static Log log = LogFactory.getLog(GenBookItem.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer number;

	private String note;

	/* ======= reference properties ======= */

	private Long memberOid;

	/* ======= internal parent neighbors ======= */

	private Book book;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Member member;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs bookItem within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenBookItem(IDomainModel model) {
		super(model);
		// internal child neighbors only
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
	public GenBookItem(Book book, Member member) {
		this(member.getModel());
		// parents
		setBook(book);
		setMember(member);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets number.
	 * 
	 * @param number
	 *            number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets number.
	 * 
	 * @return number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Sets note.
	 * 
	 * @param note
	 *            note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gets note.
	 * 
	 * @return note
	 */
	public String getNote() {
		return note;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets memberOid.
	 * 
	 * @param memberOid
	 *            memberOid
	 */
	public void setMemberOid(Long memberOid) {
		this.memberOid = memberOid;
		member = null;
	}

	/**
	 * Gets memberOid.
	 * 
	 * @return memberOid
	 */
	public Long getMemberOid() {
		return memberOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets book.
	 * 
	 * @param book
	 *            book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Gets book.
	 * 
	 * @return book
	 */
	public Book getBook() {
		return book;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets member.
	 * 
	 * @param member
	 *            member
	 */
	public void setMember(Member member) {
		this.member = member;
		if (member != null) {
			memberOid = member.getOid().getUniqueNumber();
		} else {
			memberOid = null;
		}
	}

	/**
	 * Gets member.
	 * 
	 * @return member
	 */
	public Member getMember() {
		if (member == null) {
			Library library = (Library) getModel();
			Members members = library.getMembers();
			if (memberOid != null) {
				member = members.getMember(memberOid);
			}
		}
		return member;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}