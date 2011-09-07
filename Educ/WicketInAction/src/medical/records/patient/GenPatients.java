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
package medical.records.patient;

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
 * Patient generated entities. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-09-17
 */
public abstract class GenPatients extends Entities<Patient> {
	
	private static final long serialVersionUID = 1253218298655L;

	private static Log log = LogFactory.getLog(GenPatients.class);
	
	/* ======= internal parent neighbors ======= */
	
	    
/* ======= external parent neighbors ======= */
	

	/* ======= base constructor ======= */
	
	/**
	 * Constructs patients within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenPatients(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	 
	/* ======= get entity methods based on a property ======= */
    
/**
 * Retrieves the patient with a given oid. 
 * Null if not found. 
	 * 
	 * @param oid
	 *            oid
	 * @return patient
	 */
public Patient getPatient(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
 * Retrieves the patient with a given oid unique number. 
 * Null if not found. 
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return patient
	 */
	public Patient getPatient(Long oidUniqueNumber) {
		return getPatient(new Oid(oidUniqueNumber));
	}
    
/**
 * Retrieves the first patient whose property with a  
 * property code is equal to a property object. Null if not found. 
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return patient
	 */
	public Patient getPatient(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}
	
			
		/**
	 * Selects patients whose property with a property code is equal to a property
	 * object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return patients
	 */
	public Patients getPatients(String propertyCode, Object property) {
		return (Patients) selectByProperty(propertyCode, property);
	}
	
	/**
	 * Gets patients ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered patients
	 */
	public Patients getPatients(String propertyCode, boolean ascending) {
		return (Patients) orderByProperty(propertyCode, ascending);
	}
	
	/* ======= selector and order methods ======= */
	
	/**
	 * Gets patients selected by a selector. Returns empty patients if there are no
	 * patients that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected patients
	 */
	public Patients getPatients(ISelector selector) {
		return (Patients) selectBySelector(selector);
	}
	
	/**
	 * Gets patients ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered patients
	 */
	public Patients getPatients(Comparator comparator, boolean ascending) {
		return (Patients) orderByComparator(comparator, ascending);
	}
	
	/* ======= property selector methods for not unique essential properties without email (org.modelibra.type.Email) and url (java.net.URL) ======= */
	
	/**
		 * Gets firstName patients.
		 * 
		 * @param firstName 
		 *            firstName
		 * @return firstName patients
		 */
		public Patients getFirstNamePatients(String firstName) {
			PropertySelector propertySelector = new PropertySelector("firstName");
			propertySelector.defineEqual(firstName);
			return getPatients(propertySelector);
		}
		
	/**
		 * Gets lastName patients.
		 * 
		 * @param lastName 
		 *            lastName
		 * @return lastName patients
		 */
		public Patients getLastNamePatients(String lastName) {
			PropertySelector propertySelector = new PropertySelector("lastName");
			propertySelector.defineEqual(lastName);
			return getPatients(propertySelector);
		}
		
		
	/* ======= property selector methods for unique properties ======= */
	
	/**
		 * Gets email patient.
		 * 
		 * @param email 
		 *            email
		 * @return email patient
		 */
		public Patient getEmailPatient(String email) {
			PropertySelector propertySelector = new PropertySelector("email");
						propertySelector.defineEqual(email);
			List<Patient> list = getPatients(propertySelector).getList();
			
			if (list.size() > 0)
				return list.iterator().next();
			else
				return null;
		}
		
		
	/* ======= reference property selector methods for a non many-to-many concept that has at least one external parent ======= */
	
					
	/* ======= order methods for essential properties ======= */
	
	/**
		 * Gets patients ordered by firstName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered patients
		 */
		public Patients getPatientsOrderedByFirstName(boolean ascending) {			
			return getPatients("firstName", ascending);
		}
	
	/**
		 * Gets patients ordered by lastName.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered patients
		 */
		public Patients getPatientsOrderedByLastName(boolean ascending) {			
			return getPatients("lastName", ascending);
		}
	
	/**
		 * Gets patients ordered by email.
		 * 
		 * @param ascending
		 *            <code>true</code> if ascending
		 * @return ordered patients
		 */
		public Patients getPatientsOrderedByEmail(boolean ascending) {			
			return getPatients("email", ascending);
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
	 * Creates patient.
	 *
		 * @param firstName firstName 
	 * @param lastName lastName 
	 * @param email email 
		 * @return patient
	 */
	public Patient createPatient(
											String firstName,
											String lastName,
											String email 
				) {
		Patient patient = new Patient(getModel());
						patient.setFirstName(firstName);
				patient.setLastName(lastName);
				patient.setEmail(email);
				if (!add(patient)) {
			patient = null;
		}
		return patient;
	}

}