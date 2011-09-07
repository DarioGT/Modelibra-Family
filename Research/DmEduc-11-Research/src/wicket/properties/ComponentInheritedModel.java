package wicket.properties;

import org.apache.wicket.Component;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;

/**
 * Implementation of IComponentInheritedModel that wraps another model. When
 * this model is set to container, any child component that has no model will
 * inherit wrapped model. In that sense this is similar to how
 * CompoundPropertyModel provides property model to child components, but with
 * one subtle difference. CompoundPropertyModel provides different property
 * model based on component id. This model provides same model (wrapped one) to
 * every child component, regardless of component that requests wrapped model.
 * <p>
 * Common usage of this model could be when you provide MarkupContainer and want
 * clients to be able to add components that will inherit PropertyModel, but
 * they cannot use property codes (they are not known at compile time) for their
 * component id's. In that case you would wrap specific PropertyModel and set it
 * to container. Then every child component in that container that has no model
 * set would inherit wrapped PropertyModel:
 * 
 * <pre>
 * //container provider
 * IModel model = new ComponentInheritedModel(new PropertyModel(person, &quot;name&quot;));
 * WebMarkupContainer container = new WebMarkupContainer(&quot;container&quot;, model);
 * //client
 * container.add(new Label(&quot;value&quot;));
 * </pre>
 * 
 * This is essentially the same as:
 * 
 * <pre>
 * //container provider
 * WebMarkupContainer container = new WebMarkupContainer(&quot;container&quot;);
 * //client
 * container.add(new Label(&quot;value&quot;, new PropertyModel(person, &quot;name&quot;)));
 * </pre>
 * 
 * or:
 * 
 * <pre>
 * //container provider
 * CompoundPropertyModel model = new CompoundPropertyModel(person);
 * WebMarkupContainer container = new WebMarkupContainer(&quot;container&quot;);
 * //client
 * container.add(new Label(&quot;value&quot;, model.bind(&quot;name&quot;)));
 * </pre>
 * <p>
 * but less verbose for client (much like CompoundPropertyModel when property
 * code is used as component id), especially if clients that populate container
 * have no direct reference to model nor person object.
 * </p>
 * 
 * @author Vedad Kirlic
 * 
 */
public class ComponentInheritedModel implements IComponentInheritedModel,
		IWrapModel {

	private static final long serialVersionUID = 1L;

	/** Model provided to child components */
	private IModel model;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            model to be provided to child components
	 */
	public ComponentInheritedModel(IModel model) {
		this.model = model;
	}

	/**
	 * @see IComponentInheritedModel#wrapOnInheritance(Component)
	 */
	@Override
	public IWrapModel wrapOnInheritance(Component component) {
		return this;
	}

	/**
	 * @see IModel#getObject()
	 */
	@Override
	public Object getObject() {
		return model.getObject();
	}

	/**
	 * @see IModel#setObject(Object)
	 */
	@Override
	public void setObject(Object object) {
		model.setObject(object);
	}

	/**
	 * @see IDetachable#detach()
	 */
	@Override
	public void detach() {
		model.detach();
	}

	/**
	 * @see IWrapModel#getWrappedModel()
	 */
	@Override
	public IModel getWrappedModel() {
		return model;
	}

}