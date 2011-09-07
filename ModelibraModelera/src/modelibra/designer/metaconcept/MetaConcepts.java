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
package modelibra.designer.metaconcept;

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphic;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphics;
import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metaneighbor.MetaNeighbor;
import modelibra.designer.metaneighbor.MetaNeighbors;
import modelibra.swing.app.util.MathPlus;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.ModelSession;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;

/* ======= import external parent entity and entities classes ======= */

/**
 * MetaConcept specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-30
 */
public class MetaConcepts extends GenMetaConcepts {

	private static final long serialVersionUID = 1208025843879L;

	private static Log log = LogFactory.getLog(MetaConcepts.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConcepts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaConcepts(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs metaConcepts for the metaModel parent.
	 * 
	 * @param metaModel
	 *            metaModel
	 */
	public MetaConcepts(MetaModel metaModel) {
		super(metaModel);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public MetaConcept getMetaConcept(int number) {
		int count = 0;
		for (MetaConcept metaConcept : this) {
			if (number == count) {
				return metaConcept;
			}
			count++;
		}
		return null;
	}

	public void tile() {
		Designer designer = ModelibraData.getOne().getDesigner();
		ModelSession session = designer.getSession();
		Transaction transaction = new Transaction(session);

		int nbBoxesOnOneEdge = (int) Math.sqrt(size());
		int i = 0;
		for (MetaConcept metaConcept : this) {
			MetaConceptGraphics metaConceptGraphics = metaConcept
					.getMetaConceptGraphics();
			MetaConceptGraphic metaConceptGraphic = metaConceptGraphics.first();
			if (metaConceptGraphic != null) {
				MetaConceptGraphic metaConceptGraphicCopy = metaConceptGraphic
						.copy();
				metaConceptGraphicCopy.setX(MathPlus.mod(i, nbBoxesOnOneEdge)
						* (metaConceptGraphic.getWidth() + 80) + 40);
				metaConceptGraphicCopy.setY(((int) i / nbBoxesOnOneEdge)
						* (metaConceptGraphic.getHeight() + 80) + 40);
				new UpdateAction(transaction, metaConceptGraphics,
						metaConceptGraphic, metaConceptGraphicCopy);
				i++;
			}
		}

		transaction.execute();
	}

	protected boolean postUpdate(MetaConcept beforeMetaConcept,
			MetaConcept afterMetaConcept) {
		if (!isPost()) {
			return true;
		}
		if (super.postUpdate(beforeMetaConcept, afterMetaConcept)) {
			String beforeCode = beforeMetaConcept.getCode();
			String afterCode = afterMetaConcept.getCode();
			if (!afterCode.equals(beforeCode)) {
				Designer designer = ModelibraData.getOne().getDesigner();
				ModelSession session = designer.getSession();
				Transaction transaction = new Transaction(session);
				for (MetaNeighbor destinationNeighbor : afterMetaConcept
						.getMetaDestinationNeighbors()) {
					MetaNeighbor oppositeNeighbor = destinationNeighbor
							.getOppositeNeighbor();
					MetaNeighbor oppositeNeighborCopy = oppositeNeighbor.copy();
					MetaConcept destinationConcept = destinationNeighbor
							.getMetaDestinationConcept();
					MetaNeighbors destinationConceptDestinationNeighbors = destinationConcept
							.getMetaDestinationNeighbors();
					if (oppositeNeighbor.shouldCodeBeInPlural()) {
						oppositeNeighborCopy.setCode(afterMetaConcept
								.getCodeInPluralWithLowerFirstLetter());
					} else {
						oppositeNeighborCopy.setCode(afterMetaConcept
								.getCodeWithLowerFirstLetter());
					}
					new UpdateAction(transaction,
							destinationConceptDestinationNeighbors,
							oppositeNeighbor, oppositeNeighborCopy);
					transaction.execute();
				}
			}
		} else {
			return false;
		}
		return true;
	}

}