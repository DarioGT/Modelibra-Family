package model.action;

public abstract class Action implements IAction {

	private String status = "started";

	void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	public boolean isStarted() {
		if (getStatus().equals("started")) {
			return true;
		}
		return false;
	}

	public boolean isExecuted() {
		if (getStatus().equals("executed")) {
			return true;
		}
		return false;
	}
	
	public boolean isUndone() {
		if (getStatus().equals("undo")) {
			return true;
		}
		return false;
	}
	
	public boolean isError() {
		if (getStatus().equals("error")) {
			return true;
		}
		return false;
	}

}
