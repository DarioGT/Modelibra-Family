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
package modelibra.designer.metaneighbor;

import java.util.Comparator;

import modelibra.designer.metaconcept.MetaConcept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.Entities;
import org.modelibra.IDomainModel;
import org.modelibra.ISelector;
import org.modelibra.Oid;

/*
 * ======= for each internal (part of) many-to-many child: import many-to-many
 * entities class and its external parent entity class =======
 */

/**
 * MetaNeighbor generated entities. This class should not be changed manually.
 * Use a subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-30
 */
public abstract class GenMetaNeighbors extends Entities<MetaNeighbor> {

	private static final long serialVersionUID = 1211983552927L;

	private static Log log = LogFactory.getLog(GenMetaNeighbors.class);

	/* ======= internal parent neighbors ======= */

	private MetaConcept metaSourceConcept;

	/* ======= external parent neighbors ======= */

	private MetaConcept metaDestinationConcept;

	/* ======= base constructor ======= */

	/**
	 * Constructs metaNeighbors within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenMetaNeighbors(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaNeighbors for the metaConcept parent: source or
	 * destination.
	 * 
	 * @param metaSourceConcept
	 *            metaSourceConcept
	 * @param metaDestinationConcept
	 *            metaDestinationConcept
	 */
	public GenMetaNeighbors(MetaConcept metaSourceConcept,
			MetaConcept metaDestinationConcept) {
		this(metaSourceConcept != null ? metaSourceConcept.getModel()
				: metaDestinationConcept.getModel());
		// parent
		if (metaSourceConcept != null) {
			setMetaSourceConcept(metaSourceConcept);
		} else if (metaDestinationConcept != null) {
			setMetaDestinationConcept(metaDestinationConcept);
		}
	}

	/**
	 * Retrieves the metaNeighbor with a given oid. Null if not found.
	 * 
	 * @param oid
	 *            oid
	 * @return metaNeighbor
	 */
	public MetaNeighbor getMetaNeighbor(Oid oid) {
		return retrieveByOid(oid);
	}

	/**
	 * Retrieves the metaNeighbor with a given oid unique number. Null if not
	 * found.
	 * 
	 * @param oidUniqueNumber
	 *            oid unique number
	 * @return metaNeighbor
	 */
	public MetaNeighbor getMetaNeighbor(Long oidUniqueNumber) {
		return getMetaNeighbor(new Oid(oidUniqueNumber));
	}

	/**
	 * Retrieves the first metaNeighbor whose property with a property code is
	 * equal to a property object. Null if not found.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaNeighbor
	 */
	public MetaNeighbor getMetaNeighbor(String propertyCode, Object property) {
		return retrieveByProperty(propertyCode, property);
	}

	/**
	 * Selects metaNeighbors whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return metaNeighbors
	 */
	public MetaNeighbors getMetaNeighbors(String propertyCode, Object property) {
		return (MetaNeighbors) selectByProperty(propertyCode, property);
	}

	/**
	 * Gets metaNeighbors ordered by a property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaNeighbors
	 */
	public MetaNeighbors getMetaNeighbors(String propertyCode, boolean ascending) {
		return (MetaNeighbors) orderByProperty(propertyCode, ascending);
	}

	/* ======= selector and order methods ======= */

	/**
	 * Gets metaNeighbors selected by a selector. Returns empty metaNeighbors if
	 * there are no metaNeighbors that satisfy the selector.
	 * 
	 * @param selector
	 *            selector
	 * @return selected metaNeighbors
	 */
	public MetaNeighbors getMetaNeighbors(ISelector selector) {
		return (MetaNeighbors) selectBySelector(selector);
	}

	/**
	 * Gets metaNeighbors ordered by a composite comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return ordered metaNeighbors
	 */
	public MetaNeighbors getMetaNeighbors(Comparator comparator,
			boolean ascending) {
		return (MetaNeighbors) orderByComparator(comparator, ascending);
	}

	/*
	 * ======= property selector methods for not unique essential properties
	 * without email (org.modelibra.type.Email) and url (java.net.URL) =======
	 */

	/* ======= property selector methods for unique properties ======= */

	/*
	 * ======= reference property selector methods for a non many-to-many
	 * concept that has at least one external parent =======
	 */

