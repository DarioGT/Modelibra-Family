package org.ieducnews.model.concept.member;

import java.io.Serializable;
import java.util.Date;

import org.ieducnews.model.concept.contribution.Comments;
import org.ieducnews.model.concept.contribution.Submissions;
import org.ieducnews.model.type.EasyDate;
import org.ieducnews.model.type.Email;

public class Member implements Serializable {

	private static final long serialVersionUID = 1;

	private String account;

	private String password;

	private Date creationDate = new Date();

	private Boolean approved = true;

	private String lastName;

	private String firstName;

	private Email email;

	private Integer karma = 0;

	private SecurityRole role = SecurityRole.REGULAR;

	private String about;

	private Boolean showNotApproved = false;

	private Boolean noProcrastination = false;

	private Integer maxVisit = 20; // minutes

	private Integer minAway = 180; // minutes

	private Integer showDelay = 0; // minutes

	private Votes votes = new Votes();

	private Submissions submissions = new Submissions();

	private Comments comments = new Comments();

	public enum SecurityRole {
		REGULAR, ADMIN
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Boolean getApproved() {
		return approved;
	}

	public boolean isApproved() {
		return getApproved();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Email getEmail() {
		return email;
	}

	public void setKarma(Integer karma) {
		this.karma = karma;
	}
	
	public void incrementKarma() {
		karma++;
	}
	
	public void decrementKarma() {
		karma--;
	}

	public Integer getKarma() {
		return karma;
	}

	public void setRole(SecurityRole role) {
		this.role = role;
	}

	public SecurityRole getRole() {
		return role;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAbout() {
		return about;
	}

	public void setShowNotApproved(Boolean showNotApproved) {
		this.showNotApproved = showNotApproved;
	}

	public Boolean getShowNotApproved() {
		return showNotApproved;
	}

	public boolean isShowNotApproved() {
		return getShowNotApproved();
	}

	public void setNoProcrastination(Boolean noProcrastination) {
		this.noProcrastination = noProcrastination;
	}

	public Boolean getNoProcrastination() {
		return noProcrastination;
	}

	public boolean isNoProcrastination() {
		return getNoProcrastination();
	}

	public void setMaxVisit(Integer maxVisit) {
		this.maxVisit = maxVisit;
	}

	public Integer getMaxVisit() {
		return maxVisit;
	}

	public void setMinAway(Integer minAway) {
		this.minAway = minAway;
	}

	public Integer getMinAway() {
		return minAway;
	}

	public void setShowDelay(Integer showDelay) {
		this.showDelay = showDelay;
	}

	public Integer getShowDelay() {
		return showDelay;
	}

	public Votes getVotes() {
		return votes;
	}

	public Submissions getSubmissions() {
		return submissions;
	}

	public Comments getComments() {
		return comments;
	}

	public void output() {
		System.out.println("creation date: " + getCreationDate());
		System.out.println("account: " + getAccount());
		System.out.println("password: " + getPassword());
		System.out.println("approved: " + getApproved());
		System.out.println("last name: " + getLastName());
		System.out.println("first name: " + getFirstName());
		System.out.println("email: " + getEmail());
		System.out.println("karma: " + getKarma());
		System.out.println("role: " + getRole());
		System.out.println("about: " + getAbout());

		System.out.println("getShowNotApproved: " + getShowNotApproved());
		System.out.println("getNoProcrastination: " + getNoProcrastination());
		System.out.println("getMaxVisit: " + getMaxVisit());
		System.out.println("getMinAway: " + getMinAway());
		System.out.println("getShowDelay: " + getShowDelay());

		getVotes().output("Member Votes");
		getSubmissions().output("Member Submissions");
		getComments().output("Member Comments");
	}

	public void outputWithEasyDate() {
		System.out.println("creation easy date: "
				+ new EasyDate(getCreationDate()));
		output();
	}

}
