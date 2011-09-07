package dmeduc.weblink.category;

import java.util.Comparator;

import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;
import org.modelibra.PropertyComparator;
import org.modelibra.PropertySelector;
import org.modelibra.util.CaseInsensitiveStringComparator;

/**
 * Category entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-12
 */
@SuppressWarnings("serial")
public class Categories extends Entities<Category> {

	/**
	 * Constructs categories within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Categories(IDomainModel domainModel) {
		super(domainModel);
	}

	/**
	 * Retrieves the category with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return category
	 */
	public Category getCategory(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the category with a given oid unique number. Null if not found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return category
	 */
	public Category getCategory(Long oidUniqueNumber) {
		return getCategory(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first category whose property with a property code is equal
	 * to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return category
	 */
	public Category getCategory(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Retrieves the category with a name. Null if not found.
	 * 
	 * @param name
	 *            name
	 * @return category
	 */
	public Category getCategoryByName(String name) {
		return getCategory("name", name);
	}

	/**
	 * Selects categories whose property with a property code is equal to a
	 * property object. Returns empty categories if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return categories
	 */
	public Categories getCategories(String propertyCode, Object property) {
		return (Categories) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets categories ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered categories
	 */
	public Categories getCategories(String propertyCode, boolean ascending) {
		return (Categories) orderByProperty(propertyCode, ascending);
	}

	/**
	 * Gets categories selected by a selector. Returns empty categories if there
	 * are no categories that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected categories
	 */
	public Categories getCategories(ISelector selector) {
		return (Categories) selectBySelector(selector);
	}

	/**
	 * Gets categories ordered by a comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered categories
	 */
	public Categories getCategories(Comparator<Category> comparator,
			boolean ascending) {
		return (Categories) orderByComparator(comparator, ascending);
	}

	/**
	 * Gets approved categories.
	 * 
	 * @return selected categories
	 */
	public Categories getApprovedCategories() {
		PropertySelector propertySelector = new PropertySelector("approved");
		propertySelector.defineEqual(Boolean.TRUE);
		return getCategories(propertySelector);
	}

	/**
	 * Gets not approved categories.
	 * 
	 * @return selected categories
	 */
	public Categories getNotApprovedCategories() {
		PropertySelector propertySelector = new PropertySelector("approved");
		propertySelector.defineEqual(Boolean.FALSE);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories that contain a keyword.
	 * 
	 * @param keyword
	 *            keyword
	 * @return selected categories
	 */
	public Categories getKeywordCategories(String keyword) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContain(keyword);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories that contain some keywords.
	 * 
	 * @param keywords
	 *            keywords
	 * @return selected categories
	 */
	public Categories getSomeKeywordCategories(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContainSome(keywords);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories that contain all keywords.
	 * 
	 * @param keywords
	 *            keywords
	 * @return selected categories
	 */
	public Categories getAllKeywordCategories(String[] keywords) {
		PropertySelector propertySelector = new PropertySelector("description");
		propertySelector.defineContainAll(keywords);
		return getCategories(propertySelector);
	}

	/**
	 * Gets categories ordered by name.
	 * 
	 * @return ordered categories
	 */
	public Categories getCategoriesOrderedByName() {
		return getCategories("name", true);
	}

	/**
	 * Gets categories ordered by name by ignoring the case of the name.
	 * 
	 * @return ordered categories
	 */
	public Categories getCategoriesOrderedByNameIgnoringCase() {
		CaseInsensitiveStringComparator caseInsensitiveStringComparator = new CaseInsensitiveStringComparator();
		PropertyComparator<Category> propertyComparator = new PropertyComparator<Category>(
				"name", caseInsensitiveStringComparator);
		return getCategories(propertyComparator, true);
	}

	/**
	 * Creates a category.
	 * 
	 * @param name
	 *            name
	 * @return category
	 */
	public Category createCategory(String name) {
		Category category = new Category(getModel());
		category.setName(name);
		if (!add(category)) {
			category = null;
		}
		return category;
	}

	/**
	 * Creates a category.
	 * 
	 * @param name
	 *            name param description description
	 * @param approved
	 *            approved
	 * @return category
	 */
	public Category createCategory(String name, String description,
			boolean approved) {
		Category category = new Category(getModel());
		category.setName(name);
		category.setDescription(description);
		category.setApproved(approved);
		if (!add(category)) {
			category = null;
		}
		return category;
	}

}