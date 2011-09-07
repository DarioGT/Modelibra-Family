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
package education.library;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import education.library.author.Author;
import education.library.author.Authors;
import education.library.book.Book;
import education.library.book.Books;
import education.library.bookitem.BookItem;
import education.library.bookitem.BookItems;
import education.library.category.Categories;
import education.library.member.Members;
import education.library.writer.Writers;

/**
 * Library generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenLibrary extends DomainModel {

	private static final long serialVersionUID = 1233251517083L;

	private static Log log = LogFactory.getLog(GenLibrary.class);

	private Members members;

	private Books books;

	private Writers writers;

	private Categories categories;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenLibrary(IDomain domain) {
		super(domain);
		members = new Members(this);
		books = new Books(this);
		writers = new Writers(this);
		categories = new Categories(this);
	}

	/**
	 * Gets Member entities.
	 * 
	 * @return Member entities
	 */
	public Members getMembers() {
		return members;
	}

	/**
	 * Gets Book entities.
	 * 
	 * @return Book entities
	 */
	public Books getBooks() {
		return books;
	}

	/**
	 * Gets Writer entities.
	 * 
	 * @return Writer entities
	 */
	public Writers getWriters() {
		return writers;
	}

	/**
	 * Gets Category entities.
	 * 
	 * @return Category entities
	 */
	public Categories getCategories() {
		return categories;
	}

	/**
	 * Gets all Author entities.
	 * 
	 * @return Author entities
	 */
	public Authors getAuthors() {
		Authors allAuthors = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allAuthors = new Authors(this);
				allAuthors.setPersistent(false);
				allAuthors.setPre(false);
				allAuthors.setPost(false);
				Books books = getBooks();
				for (Book book : books) {
					Authors bookAuthors = book.getAuthors();
					for (Author author : bookAuthors) {
						allAuthors.add(author);
					}
				}
			} catch (Exception e) {
				log
						.error("Error in GenLibrary.getAuthors(): "
								+ e.getMessage());
			} finally {
				allAuthors.setPersistent(true);
				allAuthors.setPre(true);
				allAuthors.setPost(true);
			}
		}
		return allAuthors;
	}

	/**
	 * Gets all BookItem entities.
	 * 
	 * @return BookItem entities
	 */
	public BookItems getBookItems() {
		BookItems allBookItems = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allBookItems = new BookItems(this);
				allBookItems.setPersistent(false);
				allBookItems.setPre(false);
				allBookItems.setPost(false);
				Books books = getBooks();
				for (Book book : books) {
					BookItems bookBookItems = book.getBookItems();
					for (BookItem bookItem : bookBookItems) {
						allBookItems.add(bookItem);
					}
				}
			} catch (Exception e) {
				log.error("Error in GenLibrary.getBookItems(): "
						+ e.getMessage());
			} finally {
				allBookItems.setPersistent(true);
				allBookItems.setPre(true);
				allBookItems.setPost(true);
			}
		}
		return allBookItems;
	}

}
