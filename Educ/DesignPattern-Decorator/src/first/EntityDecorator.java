package first;

@SuppressWarnings("serial")
public abstract class EntityDecorator extends Entity {
	
	private Entity entity;
	
	public EntityDecorator(Entity entity) {
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public Long getOid() {
		return getEntity().getOid();
	}

	public abstract void output();

}
