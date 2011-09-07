package model.persistent;

import java.io.Serializable;

import model.IModel;

public interface IPersistent extends Serializable {
	
	public IModel load();
	
	public void save();

}
