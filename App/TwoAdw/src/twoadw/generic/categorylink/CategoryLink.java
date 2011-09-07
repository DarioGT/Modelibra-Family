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

/* ======= import model class ======= */

import twoadw.generic.Generic;

/* ======= import internal parent entity class ======= */

import twoadw.generic.categorylink.CategoryLink;	

/* ======= import internal child entities classes ======= */

	import twoadw.generic.categorylink.CategoryLinks;	
	import twoadw.generic.link.Links;	

/* ======= import external parent entity and entities classes ======= */


/**
 * CategoryLink specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class CategoryLink extends GenCategoryLink {

	private static final long serialVersionUID = 1236708297990L;

	private static Log log = LogFactory.getLog(CategoryLink.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs categoryLink within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public CategoryLink(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		    /**
	     * Constructs categoryLink within its parent(s).
	     * 
	        		* @param CategoryLink categoryLink
			     */
	    public CategoryLink(
	    		    							CategoryLink categoryLink  
	    			    		    ) {
			super(
					    									categoryLink  
	    				    					);
	    }
    	
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    
}