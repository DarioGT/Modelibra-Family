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
package twoadw.reference.securityrole;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * SecurityRole specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class SecurityRoles extends GenSecurityRoles {
	
	private static final long serialVersionUID = 1236722534896L;

	private static Log log = LogFactory.getLog(SecurityRoles.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs securityRoles within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public SecurityRoles(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}