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

import java.lang.reflect.Constructor;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IDomain;
import org.modelibra.IEntities;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.util.Reflector;
import org.modelibra.wicket.container.DmPage;

/**
 * Meta handling of pages and their components.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-27
 */
@SuppressWarnings("serial")
public class ViewMeta {

	private static final String MODELIBRA_WICKET_HOME = "org.modelibra.wicket.app.home";

	private static final String MODELIBRA_WICKET_CONTAINER = "org.modelibra.wicket.container";

	private static final String MODELIBRA_WICKET_CONCEPT = "org.modelibra.wicket.concept";

	private static final String MODELIBRA_WICKET_NEIGHBOR = "org.modelibra.wicket.neighbor";

	private static final String MODELIBRA_WICKET_SECURITY = "org.modelibra.wicket.security";

	private static final String MODELIBRA_WICKET_SELECTION = "org.modelibra.wicket.concept.selection";

	private IDomain domain;

	private String domainWicketHomePackageCode;

	private String domainWicketSecurityPackageCode;

	private String domainWicketContainerPackageCode;

	private String wicketModelPackageCode;

	/**
	 * Constructs a view meta.
	 * 
	 * @param domain
	 *            domain
	 */
	public ViewMeta(IDomain domain) {
		super();
		this.domain = domain;
		DomainConfig domainConfig = domain.getDomainConfig();
		String domainPackageCode = domainConfig.getPackageCode();
		String domainWicketPackageCode = domainPackageCode + ".wicket";
		domainWicketHomePackageCode = domainWicketPackageCode + ".app.home";
		domainWicketSecurityPackageCode = domainWicketPackageCode + ".security";
		domainWicketContainerPackageCode = domainWicketPackageCode
				+ ".container";
	}

	/**
	 * Sets the model code.
	 * 
	 * @param modelCode
	 *            model code
	 */
	public void setModelCode(String modelCode) {
		ModelConfig modelConfig = domain.getDomainConfig().getModelConfig(
				modelCode);
		String domainPackageCode = modelConfig.getDomainConfig()
				.getPackageCode();
		String modelCodeInLowerLetters = modelConfig.getCodeInLowerLetters();
		wicketModelPackageCode = domainPackageCode + ".wicket."
				+ modelCodeInLowerLetters;
	}

	/**
	 * Gets a page class.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return page class
	 */
	public Class<?> getPageClass(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		IEntities<?> entities = viewModel.getEntities();
		String conceptPackageCode = entities.getConceptConfig()
				.getCodeInLowerLetters();
		return getConceptComponentClass(conceptPackageCode, classSimpleName);
	}

	/**
	 * Gets a web page.
	 * 
	 * @param webPage
	 *            web page
	 * @return web page
	 */
	public Page getPage(final Page webPage) {
		Class<? extends Page> pageClass = webPage.getClass();
		return getPage(pageClass);
	}

	/**
	 * Gets a web page.
	 * 
	 * @param pageClass
	 *            page class
	 * @return web page
	 */
	public Page getPage(final Class<?> pageClass) {
		Page result = null;
		Constructor<?> constructor = Reflector.getConstructor(pageClass);
		result = (WebPage) Reflector.getInstance(constructor);
		return result;
	}

	/**
	 * Gets a web page.
	 * 
	 * @param webPage
	 *            web page
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return web page
	 */
	public Page getPage(final Page webPage, final ViewModel viewModel,
			final View view) {
		return getPage(webPage.getClass(), viewModel, view);
	}

	/**
	 * Gets a web page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return web page
	 */
	public Page getPage(final ViewModel viewModel, final View view) {
		Page webPage = view.getPage();
		if (webPage != null) {
			return getPage(webPage.getClass(), viewModel, view);
		}
		return null;
	}

	/**
	 * Gets a web page.
	 * 
	 * @param pageClass
	 *            page class
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return web page
	 */
	public Page getPage(final Class<?> pageClass, final ViewModel viewModel,
			final View view) {
		Page result = null;
		Constructor<?> constructor = Reflector.getConstructor(pageClass,
				ViewModel.class, View.class);
		result = (WebPage) Reflector.getInstance(constructor, viewModel, view);
		return result;
	}

