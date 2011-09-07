package first;

@SuppressWarnings("serial")
public class EntityWithCode extends EntityDecorator {

	private String code;

	public EntityWithCode(Entity entity) {
		super(entity);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void output() {
		getEntity().output();
		System.out.println("code: " + getCode());
	}

}
