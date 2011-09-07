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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Patient specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-09-17
 */
public class Patients extends GenPatients {
	
	private static final long serialVersionUID = 1253218298657L;

	private static Log log = LogFactory.getLog(Patients.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs patients within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Patients(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}