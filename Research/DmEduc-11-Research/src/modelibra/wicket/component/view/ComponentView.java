package modelibra.wicket.component.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.wicket.view.UserProperties;

public class ComponentView {

	private IDomainModel model;

	private Oid oid;

	private ComponentView contextComponentView;

	private RootConceptView rootConceptView;

	private boolean recreateContextPage = false;

	private Page page;

	private Integer pageBlock = new Integer(0);

	private Locale locale = Locale.US;

	private String title;

	private String wicketId;

	private List<LookupConceptView> lookupConceptViewList = new ArrayList<LookupConceptView>();

	private UserProperties userProperties = new UserProperties();

	/**
	 * Constructs a component view.
	 * 
	 * @param model
	 *            model
	 */
	public ComponentView(final IDomainModel model) {
		this.model = model;
		if (model == null) {
			String msg = "ComponentView.constructor -- model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		setOid(new Oid());
	}

	/**
	 * Gets the domain model.
	 * 
	 * @return domain model
	 */
	public IDomainModel getModel() {
		return model;
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
	 * Sets the context component view.
	 * 
	 * @param contextComponentView
	 *            context component view
	 */
	public void setContextComponentView(ComponentView contextComponentView) {
		this.contextComponentView = contextComponentView;
	}

	/**
	 * Gets the context component view.
	 * 
	 * @return context component view
	 */
	public ComponentView getContextComponentView() {
		return contextComponentView;
	}

	/**
	 * Sets the root concept view.
	 * 
	 * @param rootConceptView
	 *            root concept view
	 */
	void setRootConceptView(RootConceptView rootConceptView) {
		this.rootConceptView = rootConceptView;
	}

	/**
	 * Gets the root concept view.
	 * 
	 * @return root concept view
	 */
	public RootConceptView getRootConceptView() {
		return rootConceptView;
	}

	/**
	 * Sets if the context page should be recreated. If <code>true</code> the
	 * context page must have a default constructor (no arguments).
	 * 
	 * @param recreateContextPage
	 *            <code>true</code> if the context page will be recreated
	 */
	public void setRecreateContextPage(boolean recreateContextPage) {
		this.recreateContextPage = recreateContextPage;
	}

	/**
	 * Checks if the context page will be recreated.
	 * 
	 * @return <code>true</code> if the context page will be recreated
	 */
	public boolean isRecreateContextPage() {
		return recreateContextPage;
	}

	/**
	 * Sets a page.
	 * 
	 * @param page
	 *            page
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * Gets a page.
	 * 
	 * @return page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * Sets a page block. If a page has many data, only a block of data is seen
	 * at the time.
	 * 
	 * @param pageBlock
	 *            page block
	 */
	public void setPageBlock(Integer pageBlock) {
		this.pageBlock = pageBlock;
	}

	/**
	 * Gets a page block. If a page has many data, only a block of data is seen
	 * at the time.
	 * 
	 * @return page block
	 */
	public Integer getPageBlock() {
		return pageBlock;
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
		return locale;
	}

	/**
	 * Sets a title.
	 * 
	 * @param title
	 *            title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets a title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
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
	 * Adds a lookup concept view.
	 * 
	 * @param lookupConceptView
	 *            lookup concept view
	 */
	void addLookupConceptView(LookupConceptView lookupConceptView) {
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
