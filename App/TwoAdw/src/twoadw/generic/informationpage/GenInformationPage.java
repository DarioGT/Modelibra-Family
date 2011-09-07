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
package twoadw.generic.informationpage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.generic.Generic;

/* ======= import property classes ======= */


/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */


/* ======= import external many-to-many internal parent entities classes ======= */


/**
 * InformationPage generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-27
 */
public abstract class GenInformationPage extends Entity<InformationPage> {

	private static final long serialVersionUID = 1236708441723L;

	private static Log log = LogFactory.getLog(GenInformationPage.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String codePage;
	
    	    private String title;
	
    	    private String page;
	
    	    private Boolean published;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs informationPage within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInformationPage(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets codePage.
		 * 
		 * @param codePage
		 *            codePage
		 */
		public void setCodePage(String codePage) {
			this.codePage = codePage;
		}
		
		/**
		 * Gets codePage.
		 * 
		 * @return codePage
		 */
		public String getCodePage() {
			return codePage;
		}  
		
				    		/**
		 * Sets title.
		 * 
		 * @param title
		 *            title
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		
		/**
		 * Gets title.
		 * 
		 * @return title
		 */
		public String getTitle() {
			return title;
		}  
		
				    		/**
		 * Sets page.
		 * 
		 * @param page
		 *            page
		 */
		public void setPage(String page) {
			this.page = page;
		}
		
		/**
		 * Gets page.
		 * 
		 * @return page
		 */
		public String getPage() {
			return page;
		}  
		
				    		/**
		 * Sets published.
		 * 
		 * @param published
		 *            published
		 */
		public void setPublished(Boolean published) {
			this.published = published;
		}
		
		/**
		 * Gets published.
		 * 
		 * @return published
		 */
		public Boolean getPublished() {
			return published;
		}  
		
							/**
		     * Sets published.
		     * 
		     * @param published
		     *            published
		     */
			public void setPublished(boolean published) {
				setPublished(new Boolean(published));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isPublished() {
				return getPublished().booleanValue();
			}
			
		        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}