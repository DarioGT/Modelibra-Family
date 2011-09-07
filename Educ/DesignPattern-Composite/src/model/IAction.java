package model;

public interface IAction {

	public String getStatus();
	
	public boolean execute();
	
	public boolean undo();

}