	/* ======= order methods for essential properties ======= */

	/**
	 * Gets metaNeighbors ordered by code.
	 * 
	 * @param ascending
	 *            <code>true</code> if ascending
	 * @return ordered metaNeighbors
	 */
	public MetaNeighbors getMetaNeighborsOrderedByCode(boolean ascending) {
		return getMetaNeighbors("code", ascending);
	}

	/*
	 * ======= for a many-to-many concept: get entity method based on all
	 * many-to-many parents =======
	 */

	/**
	 * Gets metaNeighbor based on many-to-many parents.
	 * 
	 * @param MetaConcept
	 *            metaSourceConcept
	 * @param MetaConcept
	 *            metaDestinationConcept
	 */
	public MetaNeighbor getMetaNeighbor(MetaConcept metaSourceConcept,
			MetaConcept metaDestinationConcept) {
		for (MetaNeighbor metaNeighbor : this) {
			if (metaNeighbor.getMetaSourceConcept() == metaSourceConcept
					&& metaNeighbor.getMetaDestinationConcept() == metaDestinationConcept) {
				return metaNeighbor;
			}
		}
		return null;
	}

	/*
	 * ======= for each internal (part of) many-to-many child: get entities
	 * method based on the many-to-many external parent =======
	 */

	/* ======= internal parent set and get methods ======= */

	/**
	 * Sets metaSourceConcept.
	 * 
	 * @param metaSourceConcept
	 *            metaSourceConcept
	 */
	public void setMetaSourceConcept(MetaConcept metaSourceConcept) {
		this.metaSourceConcept = metaSourceConcept;
	}

	/**
	 * Gets metaSourceConcept.
	 * 
	 * @return metaSourceConcept
	 */
	public MetaConcept getMetaSourceConcept() {
		return metaSourceConcept;
	}

	/* ======= external parent set and get methods ======= */

	/**
	 * Sets metaDestinationConcept.
	 * 
	 * @param metaDestinationConcept
	 *            metaDestinationConcept
	 */
	public void setMetaDestinationConcept(MetaConcept metaDestinationConcept) {
		this.metaDestinationConcept = metaDestinationConcept;
	}

	/**
	 * Gets metaDestinationConcept.
	 * 
	 * @return metaDestinationConcept
	 */
	public MetaConcept getMetaDestinationConcept() {
		return metaDestinationConcept;
	}

	/* ======= for a many-to-many concept: post add propagation ======= */

