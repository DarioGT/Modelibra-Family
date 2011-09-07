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
package course.quiz.item;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

import course.quiz.question.Question;

/* ======= import external parent entity and entities classes ======= */

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * Item generated entities. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public abstract class GenItems extends Entities<Item> {

	private static final long serialVersionUID = 1176743181619L;

	private static Log log = LogFactory.getLog(GenItems.class);

	/* ======= internal parent neighbors ======= */

	private Question question;

	/* ======= external parent neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs items within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenItems(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs items for the question parent.
	 * 
	 * @param question
	 *            question
	 */
	public GenItems(Question question) {
		this(question.getModel());
		// parent
		setQuestion(question);
	}

	/* ======= get entity methods based on a property ======= */

	/**
	 * Retrieves the item with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return item
	 */
	public Item getItem(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the item with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return item
	 */
	public Item getItem(Long oidUniqueNumber) {
		return getItem(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first item whose property with a property code is equal to
	 * a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return item
	 */
	public Item getItem(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects items whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return items
	 */
	public Items getItems(String propertyCode, Object property) {
		return (Items) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets items ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered items
	 */
	public Items getItems(String propertyCode, boolean ascending) {
		return (Items) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets items selected by a selector. Returns empty items if there are no
	 * items that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected items
	 */
	public Items getItems(ISelector selector) {
		return (Items) selectBySelector(selector);
	}

	/**
	 * Gets items ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered items
	 */
	public Items getItems(Comparator comparator, boolean ascending) {
		return (Items) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/**
	 * Gets text items.
	 * 
	 * @param text
	 *            text
	 * @return text items
	 */
	public Items getTextItems(String text) {
		PropertySelector propertySelector = new PropertySelector("text");
		propertySelector.defineEqual(text);
		return getItems(propertySelector);
	}

	/* ======= property selector methods for unique properties ======= */

	/**
	 * Gets number item.
	 * 
	 * @param number
	 *            number
	 * @return number item
	 */
	public Item getNumberItem(Integer number) {
		PropertySelector propertySelector = new PropertySelector("number");
		propertySelector.defineEqual(number);
		List<Item> list = getItems(propertySelector).getList();

		if (list.size() > 0)
			return list.iterator().next();
		else
			return null;
	}

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets items ordered by number.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered items
	 */
	public Items getItemsOrderedByNumber(boolean ascending) {
		return getItems("number", ascending);
	}

	/**
	 * Gets items ordered by text.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered items
	 */
	public Items getItemsOrderedByText(boolean ascending) {
		return getItems("text", ascending);
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
	 * Sets question.
	 * 
	 * @param question
	 *            question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Gets question.
	 * 
	 * @return question
	 */
	public Question getQuestion() {
		return question;
	}

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
	 * Creates item.
	 * 
	 * @param questionParent
	 *            question parent
	 * @param text
	 *            text
	 * @return item
	 */
	public Item createItem(Question questionParent, String text) {
		Item item = new Item(getModel());
		item.setQuestion(questionParent);
		item.setText(text);
		if (!add(item)) {
			item = null;
		}
		return item;
	}

}