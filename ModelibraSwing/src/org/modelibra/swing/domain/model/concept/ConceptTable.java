package org.modelibra.swing.domain.model.concept;

import org.modelibra.config.ConceptConfig;
import org.modelibra.swing.widget.ModelibraTable;

@SuppressWarnings("serial")
public class ConceptTable extends ModelibraTable {

	private ConceptTableModel conceptTableModel;

	public ConceptTable(ConceptTableModel conceptTableModel) {
		super(conceptTableModel);
		this.conceptTableModel = conceptTableModel;
	}

	public ConceptConfig getCurrentConceptConfig() {
		int selectedRow = getSelectedRow();
		if (selectedRow >= 0) {
			return conceptTableModel.getConceptConfigList().get(
					selectedRow);
		}
		return null;
	}

}
