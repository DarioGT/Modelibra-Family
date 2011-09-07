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
package dm.reference.securityrole;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/**
 * Security role entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-12
 */
public class SecurityRole extends Entity<SecurityRole> {

	private static final long serialVersionUID = 110120130L;

	private String role;

	/**
	 * Constructs a security role within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public SecurityRole(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Sets a security role.
	 * 
	 * @param role
	 *            security role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets a security role.
	 * 
	 * @return security role
	 */
	public String getRole() {
		return role;
	}

}