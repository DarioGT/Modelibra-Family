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
package dm;

import org.modelibra.Domain;
import org.modelibra.config.DomainConfig;

import dm.meta.Meta;
import dm.reference.Reference;

/**
 * Creates domain and its models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-01
 */
public class Dm extends Domain {

	private static final long serialVersionUID = 110L;

	private Meta meta;

	private Reference reference;

	/**
	 * Creates the dm.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public Dm(DomainConfig domainConfig) {
		super(domainConfig);
		meta = new Meta(this);
		reference = new Reference(this);
	}

	public Meta getMeta() {
		return meta;
	}

	public Reference getReference() {
		return reference;
	}

}