	/**
	 * Gets ViewModel.entities concept specific web page.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return web page
	 * 
	 * @see {@link ViewMeta#getConceptComponent(String, ViewModel, View)}
	 */
	public WebPage getPage(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		return (WebPage) getConceptComponent(classSimpleName, viewModel, view);
	}

	/**
	 * Gets ViewModel.lookupEntities concept specific list view.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return list view
	 * 
	 * @see {@link ViewMeta#getConceptComponentClass(String, ViewModel, View)}
	 */
	public WebPage getLookupPage(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		String conceptPackageCode = viewModel.getLookupEntities()
				.getConceptConfig().getCodeInLowerLetters();
		Class<?> claz = getConceptComponentClass(conceptPackageCode,
				classSimpleName);
		Constructor<?> constructor = Reflector.getConstructor(claz,
				ViewModel.class, View.class);
		return (WebPage) Reflector.getInstance(constructor, viewModel, view);
	}

	/**
	 * Gets concept specific panel.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return panel
	 * 
	 * @see {@link ViewMeta#getConceptComponent(String, ViewModel, View)}
	 */
	public Panel getPanel(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		return (Panel) getConceptComponent(classSimpleName, viewModel, view);
	}

	/**
	 * Gets concept specific list view.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return list view
	 * 
	 * @see {@link ViewMeta#getConceptComponent(String, ViewModel, View)}
	 */
	public ListView getListView(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		return (ListView) getConceptComponent(classSimpleName, viewModel, view);
	}

	/**
	 * Gets concept specific pageable list view.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return pageable list view
	 * 
	 * @see {@link ViewMeta#getConceptComponent(String, ViewModel, View)}
	 */
	public PageableListView getPageableListView(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		return (PageableListView) getConceptComponent(classSimpleName,
				viewModel, view);
	}

	/**
	 * Gets concept specific form.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return form
	 * 
	 * @see {@link ViewMeta#getConceptComponent(String, ViewModel, View)}
	 */
	public Form getForm(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		return (Form) getConceptComponent(classSimpleName, viewModel, view);
	}

	/**
	 * Gets ViewModel.entities concept specific component. Component is specific
	 * to ViewModel.entities concept.
	 * 
	 * @param classSimpleName
	 *            component class simple name
	 * @param viewModel
	 *            viewModel argument to be used for construction
	 * @param view
	 *            view argument to be used for construction
	 * @return component instance
	 * 
	 * @see {@link ViewMeta#getConceptComponentClass(String, ViewModel, View)}
	 */
	public Component getConceptComponent(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		IEntities<?> entities = viewModel.getEntities();
		String conceptPackageCode = entities.getConceptConfig()
				.getCodeInLowerLetters();
		Class<?> claz = getConceptComponentClass(conceptPackageCode,
				classSimpleName);
		Constructor<?> constructor = Reflector.getConstructor(claz,
				ViewModel.class, View.class);
		return (Component) Reflector.getInstance(constructor, viewModel, view);
	}

	/**
	 * Gets home(page) component.
	 * 
	 * @param classSimpleName
	 *            component class simple name
	 * @param viewModel
	 *            viewModel argument to be used for construction
	 * @param view
	 *            view argument to be used for construction
	 * @return component instance
	 * 
	 * @see {@link ViewMeta#getHomeComponentClass(String, ViewModel, View)}
	 */
	public Component getHomeComponent(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		Class<?> claz = getHomeComponentClass(classSimpleName);
		Constructor<?> constructor = Reflector.getConstructor(claz,
				ViewModel.class, View.class);
		return (Component) Reflector.getInstance(constructor, viewModel, view);
	}

