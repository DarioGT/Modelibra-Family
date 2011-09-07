package dmeduc.weblink.url;

import java.util.Comparator;
import java.util.Date;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;
import org.modelibra.type.EasyDate;

import dmeduc.weblink.category.Category;

/**
 * Url entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-31
 */
@SuppressWarnings("serial")
public class Urls extends Entities<Url> {

	// Category parent neighbor (internal)
	private Category category;

	/**
	 * Constructs urls within the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public Urls(IDomainModel model) {
		super(model);
	}

	/**
	 * Constructs urls within the parent category.
	 * 
	 * @param category
	 *            category
	 */
	public Urls(Category category) {
		this(category.getModel());
		// parent
		this.category = category;
	}

	/**
	 * Retrieves the url with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return url
	 */
	public Url getUrl(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the url with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return url
	 */
	public Url getUrl(Long oidUniqueNumber) {
		return getUrl(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first url whose property with a property code is equal to a
	 * property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return url
	 */
	public Url getUrl(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Retrieves the url with a name. Null if not found.
	 * 
	 * @param name
	 *            name
	 * @return url
	 */
	public Url getUrlByName(String name) {
		return getUrl("name", name);
	}

	/**
	 * Selects urls whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return urls
	 */
	public Urls getUrls(String propertyCode, Object property) {
		return (Urls) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets urls ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered urls
	 */
	public Urls getUrls(String propertyCode, boolean ascending) {
		return (Urls) orderByProperty(propertyCode, ascending);
	}

	/**
	 * Gets urls selected by a selector. Returns empty urls if there are no urls
	 * that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected urls
	 */
	public Urls getUrls(ISelector selector) {
		return (Urls) selectBySelector(selector);
	}

	/**
	 * Gets urls ordered by a comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered urls
	 */
	public Urls getUrls(Comparator<Url> comparator, boolean ascending) {
		return (Urls) orderByComparator(comparator, ascending);
	}

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

	/*
	 * Neighbors
	 */

	/**
	 * Sets a category.
	 * 
	 * @param category
	 *            category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets a category.
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
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
	 * @return url
	 */
	public Url createUrl(Category categoryParent, String name, String link) {
		Url url = new Url(categoryParent);
		url.setName(name);
		url.setLink(link);
		if (!add(url)) {
			url = null;
		}
		return url;
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