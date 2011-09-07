package modelibra.wicket.component.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.modelibra.IDomainModel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.Oid;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.wicket.view.UserProperties;

public abstract class ConceptView {

	private Oid oid;

	private ComponentView componentView;

	private ConceptView parentConceptView;

	private List<PropertyView> propertyViewList = new ArrayList<PropertyView>();

	private List<ChildConceptView> childConceptViewList = new ArrayList<ChildConceptView>();

	private List<LookupConceptView> lookupConceptViewList = new ArrayList<LookupConceptView>();

	private String neighborName;

	private String conceptName;

	private ConceptConfig conceptConfig;

	private IEntities<?> entities;

	private boolean displayOnly = true;

	private String action;

	private IEntity<?> entity;

	private IEntity<?> updateEntity;

	private Locale locale;

	private String subtitle;

	private boolean absorptionPermitted = false; // from parent neighbors

	private boolean lookupPermitted = false; // to parent neighbors

	private boolean lookupConcept = false;

	private boolean displayPropertyIfNull = true;

	private String wicketId;

	private UserProperties userProperties = new UserProperties();

	/**
	 * Constructs a concept view.
	 * 
	 * @param componentView
	 *            component view
	 * @param conceptName
	 *            concept name
	 */
	public ConceptView(final ComponentView componentView,
			final String conceptName) {
		this.componentView = componentView;
		this.conceptName = conceptName;
		conceptConfig = componentView.getModel().getModelConfig()
				.getConceptConfig(conceptName);
		if (conceptConfig == null) {
			String message = "ConceptView.constructor --- concept configiration is null; check the concept name: "
					+ conceptName;
			throw new ModelibraRuntimeException(message);
		}
		setOid(new Oid());
	}

	/**
	 * Gets the domain model.
	 * 
	 * @return domain model
	 */
	public IDomainModel getModel() {
		return componentView.getModel();
	}

	/**
	 * Sets the oid.
	 * 
	 * @param oid
	 *            oid
	 */
	private void setOid(Oid oid) {
		this.oid = oid;
	}

	/**
	 * Gets the oid.
	 * 
	 * @return oid
	 */
	public Oid getOid() {
		return oid;
	}

	/**
	 * Sets the component view.
	 * 
	 * @param componentView
	 *            component view
	 */
	protected void setComponentView(ComponentView componentView) {
		this.componentView = componentView;
	}

	/**
	 * Gets the component view.
	 * 
	 * @return component view
	 */
	public ComponentView getComponentView() {
		return componentView;
	}

	/**
	 * Sets the parent concept view.
	 * 
	 * @param parentConceptView
	 *            parent concept view
	 */
	protected void setParentConceptView(ConceptView parentConceptView) {
		this.parentConceptView = parentConceptView;
	}

	/**
	 * Gets the parent concept view.
	 * 
	 * @return parent concept view
	 */
	public ConceptView getParentConceptView() {
		return parentConceptView;
	}

	/**
	 * Adds a property view.
	 * 
	 * @param propertyView
	 *            property view
	 */
	void addPropertyView(PropertyView propertyView) {
		propertyViewList.add(propertyView);
	}

	/**
	 * Gets property views.
	 * 
	 * @return property views
	 */
	public List<PropertyView> getPropertyViews() {
		return propertyViewList;
	}

	/**
	 * Gets a property view.
	 * 
	 * @param propertyName
	 *            property name
	 * @return property view
	 */
	public PropertyView getPropertyView(String propertyName) {
		for (PropertyView propertyView : propertyViewList) {
			if (propertyView.getPropertyName().equals(propertyName)) {
				return propertyView;
			}
		}
		return null;
	}

	/**
	 * Adds a child concept view.
	 * 
	 * @param childConceptView
	 *            child concept view
	 */
	protected void addChildConceptView(ChildConceptView childConceptView) {
		childConceptViewList.add(childConceptView);
	}

	/**
	 * Gets child concept views.
	 * 
	 * @return child concept views
	 */
	public List<ChildConceptView> getChildConceptViews() {
		return childConceptViewList;
	}

	/**
	 * Gets a child concept view.
	 * 
	 * @param conceptName
	 *            concept name
	 * @return child concept view
	 */
	public ChildConceptView getChildConceptView(String conceptName) {
		for (ChildConceptView childConceptView : childConceptViewList) {
			if (childConceptView.getConceptName().equals(conceptName)) {
				return childConceptView;
			}
		}
		return null;
	}

	/**
	 * Gets a child concept view.
	 * 
	 * @param childNeighborName
	 *            child neighbor name
	 * @param conceptName
	 *            concept name
	 * @return child concept view
	 */
	public ChildConceptView getChildConceptView(String childNeighborName,
			String conceptName) {
		for (ChildConceptView childConceptView : childConceptViewList) {
			if (childConceptView.getNeighborName().equals(childNeighborName)
					&& childConceptView.getConceptName().equals(conceptName)) {
				return childConceptView;
			}
		}
		return null;
	}

