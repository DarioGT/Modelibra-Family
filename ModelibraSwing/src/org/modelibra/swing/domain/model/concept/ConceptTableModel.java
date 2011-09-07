package org.modelibra.swing.domain.model.concept;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.modelibra.config.ConceptConfig;

@SuppressWarnings("serial")
public abstract class ConceptTableModel extends AbstractTableModel {

	private List<ConceptConfig> conceptConfigList;

	public ConceptTableModel(List<ConceptConfig> conceptConfigList) {
		this.conceptConfigList = conceptConfigList;
	}

	public List<ConceptConfig> getConceptConfigList() {
		return conceptConfigList;
	}

	private ConceptConfig getConceptConfig(int number) {
		return conceptConfigList.get(number);
	}

	// implemented from AbstractTableModel
	public int getRowCount() {
		return conceptConfigList.size();
	}

	// implemented from AbstractTableModel
	public int getColumnCount() {
		return 1;
	}

	// implemented from AbstractTableModel
	public Object getValueAt(int r, int c) {
		if (c == 0) {
			return getConceptConfig(r).getCode();
		}
		return null;
	}

	@Override
	public String getColumnName(int c) {
		if (c == 0) {
			return getText("Concept.name");
		}
		return "?";
	}

	protected abstract String getText(String key);

	@Override
	public boolean isCellEditable(int r, int c) {
		return false;
	}

	@Override
	public Class<?> getColumnClass(int c) {
		return String.class;
	}

}
