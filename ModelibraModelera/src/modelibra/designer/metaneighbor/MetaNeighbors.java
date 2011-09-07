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

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metaconcept.MetaConcepts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.UpdateAction;

/**
 * MetaNeighbor specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-30
 */
public class MetaNeighbors extends GenMetaNeighbors {

	private static final long serialVersionUID = 1211983552929L;

	private static Log log = LogFactory.getLog(MetaNeighbors.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaNeighbors within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaNeighbors(IDomainModel model) {
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
	public MetaNeighbors(MetaConcept metaSourceConcept,
			MetaConcept metaDestinationConcept) {
		super(metaSourceConcept, metaDestinationConcept);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public MetaNeighbor getMetaNeighbor(int number) {
		int count = 0;
		for (MetaNeighbor metaNeighbor : this) {
			if (number == count) {
				return metaNeighbor;
			}
			count++;
		}
		return null;
	}

	protected boolean postAdd(MetaNeighbor metaNeighbor) {
		if (!isPost()) {
			return true;
		}
		if (super.postAdd(metaNeighbor)) {
			Designer designer = ModelibraData.getOne().getDesigner();
			if (designer.isInitialized()) {
				if (!metaNeighbor.canBeInternal()) {
					metaNeighbor.setInternal(false);
				}

				if (metaNeighbor.isChild()) {
					MetaConcept destinationConcept = metaNeighbor
							.getMetaDestinationConcept();
					if (destinationConcept.isEntry()) {
						MetaConcept destinationConceptCopy = destinationConcept
								.copy();
						destinationConceptCopy.setEntry(false);
						MetaConcepts metaConcepts = destinationConcept
								.getMetaModel().getMetaConcepts();
						EntitiesAction action = new UpdateAction(designer
								.getSession(), metaConcepts,
								destinationConcept, destinationConceptCopy);
						action.execute();
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	protected boolean preUpdate(MetaNeighbor beforeMetaNeighbor,
			MetaNeighbor afterMetaNeighbor) {
		if (!isPre()) {
			return true;
		}
		if (super.preUpdate(beforeMetaNeighbor, afterMetaNeighbor)) {
			if (beforeMetaNeighbor.getMin().intValue() == 1
					&& afterMetaNeighbor.getMin().intValue() == 0) {
				if (afterMetaNeighbor.isId()) {
					afterMetaNeighbor.setMin(new Integer(1));
				}
			}
		} else {
			return false;
		}
		return true;
	}

	protected boolean postUpdate(MetaNeighbor beforeMetaNeighbor,
			MetaNeighbor afterMetaNeighbor) {
		if (!isPost()) {
			return true;
		}
		if (super.postUpdate(beforeMetaNeighbor, afterMetaNeighbor)) {
			if (!beforeMetaNeighbor.isId() && afterMetaNeighbor.isId()) {
				if (afterMetaNeighbor.getMin().intValue() == 0) {
					afterMetaNeighbor.setMin(new Integer(1));
				}
			}

			if (!afterMetaNeighbor.isId()) {
				if (beforeMetaNeighbor.getMin().intValue() == 1
						&& afterMetaNeighbor.getMin().intValue() == 0) {
					afterMetaNeighbor.setInternal(false);
				}
			}

			Designer designer = ModelibraData.getOne().getDesigner();
			MetaNeighbor oppositeNeighbor = afterMetaNeighbor
					.getOppositeNeighbor();
			MetaNeighbor oppositeNeighborCopy = oppositeNeighbor.copy();
			MetaConcept destinationConcept = afterMetaNeighbor
					.getMetaDestinationConcept();
			MetaNeighbors destinationConceptDestinationNeighbors = destinationConcept
					.getMetaDestinationNeighbors();
			if (!beforeMetaNeighbor.isInternal()
					&& afterMetaNeighbor.isInternal()) {
				oppositeNeighborCopy.setInternal(true);
				EntitiesAction action = new UpdateAction(designer.getSession(),
						destinationConceptDestinationNeighbors,
						oppositeNeighbor, oppositeNeighborCopy);
				action.execute();
			} else if (beforeMetaNeighbor.isInternal()
					&& !afterMetaNeighbor.isInternal()) {
				oppositeNeighborCopy.setInternal(false);
				EntitiesAction action = new UpdateAction(designer.getSession(),
						destinationConceptDestinationNeighbors,
						oppositeNeighbor, oppositeNeighborCopy);
				action.execute();
			}
		} else {
			return false;
		}
		return true;
	}

}