	/**
	 * Adds a lookup concept view.
	 * 
	 * @param lookupConceptView
	 *            lookup concept view
	 */
	public void addLookupConceptView(LookupConceptView lookupConceptView) {
		lookupConceptViewList.add(lookupConceptView);
	}

	/**
	 * Gets lookup concept views.
	 * 
	 * @return lookup concept views
	 */
	public List<LookupConceptView> getLookupConceptViews() {
		return lookupConceptViewList;
	}

	/**
	 * Gets a lookup concept view.
	 * 
	 * @param conceptName
	 *            concept name
	 * @return lookup concept view
	 */
	public LookupConceptView getLookupConceptView(String conceptName) {
		for (LookupConceptView lookupConceptView : lookupConceptViewList) {
			if (lookupConceptView.getConceptName().equals(conceptName)) {
				return lookupConceptView;
			}
		}
		return null;
	}

	/**
	 * Gets a lookup concept view.
	 * 
	 * @param lookupNeighborName
	 *            lookup neighbor name
	 * @param conceptName
	 *            concept name
	 * @return lookup concept view
	 */
	public LookupConceptView getLookupConceptView(String lookupNeighborName,
			String conceptName) {
		for (LookupConceptView lookupConceptView : lookupConceptViewList) {
			if (lookupConceptView.getNeighborName().equals(lookupNeighborName)
					&& lookupConceptView.getConceptName().equals(conceptName)) {
				return lookupConceptView;
			}
		}
		return null;
	}

	/**
	 * Sets the neighbor name.
	 * 
	 * @param neighborName
	 *            neighbor name
	 */
	protected void setNeighborName(String neighborName) {
		this.neighborName = neighborName;
	}

	/**
	 * Gets the neighbor name.
	 * 
	 * @return neighbor name
	 */
	public String getNeighborName() {
		return neighborName;
	}

	/**
	 * Gets the concept name.
	 * 
	 * @return concept name
	 */
	public String getConceptName() {
		return conceptName;
	}

	/**
	 * Gets the concept configuration.
	 * 
	 * @return concept configuration
	 */
	public ConceptConfig getConceptConfig() {
		return conceptConfig;
	}

	/**
	 * Sets entities.
	 * 
	 * @param entities
	 *            entities
	 */
	public void setEntities(IEntities<?> entities) {
		this.entities = entities;
	}

	/**
	 * Gets entities.
	 * 
	 * @return entities
	 */
	public IEntities<?> getEntities() {
		return entities;
	}

	/**
	 * Sets if the concept is display only. If <code>true</code> the concept is
	 * display only.
	 * 
	 * @param displayOnly
	 *            <code>true</code> if the concept is display only
	 */
	public void setDisplayOnly(boolean displayOnly) {
		this.displayOnly = displayOnly;
	}

	/**
	 * Checks if the concept is display only.
	 * 
	 * @return <code>true</code> if the concept is display only
	 */
	public boolean isDisplayOnly() {
		return displayOnly;
	}

	/**
	 * Sets an action (add, remove or update).
	 * 
	 * @param action
	 *            action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets an action (add, remove or update).
	 * 
	 * @return action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void setEntity(IEntity<?> entity) {
		this.entity = entity;
	}

	/**
	 * Gets an entity.
	 * 
	 * @return entity
	 */
	public IEntity<?> getEntity() {
		return entity;
	}

	/**
	 * Sets an update entity.
	 * 
	 * @param updateEntity
	 *            update entity
	 */
	public void setUpdateEntity(IEntity<?> updateEntity) {
		this.updateEntity = updateEntity;
	}

	/**
	 * Gets an update entity.
	 * 
	 * @return update entity
	 */
	public IEntity<?> getUpdateEntity() {
		return updateEntity;
	}

	/**
	 * Sets a locale.
	 * 
	 * @param locale
	 *            locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Gets a locale.
	 * 
	 * @return locale
	 */
	public Locale getLocale() {
		if (locale == null) {
			if (componentView != null) {
				locale = componentView.getLocale();
			} else {
				locale = Locale.US;
			}
		}
		return locale;
	}

	/**
	 * Sets a subtitle.
	 * 
	 * @param subtitle
	 *            subtitle
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * Gets a subtitle.
	 * 
	 * @return subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * Sets if the absorption is permitted. If <code>true</code> the absorption
	 * is permitted.
	 * 
	 * @param lookupPermitted
	 *            <code>true</code> if the absorption is permitted
	 */
	public void setAbsorptionPermitted(boolean absorptionPermitted) {
		this.absorptionPermitted = absorptionPermitted;
	}

	/**
	 * Checks if the absorptionPermitted is permitted.
	 * 
	 * @return <code>true</code> if the absorptionPermitted is permitted
	 */
	public boolean isAbsorptionPermitted() {
		return absorptionPermitted;
	}

