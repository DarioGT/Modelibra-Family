package dmeduc.wicket.weblink.url;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import dmeduc.weblink.category.Category;
import dmeduc.weblink.url.Url;

/**
 * Url display list.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class UrlDisplayList extends ListView {

	/**
	 * Constructs the Category page link panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param urls
	 *            urls
	 */
	public UrlDisplayList(String wicketId, Category category) {
		super(wicketId,
				new PropertyModel(category, "approvedUrlsOrderedByName"));
	}

	/**
	 * Populates the list with a url's web link.
	 * 
	 * @param item
	 *            list item
	 */
	protected void populateItem(ListItem item) {
		Url url = (Url) item.getModelObject();
		item.add(new ExternalLink("urlLink", url.getLink(), url.getName()));
	}

}
