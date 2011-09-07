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
package twoadw.generic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;
import org.modelibra.DomainModel;

/* ======= import entry concept entities ======= */

import twoadw.generic.categorylink.CategoryLinks;	
import twoadw.generic.informationpage.InformationPages;	
import twoadw.generic.template.Templates;	
import twoadw.generic.globalconfiguration.GlobalConfigurations;	

/* ======= import non entry external child/parent required concept entities ======= */


/**
 * Generic generated model. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenGeneric extends DomainModel {

	private static final long serialVersionUID = 1236707938386L;
	
	private static Log log = LogFactory.getLog(GenGeneric.class);
	
	private CategoryLinks categoryLinks;
		
	private InformationPages informationPages;
		
	private Templates templates;
		
	private GlobalConfigurations globalConfigurations;
		
	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenGeneric(IDomain domain) {
		super(domain);
		categoryLinks = new CategoryLinks(this);
		informationPages = new InformationPages(this);
		templates = new Templates(this);
		globalConfigurations = new GlobalConfigurations(this);
	}

	/**
	 * Gets CategoryLink entities.
	 * 
	 * @return CategoryLink entities
	 */
	public CategoryLinks getCategoryLinks() {
		return categoryLinks;
	}
	
	/**
	 * Gets InformationPage entities.
	 * 
	 * @return InformationPage entities
	 */
	public InformationPages getInformationPages() {
		return informationPages;
	}
	
	/**
	 * Gets Template entities.
	 * 
	 * @return Template entities
	 */
	public Templates getTemplates() {
		return templates;
	}
	
	/**
	 * Gets GlobalConfiguration entities.
	 * 
	 * @return GlobalConfiguration entities
	 */
	public GlobalConfigurations getGlobalConfigurations() {
		return globalConfigurations;
	}
	

}