	/**
	 * Sets if the lookup is permitted. If <code>true</code> the lookup is
	 * permitted.
	 * 
	 * @param lookupPermitted
	 *            <code>true</code> if the lookup is permitted
	 */
	public void setLookupPermitted(boolean lookupPermitted) {
		this.lookupPermitted = lookupPermitted;
	}

	/**
	 * Checks if the lookup is permitted.
	 * 
	 * @return <code>true</code> if the lookup is permitted
	 */
	public boolean isLookupPermitted() {
		return lookupPermitted;
	}

	/**
	 * Sets if the concept is lookup. If <code>true</code> the concept is
	 * lookup.
	 * 
	 * @param lookupConcept
	 *            <code>true</code> if the concept is lookup
	 */
	protected void setLookupConcept(boolean lookupConcept) {
		this.lookupConcept = lookupConcept;
	}

	/**
	 * Checks if the concept is lookup.
	 * 
	 * @return <code>true</code> if the concept is lookup
	 */
	public boolean isLookupConcept() {
		return lookupConcept;
	}

	/**
	 * Checks if the concept is root.
	 * 
	 * @return <code>true</code> if the concept is root
	 */
	public boolean isRootConcept() {
		if (parentConceptView == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets a Wicket id.
	 * 
	 * @param wicketId
	 *            Wicket id
	 */
	public void setWicketId(String wicketId) {
		this.wicketId = wicketId;
	}

	/**
	 * Gets a Wicket id.
	 * 
	 * @return Wicket id
	 */
	public String getWicketId() {
		return wicketId;
	}

	/**
	 * Sets if a property will be displayed if null. If <code>true</code> a
	 * property will be displayed if null.
	 * 
	 * @param displayPropertyIfNull
	 *            <code>true</code> if a property will be displayed if null
	 */
	public void setDisplayPropertyIfNull(boolean displayPropertyIfNull) {
		this.displayPropertyIfNull = displayPropertyIfNull;
	}

	/**
	 * Checks if a property will be displayed if null
	 * 
	 * @return <code>true</code> if a property will be displayed if null
	 */
	public boolean isDisplayPropertyIfNull() {
		return displayPropertyIfNull;
	}

	/**
	 * Sets user defined properties.
	 * 
	 * @param userProperties
	 *            user properties
	 */
	public void setUserProperties(UserProperties userProperties) {
		this.userProperties = userProperties;
	}

	/**
	 * Gets user defined properties.
	 * 
	 * @return user properties
	 */
	public UserProperties getUserProperties() {
		return userProperties;
	}

	/**
	 * Gets entities code.
	 * 
	 * @return entities code
	 */
	public String getEntitiesCode() {
		String code = null;
		if (entities != null) {
			code = entities.getConceptConfig().getEntitiesCode();
		}
		return code;
	}

	/**
	 * Gets entity code.
	 * 
	 * @return entity code
	 */
	public String getEntityCode() {
		String code = null;
		if (entity != null) {
			code = entity.getConceptConfig().getCode();
		}
		return code;
	}

	/**
	 * Includes all properties.
	 */
	public void includeAllProperties() {
		PropertiesConfig propertiesConfig = getConceptConfig()
				.getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (!propertyConfig.isReference()) {
				PropertyView propertyView = new PropertyView(this,
						propertyConfig.getCode());
				if (!isDisplayPropertyIfNull()) {
					propertyView.setDisplayIfNull(false);
				}
			}
		}
	}

	/**
	 * Includes essential properties.
	 */
	public void includeEssentialProperties() {
		PropertiesConfig propertiesConfig = getConceptConfig()
				.getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (!propertyConfig.isReference() && propertyConfig.isEssential()) {
				PropertyView propertyView = new PropertyView(this,
						propertyConfig.getCode());
				if (!isDisplayPropertyIfNull()) {
					propertyView.setDisplayIfNull(false);
				}
			}
		}
	}

	/**
	 * Includes required properties.
	 */
	public void includeRequiredProperties() {
		PropertiesConfig propertiesConfig = getConceptConfig()
				.getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (!propertyConfig.isReference() && propertyConfig.isRequired()) {
				PropertyView propertyView = new PropertyView(this,
						propertyConfig.getCode());
				if (!isDisplayPropertyIfNull()) {
					propertyView.setDisplayIfNull(false);
				}
			}
		}
	}

	/**
	 * Includes lookup concept views.
	 */
	public void includeLookupConceptViews() {
		NeighborsConfig neighborsConfig = getConceptConfig()
				.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.isParent()) {
				String lookupConceptName = neighborConfig
						.getDestinationConcept();
				LookupConceptView lookupParentConceptView = getComponentView()
						.getLookupConceptView(lookupConceptName);
				if (lookupParentConceptView != null) {
					addLookupConceptView(lookupParentConceptView);
				}
			}
		}
	}
}
