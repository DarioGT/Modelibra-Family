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
package twoadw.reference.securityrole;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.type.EasyDate;

import twoadw.TwoadwTest;
import twoadw.reference.country.Country;

/**
 * JUnit tests for SecurityRoles.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class SecurityRolesTest {

	private static SecurityRoles securityRoles;

	@BeforeClass
	public static void beforeTests() throws Exception {
		// If the concept is not an entry into the model, first find a collection of entities.
		// For an entry point the following code is correct.
		securityRoles = TwoadwTest.getSingleton().getTwoadw().getReference().getSecurityRoles();
	}

	@Before
	public void beforeTest() throws Exception {
		securityRoles.getErrors().empty();
	}

	@Test
	public void securityRoleCreated() throws Exception {
		SecurityRole securityRole01 = securityRoles.createSecurityRole("1", "Customer");
		SecurityRole securityRole99 = securityRoles.createSecurityRole("99", "Administrator");

		assertTrue(securityRoles.contain(securityRole01));
		assertTrue(securityRoles.contain(securityRole99));
		assertTrue(securityRoles.getErrors().isEmpty());
	}
	
	@Test
	public void securityRoleEquality() throws Exception {
		SecurityRole securityRole = securityRoles.createSecurityRole("1", "Customer");
		SecurityRole securityRoleCopy = securityRole.copy();

		assertEquals(securityRole,securityRoleCopy);
		assertNotSame(securityRole,securityRoleCopy);
		assertTrue(securityRoles.getErrors().isEmpty());
	}
	
	
	@Test
	public void securityRoleUpdate() throws Exception {
		SecurityRole securityRole = securityRoles.createSecurityRole("1", "Customer");
		SecurityRole securityRoleCopy = securityRole.copy();
		securityRoleCopy.setRoleCode("2");
		securityRoleCopy.setRoleName("Manager");
		
		assertTrue(securityRole.equals(securityRoleCopy));
		assertTrue(securityRole.equalOid(securityRoleCopy));
		assertFalse(securityRole.equalProperties(securityRoleCopy));
	
		securityRoles.update(securityRole, securityRoleCopy);
		assertTrue(securityRoles.getErrors().isEmpty());
	}
	

	//SecurityRole.id.unique
	@Test
	public void idUnique() throws Exception {
		SecurityRole securityRole = securityRoles.createSecurityRole("1", "Customer");
		SecurityRole securityRoleNotUnique = securityRoles.createSecurityRole("1", "Customer");

		assertFalse(securityRoles.contain(securityRoleNotUnique));
		assertNotNull(securityRoles.getErrors()
				.getError("SecurityRole.id.unique"));
	}
	
	//SecurityRole.roleCode.required=RoleCode is required.
	@Test
	public void roleCodeRequired() throws Exception {
		SecurityRole securityRole = securityRoles.createSecurityRole(null, "Customer");

		assertFalse(securityRoles.contain(securityRole));
		assertNotNull(securityRoles.getErrors().getError(
				"SecurityRole.roleCode.required"));
	}
	
	
	//SecurityRole.roleCode.length=RoleCode is longer than 16.
	@Test
	public void roleCodeLength() throws Exception {
		//Data type Code = 16 char
		String stringlength = "";
		while (stringlength.length() <=16) {
			stringlength = stringlength + "1"; 
		}	
		
		SecurityRole securityRole = securityRoles.createSecurityRole(stringlength, "Customer");

		assertFalse(securityRoles.contain(securityRole));
		assertNotNull(securityRoles.getErrors().getError(
				"SecurityRole.roleCode.length"));
	}
	
	//SecurityRole.roleName.required=RoleName is required.
	@Test
	public void roleNameRequired() throws Exception {
		SecurityRole securityRole = securityRoles.createSecurityRole("1", null);

		assertFalse(securityRoles.contain(securityRole));
		assertNotNull(securityRoles.getErrors().getError(
				"SecurityRole.roleName.required"));
	}
	
	
	//SecurityRole.roleName.length=RoleName is longer than 32.
	@Test
	public void roleNameLength() throws Exception {
		//Data type Code = 32 char
		String stringlength = "";
		while (stringlength.length() <=32) {
			stringlength = stringlength + "1"; 
		}	
		
		SecurityRole securityRole = securityRoles.createSecurityRole("1", stringlength);

		assertFalse(securityRoles.contain(securityRole));
		assertNotNull(securityRoles.getErrors().getError(
				"SecurityRole.roleName.length"));
	}

	@After
	public void afterTest() throws Exception {
		for (SecurityRole securityRole : securityRoles.getList()) {
			securityRoles.remove(securityRole);
		}
	}
	
	@AfterClass
	public static void afterTests() throws Exception {
		TwoadwTest.getSingleton().close();
	}

}