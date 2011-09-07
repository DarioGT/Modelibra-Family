package dmeduc.weblink.applicant;

import org.modelibra.IDomainModel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.member.Member;
import dmeduc.weblink.member.Members;

/**
 * Applicant entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-06
 */
public class Applicants extends Members {

	/**
	 * Constructs applicants within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Applicants(IDomainModel model) {
		super(model);
	}

	/**
	 * Checks if an applicant has a unique code.
	 * 
	 * @param applicant
	 *            applicant
	 * @return <code>true</code> if the add precondition is satisfied
	 */
	protected boolean preAdd(Member member) {
		if (super.preAdd(member)) {
			Applicant applicant = (Applicant) member;
			WebLink webLink = (WebLink) getModel();
			Members members = webLink.getMembers();
			Member existingMember = members
					.getMemberByCode(applicant.getCode());
			if (existingMember == null) {
				return true;
			} else {
				getErrors().add("Applicant.id.unique",
						"Member code already exists.");
				return false;
			}
		} else {
			return false;
		}
	}

}
