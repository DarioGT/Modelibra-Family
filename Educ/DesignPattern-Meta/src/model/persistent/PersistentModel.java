package model.persistent;

import model.IModel;

public abstract class PersistentModel implements IPersistent {
	
	private static final long serialVersionUID = 1;

	private IModel model;

	public void setModel(IModel model) {
		this.model = model;
	}

	public IModel getModel() {
		return model;
	}

}
