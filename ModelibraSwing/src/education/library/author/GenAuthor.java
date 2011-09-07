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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import education.library.Library;
import education.library.book.Book;
import education.library.writer.Writer;
import education.library.writer.Writers;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Author generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public abstract class GenAuthor extends Entity<Author> {

	private static final long serialVersionUID = 1235856477617L;

	private static Log log = LogFactory.getLog(GenAuthor.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer sequence;

	/* ======= reference properties ======= */

	private Long writerOid;

	/* ======= internal parent neighbors ======= */

	private Book book;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Writer writer;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs author within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenAuthor(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs author within its parent(s).
	 * 
	 * @param Book
	 *            book
	 * @param Writer
	 *            writer
	 */
	public GenAuthor(Book book, Writer writer) {
		this(writer.getModel());
		// parents
		setBook(book);
		setWriter(writer);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets sequence.
	 * 
	 * @param sequence
	 *            sequence
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * Gets sequence.
	 * 
	 * @return sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets writerOid.
	 * 
	 * @param writerOid
	 *            writerOid
	 */
	public void setWriterOid(Long writerOid) {
		this.writerOid = writerOid;
		writer = null;
	}

	/**
	 * Gets writerOid.
	 * 
	 * @return writerOid
	 */
	public Long getWriterOid() {
		return writerOid;
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
	 * Sets writer.
	 * 
	 * @param writer
	 *            writer
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
		if (writer != null) {
			writerOid = writer.getOid().getUniqueNumber();
		} else {
			writerOid = null;
		}
	}

	/**
	 * Gets writer.
	 * 
	 * @return writer
	 */
	public Writer getWriter() {
		if (writer == null) {
			Library library = (Library) getModel();
			Writers writers = library.getWriters();
			if (writerOid != null) {
				writer = writers.getWriter(writerOid);
			}
		}
		return writer;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}