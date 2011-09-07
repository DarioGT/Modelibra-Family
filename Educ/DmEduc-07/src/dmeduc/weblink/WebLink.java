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
package dmeduc.weblink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;

import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.url.Url;
import dmeduc.weblink.url.Urls;

/**
 * WebLink specific model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-23
 */
public class WebLink extends GenWebLink {

	private static final long serialVersionUID = 1171894920490L;
	
	private static Log log = LogFactory.getLog(WebLink.class);
	
	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public WebLink(IDomain domain) {
		super(domain);
	}
	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	
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
