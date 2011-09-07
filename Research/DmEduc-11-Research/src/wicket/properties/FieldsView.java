package wicket.properties;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.model.IModel;

/**
 * Example implementation of ProperertiesViewBase with class Fields as
 * descriptors
 * 
 * @see PropertiesViewBase
 * 
 * @author Vedad Kirlic
 * 
 */
public abstract class FieldsView extends PropertiesViewBase<Field> {

	private static final long serialVersionUID = 1L;

	/**
	 * Item container for object field related components.
	 * 
	 * @see Item
	 * 
	 * @author Vedad Kirlic
	 * 
	 */
	public class FieldItem extends Item {

		private static final long serialVersionUID = 1L;

		/** @see Item#Item(String, IModel, String) */
		public FieldItem(String id, IModel model, String property) {
			super(id, model, property);
		}

		/**
		 * Gets Field object that represents object's field property for which
		 * this item is created.
		 * 
		 * @return Field
		 */
		public Field getField() {
			try {
				return FieldsView.this.getModelObject().getClass()
						.getDeclaredField(getProperty());
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchFieldException e) {
				throw new RuntimeException(
						"Field item should not be created for nonexisting field",
						e);
			}
		}

	}

	/** @see PropertiesViewBase#PropertiesViewBase(String, IModel) */
	public FieldsView(String id, IModel model) {
		super(id, model);
	}

	/** @see PropertiesViewBase#PropertiesViewBase(String, Serializable object) */
	public FieldsView(String id, Serializable object) {
		super(id, object);
	}

	/**
	 * Marked final to ensure that FieldItem, or subclass, is used. Override
	 * {@link FieldsView#newFieldItem(String, IModel, String)} instead.
	 * 
	 * @see wicket.properties.PropertiesViewBase#newItem(String, IModel, String)
	 */
	@Override
	protected final Item newItem(String id, IModel model, String property) {
		return newFieldItem(id, model, property);
	}

	/**
	 * Creates FieldItem for property. Override to provide different item.
	 * 
	 * @param id
	 *            id for item
	 * @param model
	 *            model for item
	 * @param property
	 *            property code that represents specific Field
	 * @return NeighborItem
	 */
	protected FieldItem newFieldItem(String id, IModel model, String property) {
		return new FieldItem(id, model, property);
	}

	/**
	 * Marked final to provide FieldItem for population
	 * 
	 * @see PropertiesViewBase#populateItem(Item)
	 */
	@Override
	protected final void populateItem(Item item) {
		populateItem((FieldItem) item);
	}

	/**
	 * Populate a given item.
	 * 
	 * @param item
	 *            The item to populate
	 * 
	 * @see PropertiesViewBase#populateItem(Item)
	 */
	protected abstract void populateItem(FieldItem item);

	/** 
	 * @see PropertiesViewBase#getDescriptors() 
	 */
	@Override
	protected List<Field> getDescriptors() {
		Class<?> claz = getModelObject().getClass();
		return Arrays.asList(claz.getDeclaredFields());
	}

	/** 
	 * @see PropertiesViewBase#descriptorToProperty(T) 
	 */
	@Override
	protected String descriptorToProperty(Field descriptor) {
		return descriptor.getName();
	}

}
