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
package modelibra.designer.metatype;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * MetaType specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class MetaTypes extends GenMetaTypes {

	private static final long serialVersionUID = 1211139625810L;

	private static Log log = LogFactory.getLog(MetaTypes.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaTypes within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaTypes(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public MetaType getMetaType(int number) {
		int count = 0;
		for (MetaType metaType : this) {
			if (number == count) {
				return metaType;
			}
			count++;
		}
		return null;
	}

}