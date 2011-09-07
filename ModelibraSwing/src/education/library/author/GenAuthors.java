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
package education.library.author;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

import education.library.book.Book;
import education.library.writer.Writer;

/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */

/**
 * Author generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenAuthors extends Entities<Author> {

	private static final long serialVersionUID = 1235856477618L;

	private static Log log = LogFactory.getLog(GenAuthors.class);

	/* ======= internal parent neighbors ======= */

	private Book book;

	/* ======= external parent neighbors ======= */

	private Writer writer;

	/* ======= base constructor ======= */

	/**
	 * Constructs authors within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAuthors(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs authors for the book parent.
	 * 
	 * @param book
	 *            book
	 */
	public GenAuthors(Book book) {
		this(book.getModel());
		// parent
		setBook(book);
	}

	/**
	 * Constructs authors for the writer parent.
	 * 
	 * @param writer
	 *            writer
	 */
	public GenAuthors(Writer writer) {
		this(writer.getModel());
		// parent
		setWriter(writer);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the author with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return author
	 */
	public Author getAuthor(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the author with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return author
	 */
	public Author getAuthor(Long oidUniqueNumber) {
		return getAuthor(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first author whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return author
	 */
	public Author getAuthor(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects authors whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return authors
	 */
	public Authors getAuthors(String propertyCode, Object property) {
		return (Authors) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets authors ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered authors
	 */
	public Authors getAuthors(String propertyCode, boolean ascending) {
		return (Authors) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets authors selected by a selector. Returns empty authors if there are
	 * no authors that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected authors
	 */
	public Authors getAuthors(ISelector selector) {
		return (Authors) selectBySelector(selector);
	}

	/**
	 * Gets authors ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered authors
	 */
	public Authors getAuthors(Comparator comparator, boolean ascending) {
		return (Authors) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/**
	 * Gets author based on many-to-many parents.
	 * 
	 * @param Book
	 *            book
	 * @param Writer
	 *            writer
	 */
	public Author getAuthor(Book book, Writer writer) {
		for (Author author : this) {
			if (author.getBook() == book && author.getWriter() == writer) {
				return author;
			}
		}
		return null;
	}

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
	 * Sets writer.
	 * 
	 * @param writer
	 *            writer
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	/**
	 * Gets writer.
	 * 
	 * @return writer
	 */
	public Writer getWriter() {
		return writer;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	protected boolean postAdd(Author author) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(author)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Book book = getBook();
				if (book == null) {
					Book authorBook = author.getBook();
					if (!authorBook.getAuthors().contain(author)) {
						post = authorBook.getAuthors().add(author);
					}
				}
				Writer writer = getWriter();
				if (writer == null) {
					Writer authorWriter = author.getWriter();
					if (!authorWriter.getAuthors().contain(author)) {
						post = authorWriter.getAuthors().add(author);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post remove propagation ======= */

	protected boolean postRemove(Author author) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(author)) {
			Book book = getBook();
			if (book == null) {
				Book authorBook = author.getBook();
				if (authorBook.getAuthors().contain(author)) {
					post = authorBook.getAuthors().remove(author);
				}
			}
			Writer writer = getWriter();
			if (writer == null) {
				Writer authorWriter = author.getWriter();
				if (authorWriter.getAuthors().contain(author)) {
					post = authorWriter.getAuthors().remove(author);
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post update propagation ======= */

	protected boolean postUpdate(Author beforeAuthor, Author afterAuthor) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeAuthor, afterAuthor)) {
			Book beforeAuthorBook = beforeAuthor.getBook();
			Book afterAuthorBook = afterAuthor.getBook();

			if (beforeAuthorBook != afterAuthorBook) {
				post = beforeAuthorBook.getAuthors().remove(beforeAuthor);
				if (post) {
					post = afterAuthorBook.getAuthors().add(afterAuthor);
					if (!post) {
						beforeAuthorBook.getAuthors().add(beforeAuthor);
					}
				}
			}
			Writer beforeAuthorWriter = beforeAuthor.getWriter();
			Writer afterAuthorWriter = afterAuthor.getWriter();

			if (beforeAuthorWriter != afterAuthorWriter) {
				post = beforeAuthorWriter.getAuthors().remove(beforeAuthor);
				if (post) {
					post = afterAuthorWriter.getAuthors().add(afterAuthor);
					if (!post) {
						beforeAuthorWriter.getAuthors().add(beforeAuthor);
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
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates author.
	 * 
	 * @param bookParent
	 *            book parent
	 * @param writerParent
	 *            writer parent
	 * @return author
	 */
	public Author createAuthor(Book bookParent, Writer writerParent) {
		Author author = new Author(getModel());
		author.setBook(bookParent);
		author.setWriter(writerParent);
		if (!add(author)) {
			author = null;
		}
		return author;
	}

}