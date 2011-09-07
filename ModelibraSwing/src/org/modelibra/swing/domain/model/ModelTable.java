package org.modelibra.swing.domain.model;

import org.modelibra.config.ModelConfig;
import org.modelibra.swing.widget.ModelibraTable;

@SuppressWarnings("serial")
public class ModelTable extends ModelibraTable {

	private ModelTableModel modelTableModel;

	public ModelTable(ModelTableModel modelTableModel) {
		super(modelTableModel);
		this.modelTableModel = modelTableModel;
	}

	public ModelConfig getCurrentModelConfig() {
		int selectedRow = getSelectedRow();
		if (selectedRow >= 0) {
			return modelTableModel.getModelConfigList().get(selectedRow);
		}
		return null;
	}

}
