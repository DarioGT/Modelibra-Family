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
package modelibra.designer.metaconceptgraphic;

import modelibra.designer.metaconcept.MetaConcept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import external parent entity and entities classes ======= */

/**
 * MetaConceptGraphic specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaConceptGraphics extends GenMetaConceptGraphics {

	private static final long serialVersionUID = 1208025959034L;

	private static Log log = LogFactory.getLog(MetaConceptGraphics.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConceptGraphics within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaConceptGraphics(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaConceptGraphics for the metaConcept parent.
	 * 
	 * @param metaConcept
	 *            metaConcept
	 */
	public MetaConceptGraphics(MetaConcept metaConcept) {
		super(metaConcept);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}