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
package twoadw.reference.state;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

import twoadw.reference.country.Country;	

/* ======= import external parent entity and entities classes ======= */


/**
 * State specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class States extends GenStates {
	
	private static final long serialVersionUID = 1236723018875L;

	private static Log log = LogFactory.getLog(States.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs states within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public States(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
		    /**
		 * Constructs states for the countryName parent.
		 * 
		 * @param countryName
		 *            countryName
		 */
		public States(Country countryName) {
			super(countryName);
		}
	
        
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}