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
package dm.reference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dm.DmTest;
import dm.reference.countrylanguage.CountryLanguages;
import dm.reference.countryname.CountryNames;
import dm.reference.member.Member;
import dm.reference.member.Members;
import dm.reference.securityrole.SecurityRoles;

/**
 * DmReference model tests.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-08-23
 */
public class ReferenceTest extends DmTest {

	private static Log log = LogFactory.getLog(ReferenceTest.class);

	private Reference model = getDm().getReference();

	private void initCountryLanguages() {
		outputMessage("????????? test: creation of country languages ?????????");

		CountryLanguages countryLanguages = model.getCountryLanguages();
		countryLanguages.createCountryLanguage("en", "english");
		countryLanguages.createCountryLanguage("fr", "français");
		countryLanguages.createCountryLanguage("ba", "bosanski");

		outputErrors(countryLanguages, "CountryLanguage Add Errors");
	}

	private void initCountryNames() {
		outputMessage("????????? test: creation of country names ?????????");

		CountryNames countryNames = model.getCountryNames();
		countryNames.createCountryName("CA", "CANADA");
		countryNames.createCountryName("US", "USA");

		outputErrors(countryNames, "CountryName Add Errors");
	}

	private void initSecurityRoles() {
		outputMessage("????????? test: creation of security roles ?????????");

		SecurityRoles securityRoles = model.getSecurityRoles();
		securityRoles.createSecurityRole("admin", "admin");
		securityRoles.createSecurityRole("manager", "manager");
		securityRoles.createSecurityRole("advanced", "advanced");
		securityRoles.createSecurityRole("regular", "regular");
		securityRoles.createSecurityRole("casual", "casual");

		outputErrors(securityRoles, "Security Role Add Errors");
	}

	private void initMembers() {
		outputMessage("????????? initUsers: creation of users ?????????");

		Members members = model.getMembers();
		members.createMember("Dzenan", "Ridjanovic",
				"dzenan.ridjanovic@fsa.ulaval.ca", "dr", "dr", "admin");
		members.createMember("Timur", "Ridjanovic",
				"timur.ridjanovic@videotron.ca", "tim", "tim", "regular");
		members.createMember("Robert", "Mantha", "robert.mantha@fsa.ulaval.ca",
				"rm", "rm", "casual");

		outputErrors(members, "Member Add Errors");
	}

	private void initModel() {
		initCountryLanguages();
		initCountryNames();
		initSecurityRoles();
		initMembers();
	}

	private void test01() {
		outputMessage("????????? test01: dipslay members ?????????");
		Members members = model.getMembers();
		members.output("Members");
	}

	private void test10() {
		outputMessage("????????? test01: update member ?????????");
		Members members = model.getMembers();
		Member dzenan = (Member) members
				.selectByProperty("firstName", "Dženan");
		if (dzenan != null) {
			Member dzenan2 = (Member) dzenan.copy();
			dzenan2.setFirstName("Dzenan");
			members.update(dzenan, dzenan2);
		}
	}

	private void test11() {
		outputMessage("????????? test02: select members that receive email ?????????");
		Members members = model.getMembers();
		Members selectedMembers = (Members) members
				.getRegularMembersThatReceiveEmail();
		selectedMembers.output("Regular members that receive email");
	}

	private void test12() {
		outputMessage("????????? test12: select members using selectByMethod ?????????");
		Members members = model.getMembers();
		Members adminMembers = (Members) members.getAdmins();
		adminMembers.output("Admin members");
	}

	private void test13() {
		outputMessage("????????? test12: select members using selectByMethod with parameters?????????");
		Members members = model.getMembers();
		String role = "admin";
		Members roleMembers = (Members) members.getRoleMembers(role);
		roleMembers.output("'" + role + "'" + " role members");
	}

	private void test14() {
		outputMessage("????????? test12: select members using selectByMethod with parameters?????????");
		Members members = model.getMembers();
		String role = "admin";
		int length = 9;
		Members roleMembers = (Members) members
				.getRoleMembersWithLastNameLongerThanLength(role, length);
		roleMembers.output("'" + role + "'"
				+ " role members with last name longer than " + length);
	}

	private void test15() {
		outputMessage("????????? test15: propertySelector case sensitivity ?????????");
		Members members = model.getMembers();
		String innerString = "dze";
		boolean caseSensitive = true;
		Members selectedMembers = (Members) members
				.getMembersWithFirstNameContainingString(innerString,
						caseSensitive);
		selectedMembers.output("Members with first name containing string: "
				+ innerString + " - case sensitive " + caseSensitive);

		caseSensitive = false;
		Members selectedMembers1 = (Members) members
				.getMembersWithFirstNameContainingString(innerString,
						caseSensitive);
		selectedMembers1.output("Members with first name containing string: "
				+ innerString + " - case sensitive " + caseSensitive);

		String[] strings = { "E", "nAn" };
		Members selectedMembers2 = (Members) members
				.getMembersWithFirstNameContainingSomeStrings(strings,
						caseSensitive);
		selectedMembers2
				.output("Members with first name containing some of the strings: "
						+ strings[0]
						+ ", "
						+ strings[1]
						+ " - case sensitive "
						+ caseSensitive);

		caseSensitive = true;
		Members selectedMembers3 = (Members) members
				.getMembersWithFirstNameContainingSomeStrings(strings,
						caseSensitive);
		selectedMembers3
				.output("Members with first name containing some of the strings: "
						+ strings[0]
						+ ", "
						+ strings[1]
						+ " - case sensitive "
						+ caseSensitive);

		Members selectedMembers4 = (Members) members
				.getMembersWithFirstNameContainingAllStrings(strings,
						caseSensitive);
		selectedMembers4
				.output("Members with first name containing all of the strings: "
						+ strings[0]
						+ ", "
						+ strings[1]
						+ " - case sensitive "
						+ caseSensitive);

		caseSensitive = false;
		Members selectedMembers5 = (Members) members
				.getMembersWithFirstNameContainingAllStrings(strings,
						caseSensitive);
		selectedMembers5
				.output("Members with first name containing all of the strings: "
						+ strings[0]
						+ ", "
						+ strings[1]
						+ " - case sensitive "
						+ caseSensitive);

	}

	private void test() {
		// test01();
		// test02();
		// test11();
		// test12();
		// test13();
		// test14();
		test15();
	}

	public static void main(String[] args) {
		ReferenceTest modelTest = null;
		try {
			modelTest = new ReferenceTest();
			// modelTest.initModel();
			modelTest.test();
			modelTest.close();
		} catch (Exception e) {
			log.error("Error in DmReferenceTest.main: " + e.getMessage());
			modelTest.close();
		}
	}

}
