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
package modelibra.designer.metamodel;

import modelibra.designer.metadomain.MetaDomain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import external parent entity and entities classes ======= */

/**
 * MetaModel specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaModels extends GenMetaModels {

	private static final long serialVersionUID = 1208025838301L;

	private static Log log = LogFactory.getLog(MetaModels.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaModels within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaModels(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaModels for the metaDomain parent.
	 * 
	 * @param metaDomain
	 *            metaDomain
	 */
	public MetaModels(MetaDomain metaDomain) {
		super(metaDomain);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public MetaModel getMetaModel(int number) {
		int count = 0;
		for (MetaModel metaModel : this) {
			if (number == count) {
				return metaModel;
			}
			count++;
		}
		return null;
	}

}