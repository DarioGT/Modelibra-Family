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
package course.reference.questiontype;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * QuestionType generated entities. This class should not be changed manually.
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenQuestionTypes extends Entities<QuestionType> {

	private static final long serialVersionUID = 1176746303135L;

	private static Log log = LogFactory.getLog(GenQuestionTypes.class);

	/* ======= internal parent neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs questionTypes within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQuestionTypes(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the questionType with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return questionType
	 */
	public QuestionType getQuestionType(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the questionType with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return questionType
	 */
	public QuestionType getQuestionType(Long oidUniqueNumber) {
		return getQuestionType(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first questionType whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return questionType
	 */
	public QuestionType getQuestionType(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects questionTypes whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return questionTypes
	 */
	public QuestionTypes getQuestionTypes(String propertyCode, Object property) {
		return (QuestionTypes) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets questionTypes ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered questionTypes
	 */
	public QuestionTypes getQuestionTypes(String propertyCode, boolean ascending) {
		return (QuestionTypes) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets questionTypes selected by a selector. Returns empty questionTypes if
	 * there are no questionTypes that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected questionTypes
	 */
	public QuestionTypes getQuestionTypes(ISelector selector) {
		return (QuestionTypes) selectBySelector(selector);
	}

	/**
	 * Gets questionTypes ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered questionTypes
	 */
	public QuestionTypes getQuestionTypes(Comparator comparator,
			boolean ascending) {
		return (QuestionTypes) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets type questionTypes.
	 * 
	 * @param type
	 *            type
	 * @return type questionTypes
	 */
	public QuestionTypes getTypeQuestionTypes(String type) {
		PropertySelector propertySelector = new PropertySelector("type");
		propertySelector.defineEqual(type);
		return getQuestionTypes(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets questionTypes ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered questionTypes
	 */
	public QuestionTypes getQuestionTypesOrderedByCode(boolean ascending) {
		return getQuestionTypes("code", ascending);
	}

	/**
	 * Gets questionTypes ordered by type.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered questionTypes
	 */
	public QuestionTypes getQuestionTypesOrderedByType(boolean ascending) {
		return getQuestionTypes("type", ascending);
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

	/* ======= external parent set and get methods ======= */

	/* ======= for a many-to-many concept: post add propagation ======= */

	/* ======= for a many-to-many concept: post remove propagation ======= */

	/* ======= for a many-to-many concept: post update propagation ======= */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates questionType.
	 * 
	 * @param code
	 *            code
	 * @param type
	 *            type
	 * @return questionType
	 */
	public QuestionType createQuestionType(String code, String type) {
		QuestionType questionType = new QuestionType(getModel());
		questionType.setCode(code);
		questionType.setType(type);
		if (!add(questionType)) {
			questionType = null;
		}
		return questionType;
	}

}