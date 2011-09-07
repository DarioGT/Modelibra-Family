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
package twoadw.generic.link;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.generic.categorylink.CategoryLink;	

/* ======= import external parent entity and entities classes ======= */


/**
 * Link specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Links extends GenLinks {
	
	private static final long serialVersionUID = 1236708354134L;

	private static Log log = LogFactory.getLog(Links.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs links within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Links(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs links for the categoryLink parent.
		 * 
		 * @param categoryLink
		 *            categoryLink
		 */
		public Links(CategoryLink categoryLink) {
			super(categoryLink);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates link.
		 * 
		 * @param categoryLinkParent
		 *            categoryLink parent
		 * @param url
		 *            url
		 * @param name
		 *            name
		 * @return link
		 */
		public Link createLink(CategoryLink categoryLinkParent, String url,
				String name, String description, Boolean published) {
			Link link = new Link(getModel());
			link.setCategoryLink(categoryLinkParent);
			link.setUrl(url);
			link.setName(name);
			link.setDescription(description);
			link.setPublished(published);
			if (!add(link)) {
				link = null;
			}
			return link;
		}
}