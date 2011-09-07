package org.modelibra.swing.domain.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.modelibra.config.ModelConfig;

@SuppressWarnings("serial")
public abstract class ModelTableModel extends AbstractTableModel {

	private List<ModelConfig> modelConfigList;

	public ModelTableModel(List<ModelConfig> modelConfigList) {
		this.modelConfigList = modelConfigList;
	}

	public List<ModelConfig> getModelConfigList() {
		return modelConfigList;
	}

	private ModelConfig getModelConfig(int number) {
		return modelConfigList.get(number);
	}

	// implemented from AbstractTableModel
	public int getRowCount() {
		return modelConfigList.size();
	}

	// implemented from AbstractTableModel
	public int getColumnCount() {
		return 1;
	}

	// implemented from AbstractTableModel
	public Object getValueAt(int r, int c) {
		if (c == 0) {
			return getModelConfig(r).getCode();
		}
		return null;
	}

	@Override
	public String getColumnName(int c) {
		if (c == 0) {
			return getText("Model.name");
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
