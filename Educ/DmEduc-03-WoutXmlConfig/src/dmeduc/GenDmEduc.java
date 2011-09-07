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

import dmeduc.weblink.WebLink;

/**
 * DmEduc generated domain. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author unknown
 * @version 2008-12-02
 */
public abstract class GenDmEduc extends Domain {

	private static final long serialVersionUID = 1228241321946L;

	private WebLink webLink;

	/**
	 * Creates the DmEduc domain.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public GenDmEduc(DomainConfig domainConfig) {
		super(domainConfig);
		webLink = new WebLink(this);
	}

	public WebLink getWebLink() {
		return webLink;
	}

}