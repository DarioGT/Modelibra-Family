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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import education.library.Library;
import education.library.author.Authors;
import education.library.bookitem.BookItems;
import education.library.category.Categories;
import education.library.category.Category;
import education.library.review.Reviews;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Book generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenBook extends Entity<Book> {

	private static final long serialVersionUID = 1234736431784L;

	private static Log log = LogFactory.getLog(GenBook.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String isbn;

	private String name;

	private String edition;

	private Integer year;

	private Float price;

	private String webLink;

	private String description;

	/* ======= reference properties ======= */

	private Long categoryOid;

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	private Authors authors;

	private BookItems bookItems;

	private Reviews reviews;

	/* ======= external parent neighbors ======= */

	private Category category;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs book within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenBook(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setAuthors(new Authors((Book) this));
		setBookItems(new BookItems((Book) this));
		setReviews(new Reviews((Book) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs book within its parent(s).
	 * 
	 * @param Category
	 *            category
	 */
	public GenBook(Category category) {
		this(category.getModel());
		// parents
		setCategory(category);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets isbn.
	 * 
	 * @param isbn
	 *            isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Gets isbn.
	 * 
	 * @return isbn
	 */
	public String getIsbn() {
		return isbn;
	}

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
	 * Sets edition.
	 * 
	 * @param edition
	 *            edition
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * Gets edition.
	 * 
	 * @return edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * Sets year.
	 * 
	 * @param year
	 *            year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets year.
	 * 
	 * @return year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets price.
	 * 
	 * @param price
	 *            price
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * Gets price.
	 * 
	 * @return price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * Sets webLink.
	 * 
	 * @param webLink
	 *            webLink
	 */
	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	/**
	 * Gets webLink.
	 * 
	 * @return webLink
	 */
	public String getWebLink() {
		return webLink;
	}

	/**
	 * Sets description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets categoryOid.
	 * 
	 * @param categoryOid
	 *            categoryOid
	 */
	public void setCategoryOid(Long categoryOid) {
		this.categoryOid = categoryOid;
		category = null;
	}

	/**
	 * Gets categoryOid.
	 * 
	 * @return categoryOid
	 */
	public Long getCategoryOid() {
		return categoryOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets authors.
	 * 
	 * @param authors
	 *            authors
	 */
	public void setAuthors(Authors authors) {
		this.authors = authors;
		if (authors != null) {
			authors.setBook((Book) this);
		}
	}

	/**
	 * Gets authors.
	 * 
	 * @return authors
	 */
	public Authors getAuthors() {
		return authors;
	}

	/**
	 * Sets bookItems.
	 * 
	 * @param bookItems
	 *            bookItems
	 */
	public void setBookItems(BookItems bookItems) {
		this.bookItems = bookItems;
		if (bookItems != null) {
			bookItems.setBook((Book) this);
		}
	}

	/**
	 * Gets bookItems.
	 * 
	 * @return bookItems
	 */
	public BookItems getBookItems() {
		return bookItems;
	}

	/**
	 * Sets reviews.
	 * 
	 * @param reviews
	 *            reviews
	 */
	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
		if (reviews != null) {
			reviews.setBook((Book) this);
		}
	}

	/**
	 * Gets reviews.
	 * 
	 * @return reviews
	 */
	public Reviews getReviews() {
		return reviews;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
		if (category != null) {
			categoryOid = category.getOid().getUniqueNumber();
		} else {
			categoryOid = null;
		}
	}

	/**
	 * Gets category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		if (category == null) {
			Library library = (Library) getModel();
			Categories categories = library.getCategories();
			if (categoryOid != null) {
				category = categories.getReflexiveCategory(categoryOid);
			}
		}
		return category;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}