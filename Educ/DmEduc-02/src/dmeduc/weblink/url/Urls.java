package dmeduc.weblink.url;

import java.util.Date;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;
import org.modelibra.type.EasyDate;

/**
 * Url entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-31
 */
@SuppressWarnings("serial")
public class Urls extends Entities<Url> {

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
	 * Retrieves the url with a given link. Null if not found.
	 * 
	 * @param link
	 *            link
	 * @return url
	 */
	public Url getUrlByLink(String link) {
		return retrieveByProperty("link", link);
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
	 * Post update.
	 * 
	 * @param beforeUrl
	 *            before url
	 * @param afterUrl
	 *            after url
	 * @return <code>true</code> if the post update is satisfied.
	 */
	@Override
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
	 * @param link
	 *            link
	 * @param category
	 *            category
	 * @return url
	 */
	public Url createUrl(String link, String category) {
		Url url = new Url(getModel());
		url.setLink(link);
		url.setCategory(category);
		if (!add(url)) {
			url = null;
		}
		return url;
	}

}