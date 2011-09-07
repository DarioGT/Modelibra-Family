/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra.wicket.view;

import java.io.Serializable;
import java.util.Locale;

import org.apache.wicket.Page;

/**
 * A view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class View implements Serializable {

	private Locale locale = Locale.US;

	private View contextView;

	private boolean recreateContext = false;

	private String wicketId;

	private String title;

	private Page page;

	private Integer pageBlock = new Integer(0);

	private boolean update = false;

	private UserProperties userProperties = new UserProperties();

	/**
	 * Constructs a view.
	 */
	public View() {
		super();
	}

	/**
	 * Sets the locale.
	 * 
	 * @param locale
	 *            locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Gets the locale.
	 * 
	 * @return locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Sets the context view.
	 * 
	 * @param contextView
	 *            context view
	 */
	public void setContextView(View contextView) {
		this.contextView = contextView;
	}

	/**
	 * Gets the context view.
	 * 
	 * @return context view
	 */
	public View getContextView() {
		return contextView;
	}

	/**
	 * Sets if the context page should be recreated. If <code>true</code> the
	 * context page must have a default constructor (no arguments)
	 * 
	 * @param recreateContext
	 *            <code>true</code> if the context page will be recreated
	 */
	public void setRecreateContext(boolean recreateContext) {
		this.recreateContext = recreateContext;
	}

	/**
	 * Checks if the context page will be recreated.
	 * 
	 * @return <code>true</code> if the context page will be recreated
	 */
	public boolean isRecreateContext() {
		return recreateContext;
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
	 * Sets a view title.
	 * 
	 * @param title
	 *            title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets a view title.
	 * 
	 * @return view title
	 */
	public String getTitle() {
		return title;
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
	 * Sets if page data are updateable.
	 * 
	 * @param update
	 *            <code>true</code> if page data are updateable
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * Checks if page data are updateable.
	 * 
	 * @return <code>true</code> if page data are updateable
	 */
	public boolean isUpdate() {
		return update;
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
	 * Copies properties from another view to this view.
	 * 
	 * @param anotherView
	 *            another view
	 */
	public void copyPropertiesFrom(View anotherView) {
		setLocale(anotherView.getLocale());
		setContextView(anotherView.getContextView());
		setRecreateContext(anotherView.isRecreateContext());
		setWicketId(anotherView.getWicketId());
		setPage(anotherView.getPage());
		setPageBlock(anotherView.getPageBlock());
		setUpdate(anotherView.isUpdate());
		setTitle(anotherView.getTitle());
		setUserProperties(anotherView.getUserProperties());
	}

}