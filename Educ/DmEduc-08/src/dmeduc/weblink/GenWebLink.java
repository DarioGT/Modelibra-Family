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
package dmeduc.weblink;

import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import dmeduc.weblink.category.Categories;
import dmeduc.weblink.comment.Comments;
import dmeduc.weblink.member.Members;
import dmeduc.weblink.question.Questions;

/**
 * WebLink generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-15
 */
public abstract class GenWebLink extends DomainModel {

	private static final long serialVersionUID = 1171894920489L;

	private Comments comments;

	private Categories categories;

	private Questions questions;

	private Members members;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenWebLink(IDomain domain) {
		super(domain);
		comments = new Comments(this);
		categories = new Categories(this);
		questions = new Questions(this);
		members = new Members(this);
	}

	/**
	 * Gets Comment entities.
	 * 
	 * @return Comment entities
	 */
	public Comments getComments() {
		return comments;
	}

	/**
	 * Gets Category entities.
	 * 
	 * @return Category entities
	 */
	public Categories getCategories() {
		return categories;
	}

	/**
	 * Gets Question entities.
	 * 
	 * @return Question entities
	 */
	public Questions getQuestions() {
		return questions;
	}

	/**
	 * Gets Member entities.
	 * 
	 * @return Member entities
	 */
	public Members getMembers() {
		return members;
	}

}
