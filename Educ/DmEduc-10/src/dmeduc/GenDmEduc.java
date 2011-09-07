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
package dmeduc;

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

import dmeduc.reference.Reference;
import dmeduc.weblink.WebLink;

/**
 * DmEduc generated domain. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-15
 */
public abstract class GenDmEduc extends Domain {

	private static final long serialVersionUID = 1189989374359L;

	private WebLink webLink;

	private Reference reference;

	/**
	 * Creates the DmEduc domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public GenDmEduc(DomainConfig domainConfig) {
		super(domainConfig);
		webLink = new WebLink(this);
		reference = new Reference(this);
	}

	public WebLink getWebLink() {
		return webLink;
	}

	public Reference getReference() {
		return reference;
	}

}