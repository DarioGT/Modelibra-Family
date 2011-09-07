package model;

public abstract class ModelTestTemplate {
	
	public final void prepareDataTemplate() {
		prepareModel();
		prepareReferences();
		prepareEntities();
	}
	
	protected abstract IModel prepareModel();
	
	protected void prepareReferences() {
		
	}
	
	protected abstract void prepareEntities();

}
