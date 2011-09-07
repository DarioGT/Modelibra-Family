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
package twoadw.generic.template;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Template specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Templates extends GenTemplates {
	
	private static final long serialVersionUID = 1236796790479L;

	private static Log log = LogFactory.getLog(Templates.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs templates within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Templates(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	/**
	 * Creates template.
	 * 
	 * @param name
	 *            name
	 * @return template
	 */
	public Template createTemplate(String name, String directory) {
		Template template = new Template(getModel());
		template.setName(name);
		template.setDirectory(directory);
		if (!add(template)) {
			template = null;
		}
		return template;
	}
}