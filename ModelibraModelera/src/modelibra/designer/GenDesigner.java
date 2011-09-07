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
import modelibra.designer.metatype.MetaTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.IDomain;

/**
 * Designer generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenDesigner extends DomainModel {

	private static final long serialVersionUID = 1208025817276L;

	private static Log log = LogFactory.getLog(GenDesigner.class);

	private MetaDomains metaDomains;

	private MetaTypes metaTypes;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenDesigner(IDomain domain) {
		super(domain);
		metaDomains = new MetaDomains(this);
		metaTypes = new MetaTypes(this);
	}

	/**
	 * Gets MetaDomain entities.
	 * 
	 * @return MetaDomain entities
	 */
	public MetaDomains getMetaDomains() {
		return metaDomains;
	}

	/**
	 * Gets MetaType entities.
	 * 
	 * @return MetaType entities
	 */
	public MetaTypes getMetaTypes() {
		return metaTypes;
	}

	/**
	 * Gets all MetaProperty entities.
	 * 
	 * @return MetaProperty entities
	 */
	public MetaProperties getMetaProperties() {
		MetaProperties allMetaProperties = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allMetaProperties = new MetaProperties(this);
				allMetaProperties.setPersistent(false);
				allMetaProperties.setPre(false);
				allMetaProperties.setPost(false);
				MetaConcepts metaConcepts = getMetaConcepts();
				for (MetaConcept metaConcept : metaConcepts) {
					MetaProperties metaConceptMetaProperties = metaConcept
							.getMetaProperties();
					for (MetaProperty metaProperty : metaConceptMetaProperties) {
						allMetaProperties.add(metaProperty);
					}
				}
			} catch (Exception e) {
				log.error("Error in GenDesigner.getMetaProperties(): "
						+ e.getMessage());
			} finally {
				allMetaProperties.setPersistent(true);
				allMetaProperties.setPre(true);
				allMetaProperties.setPost(true);
			}
		}
		return allMetaProperties;
	}

	/**
	 * Gets all MetaConcept entities.
	 * 
	 * @return MetaConcept entities
	 */
	public MetaConcepts getMetaConcepts() {
		MetaConcepts allMetaConcepts = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allMetaConcepts = new MetaConcepts(this);
				allMetaConcepts.setPersistent(false);
				allMetaConcepts.setPre(false);
				allMetaConcepts.setPost(false);
				MetaModels metaModels = getMetaModels();
				for (MetaModel metaModel : metaModels) {
					MetaConcepts metaModelMetaConcepts = metaModel
							.getMetaConcepts();
					for (MetaConcept metaConcept : metaModelMetaConcepts) {
						allMetaConcepts.add(metaConcept);
					}
				}
			} catch (Exception e) {
				log.error("Error in GenDesigner.getMetaConcepts(): "
						+ e.getMessage());
			} finally {
				allMetaConcepts.setPersistent(true);
				allMetaConcepts.setPre(true);
				allMetaConcepts.setPost(true);
			}
		}
		return allMetaConcepts;
	}

	/**
	 * Gets all MetaModel entities.
	 * 
	 * @return MetaModel entities
	 */
	public MetaModels getMetaModels() {
		MetaModels allMetaModels = null;
		boolean dataLoaded = isInitialized();
		if (dataLoaded) {
			try {
				allMetaModels = new MetaModels(this);
				allMetaModels.setPersistent(false);
				allMetaModels.setPre(false);
				allMetaModels.setPost(false);
				MetaDomains metaDomains = getMetaDomains();
				for (MetaDomain metaDomain : metaDomains) {
					MetaModels metaDomainMetaModels = metaDomain
							.getMetaModels();
					for (MetaModel metaModel : metaDomainMetaModels) {
						allMetaModels.add(metaModel);
					}
				}
			} catch (Exception e) {
				log.error("Error in GenDesigner.getMetaModels(): "
						+ e.getMessage());
			} finally {
				allMetaModels.setPersistent(true);
				allMetaModels.setPre(true);
				allMetaModels.setPost(true);
			}
		}
		return allMetaModels;
	}

}
