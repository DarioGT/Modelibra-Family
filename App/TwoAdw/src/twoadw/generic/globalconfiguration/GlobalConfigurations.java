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
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * GlobalConfiguration specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class GlobalConfigurations extends GenGlobalConfigurations {
	
	private static final long serialVersionUID = 1236798670106L;

	private static Log log = LogFactory.getLog(GlobalConfigurations.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs globalConfigurations within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GlobalConfigurations(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	/**
	 * Creates globalConfiguration.
	 * 
	 * @param siteName
	 *            siteName
	 * @param organisationName
	 *            organisationName
	 * @return globalConfiguration
	 */
	public GlobalConfiguration createGlobalConfiguration(String siteName,
			String organisationName, Boolean online, String offlineMessage, String template,
			String siteMetaDescription, String siteMetaKeywords) {
		GlobalConfiguration globalConfiguration = new GlobalConfiguration(
				getModel());
		globalConfiguration.setSiteName(siteName);
		globalConfiguration.setOrganisationName(organisationName);
		globalConfiguration.setOnline(online);
		globalConfiguration.setOfflineMessage(offlineMessage);
		globalConfiguration.setTemplate(template);
		globalConfiguration.setSiteMetaDescription(siteMetaDescription);
		globalConfiguration.setSiteMetaKeywords(siteMetaKeywords);
		
		if (!add(globalConfiguration)) {
			globalConfiguration = null;
		}
		return globalConfiguration;
	}
}