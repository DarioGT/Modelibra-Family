package first;

@SuppressWarnings("serial")
public abstract class EntityReferenceFacade<T extends IEntity<T>> extends
		Entity<T> {

	public String getReferenceName(String entityPropertyName,
			IReference reference) {
		String entityPropertyText = (String) getProperty(entityPropertyName);
		return reference.getName(entityPropertyText);
	}

}
