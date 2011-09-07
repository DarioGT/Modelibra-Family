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
package twoadw.generic.categorylink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.generic.categorylink.CategoryLink;	

/* ======= import external parent entity and entities classes ======= */


/**
 * CategoryLink specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class CategoryLinks extends GenCategoryLinks {
	
	private static final long serialVersionUID = 1236708297991L;

	private static Log log = LogFactory.getLog(CategoryLinks.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs categoryLinks within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public CategoryLinks(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs categoryLinks for the categoryLink parent.
		 * 
		 * @param categoryLink
		 *            categoryLink
		 */
		public CategoryLinks(CategoryLink categoryLink) {
			super(categoryLink);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
		/**
		 * Creates categoryLink.
		 * 
		 * @param categoryLinkParent
		 *            categoryLink parent
		 * @param name
		 *            name
		 * @return categoryLink
		 */
		public CategoryLink createCategoryLink(CategoryLink categoryLinkParent,
				String name, Boolean published) {
			CategoryLink categoryLink = new CategoryLink(getModel());
			categoryLink.setCategoryLink(categoryLinkParent);
			categoryLink.setName(name);
			categoryLink.setPublished(published);
			if (!add(categoryLink)) {
				categoryLink = null;
			}
			return categoryLink;
		}
}