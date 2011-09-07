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
package dm.meta.domain;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

/**
 * Domain entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-01
 */
public class Domains extends Entities<Domain> {

	private static final long serialVersionUID = 110110111L;

	/**
	 * Constructs domains within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Domains(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Gets a domain.
	 * 
	 * @param code
	 *            code
	 * @return domain
	 */
	public Domain getDomain(String code) {
		return retrieveByCode(code);
	}

	/**
	 * Gets domains ordered by code.
	 * 
	 * @return ordered domains
	 */
	public Domains getDomainsOrderedByCode() {
		return (Domains) orderByCode();
	}

}