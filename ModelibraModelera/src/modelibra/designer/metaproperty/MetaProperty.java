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
package modelibra.designer.metaproperty;

import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metatype.MetaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/**
 * MetaProperty specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaProperty extends GenMetaProperty {

	private static final long serialVersionUID = 1211138929241L;

	private static Log log = LogFactory.getLog(MetaProperty.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaProperty within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaProperty(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaProperty within its parent(s).
	 * 
	 * @param MetaConcept
	 *            metaConcept
	 * @param MetaType
	 *            metaType
	 */
	public MetaProperty(MetaConcept metaConcept, MetaType metaType) {
		super(metaConcept, metaType);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	/**
	 * Constructs metaProperty within its parent(s).
	 * 
	 * @param MetaConcept
	 *            metaConcept
	 */
	public MetaProperty(MetaConcept metaConcept) {
		super(metaConcept, null);
	}

	/**
	 * Sets id.
	 * 
	 * @param id
	 *            id
	 */
	public void setId(Boolean id) {
		super.setId(id);
		if (id) {
			this.setValue(true);
		}
	}

}