	/**
	 * Gets container component.
	 * 
	 * @param classSimpleName
	 *            component class simple name
	 * @param viewModel
	 *            viewModel argument to be used for construction
	 * @param view
	 *            view argument to be used for construction
	 * @return component instance
	 * 
	 * @see {@link ViewMeta#getContainerComponentClass(String, ViewModel, View)}
	 */
	public Component getContainerComponent(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		Class<?> claz = getContainerComponentClass(classSimpleName);
		Constructor<?> constructor = Reflector.getConstructor(claz,
				ViewModel.class, View.class);
		return (Component) Reflector.getInstance(constructor, viewModel, view);
	}

	/**
	 * Gets security related component.
	 * 
	 * @param classSimpleName
	 *            component class simple name
	 * @param viewModel
	 *            viewModel argument to be used for construction
	 * @param view
	 *            view argument to be used for construction
	 * @return component instance
	 * 
	 * @see {@link ViewMeta#getSecurityComponentClass(String, ViewModel, View)}
	 */
	public Component getSecurityComponent(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		Class<?> claz = getSecurityComponentClass(classSimpleName);
		Constructor<?> constructor = Reflector.getConstructor(claz,
				ViewModel.class, View.class);
		return (Component) Reflector.getInstance(constructor, viewModel, view);
	}

	/**
	 * Gets Class of a concept specific component, based on concept package
	 * code, and component class simple name. Component class is looked up in
	 * following packages:
	 * 
	 * <ol>
	 * <li>{@literal <domainCode>.wicket.<modelCode>.<conceptPackageCode>}</li>
	 * <li>{@link ViewMeta#MODELIBRA_WICKET_CONCEPT}</li>
	 * <li>{@link ViewMeta#MODELIBRA_WICKET_NEIGHBOR}</li>
	 * <li>{@link ViewMeta#MODELIBRA_WICKET_SELECTION}</li>
	 * </ol>
	 * 
	 * @param conceptPackageCode
	 *            concept package code
	 * @param classSimpleName
	 *            component class simple name
	 * @return component Class
	 */
	public Class<?> getConceptComponentClass(String conceptPackageCode,
			String classSimpleName) {
		String className = wicketModelPackageCode + "." + conceptPackageCode
				+ "." + classSimpleName;
		Class<?> claz = Reflector.getClass(className);
		if (claz == null) {
			claz = Reflector.getClass(MODELIBRA_WICKET_CONCEPT + "."
					+ classSimpleName);
		}
		if (claz == null) {
			claz = Reflector.getClass(MODELIBRA_WICKET_NEIGHBOR + "."
					+ classSimpleName);
		}
		if (claz == null) {
			claz = Reflector.getClass(MODELIBRA_WICKET_SELECTION + "."
					+ classSimpleName);
		}
		return claz;
	}

	/**
	 * Gets Class of a home page component, based on component class simple
	 * name. Component class is looked up in following packages:
	 * 
	 * <ol>
	 * <li>{@literal <domaincode>.wicket.app.home}</li>
	 * <li>{@link ViewMeta#MODELIBRA_WICKET_HOME}</li>
	 * </ol>
	 * 
	 * @param classSimpleName
	 *            component class simple name
	 * @return component Class
	 */
	public Class<?> getHomeComponentClass(String classSimpleName) {
		String className = domainWicketHomePackageCode + "." + classSimpleName;
		Class<?> claz = Reflector.getClass(className);
		if (claz == null) {
			className = MODELIBRA_WICKET_HOME + "." + classSimpleName;
			claz = Reflector.getClass(className);
		}
		return claz;
	}

	/**
	 * Gets Class of a container component (not specific for any concept), based
	 * on component class simple name. Component class is looked up in following
	 * packages:
	 * 
	 * <ol>
	 * <li>{@literal <domainCode>.wicket.container}</li>
	 * <li>{@link ViewMeta#MODELIBRA_WICKET_CONTAINER}</li>
	 * </ol>
	 * 
	 * @param classSimpleName
	 *            component class simple name
	 * @return component Class
	 */
	public Class<?> getContainerComponentClass(String classSimpleName) {
		String className = domainWicketContainerPackageCode + "."
				+ classSimpleName;
		Class<?> claz = Reflector.getClass(className);
		if (claz == null) {
			className = MODELIBRA_WICKET_CONTAINER + "." + classSimpleName;
			claz = Reflector.getClass(className);
		}
		return claz;
	}

