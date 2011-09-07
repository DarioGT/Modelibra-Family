package wicket.properties;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Base class for property views.
 * 
 * This repeater that makes it easy to display object properties in repeating
 * fashion (lists or table rows...), where child components reuse markup of this
 * container. Properties to be displayed are determined by implementing
 * {@link PropertiesViewBase#getDescriptors()} method. Descriptor is an object
 * of any type (type is determined by parameter T), that describes specific
 * property. For example it could be {@link Field} object, or simple
 * {@link String}, or some kind of property configuration object based on XML or
 * annotations etc.
 * 
 * <p>
 * For example, implementation of this class for Field as descriptor could be:
 * 
 * <pre>
 * abstract class FieldsView extends PropertiesViewBase&lt;Field&gt; {
 * 
 * 	protected final List&lt;Field&gt; getDescriptors() {
 * 		Class&lt;?&gt; claz = getModelObject().getClass();
 * 		return Arrays.asList(claz.getDeclaredFields());
 * 	}
 * 
 * 	protected final String descriptorToProperty(Field descriptor) {
 * 		return descriptor.getName();
 * 	}
 * }
 * </pre>
 * 
 * And usage of that class:
 * 
 * <pre>
 * add(new FieldsView(&quot;stringProperties&quot;, person) {
 * 
 * 	protected void populateItem(Item item) {
 * 		item.add(new Label(&quot;label&quot;, item.getProperty()));
 * 		item.add(new Label(&quot;value&quot;));
 * 	}
 * });
 * </pre>
 * 
 * </p>
 * Note that no model is provided for second Label. However that component
 * inherits model from Item container. Model to be shared between items child
 * components is determined by
 * {@link PropertiesViewBase#getItemSharedModel(String)}.
 * 
 * Properties can be sorted by comparator provided by
 * {@link PropertiesViewBase#comparator()} method, and filtered by
 * {@link PropertiesViewBase#show(T)} method.
 * 
 * @see RepeatingView
 * @see ComponentInheritedModel
 * 
 * @param <T>
 *            Descriptor type
 * 
 * @author Vedad Kirlic
 */
public abstract class PropertiesViewBase<T> extends RepeatingView {

	private static final long serialVersionUID = 1L;

	/**
	 * Item container for property related components. This container shares
	 * it's model with child components whose model is null.
	 * 
	 * @see ComponentInheritedModel
	 * 
	 * @author Vedad Kirlic
	 * 
	 */
	public static class Item extends WebMarkupContainer {

		private static final long serialVersionUID = 1L;

		/** Property code of this item */
		private String property;

		/**
		 * Constructor
		 * 
		 * @param id
		 *            component id
		 * @param model
		 *            model of this item
		 * @param property
		 *            String that represents property for which this item is
		 *            created
		 */
		public Item(String id, IModel model, String property) {
			super(id, model);
			this.property = property;
		}

		/**
		 * Gets String that represents property for which this item is created.
		 * 
		 * @return property code
		 */
		public final String getProperty() {
			return property;
		}

	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 * @param object
	 *            object whose properties you want to display
	 */
	public PropertiesViewBase(String id, Serializable object) {
		this(id, new Model(object));
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            component id
	 * @param model
	 *            model containing object whose properties you want to display
	 */
	public PropertiesViewBase(String id, IModel model) {
		super(id, model);
		for (String property : getProperties()) {
			IModel itemModel = itemModel(property);
			Item item = newItem(newChildId(), itemModel, property);
			add(item);
			populateItem(item);
		}
	}

	/**
	 * Creates new item for the property.
	 * 
	 * @param id
	 *            id for item container as defined by
	 *            {@link RepeatingView#newChildId()}
	 * @param model
	 *            model for item container as defined by
	 *            {@link PropertiesViewBase#itemModel(String)}
	 * @param property
	 *            property code
	 * @return Item
	 */
	protected Item newItem(String id, IModel model, String property) {
		return new Item(id, model, property);
	}

	/**
	 * Wraps model provided by
	 * {@link PropertiesViewBase#getItemSharedModel(String)} into
	 * {@link ComponentInheritedModel}. This model will be shared among child
	 * components of corresponding property Item. That is, all item's child
	 * components that have no model will inherit model returned by
	 * {@link PropertiesViewBase#getItemSharedModel(String)} method.
	 * 
	 * @param property
	 * @return
	 */
	protected final IModel itemModel(String property) {
		return new ComponentInheritedModel(getItemSharedModel(property));
	}

	/**
	 * Gets model to be shared between Item's child components without model.
	 * Default implementation provides PropertyModel whose model object is model
	 * of this AbstractPropertiesView, and property expression is property, that
	 * is:
	 * 
	 * <pre>
	 * PropertyModel(getModel(), property)
	 * </pre>
	 * 
	 * Override this method to provide different model. Be careful to provide
	 * model that represents the actual property
	 * <p>
	 * 
	 * @param property
	 *            property code
	 * @return model to share
	 */
	protected IModel getItemSharedModel(String property) {
		return new PropertyModel(getModel(), property);
	}

	/**
	 * Populate a given item.
	 * <p>
	 * <b>be careful</b> to add any components to the item. So, don't do:
	 * 
	 * <pre>
	 * add(new Label(&quot;foo&quot;, &quot;bar&quot;));
	 * </pre>
	 * 
	 * but:
	 * 
	 * <pre>
	 * item.add(new Label(&quot;foo&quot;, &quot;bar&quot;));
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param item
	 *            The item to populate
	 */
	protected abstract void populateItem(Item item);

	/**
	 * Gets string list of properties from descriptors, after they are sorted
	 * and filtered.
	 * 
	 * @return properties list
	 */
	private List<String> getProperties() {
		List<String> properties = new ArrayList<String>();
		List<T> descriptors = getDescriptors();
		sort(descriptors);
		for (T descriptor : descriptors) {
			if (show(descriptor)) {
				properties.add(descriptorToProperty(descriptor));
			}
		}
		return properties;
	}

	/**
	 * Sorts list of descriptors using comparator.
	 * 
	 * @param properties
	 */
	private void sort(List<T> properties) {
		Comparator<T> comparator = comparator();
		if (comparator != null) {
			Collections.sort(properties, comparator);
		}
	}

	/**
	 * Comparator used for sorting properties. By default no comparator is used.
	 * Override to provide some.
	 */
	protected Comparator<T> comparator() {
		return null;
	}

	/**
	 * Whether to show property represented by descriptor. Default is true for
	 * all properties.
	 * 
	 * @param descriptor
	 *            property descriptor
	 * @return whether to show property
	 */
	protected boolean show(T descriptor) {
		return true;
	}

	/**
	 * Implement to provide list of descriptors.
	 * 
	 * @return list of descriptors
	 */
	protected abstract List<T> getDescriptors();

	/**
	 * Implement to convert descriptor to string that represents property.
	 * 
	 * @param descriptor
	 *            property descriptor
	 * @return string representing actual property
	 */
	protected abstract String descriptorToProperty(T descriptor);

}