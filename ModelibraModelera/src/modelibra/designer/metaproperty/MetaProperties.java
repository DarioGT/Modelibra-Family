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
 * MetaProperty specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaProperties extends GenMetaProperties {

	private static final long serialVersionUID = 1211138929242L;

	private static Log log = LogFactory.getLog(MetaProperties.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaProperties within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaProperties(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaProperties for the metaConcept parent.
	 * 
	 * @param metaConcept
	 *            metaConcept
	 */
	public MetaProperties(MetaConcept metaConcept) {
		super(metaConcept);
	}

	/**
	 * Constructs metaProperties for the metaType parent.
	 * 
	 * @param metaType
	 *            metaType
	 */
	public MetaProperties(MetaType metaType) {
		super(metaType);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public MetaProperty getMetaProperty(int number) {
		int count = 0;
		for (MetaProperty metaProperty : this) {
			if (number == count) {
				return metaProperty;
			}
			count++;
		}
		return null;
	}

}