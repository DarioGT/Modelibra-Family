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

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import education.library.book.Book;
import education.library.member.Member;

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */

/**
 * BookItem generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenBookItems extends Entities<BookItem> {

	private static final long serialVersionUID = 1235857282288L;

	private static Log log = LogFactory.getLog(GenBookItems.class);

	/* ======= internal parent neighbors ======= */

	private Book book;

	/* ======= external parent neighbors ======= */

	private Member member;

	/* ======= base constructor ======= */

	/**
	 * Constructs bookItems within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenBookItems(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs bookItems for the book parent.
	 * 
	 * @param book
	 *            book
	 */
	public GenBookItems(Book book) {
		this(book.getModel());
		// parent
		setBook(book);
	}

	/**
	 * Constructs bookItems for the member parent.
	 * 
	 * @param member
	 *            member
	 */
	public GenBookItems(Member member) {
		this(member.getModel());
		// parent
		setMember(member);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the bookItem with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return bookItem
	 */
	public BookItem getBookItem(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the bookItem with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return bookItem
	 */
	public BookItem getBookItem(Long oidUniqueNumber) {
		return getBookItem(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first bookItem whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return bookItem
	 */
	public BookItem getBookItem(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects bookItems whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return bookItems
	 */
	public BookItems getBookItems(String propertyCode, Object property) {
		return (BookItems) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets bookItems ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered bookItems
	 */
	public BookItems getBookItems(String propertyCode, boolean ascending) {
		return (BookItems) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets bookItems selected by a selector. Returns empty bookItems if there
	 * are no bookItems that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected bookItems
	 */
	public BookItems getBookItems(ISelector selector) {
		return (BookItems) selectBySelector(selector);
	}

	/**
	 * Gets bookItems ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered bookItems
	 */
	public BookItems getBookItems(Comparator comparator, boolean ascending) {
		return (BookItems) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets number bookItem.
	 * 
	 * @param number
	 *            number
	 * @return number bookItem
	 */
	public BookItem getNumberBookItem(Integer number) {
		PropertySelector propertySelector = new PropertySelector("number");
		propertySelector.defineEqual(number);
		List<BookItem> list = getBookItems(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets member bookItems.
	 * 
	 * @param member
	 *            member oid unique number
	 * @return member bookItems
	 */
	public BookItems getMemberBookItems(Long member) {
		PropertySelector propertySelector = new PropertySelector("memberOid");
		propertySelector.defineEqual(member);
		return getBookItems(propertySelector);
	}

	/**
	 * Gets member bookItems.
	 * 
	 * @param member
	 *            member oid
	 * @return member bookItems
	 */
	public BookItems getMemberBookItems(Oid member) {
		return getMemberBookItems(member.getUniqueNumber());
	}

	/**
	 * Gets member bookItems.
	 * 
	 * @param member
	 *            member
	 * @return member bookItems
	 */
	public BookItems getMemberBookItems(Member member) {
		return getMemberBookItems(member.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets bookItems ordered by number.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered bookItems
	 */
	public BookItems getBookItemsOrderedByNumber(boolean ascending) {
		return getBookItems("number", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

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

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets member.
	 * 
	 * @param member
	 *            member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets member.
	 * 
	 * @return member
	 */
	public Member getMember() {
		return member;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(BookItem bookItem) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(bookItem)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Member member = getMember();
				if (member == null) {
					Member bookItemMember = bookItem.getMember();
					if (bookItemMember != null) {
						if (!bookItemMember.getBookItems().contain(bookItem)) {
							bookItemMember.getBookItems().setPropagateToSource(
									false);
							post = bookItemMember.getBookItems().add(bookItem);
							bookItemMember.getBookItems().setPropagateToSource(
									true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	protected boolean postRemove(BookItem bookItem) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(bookItem)) {
			Member member = getMember();
			if (member == null) {
				Member bookItemMember = bookItem.getMember();
				if (bookItemMember != null) {
					if (bookItemMember.getBookItems().contain(bookItem)) {
						bookItemMember.getBookItems().setPropagateToSource(
								false);
						post = bookItemMember.getBookItems().remove(bookItem);
						bookItemMember.getBookItems()
								.setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	protected boolean postUpdate(BookItem beforeBookItem, BookItem afterBookItem) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeBookItem, afterBookItem)) {
			Member member = getMember();
			if (member == null) {
				Member beforeBookItemMember = beforeBookItem.getMember();
				Member afterBookItemMember = afterBookItem.getMember();
				if (beforeBookItemMember == null && afterBookItemMember != null) {
					// attach
					if (!afterBookItemMember.getBookItems().contain(
							afterBookItem)) {
						afterBookItemMember.getBookItems()
								.setPropagateToSource(false);
						post = afterBookItemMember.getBookItems().add(
								afterBookItem);
						afterBookItemMember.getBookItems()
								.setPropagateToSource(true);
					}
				} else if (beforeBookItemMember != null
						&& afterBookItemMember == null) {
					// detach
					if (beforeBookItemMember.getBookItems().contain(
							beforeBookItem)) {
						beforeBookItemMember.getBookItems()
								.setPropagateToSource(false);
						post = beforeBookItemMember.getBookItems().remove(
								beforeBookItem);
						beforeBookItemMember.getBookItems()
								.setPropagateToSource(true);
					}
				} else if (beforeBookItemMember != null
						&& afterBookItemMember != null
						&& beforeBookItemMember != afterBookItemMember) {
					// detach
					if (beforeBookItemMember.getBookItems().contain(
							beforeBookItem)) {
						beforeBookItemMember.getBookItems()
								.setPropagateToSource(false);
						post = beforeBookItemMember.getBookItems().remove(
								beforeBookItem);
						beforeBookItemMember.getBookItems()
								.setPropagateToSource(true);
					}
					// attach
					if (!afterBookItemMember.getBookItems().contain(
							afterBookItem)) {
						afterBookItemMember.getBookItems()
								.setPropagateToSource(false);
						post = afterBookItemMember.getBookItems().add(
								afterBookItem);
						afterBookItemMember.getBookItems()
								.setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= create entity method ======= */

	/**
	 * Creates bookItem.
	 * 
	 * @param bookParent
	 *            book parent
	 * @param memberParent
	 *            member parent
	 * @param number
	 *            number
	 * @return bookItem
	 */
	public BookItem createBookItem(Book bookParent, Member memberParent,
			Integer number) {
		BookItem bookItem = new BookItem(getModel());
		bookItem.setBook(bookParent);
		bookItem.setMember(memberParent);
		bookItem.setNumber(number);
		if (!add(bookItem)) {
			bookItem = null;
		}
		return bookItem;
	}

}