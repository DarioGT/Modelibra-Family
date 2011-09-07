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

import modelibra.designer.metaconcept.MetaConcepts;
import modelibra.designer.metadomain.MetaDomain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * MetaModel generated entity. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaModel extends Entity<MetaModel> {

	private static final long serialVersionUID = 1208025838298L;

	private static Log log = LogFactory.getLog(GenMetaModel.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private String author;

	private String description;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private MetaDomain metaDomain;

	/* ======= internal child neighbors ======= */

	private MetaConcepts metaConcepts;

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaModel within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaModel(IDomainModel model) {
		super(model);
		// internal child neighbors only
		setMetaConcepts(new MetaConcepts((MetaModel) this));
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaModel within its parent(s).
	 * 
	 * @param MetaDomain
	 *            metaDomain
	 */
	public GenMetaModel(MetaDomain metaDomain) {
		this(metaDomain.getModel());
		// parents
		setMetaDomain(metaDomain);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets author.
	 * 
	 * @param author
	 *            author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets author.
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets metaDomain.
	 * 
	 * @param metaDomain
	 *            metaDomain
	 */
	public void setMetaDomain(MetaDomain metaDomain) {
		this.metaDomain = metaDomain;
	}

	/**
	 * Gets metaDomain.
	 * 
	 * @return metaDomain
	 */
	public MetaDomain getMetaDomain() {
		return metaDomain;
	}

	/* ======= internal child set and get methods ======= */

	/**
	 * Sets metaConcepts.
	 * 
	 * @param metaConcepts
	 *            metaConcepts
	 */
	public void setMetaConcepts(MetaConcepts metaConcepts) {
		this.metaConcepts = metaConcepts;
		if (metaConcepts != null) {
			metaConcepts.setMetaModel((MetaModel) this);
		}
	}

	/**
	 * Gets metaConcepts.
	 * 
	 * @return metaConcepts
	 */
	public MetaConcepts getMetaConcepts() {
		return metaConcepts;
	}

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}