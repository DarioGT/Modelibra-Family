package org.ieducnews.model.concept.contribution;

import java.io.Serializable;
import java.util.Date;

import org.ieducnews.model.type.EasyDate;

public abstract class Contribution implements Serializable {

	private static final long serialVersionUID = 1;

	private Date creationDate = new Date();

	private Boolean approved = true;

	private Integer points = 0;

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

	public void setPoints(Integer points) {
		this.points = points;
	}

	public void incrementPoints() {
		points++;
	}

	public void decrementPoints() {
		points--;
	}

	public Integer getPoints() {
		return points;
	}

	public void output() {
		System.out.println("creation date: " + getCreationDate());
		System.out.println("approved: " + getApproved());
		System.out.println("points: " + getPoints());
	}

	public void outputWithEasyDate() {
		System.out.println("creation easy date: "
				+ new EasyDate(getCreationDate()));
		output();
	}

}
