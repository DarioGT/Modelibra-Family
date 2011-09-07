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
package dmeduc.weblink.url;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.PropertySelector;

import dmeduc.weblink.category.Category;

/* ======= import external parent entity and entities classes ======= */

/**
 * Url specific entities.
 * 
 * @author unknown
 * @version 2008-12-02
 */
public class Urls extends GenUrls {

	private static final long serialVersionUID = 1228241321994L;

	private static Log log = LogFactory.getLog(Urls.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs urls within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Urls(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs urls for the category parent.
	 * 
	 * @param category
	 *            category
	 */
	public Urls(Category category) {
		super(category);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Creates url.
	 * 
	 * @param categoryParent
	 *            category parent
	 * @param link
	 *            link
	 * @param description
	 *            description
	 * @return url
	 */
	public Url createUrl(Category categoryParent, String link,
			String description) {
		Url url = new Url(getModel());
		url.setCategory(categoryParent);
		url.setLink(link);
		url.setDescription(description);
		if (!add(url)) {
			url = null;
		}
		return url;
	}

	/**
	 * Gets urls that contain a keyword.
	 * 
	 * @param keyword
	 *            keyword
	 * @return selected urls
	 */
	public Urls getKeywordUrls(String keyword) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContain(keyword);
		return getUrls(propertySelector);
	}

	/**
	 * Gets urls that contain some keywords.
	 * 
	 * @param keywords
	 *            keywords
	 * @return selected urls
	 */
	public Urls getSomeKeywordUrls(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContainSome(keywords);
		return getUrls(propertySelector);
	}

	/**
	 * Gets urls that contain all keywords.
	 * 
	 * @param keywords
	 *            keywords
	 * @return selected urls
	 */
	public Urls getAllKeywordUrls(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContainAll(keywords);
		return getUrls(propertySelector);
	}

}