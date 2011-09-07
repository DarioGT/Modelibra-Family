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

import java.util.ArrayList;
import java.util.List;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;

/**
 * Security role entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-12
 */
public class SecurityRoles extends Entities<SecurityRole> {

	private static final long serialVersionUID = 110120131L;

	/**
	 * Constructs security roles within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public SecurityRoles(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Gets a security code given the role name.
	 * 
	 * @param role
	 *            role name
	 * @return security code
	 */
	public String getSecurityCode(String role) {
		String securityCode = null;
		SecurityRole securityRole = (SecurityRole) retrieveByProperty("role",
				role);
		if (securityRole != null) {
			securityCode = securityRole.getCode();
		}
		return securityCode;
	}

	/**
	 * Gets a list of security roles.
	 * 
	 * @return list of security roles
	 */
	public List<String> getRoleList() {
		List<String> roleList = new ArrayList<String>();
		synchronized (this) {
			for (SecurityRole securityRole : this) {				
				roleList.add(securityRole.getRole());
			}
		}
		return roleList;
	}

	/**
	 * Creates a security role.
	 * 
	 * @param code
	 *            code
	 * @param role
	 *            role
	 * @return security role
	 */
	public SecurityRole createSecurityRole(String code, String role) {
		SecurityRole securityRole = new SecurityRole(getModel());
		securityRole.setCode(code);
		securityRole.setRole(role);
		if (!add(securityRole)) {
			securityRole = null;
		}
		return securityRole;
	}

}