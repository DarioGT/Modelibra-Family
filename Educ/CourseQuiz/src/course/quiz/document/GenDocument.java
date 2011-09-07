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
package course.quiz.document;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.quiz.Quiz;
import course.quiz.member.Member;
import course.quiz.test.Test;
import course.quiz.test.Tests;

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Document generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenDocument extends Entity<Document> {

	private static final long serialVersionUID = 1176743350745L;

	private static Log log = LogFactory.getLog(GenDocument.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String fileName;

	private Date uploadDate;

	/* ======= reference properties ======= */

	private Long testOid;

	/* ======= internal parent neighbors ======= */

	private Member member;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	private Test test;

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs document within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenDocument(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs document within its parent(s).
	 * 
	 * @param Member
	 *            member
	 * @param Test
	 *            test
	 */
	public GenDocument(Member member, Test test) {
		this(test.getModel());
		// parents
		setMember(member);
		setTest(test);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets fileName.
	 * 
	 * @param fileName
	 *            fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets fileName.
	 * 
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets uploadDate.
	 * 
	 * @param uploadDate
	 *            uploadDate
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * Gets uploadDate.
	 * 
	 * @return uploadDate
	 */
	public Date getUploadDate() {
		return uploadDate;
	}

	/* ======= reference property set and get methods ======= */

	/**
	 * Sets testOid.
	 * 
	 * @param testOid
	 *            testOid
	 */
	public void setTestOid(Long testOid) {
		this.testOid = testOid;
		test = null;
	}

	/**
	 * Gets testOid.
	 * 
	 * @return testOid
	 */
	public Long getTestOid() {
		return testOid;
	}

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets member.
	 * 
	 * @param member
	 *            member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets member.
	 * 
	 * @return member
	 */
	public Member getMember() {
		return member;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets test.
	 * 
	 * @param test
	 *            test
	 */
	public void setTest(Test test) {
		this.test = test;
		if (test != null) {
			testOid = test.getOid().getUniqueNumber();
		} else {
			testOid = null;
		}
	}

	/**
	 * Gets test.
	 * 
	 * @return test
	 */
	public Test getTest() {
		if (test == null) {
			Quiz quiz = (Quiz) getModel();
			Tests tests = quiz.getTests();
			if (testOid != null) {
				test = tests.getTest(testOid);
			}
		}
		return test;
	}

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}