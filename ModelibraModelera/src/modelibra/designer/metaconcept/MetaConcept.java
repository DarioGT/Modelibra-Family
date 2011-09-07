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

import modelibra.designer.metaconceptgraphic.MetaConceptGraphic;
import modelibra.designer.metaconceptgraphic.MetaConceptGraphics;
import modelibra.designer.metamodel.MetaModel;
import modelibra.designer.metaneighbor.MetaNeighbor;
import modelibra.designer.metaneighbor.MetaNeighbors;
import modelibra.swing.app.config.Para;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.util.TextHandler;

/* ======= import external parent entity and entities classes ======= */

/**
 * MetaConcept specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-06-11
 */
public class MetaConcept extends GenMetaConcept {

	private static final long serialVersionUID = 1208025843878L;

	private static Log log = LogFactory.getLog(MetaConcept.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs metaConcept within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public MetaConcept(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs metaConcept within its parent(s).
	 * 
	 * @param MetaModel
	 *            metaModel
	 */
	public MetaConcept(MetaModel metaModel) {
		super(metaModel);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	public MetaConceptGraphic getMetaConceptGraphic() {
		MetaConceptGraphics metaConceptGraphics = getMetaConceptGraphics();
		return metaConceptGraphics.first();
	}

	public String getCodeWithLowerFirstLetter() {
		TextHandler textHandler = new TextHandler();
		String code = getCode();
		if (!code.startsWith("?") && (!code.startsWith(""))) {
			return textHandler.firstLetterToLower(getCode());
		}
		return code;
	}

	public String getCodeInPluralWithLowerFirstLetter() {
		String name = getCodeWithLowerFirstLetter();
		if (name != null && !name.startsWith("?") && !name.trim().equals("")) {
			String language = Para.getOne().getNatLang().getLocale()
					.getLanguage();
			if (language.equals("en")) {
				TextHandler textHandler = new TextHandler();
				return textHandler.putInEnglishPlural(name);
			} else if (language.equals("fr")) {
				return name + "s";
			} else if (language.equals("ba")) {
				return name + "i";
			}
		}
		return name;
	}

	public boolean hasNoNeighbors() {
		int destinationNeighborsCount = getMetaDestinationNeighbors().size();
		if (destinationNeighborsCount == 0) {
			return true;
		}
		return false;
	}

	public boolean hasOnlyChidNeighbors() {
		MetaNeighbors metaNeighbors = getMetaDestinationNeighbors();
		for (MetaNeighbor metaNeighbor : metaNeighbors) {
			if (metaNeighbor.isParent()) {
				return false;
			}
		}
		return true;
	}

	public boolean isQuestionMark() {
		if (parentNeighborsCount() > 1 && internalParentNeighborsCount() > 1) {
			return true;
		}
		return false;
	}

	public boolean isV() {
		if (parentNeighborsCount() > 1 && hasOneInternalParent()) {
			return true;
		}
		return false;
	}

	public boolean hasNoInternalParent() {
		if (internalParentNeighborsCount() == 0) {
			return true;
		}
		return false;
	}

	public boolean hasOneInternalParent() {
		if (internalParentNeighborsCount() == 1) {
			return true;
		}
		return false;
	}

	public int parentNeighborsCount() {
		int count = 0;
		MetaNeighbors metaDestinationNeighbors = getMetaDestinationNeighbors();
		for (MetaNeighbor metaDestinationNeighbor : metaDestinationNeighbors) {
			if (metaDestinationNeighbor.isParent()) {
				count++;
			}
		}
		return count;
	}

	public int internalParentNeighborsCount() {
		int count = 0;
		MetaNeighbors metaDestinationNeighbors = getMetaDestinationNeighbors();
		for (MetaNeighbor metaDestinationNeighbor : metaDestinationNeighbors) {
			if (metaDestinationNeighbor.isParent()) {
				count++;
			}
		}
		return count;
	}

}