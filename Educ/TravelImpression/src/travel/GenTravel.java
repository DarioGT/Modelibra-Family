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
package travel;

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

/* ======= import models ======= */

import travel.impression.Impression;	
import travel.reference.Reference;	

/**
 * Travel generated domain. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public abstract class GenTravel extends Domain {

	private static final long serialVersionUID = 1189015928269L;
	
	private Impression impression;
		
	private Reference reference;
		
	/**
	 * Creates the Travel domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public GenTravel(DomainConfig domainConfig) {
		super(domainConfig);
		impression = new Impression(this);		
		reference = new Reference(this);		
	}

	public Impression getImpression() {
		return impression;
	}
		
	public Reference getReference() {
		return reference;
	}
		
}