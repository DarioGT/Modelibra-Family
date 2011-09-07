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
package dm.meta;

import org.modelibra.IDomain;
import org.modelibra.DomainModel;

import dm.meta.domain.Domains;

/**
 * Meta domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-13
 */
public class Meta extends DomainModel {

	private static final long serialVersionUID = 110110L;

	private Domains domains;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Meta(IDomain domain) {
		super(domain);
		domains = new Domains(this);
	}

	public Domains getDomains() {
		return domains;
	}

}
