package second;

public interface IHistory {

	public void add(IAction action);

	public boolean undo();

}
