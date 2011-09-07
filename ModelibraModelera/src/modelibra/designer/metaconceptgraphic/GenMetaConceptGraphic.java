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
import org.modelibra.Entity;
import org.modelibra.IDomainModel;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/* ======= import external child entities classes ======= */

/* ======= import external many-to-many internal parent entities classes ======= */

/**
 * MetaConceptGraphic generated entity. This class should not be changed
 * manually. Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-19
 */
public abstract class GenMetaConceptGraphic extends Entity<MetaConceptGraphic> {

	private static final long serialVersionUID = 1208025959031L;

	private static Log log = LogFactory.getLog(GenMetaConceptGraphic.class);

	/*
	 * ======= entity properties (without the code, reference and derived
	 * properties) =======
	 */

	private Integer x;

	private Integer y;

	private Integer width;

	private Integer height;

	/* ======= reference properties ======= */

	/* ======= internal parent neighbors ======= */

	private MetaConcept metaConcept;

	/* ======= internal child neighbors ======= */

	/* ======= external parent neighbors ======= */

	/* ======= external child neighbors ======= */

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConceptGraphic within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaConceptGraphic(IDomainModel model) {
		super(model);
		// internal child neighbors only
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaConceptGraphic within its parent(s).
	 * 
	 * @param MetaConcept
	 *            metaConcept
	 */
	public GenMetaConceptGraphic(MetaConcept metaConcept) {
		this(metaConcept.getModel());
		// parents
		setMetaConcept(metaConcept);
	}

	/*
	 * ======= entity property (without the code, reference and derived
	 * properties) set and get methods =======
	 */

	/**
	 * Sets x.
	 * 
	 * @param x
	 *            x
	 */
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	 * Gets x.
	 * 
	 * @return x
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * Sets y.
	 * 
	 * @param y
	 *            y
	 */
	public void setY(Integer y) {
		this.y = y;
	}

	/**
	 * Gets y.
	 * 
	 * @return y
	 */
	public Integer getY() {
		return y;
	}

	/**
	 * Sets width.
	 * 
	 * @param width
	 *            width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * Gets width.
	 * 
	 * @return width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * Sets height.
	 * 
	 * @param height
	 *            height
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * Gets height.
	 * 
	 * @return height
	 */
	public Integer getHeight() {
		return height;
	}

	/* ======= reference property set and get methods ======= */

	/* ======= derived property abstract get methods ======= */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets metaConcept.
	 * 
	 * @param metaConcept
	 *            metaConcept
	 */
	public void setMetaConcept(MetaConcept metaConcept) {
		this.metaConcept = metaConcept;
	}

	/**
	 * Gets metaConcept.
	 * 
	 * @return metaConcept
	 */
	public MetaConcept getMetaConcept() {
		return metaConcept;
	}

	/* ======= internal child set and get methods ======= */

	/* ======= external parent set and get methods ======= */

	/* ======= external one-to-many child set and get methods ======= */

	/* ======= external (part of) many-to-many child set and get methods ======= */

}