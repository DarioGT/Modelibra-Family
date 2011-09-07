package modelibra.wicket.properties;

import java.util.List;

import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.PropertyConfig;

import wicket.properties.PropertiesViewBase;

/**
 * Implementation of {@link PropertiesViewBase} with {@link PropertyConfig} as
 * descriptor.
 * 
 * @see PropertiesViewBase
 * @see RepeatingView
 * 
 * @author Vedad Kirlic
 * 
 */
public abstract class PropertiesView extends PropertiesViewBase<PropertyConfig> {

	private static final long serialVersionUID = 1L;

	/**
	 * Item container for entity property related components.
	 * 
	 * @see Item
	 * 
	 * @author Vedad Kirlic
	 * 
	 */
	public class PropertyItem extends Item {

		private static final long serialVersionUID = 1L;

		/** Property key for localization */
		private String propertyKey;

		/** @see Item#Item(String, IModel, String) */
		public PropertyItem(String id, IModel model, String propertyCode) {
			super(id, model, propertyCode);
			String conceptCode = getEntity().getConceptConfig().getCode();
			this.propertyKey = conceptCode + "." + propertyCode;
		}

		/**
		 * Gets property key used for localization.
		 * 
		 * @return property key
		 */
		public String getPropertyKey() {
			return propertyKey;
		}

		/**
		 * Gets property configuration of an entity property for which this item
		 * is created.
		 * 
		 * @return neighbor configuration
		 */
		public PropertyConfig getPropertyConfig() {
			String propertyCode = getProperty();
			return getEntity().getConceptConfig().getPropertyConfig(
					propertyCode);
		}

		/**
		 * Gets property class of an entity property for which this item is
		 * created
		 * 
		 * @return property class
		 */
		public Class<?> getPropertyClass() {
			return getPropertyConfig().getPropertyClassObject();
		}
	}

	/** @see PropertiesViewBase#PropertiesViewBase(String, IModel) */
	public PropertiesView(String id, IEntityModel model) {
		super(id, model);
	}

	/**
	 * Marked final to ensure that PropertyItem, or subclass, is used. Override
	 * {@link PropertiesView#newPropertyItem(String, IModel, String)} instead.
	 * 
	 * @see wicket.properties.PropertiesViewBase#newItem(String, IModel, String)
	 */
	@Override
	protected final PropertyItem newItem(String id, IModel model,
			String property) {
		return newPropertyItem(id, model, property);
	}

	/**
	 * Creates PropertyItem for property. Override to provide different item.
	 * 
	 * @param id
	 *            id for item
	 * @param model
	 *            model for item
	 * @param property
	 *            property code for item
	 * @return PropertyItem
	 */
	protected PropertyItem newPropertyItem(String id, IModel model,
			String property) {
		return new PropertyItem(id, model, property);
	}

	/**
	 * Marked final to provide PropertyItem for population
	 * 
	 * @see PropertiesViewBase#populateItem(Item)
	 */
	@Override
	protected final void populateItem(Item item) {
		populateItem((PropertyItem) item);
	}

	/**
	 * Populate a given item.
	 * 
	 * @param item
	 *            The item to populate
	 * 
	 * @see PropertiesViewBase#populateItem(Item)
	 */
	protected abstract void populateItem(PropertyItem item);

	/**
	 * Marked final to ensure filtering of reference properties and to provide
	 * better contextual name. Override
	 * {@link PropertiesView#showProperty(PropertyConfig)} instead.
	 * 
	 * @see PropertiesViewBase#show(T)
	 */
	@Override
	protected final boolean show(PropertyConfig descriptor) {
		return !descriptor.isReference() && showProperty(descriptor);
	}

	/**
	 * Whether to show property represented by PropertyConfig. Default is true
	 * for all non reference properties.
	 * 
	 * @param config
	 *            configuration of given property
	 * @return whether to show property
	 */
	protected boolean showProperty(PropertyConfig config) {
		return true;
	}

	/** 
	 * @see PropertiesViewBase#getDescriptors() 
	 */
	@Override
	protected final List<PropertyConfig> getDescriptors() {
		ConceptConfig config = getEntity().getConceptConfig();
		return config.getPropertiesConfig().getList();
	}

	/** 
	 * @see PropertiesViewBase#descriptorToProperty(T) 
	 */
	@Override
	protected final String descriptorToProperty(PropertyConfig descriptor) {
		return descriptor.getCode();
	}

	// TODO: will not be needed when we switch to generic wicket
	public final IEntity<?> getEntity() {
		return (IEntity<?>) getModelObject();
	}
}