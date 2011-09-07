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
package twoadw;

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

/* ======= import models ======= */

import twoadw.website.Website;	
import twoadw.generic.Generic;	
import twoadw.reference.Reference;	

/**
 * Twoadw generated domain. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenTwoadw extends Domain {

	private static final long serialVersionUID = 1231088671635L;
	
	private Website website;
		
	private Generic generic;
		
	private Reference reference;
		
	/**
	 * Creates the Twoadw domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public GenTwoadw(DomainConfig domainConfig) {
		super(domainConfig);
		website = new Website(this);		
		generic = new Generic(this);		
		reference = new Reference(this);		
	}

	public Website getWebsite() {
		return website;
	}
		
	public Generic getGeneric() {
		return generic;
	}
		
	public Reference getReference() {
		return reference;
	}
		
}