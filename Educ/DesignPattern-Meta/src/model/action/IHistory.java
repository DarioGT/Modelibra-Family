package model.action;

public interface IHistory {

	public void add(IAction action);
	
	public boolean execute();
	
	public boolean executeAll();

	public boolean undo();
	
	public boolean undoAll();

}
