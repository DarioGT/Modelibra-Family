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
package twoadw.generic.globalconfiguration;

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
 * GlobalConfiguration generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenGlobalConfiguration extends Entity<GlobalConfiguration> {

	private static final long serialVersionUID = 1236798670103L;

	private static Log log = LogFactory.getLog(GenGlobalConfiguration.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String siteName;
	
    	    private String organisationName;
	
    	    private Boolean online;
	
    	    private String offlineMessage;
	
    	    private String template;
	
    	    private String siteMetaDescription;
	
    	    private String siteMetaKeywords;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs globalConfiguration within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenGlobalConfiguration(IDomainModel model) {
		super(model);
		// internal child neighbors only
			}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets siteName.
		 * 
		 * @param siteName
		 *            siteName
		 */
		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}
		
		/**
		 * Gets siteName.
		 * 
		 * @return siteName
		 */
		public String getSiteName() {
			return siteName;
		}  
		
				    		/**
		 * Sets organisationName.
		 * 
		 * @param organisationName
		 *            organisationName
		 */
		public void setOrganisationName(String organisationName) {
			this.organisationName = organisationName;
		}
		
		/**
		 * Gets organisationName.
		 * 
		 * @return organisationName
		 */
		public String getOrganisationName() {
			return organisationName;
		}  
		
				    		/**
		 * Sets online.
		 * 
		 * @param online
		 *            online
		 */
		public void setOnline(Boolean online) {
			this.online = online;
		}
		
		/**
		 * Gets online.
		 * 
		 * @return online
		 */
		public Boolean getOnline() {
			return online;
		}  
		
							/**
		     * Sets online.
		     * 
		     * @param online
		     *            online
		     */
			public void setOnline(boolean online) {
				setOnline(new Boolean(online));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isOnline() {
				return getOnline().booleanValue();
			}
			
		    		/**
		 * Sets offlineMessage.
		 * 
		 * @param offlineMessage
		 *            offlineMessage
		 */
		public void setOfflineMessage(String offlineMessage) {
			this.offlineMessage = offlineMessage;
		}
		
		/**
		 * Gets offlineMessage.
		 * 
		 * @return offlineMessage
		 */
		public String getOfflineMessage() {
			return offlineMessage;
		}  
		
				    		/**
		 * Sets template.
		 * 
		 * @param template
		 *            template
		 */
		public void setTemplate(String template) {
			this.template = template;
		}
		
		/**
		 * Gets template.
		 * 
		 * @return template
		 */
		public String getTemplate() {
			return template;
		}  
		
				    		/**
		 * Sets siteMetaDescription.
		 * 
		 * @param siteMetaDescription
		 *            siteMetaDescription
		 */
		public void setSiteMetaDescription(String siteMetaDescription) {
			this.siteMetaDescription = siteMetaDescription;
		}
		
		/**
		 * Gets siteMetaDescription.
		 * 
		 * @return siteMetaDescription
		 */
		public String getSiteMetaDescription() {
			return siteMetaDescription;
		}  
		
				    		/**
		 * Sets siteMetaKeywords.
		 * 
		 * @param siteMetaKeywords
		 *            siteMetaKeywords
		 */
		public void setSiteMetaKeywords(String siteMetaKeywords) {
			this.siteMetaKeywords = siteMetaKeywords;
		}
		
		/**
		 * Gets siteMetaKeywords.
		 * 
		 * @return siteMetaKeywords
		 */
		public String getSiteMetaKeywords() {
			return siteMetaKeywords;
		}  
		
				        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
    	
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
        
}