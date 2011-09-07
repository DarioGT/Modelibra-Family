package modelibra.wicket.component.view;

import java.util.Locale;

import org.modelibra.Oid;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.wicket.view.UserProperties;

public class PropertyView {

	private Oid oid;

	private ConceptView conceptView;

	private String propertyName;

	private PropertyConfig propertyConfig;

	private Locale locale = Locale.US;

	private String header;

	private boolean shortDisplay = false;

	private boolean displayIfNull = true;

	private String wicketId;

	private UserProperties userProperties = new UserProperties();

	/**
	 * Constructs a property view.
	 * 
	 * @param conceptView
	 *            concept view
	 * @param propertyName
	 *            property name
	 */
	public PropertyView(final ConceptView conceptView, final String propertyName) {
		this.conceptView = conceptView;
		this.propertyName = propertyName;
		conceptView.addPropertyView(this);
		propertyConfig = conceptView.getConceptConfig().getPropertiesConfig()
				.getPropertyConfig(propertyName);
		if (propertyConfig == null) {
			String message = "PropertyView.constructor --- property configiration is null; check the property name: "
					+ propertyName;
			throw new ModelibraRuntimeException(message);
		}
		setOid(new Oid());
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
	 * Gets the concept view.
	 * 
	 * @return concept view
	 */
	public ConceptView getConceptView() {
		return conceptView;
	}

	/**
	 * Gets a property configuration.
	 * 
	 * @return property configuration
	 */
	public PropertyConfig getPropertyConfig() {
		return propertyConfig;
	}

	/**
	 * Gets a property name.
	 * 
	 * @return property name
	 */
	public String getPropertyName() {
		return propertyName;
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
			locale = getConceptView().getLocale();
		}
		return locale;
	}

	/**
	 * Sets a header.
	 * 
	 * @param header
	 *            header
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * Gets a header.
	 * 
	 * @return header
	 */
	public String getHeader() {
		if (header == null) {
			header = getPropertyName();
		}
		return header;
	}

	/**
	 * Sets if the short value will be displayed. If <code>true</code> the short
	 * value will be displayed.
	 * 
	 * @param shortDisplay
	 *            <code>true</code> if the short value will be displayed
	 */
	public void setShortDisplay(boolean shortDisplay) {
		this.shortDisplay = shortDisplay;
	}

	/**
	 * Checks if the short value will be displayed.
	 * 
	 * @return <code>true</code> if the short value will be displayed
	 */
	public boolean isShortDisplay() {
		return shortDisplay;
	}

	/**
	 * Sets if the value will be displayed if null. If <code>true</code> the
	 * value will be displayed if null.
	 * 
	 * @param displayIfNull
	 *            <code>true</code> if the value will be displayed if null
	 */
	public void setDisplayIfNull(boolean displayIfNull) {
		this.displayIfNull = displayIfNull;
	}

	/**
	 * Checks if the value will be displayed if null.
	 * 
	 * @return <code>true</code> if tvalue will be displayed if null
	 */
	public boolean isDisplayIfNull() {
		return displayIfNull;
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

}
