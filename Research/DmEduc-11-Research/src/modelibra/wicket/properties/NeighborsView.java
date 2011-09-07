package modelibra.wicket.properties;

import java.util.List;

import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;

import wicket.properties.PropertiesViewBase;

/**
 * Implementation of {@link PropertiesViewBase} with {@link NeighborConfig} as
 * descriptor.
 * 
 * @see PropertiesViewBase
 * @see RepeatingView
 * 
 * @author Vedad Kirlic
 * 
 */
public abstract class NeighborsView extends PropertiesViewBase<NeighborConfig> {

	private static final long serialVersionUID = 1L;

	/**
	 * Item container for entity neighbor related components.
	 * 
	 * @see Item
	 * 
	 * @author Vedad Kirlic
	 * 
	 */
	public class NeighborItem extends Item {

		private static final long serialVersionUID = 1L;

		/** Property key for localization */
		private String propertyKey;

		/** @see Item#Item(String, IModel, String) */
		public NeighborItem(String id, IModel model, String neighborCode) {
			super(id, model, neighborCode);
			String conceptCode = getEntity().getConceptConfig().getCode();
			this.propertyKey = conceptCode + "." + getProperty();
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
		 * Gets neighbor configuration of an entity neighbor property for which
		 * this item is created
		 * 
		 * @return neighbor configuration
		 */
		public NeighborConfig getNeighborConfig() {
			ConceptConfig conceptConfig = getEntity().getConceptConfig();
			return conceptConfig.getNeighborConfig(getProperty());
		}
	}

	/** @see PropertiesViewBase#PropertiesViewBase(String, IModel) */
	public NeighborsView(String id, IEntityModel model) {
		super(id, model);
	}

	/**
	 * Marked final to ensure that NeighborItem, or subclass, is used. Override
	 * {@link NeighborsView#newNeighborItem(String, IModel, String)} instead.
	 * 
	 * @see wicket.properties.PropertiesViewBase#newItem(String, IModel, String)
	 */
	@Override
	protected final Item newItem(String id, IModel model, String property) {
		return newNeighborItem(id, model, property);
	}

	/**
	 * Creates NeighborItem for property. Override to provide different item.
	 * 
	 * @param id
	 *            id for item
	 * @param model
	 *            model for item
	 * @param property
	 *            property code that represents specific neighbor
	 * @return NeighborItem
	 */
	protected NeighborItem newNeighborItem(String id, IModel model,
			String property) {
		return new NeighborItem(id, model, property);
	}

	/**
	 * Marked final to provide NeighborItem for population
	 * 
	 * @see PropertiesViewBase#populateItem(Item)
	 */
	@Override
	protected final void populateItem(Item item) {
		populateItem((NeighborItem) item);
	}

	/**
	 * Populate a given item.
	 * 
	 * @param item
	 *            The item to populate
	 * 
	 * @see PropertiesViewBase#populateItem(Item)
	 */
	protected abstract void populateItem(NeighborItem item);

	/**
	 * Marked final to provide better contextual name, and to be consistent with
	 * {@link PropertiesView#showProperty(org.modelibra.config.PropertyConfig)}.
	 * Override {@link NeighborsView#showNeighbor(NeighborConfig)} instead.
	 */
	@Override
	protected final boolean show(NeighborConfig descriptor) {
		return showNeighbor(descriptor);
	}

	/**
	 * Whether to show property represented by NeighborConfig. Default is true
	 * for all neighbors.
	 * 
	 * @param config
	 *            neighbor configuration
	 * @return whether to show property
	 */
	protected boolean showNeighbor(NeighborConfig config) {
		return true;
	}

	/** 
	 * @see PropertiesViewBase#getDescriptors() 
	 */
	protected final List<NeighborConfig> getDescriptors() {
		ConceptConfig conceptConfig = getEntity().getConceptConfig();
		return conceptConfig.getNeighborsConfig().getList();
	}

	/** 
	 * @see PropertiesViewBase#descriptorToProperty(T) 
	 */
	@Override
	protected final String descriptorToProperty(NeighborConfig decriptor) {
		return decriptor.getCode();
	}

	// TODO: will not be needed when we switch to generic wicket
	public final IEntity<?> getEntity() {
		return (IEntity<?>) getModelObject();
	}

}