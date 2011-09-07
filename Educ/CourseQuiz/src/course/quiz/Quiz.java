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
package course.quiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;

import course.quiz.document.Document;
import course.quiz.document.Documents;
import course.quiz.member.Member;
import course.quiz.member.Members;
import course.quiz.question.Question;
import course.quiz.question.Questions;
import course.quiz.test.Test;
import course.quiz.test.Tests;

/**
 * Quiz specific model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Quiz extends GenQuiz {

	private static final long serialVersionUID = 1176742903871L;

	private static Log log = LogFactory.getLog(Quiz.class);

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Quiz(IDomain domain) {
		super(domain);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Gets questions for all tests.
	 * 
	 * @return all questions
	 */
	public Questions getQuestions() {
		Questions allQuestions = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allQuestions = new Questions(this);
				allQuestions.setPersistent(false);
				allQuestions.setPre(false);
				allQuestions.setPost(false);
				Tests tests = getTests();
				for (Test test : tests) {
					Questions categoryQuestions = test.getQuestions();
					for (Question question : categoryQuestions) {
						allQuestions.add(question);
					}
				}
			} catch (Exception e) {
				log.error("Error in Quiz.getQuestions: " + e.getMessage());
			} finally {
				allQuestions.setPersistent(true);
				allQuestions.setPre(true);
				allQuestions.setPost(true);
			}
		}
		return allQuestions;
	}

	/**
	 * Gets documents for all members.
	 * 
	 * @return all documents
	 */
	public Documents getDocuments() {
		Documents allDocuments = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allDocuments = new Documents(this);
				allDocuments.setPersistent(false);
				allDocuments.setPre(false);
				allDocuments.setPost(false);
				Members members = getMembers();
				for (Member member : members) {
					Documents categoryDocuments = member.getDocuments();
					for (Document document : categoryDocuments) {
						allDocuments.add(document);
					}
				}
			} catch (Exception e) {
				log.error("Error in Quiz.getDocuments: " + e.getMessage());
			} finally {
				allDocuments.setPersistent(true);
				allDocuments.setPre(true);
				allDocuments.setPost(true);
			}
		}
		return allDocuments;
	}

}
