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

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import course.quiz.member.Member;
import course.quiz.test.Test;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Document generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenDocuments extends Entities<Document> {

	private static final long serialVersionUID = 1176743350746L;

	private static Log log = LogFactory.getLog(GenDocuments.class);

	/* ======= internal parent neighbors ======= */

	private Member member;

	/* ======= external parent neighbors ======= */

	private Test test;

	/* ======= base constructor ======= */

	/**
	 * Constructs documents within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenDocuments(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs documents for the member parent.
	 * 
	 * @param member
	 *            member
	 */
	public GenDocuments(Member member) {
		this(member.getModel());
		// parent
		setMember(member);
	}

	/**
	 * Constructs documents for the test parent.
	 * 
	 * @param test
	 *            test
	 */
	public GenDocuments(Test test) {
		this(test.getModel());
		// parent
		setTest(test);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the document with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return document
	 */
	public Document getDocument(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the document with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return document
	 */
	public Document getDocument(Long oidUniqueNumber) {
		return getDocument(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first document whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return document
	 */
	public Document getDocument(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects documents whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return documents
	 */
	public Documents getDocuments(String propertyCode, Object property) {
		return (Documents) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets documents ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered documents
	 */
	public Documents getDocuments(String propertyCode, boolean ascending) {
		return (Documents) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets documents selected by a selector. Returns empty documents if there
	 * are no documents that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected documents
	 */
	public Documents getDocuments(ISelector selector) {
		return (Documents) selectBySelector(selector);
	}

	/**
	 * Gets documents ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered documents
	 */
	public Documents getDocuments(Comparator comparator, boolean ascending) {
		return (Documents) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets fileName document.
	 * 
	 * @param fileName
	 *            fileName
	 * @return fileName document
	 */
	public Document getFileNameDocument(String fileName) {
		PropertySelector propertySelector = new PropertySelector("fileName");
		propertySelector.defineEqual(fileName);
		List<Document> list = getDocuments(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/**
	 * Gets test documents.
	 * 
	 * @param test
	 *            test oid unique number
	 * @return test documents
	 */
	public Documents getTestDocuments(Long test) {
		PropertySelector propertySelector = new PropertySelector("testOid");
		propertySelector.defineEqual(test);
		return getDocuments(propertySelector);
	}

	/**
	 * Gets test documents.
	 * 
	 * @param test
	 *            test oid
	 * @return test documents
	 */
	public Documents getTestDocuments(Oid test) {
		return getTestDocuments(test.getUniqueNumber());
	}

	/**
	 * Gets test documents.
	 * 
	 * @param test
	 *            test
	 * @return test documents
	 */
	public Documents getTestDocuments(Test test) {
		return getTestDocuments(test.getOid());
	}

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets documents ordered by fileName.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered documents
	 */
	public Documents getDocumentsOrderedByFileName(boolean ascending) {
		return getDocuments("fileName", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

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

	/* ======= external parent set and get methods ======= */

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

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	protected boolean postAdd(Document document) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(document)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				Test test = getTest();
				if (test == null) {
					Test documentTest = document.getTest();
					if (documentTest != null) {
						if (!documentTest.getDocuments().contain(document)) {
							documentTest.getDocuments().setPropagateToSource(
									false);
							post = documentTest.getDocuments().add(document);
							documentTest.getDocuments().setPropagateToSource(
									true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	protected boolean postRemove(Document document) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(document)) {
			Test test = getTest();
			if (test == null) {
				Test documentTest = document.getTest();
				if (documentTest != null) {
					if (documentTest.getDocuments().contain(document)) {
						documentTest.getDocuments().setPropagateToSource(false);
						post = documentTest.getDocuments().remove(document);
						documentTest.getDocuments().setPropagateToSource(true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	protected boolean postUpdate(Document beforeDocument, Document afterDocument) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeDocument, afterDocument)) {
			Test test = getTest();
			if (test == null) {
				Test beforeDocumentTest = beforeDocument.getTest();
				Test afterDocumentTest = afterDocument.getTest();
				if (beforeDocumentTest == null && afterDocumentTest != null) {
					// attach
					if (!afterDocumentTest.getDocuments()
							.contain(afterDocument)) {
						afterDocumentTest.getDocuments().setPropagateToSource(
								false);
						post = afterDocumentTest.getDocuments().add(
								afterDocument);
						afterDocumentTest.getDocuments().setPropagateToSource(
								true);
					}
				} else if (beforeDocumentTest != null
						&& afterDocumentTest == null) {
					// detach
					if (beforeDocumentTest.getDocuments().contain(
							beforeDocument)) {
						beforeDocumentTest.getDocuments().setPropagateToSource(
								false);
						post = beforeDocumentTest.getDocuments().remove(
								beforeDocument);
						beforeDocumentTest.getDocuments().setPropagateToSource(
								true);
					}
				} else if (beforeDocumentTest != null
						&& afterDocumentTest != null
						&& beforeDocumentTest != afterDocumentTest) {
					// detach
					if (beforeDocumentTest.getDocuments().contain(
							beforeDocument)) {
						beforeDocumentTest.getDocuments().setPropagateToSource(
								false);
						post = beforeDocumentTest.getDocuments().remove(
								beforeDocument);
						beforeDocumentTest.getDocuments().setPropagateToSource(
								true);
					}
					// attach
					if (!afterDocumentTest.getDocuments()
							.contain(afterDocument)) {
						afterDocumentTest.getDocuments().setPropagateToSource(
								false);
						post = afterDocumentTest.getDocuments().add(
								afterDocument);
						afterDocumentTest.getDocuments().setPropagateToSource(
								true);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= create entity method ======= */

	/**
	 * Creates document.
	 * 
	 * @param memberParent
	 *            member parent
	 * @param testParent
	 *            test parent
	 * @param fileName
	 *            fileName
	 * @return document
	 */
	public Document createDocument(Member memberParent, Test testParent,
			String fileName) {
		Document document = new Document(getModel());
		document.setMember(memberParent);
		document.setTest(testParent);
		document.setFileName(fileName);
		if (!add(document)) {
			document = null;
		}
		return document;
	}

}