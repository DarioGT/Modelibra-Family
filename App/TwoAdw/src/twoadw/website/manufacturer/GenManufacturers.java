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
package twoadw.website.manufacturer;

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
 * Manufacturer generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenManufacturers extends Entities<Manufacturer> {
	
	private static final long serialVersionUID = 1234727536424L;

	private static Log log = LogFactory.getLog(GenManufacturers.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs manufacturers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenManufacturers(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the manufacturer with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return manufacturer
	 */
public Manufacturer getManufacturer(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the manufacturer with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return manufacturer
	 */
	public Manufacturer getManufacturer(Long oidUniqueNumber) {
		return getManufacturer(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first manufacturer whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return manufacturer
	 */
	public Manufacturer getManufacturer(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects manufacturers whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return manufacturers
	 */
	public Manufacturers getManufacturers(String propertyCode, Object property) {
		return (Manufacturers) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets manufacturers ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered manufacturers
	 */
	public Manufacturers getManufacturers(String propertyCode, boolean ascending) {
		return (Manufacturers) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets manufacturers selected by a selector. Returns empty manufacturers if there are no
	 * manufacturers that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected manufacturers
	 */
	public Manufacturers getManufacturers(ISelector selector) {
		return (Manufacturers) selectBySelector(selector);
	}
	
	/**
	 * Gets manufacturers ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered manufacturers
	 */
	public Manufacturers getManufacturers(Comparator comparator, boolean ascending) {
		return (Manufacturers) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets name manufacturers.
		 * 
		 * @param name 
		 *            name
		 * @return name manufacturers
		 */
		public Manufacturers getNameManufacturers(String name) {
			PropertySelector propertySelector = new PropertySelector("name");
			propertySelector.defineEqual(name);
			return getManufacturers(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets manufacturerNumber manufacturer.
		 * 
		 * @param manufacturerNumber 
		 *            manufacturerNumber
		 * @return manufacturerNumber manufacturer
		 */
		public Manufacturer getManufacturerNumberManufacturer(String manufacturerNumber) {
			PropertySelector propertySelector = new PropertySelector("manufacturerNumber");
						propertySelector.defineEqual(manufacturerNumber);
			List<Manufacturer> list = getManufacturers(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets manufacturers ordered by manufacturerNumber.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered manufacturers
		 */
		public Manufacturers getManufacturersOrderedByManufacturerNumber(boolean ascending) {			
			return getManufacturers("manufacturerNumber", ascending);
		}
	
	/**
		 * Gets manufacturers ordered by name.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered manufacturers
		 */
		public Manufacturers getManufacturersOrderedByName(boolean ascending) {			
			return getManufacturers("name", ascending);
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
	 * Creates manufacturer.
	 *
		 * @param manufacturerNumber manufacturerNumber 
	 * @param name name 
		 * @return manufacturer
	 */
	public Manufacturer createManufacturer(
											String manufacturerNumber,
											String name 
				) {
		Manufacturer manufacturer = new Manufacturer(getModel());
						manufacturer.setManufacturerNumber(manufacturerNumber);
				manufacturer.setName(name);
				if (!add(manufacturer)) {
			manufacturer = null;
		}
		return manufacturer;
	}

}