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

import java.util.Date;

import org.modelibra.IDomainModel;
import org.modelibra.PropertySelector;
import org.modelibra.type.EasyDate;

import dmeduc.weblink.category.Category;

/* ======= import external parent entity and entities classes ======= */

/**
 * Url specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-31
 */
public class Urls extends GenUrls {

	private static final long serialVersionUID = 1171894920493L;

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
	 * Gets approved urls.
	 * 
	 * @return selected urls
	 */
	public Urls getApprovedUrls() {
		PropertySelector propertySelector = new PropertySelector("approved");
		propertySelector.defineEqual(Boolean.TRUE);
		return getUrls(propertySelector);
	}

	/**
	 * Gets not approved urls.
	 * 
	 * @return selected urls
	 */
	public Urls getNotApprovedUrls() {
		PropertySelector propertySelector = new PropertySelector("approved");
		propertySelector.defineEqual(Boolean.FALSE);
		return getUrls(propertySelector);
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

	/**
	 * Gets recently created urls.
	 * 
	 * @param beforeRecentDate
	 *            before recent date
	 * @return selected urls
	 */
	public Urls getRecentlyCreatedUrls(Date beforeRecentDate) {
		PropertySelector propertySelector = new PropertySelector("creationDate");
		propertySelector.defineGreaterThan(beforeRecentDate);
		return getUrls(propertySelector);
	}

	/**
	 * Gets the last month urls.
	 * 
	 * @return selected urls
	 */
	public Urls getLastMonthUrls() {
		Date date = new Date();
		EasyDate easyDate = new EasyDate(date);
		return getRecentlyCreatedUrls(easyDate.getPreviousMonthDate());
	}

	/**
	 * Gets today`s urls.
	 * 
	 * @return selected urls
	 */
	public Urls getTodayUrls() {
		Date today = new Date();
		EasyDate easyToday = new EasyDate(today);
		Date yesterday = easyToday.getPreviousDayDate();
		return getRecentlyCreatedUrls(yesterday);
	}

	/**
	 * Gets the first link creation date.
	 * 
	 * @return first link creation date
	 */
	public Date getFirstCreationDate() {
		Date firstCreationDate = null;
		Urls orderByCreationDate = getUrlsOrderedByCreationDate(true);
		if (orderByCreationDate.size() > 0) {
			Url webLink = (Url) orderByCreationDate.first();
			firstCreationDate = webLink.getCreationDate();
		}
		return firstCreationDate;
	}

	/**
	 * Gets urls created and approved today.
	 * 
	 * @return selected urls
	 */
	public Urls getUrlsCreatedAndApprovedToday() {
		return (Urls) selectByMethod("isCreatedAndApprovedToday", null);
	}

	/**
	 * Gets urls ordered by name.
	 * 
	 * @return ordered urls
	 */
	public Urls getUrlsOrderedByName() {
		return getUrls("name", true);
	}

	/**
	 * Gets urls ordered by creation date.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered urls
	 */
	public Urls getUrlsOrderedByCreationDate(boolean ascending) {
		return getUrls("creationDate", ascending);
	}

	/**
	 * Post update.
	 * 
	 * @param beforeUrl
	 *            before url
	 * @param afterUrl
	 *            after url
	 * @return <code>true</code> if the post update is satisfied.
	 */
	protected boolean postUpdate(Url beforeUrl, Url afterUrl) {
		if (super.postUpdate(beforeUrl, afterUrl)) {
			Date today = new Date();
			Date updateDate = afterUrl.getUpdateDate();
			EasyDate easyToday = new EasyDate(today);
			EasyDate easyUpdateDate = new EasyDate(updateDate);
			if (!easyToday.equals(easyUpdateDate)) {
				Url afterAfterEntity = afterUrl.copy();
				afterAfterEntity.setUpdateDate(today);
				return update(afterUrl, afterAfterEntity);
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates url.
	 * 
	 * @param categoryParent
	 *            category parent
	 * @param name
	 *            name
	 * @param link
	 *            link
	 * @param description
	 *            description
	 * @param easyDate
	 *            easy date
	 * @param approved
	 *            approved
	 * @param category
	 *            category
	 * @return url
	 */
	public Url createUrl(Category categoryParent, String name, String link,
			String description, EasyDate easyDate, boolean approved) {
		Url url = new Url(categoryParent);
		url.setName(name);
		url.setLink(link);
		url.setDescription(description);
		url.setCreationDate(easyDate.getDate());
		url.setApproved(approved);
		if (!add(url)) {
			url = null;
		}
		return url;
	}

}