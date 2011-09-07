package second;

public abstract class Action implements IAction {

	private String status = "started";

	void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public boolean isExecuted() {
		if (getStatus().equals("executed")) {
			return true;
		}
		return false;
	}

}
