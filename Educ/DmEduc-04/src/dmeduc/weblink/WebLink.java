package dmeduc.weblink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.comment.Comments;
import dmeduc.weblink.url.Url;
import dmeduc.weblink.url.Urls;

/**
 * WebLink domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-12
 */
@SuppressWarnings("serial")
public class WebLink extends DomainModel {

	private static Log log = LogFactory.getLog(WebLink.class);

	private Categories categories;

	private Comments comments;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public WebLink(IDomain domain) {
		super(domain);
		categories = new Categories(this);
		comments = new Comments(this);
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
	 * Gets Comment entities.
	 * 
	 * @return Comment entities
	 */
	public Comments getComments() {
		return comments;
	}

	/**
	 * Gets urls for all categories.
	 * 
	 * @return all urls
	 */
	public Urls getUrls() {
		Urls allUrls = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allUrls = new Urls(this);
				allUrls.setPersistent(false);
				allUrls.setPre(false);
				allUrls.setPost(false);
				Categories categories = getCategories();
				for (Category category : categories) {
					Urls categoryUrls = category.getUrls();
					for (Url url : categoryUrls) {
						allUrls.add(url);
					}
				}
			} catch (Exception e) {
				log.error("Error in WebLink.getUrls: " + e.getMessage());
			} finally {
				allUrls.setPersistent(true);
				allUrls.setPre(true);
				allUrls.setPost(true);
			}
		}
		return allUrls;
	}

}
