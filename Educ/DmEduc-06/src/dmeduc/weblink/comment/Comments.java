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
package dmeduc.weblink.comment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.PropertySelector;
import org.modelibra.type.EasyDate;

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Comment specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-10
 */
public class Comments extends GenComments {

	private static final long serialVersionUID = 1171894920501L;

	private static Log log = LogFactory.getLog(Comments.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs comments within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Comments(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Gets comments that have the same, given source.
	 * 
	 * @return selected comments
	 */
	public Comments getSourceComments(String source) {
		return getComments("source", source);
	}

	/**
	 * Gets comments without source.
	 * 
	 * @return selected comments
	 */
	public Comments getCommentsWithoutSource() {
		PropertySelector propertySelector = new PropertySelector("source");
		propertySelector.defineNull();
		return getComments(propertySelector);
	}

	/**
	 * Gets comments selected by a keyword.
	 * 
	 * @param keyword
	 *            keyword
	 * @return selected comments
	 */
	public Comments getKeywordComments(String keyword) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineContain(keyword);
		return getComments(propertySelector);
	}

	/**
	 * Gets comments that begin with a keyword.
	 * 
	 * @param keyword
	 *            keyword
	 * @return selected comments
	 */
	public Comments getBeginKeywordComments(String keyword) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineBegin(keyword);
		return getComments(propertySelector);
	}

	/**
	 * Gets comments ordered by creation date.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered comments
	 */
	public Comments getCommentsOrderedByCreationDate(boolean ascending) {
		return getComments("creationDate", ascending);
	}

	/**
	 * Gets comments ordered by source.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered comments
	 */
	public Comments getCommentsOrderedBySource(boolean ascending) {
		return getComments("source", ascending);
	}

	/**
	 * Creates comment.
	 * 
	 * @param text
	 *            text
	 * @param easyDate
	 *            easy date
	 * @return comment
	 */
	public Comment createComment(String text, EasyDate easyDate) {
		Comment comment = new Comment(getModel());
		comment.setText(text);
		comment.setCreationDate(easyDate.getDate());
		if (!add(comment)) {
			comment = null;
		}
		return comment;
	}

}