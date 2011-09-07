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
package modelibra.designer;

import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metaconcept.MetaConcepts;
import modelibra.designer.metadomain.MetaDomain;
import modelibra.designer.metadomain.MetaDomains;
import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metamodel.MetaModels;
import modelibra.designer.metaproperty.MetaProperties;
import modelibra.designer.metaproperty.MetaProperty;
import modelibra.designer.metatype.MetaType;
import modelibra.designer.metatype.MetaTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;
import org.modelibra.ModelSession;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;

/**
 * Designer specific model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public class Designer extends GenDesigner {

	private static final long serialVersionUID = 1208025817277L;

	private static Log log = LogFactory.getLog(Designer.class);

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Designer(IDomain domain) {
		super(domain);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private ModelSession modelSession;

	public ModelSession getSession() {
		if (modelSession != null) {
			return modelSession;
		} else {
			modelSession = super.getNewSession();
			return modelSession;
		}
	}

	public void emptyDomains() {
		ModelSession session = getSession();
		Transaction transaction = new Transaction(session);

		MetaDomains metaDomains = getMetaDomains();
		for (MetaDomain metaDomain : metaDomains) {
			MetaModels metaModels = metaDomain.getMetaModels();
			for (MetaModel metaModel : metaModels) {
				MetaConcepts metaConcepts = metaModel.getMetaConcepts();
				for (MetaConcept metaConcept : metaConcepts) {
					MetaProperties metaProperties = metaConcept
							.getMetaProperties();
					for (MetaProperty metaProperty : metaProperties) {
						new RemoveAction(transaction, metaProperties,
								metaProperty);
					}
					new RemoveAction(transaction, metaConcepts, metaConcept);
				}
				new RemoveAction(transaction, metaModels, metaModel);
			}
			new RemoveAction(transaction, metaDomains, metaDomain);
		}

		transaction.execute();
		if (transaction.isExecuted()) {
			session = null;
		}
	}

	public void emptyTypes() {
		ModelSession session = getSession();
		Transaction transaction = new Transaction(session);

		MetaTypes metaTypes = getMetaTypes();
		for (MetaType metaType : metaTypes) {
			new RemoveAction(transaction, metaTypes, metaType);
		}

		transaction.execute();
		if (transaction.isExecuted()) {
			session = null;
		}
	}

}
