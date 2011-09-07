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

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */


/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * GlobalConfiguration generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenGlobalConfigurations extends Entities<GlobalConfiguration> {
	
	private static final long serialVersionUID = 1236798670104L;

	private static Log log = LogFactory.getLog(GenGlobalConfigurations.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs globalConfigurations within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenGlobalConfigurations(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the globalConfiguration with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return globalConfiguration
	 */
public GlobalConfiguration getGlobalConfiguration(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the globalConfiguration with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return globalConfiguration
	 */
	public GlobalConfiguration getGlobalConfiguration(Long oidUniqueNumber) {
		return getGlobalConfiguration(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first globalConfiguration whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return globalConfiguration
	 */
	public GlobalConfiguration getGlobalConfiguration(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects globalConfigurations whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return globalConfigurations
	 */
	public GlobalConfigurations getGlobalConfigurations(String propertyCode, Object property) {
		return (GlobalConfigurations) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets globalConfigurations ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered globalConfigurations
	 */
	public GlobalConfigurations getGlobalConfigurations(String propertyCode, boolean ascending) {
		return (GlobalConfigurations) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets globalConfigurations selected by a selector. Returns empty globalConfigurations if there are no
	 * globalConfigurations that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected globalConfigurations
	 */
	public GlobalConfigurations getGlobalConfigurations(ISelector selector) {
		return (GlobalConfigurations) selectBySelector(selector);
	}
	
	/**
	 * Gets globalConfigurations ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered globalConfigurations
	 */
	public GlobalConfigurations getGlobalConfigurations(Comparator comparator, boolean ascending) {
		return (GlobalConfigurations) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets organisationName globalConfigurations.
		 * 
		 * @param organisationName 
		 *            organisationName
		 * @return organisationName globalConfigurations
		 */
		public GlobalConfigurations getOrganisationNameGlobalConfigurations(String organisationName) {
			PropertySelector propertySelector = new PropertySelector("organisationName");
			propertySelector.defineEqual(organisationName);
			return getGlobalConfigurations(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets siteName globalConfiguration.
		 * 
		 * @param siteName 
		 *            siteName
		 * @return siteName globalConfiguration
		 */
		public GlobalConfiguration getSiteNameGlobalConfiguration(String siteName) {
			PropertySelector propertySelector = new PropertySelector("siteName");
						propertySelector.defineEqual(siteName);
			List<GlobalConfiguration> list = getGlobalConfigurations(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets globalConfigurations ordered by siteName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered globalConfigurations
		 */
		public GlobalConfigurations getGlobalConfigurationsOrderedBySiteName(boolean ascending) {			
			return getGlobalConfigurations("siteName", ascending);
		}
	
	/**
		 * Gets globalConfigurations ordered by organisationName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered globalConfigurations
		 */
		public GlobalConfigurations getGlobalConfigurationsOrderedByOrganisationName(boolean ascending) {			
			return getGlobalConfigurations("organisationName", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates globalConfiguration.
	 *
		 * @param siteName siteName 
	 * @param organisationName organisationName 
		 * @return globalConfiguration
	 */
	public GlobalConfiguration createGlobalConfiguration(
											String siteName,
											String organisationName 
				) {
		GlobalConfiguration globalConfiguration = new GlobalConfiguration(getModel());
						globalConfiguration.setSiteName(siteName);
				globalConfiguration.setOrganisationName(organisationName);
				if (!add(globalConfiguration)) {
			globalConfiguration = null;
		}
		return globalConfiguration;
	}

}