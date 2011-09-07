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

import java.awt.Point;

import modelibra.designer.metaconcept.MetaConcept;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/**
 * MetaNeighbor specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-31
 */
public class MetaNeighbor extends GenMetaNeighbor {

	private static final long serialVersionUID = 1211983552928L;

	private static Log log = LogFactory.getLog(MetaNeighbor.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaNeighbor within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaNeighbor(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaNeighbor within its parent(s).
	 * 
	 * @param MetaConcept
	 *            metaSourceConcept
	 * @param MetaConcept
	 *            metaDestinationConcept
	 */
	public MetaNeighbor(MetaConcept metaSourceConcept,
			MetaConcept metaDestinationConcept) {
		super(metaSourceConcept, metaDestinationConcept);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public MetaNeighbor getOppositeNeighbor() {
		MetaConcept sourceConcept = getMetaSourceConcept();
		MetaConcept destinationConcept = getMetaDestinationConcept();
		for (MetaNeighbor destinationNeighbor : destinationConcept
				.getMetaDestinationNeighbors()) {
			MetaConcept desatinationConcept2 = destinationNeighbor
					.getMetaDestinationConcept();
			if (desatinationConcept2 == sourceConcept) {
				return destinationNeighbor;
			}
		}
		return null;
	}

	public boolean canBeInternal() {
		if (isChild()) {
			MetaConcept destinationConcept = getMetaDestinationConcept();
			if (destinationConcept.hasNoInternalParent()) {
				return true;
			} else {
				return false;
			}
		} else {
			MetaConcept sourceConcept = getMetaSourceConcept();
			if (sourceConcept.hasOneInternalParent()) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean isParent() {
		return !isChild();
	}

	public boolean isChild() {
		if (getMax().equals("N")) {
			return true;
		} else if (getMax().equals("1")) {
			if (getMin().intValue() == 0) {
				return true;
			}
		}
		return false;
	}

	public boolean shouldCodeBeInPlural() {
		if (isChild()) {
			if (getMax().equals("1")) {
				return false;
			}
		} else if (isParent()) {
			return false;
		}
		return true;
	}

	public String getMinString() {
		return getMin().toString();
	}

	private Point getBeginPoint() {
		MetaConcept sourceConcept = getMetaSourceConcept();
		MetaConceptGraphic beginGraphic = sourceConcept.getMetaConceptGraphic();
		return beginGraphic.getCenter();
	}

	private Point getEndPoint() {
		MetaConcept destinationConcept = getMetaDestinationConcept();
		MetaConceptGraphic endGraphic = destinationConcept
				.getMetaConceptGraphic();
		return endGraphic.getCenter();
	}

	private Point getVisibleBeginPoint() {
		MetaConcept sourceConcept = getMetaSourceConcept();
		MetaConceptGraphic beginGraphic = sourceConcept.getMetaConceptGraphic();
		return beginGraphic
				.getIntersectionPoint(getBeginPoint(), getEndPoint());
	}

	private Point getVisibleEndPoint() {
		MetaConcept desctinationConcept = getMetaDestinationConcept();
		MetaConceptGraphic endGraphic = desctinationConcept
				.getMetaConceptGraphic();
		return endGraphic.getIntersectionPoint(getEndPoint(), getBeginPoint());
	}

	public Point getMinMaxPoint() {
		int x = 0;
		int y = 0;
		MetaConcept sourceConcept = getMetaSourceConcept();
		MetaConcept destinationConcept = getMetaDestinationConcept();
		if ((sourceConcept != null) && (destinationConcept != null)) {
			int x1 = getVisibleBeginPoint().x;
			int y1 = getVisibleBeginPoint().y;
			int x2 = getVisibleEndPoint().x;
			int y2 = getVisibleEndPoint().y;
			if (x1 <= x2) {
				x = x1 + 1 * ((x2 - x1) / 8);
				if (y1 <= y2) {
					y = y1 + 1 * ((y2 - y1) / 8);
				} else {
					y = y2 + 7 * ((y1 - y2) / 8);
				}
			} else {
				x = x2 + 7 * ((x1 - x2) / 8);
				if (y1 <= y2) {
					y = y1 + 1 * ((y2 - y1) / 8);
				} else {
					y = y2 + 7 * ((y1 - y2) / 8);
				}
			}
		}
		return new Point(x, y);
	}

	public Point getNamePoint() {
		int x = 0;
		int y = 0;
		MetaConcept sourceConcept = getMetaSourceConcept();
		MetaConcept destinationConcept = getMetaDestinationConcept();
		if ((sourceConcept != null) && (destinationConcept != null)) {
			int x1 = getVisibleBeginPoint().x;
			int y1 = getVisibleBeginPoint().y;
			int x2 = getVisibleEndPoint().x;
			int y2 = getVisibleEndPoint().y;
			if (x1 <= x2) {
				x = x1 + 3 * ((x2 - x1) / 8);
				if (y1 <= y2) {
					y = y1 + 3 * ((y2 - y1) / 8);
				} else {
					y = y2 + 5 * ((y1 - y2) / 8);
				}
			} else {
				x = x2 + 5 * ((x1 - x2) / 8);
				if (y1 <= y2) {
					y = y1 + 3 * ((y2 - y1) / 8);
				} else {
					y = y2 + 5 * ((y1 - y2) / 8);
				}
			}
		}
		return new Point(x, y);
	}

}