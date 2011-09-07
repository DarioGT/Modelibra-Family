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
package course.quiz.question;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.quiz.Quiz;
import course.quiz.item.Items;
import course.quiz.member.Member;
import course.quiz.member.Members;
import course.quiz.test.Test;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Question generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenQuestion extends Entity<Question> {

	private static final long serialVersionUID = 1176743111750L;

	private static Log log = LogFactory.getLog(GenQuestion.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer number;

	private String type;

	private String text;

	private String response;

	private Float points;

	private Date creationDate;

	/* ======= reference properties ======= */

	private Long memberOid;

	/* ======= internal parent neighbors ======= */

	private Test test;

	/* ======= internal child neighbors ======= */

	private Items items;

	/* ======= external parent neighbors ======= */

	private Member member;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs question within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQuestion(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setItems(new Items((Question) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs question within its parent(s).
	 * 
	 * @param Test
	 *            test
	 * @param Member
	 *            member
	 */
	public GenQuestion(Test test, Member member) {
		this(member.getModel());
		// parents
		setTest(test);
		setMember(member);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets number.
	 * 
	 * @param number
	 *            number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Gets number.
	 * 
	 * @return number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Sets type.
	 * 
	 * @param type
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets type.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets text.
	 * 
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets text.
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets response.
	 * 
	 * @param response
	 *            response
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * Gets response.
	 * 
	 * @return response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * Sets points.
	 * 
	 * @param points
	 *            points
	 */
	public void setPoints(Float points) {
		this.points = points;
	}

	/**
	 * Gets points.
	 * 
	 * @return points
	 */
	public Float getPoints() {
		return points;
	}

	/**
	 * Sets creationDate.
	 * 
	 * @param creationDate
	 *            creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets creationDate.
	 * 
	 * @return creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets memberOid.
	 * 
	 * @param memberOid
	 *            memberOid
	 */
	public void setMemberOid(Long memberOid) {
		this.memberOid = memberOid;
		member = null;
	}

	/**
	 * Gets memberOid.
	 * 
	 * @return memberOid
	 */
	public Long getMemberOid() {
		return memberOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets test.
	 * 
	 * @param test
	 *            test
	 */
	public void setTest(Test test) {
		this.test = test;
	}

	/**
	 * Gets test.
	 * 
	 * @return test
	 */
	public Test getTest() {
		return test;
	}

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets items.
	 * 
	 * @param items
	 *            items
	 */
	public void setItems(Items items) {
		this.items = items;
		if (items != null) {
			items.setQuestion((Question) this);
		}
	}

	/**
	 * Gets items.
	 * 
	 * @return items
	 */
	public Items getItems() {
		return items;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets member.
	 * 
	 * @param member
	 *            member
	 */
	public void setMember(Member member) {
		this.member = member;
		if (member != null) {
			memberOid = member.getOid().getUniqueNumber();
		} else {
			memberOid = null;
		}
	}

	/**
	 * Gets member.
	 * 
	 * @return member
	 */
	public Member getMember() {
		if (member == null) {
			Quiz quiz = (Quiz) getModel();
			Members members = quiz.getMembers();
			if (memberOid != null) {
				member = members.getMember(memberOid);
			}
		}
		return member;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}