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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Manufacturer specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Manufacturers extends GenManufacturers {
	
	private static final long serialVersionUID = 1234727536426L;

	private static Log log = LogFactory.getLog(Manufacturers.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs manufacturers within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Manufacturers(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	/**
	 * Creates manufacturer.
	 *
		 * @param manufacturerNumber manufacturerNumber 
	 * @param name name 
		 * @return manufacturer
	 */
	public Manufacturer createManufacturer(	String manufacturerNumber,
											String name,
											String websiteUrl,
											String supportUrl,
											String contactEmail,
											String contactName
											) {
		Manufacturer manufacturer = new Manufacturer(getModel());
		manufacturer.setManufacturerNumber(manufacturerNumber);
		manufacturer.setName(name);
		manufacturer.setWebsiteUrl(websiteUrl);
		manufacturer.setSupportUrl(supportUrl);
		manufacturer.setContactEmail(contactEmail);
		manufacturer.setContactName(contactName);
		if (!add(manufacturer)) {
				manufacturer = null;
		}
		return manufacturer;
	}
}