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
package education.library.book;

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

import education.library.author.Author;
import education.library.author.Authors;
import education.library.category.Category;
import education.library.writer.Writer;

/**
 * Book generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenBooks extends Entities<Book> {

	private static final long serialVersionUID = 1234736431785L;

	private static Log log = LogFactory.getLog(GenBooks.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Category category;

	/* ======= base constructor ======= */

	/**
	 * Constructs books within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenBooks(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs books for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public GenBooks(Category category) {
		this(category.getModel());
		// parent
		setCategory(category);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the book with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return book
	 */
	public Book getBook(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the book with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return book
	 */
	public Book getBook(Long oidUniqueNumber) {
		return getBook(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first book whose property with a property code is equal to
	 * a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return book
	 */
	public Book getBook(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects books whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return books
	 */
	public Books getBooks(String propertyCode, Object property) {
		return (Books) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets books ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered books
	 */
	public Books getBooks(String propertyCode, boolean ascending) {
		return (Books) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets books selected by a selector. Returns empty books if there are no
	 * books that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected books
	 */
	public Books getBooks(ISelector selector) {
		return (Books) selectBySelector(selector);
	}

	/**
	 * Gets books ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered books
	 */
	public Books getBooks(Comparator comparator, boolean ascending) {
		return (Books) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets name books.
	 * 
	 * @param name
	 *            name
	 * @return name books
	 */
	public Books getNameBooks(String name) {
		PropertySelector propertySelector = new PropertySelector("name");
		propertySelector.defineEqual(name);
		return getBooks(propertySelector);
	}

	/**
	 * Gets edition books.
	 * 
	 * @param edition
	 *            edition
	 * @return edition books
	 */
	public Books getEditionBooks(String edition) {
		PropertySelector propertySelector = new PropertySelector("edition");
		propertySelector.defineEqual(edition);
		return getBooks(propertySelector);
	}

	/**
	 * Gets year books.
	 * 
	 * @param year
	 *            year
	 * @return year books
	 */
	public Books getYearBooks(Integer year) {
		PropertySelector propertySelector = new PropertySelector("year");
		propertySelector.defineEqual(year);
		return getBooks(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets isbn book.
	 * 
	 * @param isbn
	 *            isbn
	 * @return isbn book
	 */
	public Book getIsbnBook(String isbn) {
		PropertySelector propertySelector = new PropertySelector("isbn");
		propertySelector.defineEqual(isbn);
		List<Book> list = getBooks(propertySelector).getList();

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
	 * Gets category books.
	 * 
	 * @param category
	 *            category oid unique number
	 * @return category books
	 */
	public Books getCategoryBooks(Long category) {
		PropertySelector propertySelector = new PropertySelector("categoryOid");
		propertySelector.defineEqual(category);
		return getBooks(propertySelector);
	}

	/**
	 * Gets category books.
	 * 
	 * @param category
	 *            category oid
	 * @return category books
	 */
	public Books getCategoryBooks(Oid category) {
		return getCategoryBooks(category.getUniqueNumber());
	}

	/**
	 * Gets category books.
	 * 
	 * @param category
	 *            category
	 * @return category books
	 */
	public Books getCategoryBooks(Category category) {
		return getCategoryBooks(category.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets books ordered by isbn.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered books
	 */
	public Books getBooksOrderedByIsbn(boolean ascending) {
		return getBooks("isbn", ascending);
	}

	/**
	 * Gets books ordered by name.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered books
	 */
	public Books getBooksOrderedByName(boolean ascending) {
		return getBooks("name", ascending);
	}

	/**
	 * Gets books ordered by edition.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered books
	 */
	public Books getBooksOrderedByEdition(boolean ascending) {
		return getBooks("edition", ascending);
	}

	/**
	 * Gets books ordered by year.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered books
	 */
	public Books getBooksOrderedByYear(boolean ascending) {
		return getBooks("year", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/**
	 * Gets writer authors.
	 * 
	 * @return writer authors
	 */
	public Authors getWriterAuthors(Writer writer) {
		Authors authors = new Authors(writer);
		authors.setPersistent(false);
		for (Book book : this) {
			Author author = book.getAuthors().getAuthor(book, writer);
			if (author != null) {
				authors.add(author);
			}
		}
		return authors;
	}

	/* ======= internal parent set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Book book) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(book)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Category category = getCategory();
				if (category == null) {
					Category bookCategory = book.getCategory();
					if (bookCategory != null) {
						if (!bookCategory.getBooks().contain(book)) {
							bookCategory.getBooks().setPropagateToSource(false);
							post = bookCategory.getBooks().add(book);
							bookCategory.getBooks().setPropagateToSource(true);
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

	protected boolean postRemove(Book book) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(book)) {
			Category category = getCategory();
			if (category == null) {
				Category bookCategory = book.getCategory();
				if (bookCategory != null) {
					if (bookCategory.getBooks().contain(book)) {
						bookCategory.getBooks().setPropagateToSource(false);
						post = bookCategory.getBooks().remove(book);
						bookCategory.getBooks().setPropagateToSource(true);
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

	protected boolean postUpdate(Book beforeBook, Book afterBook) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeBook, afterBook)) {
			Category category = getCategory();
			if (category == null) {
				Category beforeBookCategory = beforeBook.getCategory();
				Category afterBookCategory = afterBook.getCategory();
				if (beforeBookCategory == null && afterBookCategory != null) {
					// attach
					if (!afterBookCategory.getBooks().contain(afterBook)) {
						afterBookCategory.getBooks()
								.setPropagateToSource(false);
						post = afterBookCategory.getBooks().add(afterBook);
						afterBookCategory.getBooks().setPropagateToSource(true);
					}
				} else if (beforeBookCategory != null
						&& afterBookCategory == null) {
					// detach
					if (beforeBookCategory.getBooks().contain(beforeBook)) {
						beforeBookCategory.getBooks().setPropagateToSource(
								false);
						post = beforeBookCategory.getBooks().remove(beforeBook);
						beforeBookCategory.getBooks()
								.setPropagateToSource(true);
					}
				} else if (beforeBookCategory != null
						&& afterBookCategory != null
						&& beforeBookCategory != afterBookCategory) {
					// detach
					if (beforeBookCategory.getBooks().contain(beforeBook)) {
						beforeBookCategory.getBooks().setPropagateToSource(
								false);
						post = beforeBookCategory.getBooks().remove(beforeBook);
						beforeBookCategory.getBooks()
								.setPropagateToSource(true);
					}
					// attach
					if (!afterBookCategory.getBooks().contain(afterBook)) {
						afterBookCategory.getBooks()
								.setPropagateToSource(false);
						post = afterBookCategory.getBooks().add(afterBook);
						afterBookCategory.getBooks().setPropagateToSource(true);
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
	 * Creates book.
	 * 
	 * @param categoryParent
	 *            category parent
	 * @param isbn
	 *            isbn
	 * @param name
	 *            name
	 * @param edition
	 *            edition
	 * @param year
	 *            year
	 * @return book
	 */
	public Book createBook(Category categoryParent, String isbn, String name,
			String edition, Integer year) {
		Book book = new Book(getModel());
		book.setCategory(categoryParent);
		book.setIsbn(isbn);
		book.setName(name);
		book.setEdition(edition);
		book.setYear(year);
		if (!add(book)) {
			book = null;
		}
		return book;
	}

}