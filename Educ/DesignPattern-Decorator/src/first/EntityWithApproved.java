package first;

@SuppressWarnings("serial")
public class EntityWithApproved extends EntityDecorator {

	private Boolean approved;

	public EntityWithApproved(Entity entity) {
		super(entity);
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void output() {
		getEntity().output();
		System.out.println("approved: " + getApproved());
	}

}
