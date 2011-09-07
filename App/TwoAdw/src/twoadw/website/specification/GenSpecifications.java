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
package twoadw.website.specification;

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

import twoadw.website.specificationcategory.SpecificationCategory;	

/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * Specification generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenSpecifications extends Entities<Specification> {
	
	private static final long serialVersionUID = 1236796162011L;

	private static Log log = LogFactory.getLog(GenSpecifications.class);
	
	/* ======= internal parent neighbors ======= */
	
	private SpecificationCategory specificationCategory;	
    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs specifications within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenSpecifications(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		/**
		 * Constructs specifications for the specificationCategory parent.
		 * 
		 * @param specificationCategory
		 *            specificationCategory
		 */
		public GenSpecifications(SpecificationCategory specificationCategory) {
			this(specificationCategory.getModel());
			// parent
			setSpecificationCategory(specificationCategory);
		}
	
     
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the specification with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return specification
	 */
public Specification getSpecification(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the specification with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return specification
	 */
	public Specification getSpecification(Long oidUniqueNumber) {
		return getSpecification(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first specification whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return specification
	 */
	public Specification getSpecification(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects specifications whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return specifications
	 */
	public Specifications getSpecifications(String propertyCode, Object property) {
		return (Specifications) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets specifications ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered specifications
	 */
	public Specifications getSpecifications(String propertyCode, boolean ascending) {
		return (Specifications) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets specifications selected by a selector. Returns empty specifications if there are no
	 * specifications that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected specifications
	 */
	public Specifications getSpecifications(ISelector selector) {
		return (Specifications) selectBySelector(selector);
	}
	
	/**
	 * Gets specifications ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered specifications
	 */
	public Specifications getSpecifications(Comparator comparator, boolean ascending) {
		return (Specifications) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets detail specifications.
		 * 
		 * @param detail 
		 *            detail
		 * @return detail specifications
		 */
		public Specifications getDetailSpecifications(String detail) {
			PropertySelector propertySelector = new PropertySelector("detail");
			propertySelector.defineEqual(detail);
			return getSpecifications(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets title specification.
		 * 
		 * @param title 
		 *            title
		 * @return title specification
		 */
		public Specification getTitleSpecification(String title) {
			PropertySelector propertySelector = new PropertySelector("title");
						propertySelector.defineEqual(title);
			List<Specification> list = getSpecifications(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets specifications ordered by title.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered specifications
		 */
		public Specifications getSpecificationsOrderedByTitle(boolean ascending) {			
			return getSpecifications("title", ascending);
		}
	
	/**
		 * Gets specifications ordered by detail.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered specifications
		 */
		public Specifications getSpecificationsOrderedByDetail(boolean ascending) {			
			return getSpecifications("detail", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	/**
		 * Sets specificationCategory.
		 * 
		 * @param specificationCategory
		 *            specificationCategory
		 */
public void setSpecificationCategory(SpecificationCategory specificationCategory) {
			this.specificationCategory = specificationCategory;
		}

		/**
		 * Gets specificationCategory.
		 * 
		 * @return specificationCategory
		 */
		public SpecificationCategory getSpecificationCategory() {
			return specificationCategory;
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
	 * Creates specification.
	 *
			 * @param specificationCategoryParent specificationCategory parent
		 * @param title title 
	 * @param detail detail 
		 * @return specification
	 */
	public Specification createSpecification(
																	SpecificationCategory specificationCategoryParent,
																	String title,
											String detail 
				) {
		Specification specification = new Specification(getModel());
					specification.setSpecificationCategory(specificationCategoryParent);
						specification.setTitle(title);
				specification.setDetail(detail);
				if (!add(specification)) {
			specification = null;
		}
		return specification;
	}

}