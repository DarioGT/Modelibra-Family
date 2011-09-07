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
package education.library.category;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import education.library.Library;
import education.library.book.Books;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Category generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenCategory extends Entity<Category> {

	private static final long serialVersionUID = 1235856986413L;

	private static Log log = LogFactory.getLog(GenCategory.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String note;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private Category category;

	/* ======= internal child neighbors ======= */

	private Categories categories;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Books books;

	/* ======= base constructor ======= */

	/**
	 * Constructs category within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenCategory(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setCategories(new Categories((Category) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs category within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public GenCategory(Category category) {
		this(category.getModel());
		// parents
		setCategory(category);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
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

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

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

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets categories.
	 * 
	 * @param categories
	 *            categories
	 */
	public void setCategories(Categories categories) {
		this.categories = categories;
		if (categories != null) {
			categories.setCategory((Category) this);
		}
	}

	/**
	 * Gets categories.
	 * 
	 * @return categories
	 */
	public Categories getCategories() {
		return categories;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets books.
	 * 
	 * @param books
	 *            books
	 */
	public void setBooks(Books books) {
		this.books = books;
		if (books != null) {
			books.setCategory((Category) this);
		}
	}

	/**
	 * Gets books.
	 * 
	 * @return books
	 */
	public Books getBooks() {
		if (books == null) {
			Library library = (Library) getModel();
			Books books = library.getBooks();
			setBooks(books.getCategoryBooks((Category) this));
		}
		return books;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}