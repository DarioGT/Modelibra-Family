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
package course.quiz.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import course.quiz.Quiz;
import course.quiz.document.Documents;
import course.quiz.question.Questions;

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * Test generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenTest extends Entity<Test> {

	private static final long serialVersionUID = 1176743084480L;

	private static Log log = LogFactory.getLog(GenTest.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String name;

	private String description;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	/* ======= internal child neighbors ======= */

	private Questions questions;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	private Documents documents;

	/* ======= base constructor ======= */

	/**
	 * Constructs test within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenTest(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setQuestions(new Questions((Test) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets questions.
	 * 
	 * @param questions
	 *            questions
	 */
	public void setQuestions(Questions questions) {
		this.questions = questions;
		if (questions != null) {
			questions.setTest((Test) this);
		}
	}

	/**
	 * Gets questions.
	 * 
	 * @return questions
	 */
	public Questions getQuestions() {
		return questions;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/**
	 * Sets documents.
	 * 
	 * @param documents
	 *            documents
	 */
	public void setDocuments(Documents documents) {
		this.documents = documents;
		if (documents != null) {
			documents.setTest((Test) this);
		}
	}

	/**
	 * Gets documents.
	 * 
	 * @return documents
	 */
	public Documents getDocuments() {
		if (documents == null) {
			Quiz quiz = (Quiz) getModel();
			Documents documents = quiz.getDocuments();
			setDocuments(documents.getTestDocuments((Test) this));
		}
		return documents;
	}

	/* ======= external (part of) many-to-many child set and get methods ======= */

}