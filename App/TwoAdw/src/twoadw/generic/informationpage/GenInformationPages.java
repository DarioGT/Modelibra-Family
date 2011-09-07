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
package twoadw.generic.informationpage;

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


/* ======= import external parent entity and entities classes ======= */


/* ======= for each internal (part of) many-to-many child: import many-to-many entities class and its external parent entity class ======= */
    

/**
 * InformationPage generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-27
 */
public abstract class GenInformationPages extends Entities<InformationPage> {
	
	private static final long serialVersionUID = 1236708441724L;

	private static Log log = LogFactory.getLog(GenInformationPages.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs informationPages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenInformationPages(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the informationPage with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return informationPage
	 */
public InformationPage getInformationPage(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the informationPage with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return informationPage
	 */
	public InformationPage getInformationPage(Long oidUniqueNumber) {
		return getInformationPage(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first informationPage whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return informationPage
	 */
	public InformationPage getInformationPage(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects informationPages whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return informationPages
	 */
	public InformationPages getInformationPages(String propertyCode, Object property) {
		return (InformationPages) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets informationPages ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered informationPages
	 */
	public InformationPages getInformationPages(String propertyCode, boolean ascending) {
		return (InformationPages) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets informationPages selected by a selector. Returns empty informationPages if there are no
	 * informationPages that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected informationPages
	 */
	public InformationPages getInformationPages(ISelector selector) {
		return (InformationPages) selectBySelector(selector);
	}
	
	/**
	 * Gets informationPages ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered informationPages
	 */
	public InformationPages getInformationPages(Comparator comparator, boolean ascending) {
		return (InformationPages) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets title informationPages.
		 * 
		 * @param title 
		 *            title
		 * @return title informationPages
		 */
		public InformationPages getTitleInformationPages(String title) {
			PropertySelector propertySelector = new PropertySelector("title");
			propertySelector.defineEqual(title);
			return getInformationPages(propertySelector);
		}
		
	/**
		 * Gets page informationPages.
		 * 
		 * @param page 
		 *            page
		 * @return page informationPages
		 */
		public InformationPages getPageInformationPages(String page) {
			PropertySelector propertySelector = new PropertySelector("page");
			propertySelector.defineEqual(page);
			return getInformationPages(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets codePage informationPage.
		 * 
		 * @param codePage 
		 *            codePage
		 * @return codePage informationPage
		 */
		public InformationPage getCodePageInformationPage(String codePage) {
			PropertySelector propertySelector = new PropertySelector("codePage");
						propertySelector.defineEqual(codePage);
			List<InformationPage> list = getInformationPages(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets informationPages ordered by codePage.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered informationPages
		 */
		public InformationPages getInformationPagesOrderedByCodePage(boolean ascending) {			
			return getInformationPages("codePage", ascending);
		}
	
	/**
		 * Gets informationPages ordered by title.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered informationPages
		 */
		public InformationPages getInformationPagesOrderedByTitle(boolean ascending) {			
			return getInformationPages("title", ascending);
		}
	
	/**
		 * Gets informationPages ordered by page.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered informationPages
		 */
		public InformationPages getInformationPagesOrderedByPage(boolean ascending) {			
			return getInformationPages("page", ascending);
		}
	
		
	/* ======= for a many-to-many concept: get entity method based on all many-to-many parents ======= */
	
			
	/* ======= for each internal (part of) many-to-many child: get entities method based on the many-to-many external parent ======= */
    
	
	/* ======= internal parent set and get methods ======= */
    
	
	/* ======= external parent set and get methods ======= */
    
	
	/* ======= for a many-to-many concept: post add propagation ======= */
	
			
	/* ======= for a many-to-many concept: post remove propagation ======= */
	
			
	/* ======= for a many-to-many concept: post update propagation ======= */
	
			
	/* ======= for a non many-to-many concept that has at least one external parent: post add propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post remove propagation ======= */
	
		
	/* ======= for a non many-to-many concept that has at least one external parent: post update propagation ======= */
	
		
	/* ======= create entity method ======= */
	
		/**
	 * Creates informationPage.
	 *
		 * @param codePage codePage 
	 * @param title title 
	 * @param page page 
		 * @return informationPage
	 */
	public InformationPage createInformationPage(
											String codePage,
											String title,
											String page 
				) {
		InformationPage informationPage = new InformationPage(getModel());
						informationPage.setCodePage(codePage);
				informationPage.setTitle(title);
				informationPage.setPage(page);
				if (!add(informationPage)) {
			informationPage = null;
		}
		return informationPage;
	}

}