	protected boolean postAdd(MetaNeighbor metaNeighbor) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postAdd(metaNeighbor)) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				MetaConcept metaSourceConcept = getMetaSourceConcept();
				if (metaSourceConcept == null) {
					MetaConcept metaNeighborMetaSourceConcept = metaNeighbor
							.getMetaSourceConcept();
					if (metaNeighborMetaSourceConcept != null) {
						if (!metaNeighborMetaSourceConcept
								.getMetaDestinationNeighbors().contain(
										metaNeighbor)) {
							metaNeighborMetaSourceConcept
									.getMetaDestinationNeighbors()
									.setPropagateToSource(false);
							post = metaNeighborMetaSourceConcept
									.getMetaDestinationNeighbors().add(
											metaNeighbor);
							metaNeighborMetaSourceConcept
									.getMetaDestinationNeighbors()
									.setPropagateToSource(true);
						}
					}
				}
				MetaConcept metaDestinationConcept = getMetaDestinationConcept();
				if (metaDestinationConcept == null) {
					MetaConcept metaNeighborMetaDestinationConcept = metaNeighbor
							.getMetaDestinationConcept();
					if (metaNeighborMetaDestinationConcept != null) {
						if (!metaNeighborMetaDestinationConcept
								.getMetaSourceNeighbors().contain(metaNeighbor)) {
							metaNeighborMetaDestinationConcept
									.getMetaSourceNeighbors()
									.setPropagateToSource(false);
							post = metaNeighborMetaDestinationConcept
									.getMetaSourceNeighbors().add(metaNeighbor);
							metaNeighborMetaDestinationConcept
									.getMetaSourceNeighbors()
									.setPropagateToSource(true);
						}
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post remove propagation ======= */

	protected boolean postRemove(MetaNeighbor metaNeighbor) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postRemove(metaNeighbor)) {
			MetaConcept metaSourceConcept = getMetaSourceConcept();
			if (metaSourceConcept == null) {
				MetaConcept metaNeighborMetaSourceConcept = metaNeighbor
						.getMetaSourceConcept();
				if (metaNeighborMetaSourceConcept.getMetaDestinationNeighbors()
						.contain(metaNeighbor)) {
					post = metaNeighborMetaSourceConcept
							.getMetaDestinationNeighbors().remove(metaNeighbor);
				}
			}
			MetaConcept metaDestinationConcept = getMetaDestinationConcept();
			if (metaDestinationConcept == null) {
				MetaConcept metaNeighborMetaDestinationConcept = metaNeighbor
						.getMetaDestinationConcept();
				if (metaNeighborMetaDestinationConcept.getMetaSourceNeighbors()
						.contain(metaNeighbor)) {
					post = metaNeighborMetaDestinationConcept
							.getMetaSourceNeighbors().remove(metaNeighbor);
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/* ======= for a many-to-many concept: post update propagation ======= */

	protected boolean postUpdate(MetaNeighbor beforeMetaNeighbor,
			MetaNeighbor afterMetaNeighbor) {
		if (!isPost()) {
			return true;
		}
		boolean post = true;
		if (super.postUpdate(beforeMetaNeighbor, afterMetaNeighbor)) {
			MetaConcept beforeMetaNeighborMetaSourceConcept = beforeMetaNeighbor
					.getMetaSourceConcept();
			MetaConcept afterMetaNeighborMetaSourceConcept = afterMetaNeighbor
					.getMetaSourceConcept();

			if (beforeMetaNeighborMetaSourceConcept != afterMetaNeighborMetaSourceConcept) {
				post = beforeMetaNeighborMetaSourceConcept
						.getMetaDestinationNeighbors().remove(
								beforeMetaNeighbor);
				if (post) {
					post = afterMetaNeighborMetaSourceConcept
							.getMetaDestinationNeighbors().add(
									afterMetaNeighbor);
					if (!post) {
						beforeMetaNeighborMetaSourceConcept
								.getMetaDestinationNeighbors().add(
										beforeMetaNeighbor);
					}
				}
			}
			MetaConcept beforeMetaNeighborMetaDestinationConcept = beforeMetaNeighbor
					.getMetaDestinationConcept();
			MetaConcept afterMetaNeighborMetaDestinationConcept = afterMetaNeighbor
					.getMetaDestinationConcept();

			if (beforeMetaNeighborMetaDestinationConcept != afterMetaNeighborMetaDestinationConcept) {
				post = beforeMetaNeighborMetaDestinationConcept
						.getMetaSourceNeighbors().remove(beforeMetaNeighbor);
				if (post) {
					post = afterMetaNeighborMetaDestinationConcept
							.getMetaSourceNeighbors().add(afterMetaNeighbor);
					if (!post) {
						beforeMetaNeighborMetaDestinationConcept
								.getMetaSourceNeighbors().add(
										beforeMetaNeighbor);
					}
				}
			}
		} else {
			post = false;
		}
		return post;
	}

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post add propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post remove propagation =======
	 */

	/*
	 * ======= for a non many-to-many concept that has at least one external
	 * parent: post update propagation =======
	 */

	/* ======= create entity method ======= */

	/**
	 * Creates metaNeighbor.
	 * 
	 * @param metaSourceConceptParent
	 *            metaSourceConcept parent
	 * @param metaDestinationConceptParent
	 *            metaDestinationConcept parent
	 * @param code
	 *            code
	 * @return metaNeighbor
	 */
	public MetaNeighbor createMetaNeighbor(MetaConcept metaSourceConceptParent,
			MetaConcept metaDestinationConceptParent, String code) {
		MetaNeighbor metaNeighbor = new MetaNeighbor(getModel());
		metaNeighbor.setMetaSourceConcept(metaSourceConceptParent);
		metaNeighbor.setMetaDestinationConcept(metaDestinationConceptParent);
		metaNeighbor.setCode(code);
		if (!add(metaNeighbor)) {
			metaNeighbor = null;
		}
		return metaNeighbor;
	}

}