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
import org.modelibra.IDomainModel;

import education.library.book.Book;
import education.library.writer.Writer;

/**
 * Author specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-04
 */
public class Authors extends GenAuthors {

	private static final long serialVersionUID = 1235856477620L;

	private static Log log = LogFactory.getLog(Authors.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs authors within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Authors(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs authors for the book parent.
	 * 
	 * @param book
	 *            book
	 */
	public Authors(Book book) {
		super(book);
	}

	/**
	 * Constructs authors for the writer parent.
	 * 
	 * @param writer
	 *            writer
	 */
	public Authors(Writer writer) {
		super(writer);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}