	/**
	 * Gets Class of a security component , based on component class simple
	 * name. Component class is looked up in following packages:
	 * 
	 * <ol>
	 * <li>{@literal <domainCode>.wicket.security}</li>
	 * <li>{@link ViewMeta#MODELIBRA_WICKET_SECURITY}</li>
	 * </ol>
	 * 
	 * @param classSimpleName
	 *            component class simple name
	 * @return component Class
	 */
	public Class<?> getSecurityComponentClass(String classSimpleName) {
		String className = domainWicketSecurityPackageCode + "."
				+ classSimpleName;
		Class<?> claz = Reflector.getClass(className);
		if (claz == null) {
			className = MODELIBRA_WICKET_SECURITY + "." + classSimpleName;
			claz = Reflector.getClass(className);
		}
		return claz;
	}

	/*
	 * ==================== Convenience View Methods ====================
	 */

	/**
	 * Gets the home page class.
	 * 
	 * @return home page class
	 */
	public Class<?> getHomePageClass() {
		return getHomePageClass("HomePage");
	}

	/**
	 * Gets the sign in page class.
	 * 
	 * @return sign in page class
	 */
	public Class<?> getSigninPageClass() {
		return getSigninPageClass("SigninPage");
	}

	/**
	 * Gets the home page.
	 * 
	 * @return home page
	 */
	public Page getHomePage() {
		Class<?> homePageClass = getHomePageClass();
		return (DmPage) Reflector.getInstance(homePageClass);
	}

	/**
	 * Gets the sign in page.
	 * 
	 * @return sign in page
	 */
	public Page getSigninPage() {
		Class<?> signinPageClass = getSigninPageClass();
		return (DmPage) Reflector.getInstance(signinPageClass);
	}

	/**
	 * Gets the home page menu panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @return home page menu panel
	 */
	public Panel getHomePageMenuPanel(final String wicketId) {
		return getHomePageMenuPanel("HomePageMenuPanel", wicketId);
	}

	/**
	 * Gets the dm menu panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return dmRad menu panel
	 */
	public Panel getDmMenuPanel(final ViewModel viewModel, final View view) {
		return getMenuPanel("DmMenuPanel", viewModel, view);
	}

	/**
	 * Gets the sign in panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return sign in panel
	 */
	public Panel getSigninPanel(final ViewModel viewModel, final View view) {
		return getSigninPanel("SigninPanel", viewModel, view);
	}

	/*
	 * ================= Used by Convenience View Methods =================
	 */

	/**
	 * Gets a home page class.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @return home page class
	 */
	public Class<?> getHomePageClass(final String classSimpleName) {
		return getHomeComponentClass(classSimpleName);
	}

	/**
	 * Gets a sign in page class.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @return sign in page class
	 */
	public Class<?> getSigninPageClass(final String classSimpleName) {
		return getSecurityComponentClass(classSimpleName);
	}

	/**
	 * Gets a home page menu panel.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param wicketId
	 *            Wicket id
	 * @return menu panel
	 */
	public Panel getHomePageMenuPanel(final String classSimpleName,
			final String wicketId) {
		Class<?> claz = getHomeComponentClass(classSimpleName);
		Constructor<?> constructor = Reflector.getConstructor(claz,
				String.class);
		return (Panel) Reflector.getInstance(constructor, wicketId);
	}

	/**
	 * Gets a menu panel.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return menu panel
	 */
	public Panel getMenuPanel(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		return (Panel) getContainerComponent(classSimpleName, viewModel, view);
	}

	/**
	 * Gets a sign in panel.
	 * 
	 * @param classSimpleName
	 *            class simple name
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @return sign in panel
	 */
	public Panel getSigninPanel(final String classSimpleName,
			final ViewModel viewModel, final View view) {
		Class<?> claz = getSecurityComponentClass(classSimpleName);
		Constructor<?> constructor = Reflector.getConstructor(claz,
				ViewModel.class, View.class);
		return (Panel) Reflector.getInstance(constructor, viewModel, view);
	}
}
