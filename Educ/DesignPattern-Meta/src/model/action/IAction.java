package model.action;

public interface IAction {

	public String getStatus();
	
	public boolean execute();
	
	public boolean undo();
	
	public boolean redo();

}
