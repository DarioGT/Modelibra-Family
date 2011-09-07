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
package twoadw.website.questioncategory;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.PropertySelector;

/* ======= import essential property classes ======= */


/* ======= import internal parent entity class ======= */

import twoadw.website.questioncategory.QuestionCategory;	

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * QuestionCategory generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenQuestionCategories extends Entities<QuestionCategory> {
	
	private static final long serialVersionUID = 1236738768963L;

	private static Log log = LogFactory.getLog(GenQuestionCategories.class);
	
	/* ======= internal parent neighbors ======= */
	
	private QuestionCategory questionCategory;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs questionCategories within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenQuestionCategories(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs questionCategories for the questionCategory parent.
		 * 
		 * @param questionCategory
		 *            questionCategory
		 */
		public GenQuestionCategories(QuestionCategory questionCategory) {
			this(questionCategory.getModel());
			// parent
			setQuestionCategory(questionCategory);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the questionCategory with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return questionCategory
	 */
public QuestionCategory getQuestionCategory(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the questionCategory with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return questionCategory
	 */
	public QuestionCategory getQuestionCategory(Long oidUniqueNumber) {
		return getQuestionCategory(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first questionCategory whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return questionCategory
	 */
	public QuestionCategory getQuestionCategory(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
				/**
	 	 * Retrieves the questionCategory in a reflexive hierarchy using a given oid unique
	 	 * number. Null if not found.
	 	 * 
	 	 * @param oidUniqueNumber
	 	 *            oid unique number
	 	 * @return questionCategory
	 	 */
		public QuestionCategory getReflexiveQuestionCategory(Long oidUniqueNumber) {
			QuestionCategory questionCategory = getQuestionCategory(oidUniqueNumber);
			if (questionCategory == null) {
				for (QuestionCategory currentQuestionCategory : this) {
					questionCategory = currentQuestionCategory.getQuestionCategories()
							.getReflexiveQuestionCategory(oidUniqueNumber);
					if (questionCategory != null) {
						break;
					}
				}
			}
			return questionCategory;
		}
		
		/**
	 * Selects questionCategories whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return questionCategories
	 */
	public QuestionCategories getQuestionCategories(String propertyCode, Object property) {
		return (QuestionCategories) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets questionCategories ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered questionCategories
	 */
	public QuestionCategories getQuestionCategories(String propertyCode, boolean ascending) {
		return (QuestionCategories) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets questionCategories selected by a selector. Returns empty questionCategories if there are no
	 * questionCategories that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected questionCategories
	 */
	public QuestionCategories getQuestionCategories(ISelector selector) {
		return (QuestionCategories) selectBySelector(selector);
	}
	
	/**
	 * Gets questionCategories ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered questionCategories
	 */
	public QuestionCategories getQuestionCategories(Comparator comparator, boolean ascending) {
		return (QuestionCategories) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets name questionCategory.
		 * 
		 * @param name 
		 *            name
		 * @return name questionCategory
		 */
		public QuestionCategory getNameQuestionCategory(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
						propertySelector.defineEqual(name);
			List<QuestionCategory> list = getQuestionCategories(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets questionCategories ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered questionCategories
		 */
		public QuestionCategories getQuestionCategoriesOrderedByName(boolean ascending) {			
			return getQuestionCategories("name", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	/**
		 * Sets questionCategory.
		 * 
		 * @param questionCategory
		 *            questionCategory
		 */
public void setQuestionCategory(QuestionCategory questionCategory) {
			this.questionCategory = questionCategory;
		}

		/**
		 * Gets questionCategory.
		 * 
		 * @return questionCategory
		 */
		public QuestionCategory getQuestionCategory() {
			return questionCategory;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates questionCategory.
	 *
			 * @param questionCategoryParent questionCategory parent
		 * @param name name 
		 * @return questionCategory
	 */
	public QuestionCategory createQuestionCategory(
																	QuestionCategory questionCategoryParent,
																	String name 
				) {
		QuestionCategory questionCategory = new QuestionCategory(getModel());
					questionCategory.setQuestionCategory(questionCategoryParent);
						questionCategory.setName(name);
				if (!add(questionCategory)) {
			questionCategory = null;
		}
		return questionCategory;
	}

}