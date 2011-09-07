package model;

import java.util.List;

public interface IDomain {

	public boolean addModel(String name, IModel model);

	public IModel getModel(String name);

	public List<String> getModelNameList();

}
