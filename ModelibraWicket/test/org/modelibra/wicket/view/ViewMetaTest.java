package org.modelibra.wicket.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.http.MockWebApplication;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.Domain;
import org.modelibra.Entities;
import org.modelibra.IDomain;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.ModelsConfig;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.concept.ConceptPage;

/**
 * ViewMeta tests. ViewMeta looks for components in different places, and have
 * methods to create instances of such components. If those methods are tested
 * with real components, ViewModel and View arguments should be created and
 * populated specifically for each component, which would clutter test methods
 * (we do not test real component instantiation, but the ViewMeta ability to get
 * the component from the right place). To avoid that, parallel hierarchy of
 * ModelibraWicekt packages (in which ViewMeta looks for components) is created
 * in test source folder:
 * <ul>
 * <li>org.modelibra.wicket.app.home</li>
 * <li>org.modelibra.wicket.concept</li>
 * <li>org.modelibra.wicket.concept.selection</li>
 * <li>org.modelibra.wicket.container</li>
 * <li>org.modelibra.wicket.neighbor</li>
 * <li>org.modelibra.wicket.security</li>
 * </ul>
 * 
 * Each package contains components that will ViewMeta look for (Page, Panel,
 * ListView etc.). For each such component there are two different classes
 * ComponentName MWComponentName
 * 
 * For ones without MW prefix there is component with same name in corresponding
 * domain package that should override (be returned by ViewMeta) original
 * component from ModelibraWicket packages. Those with MW prefix, does not have
 * corresponding component with same name in domain packages, thus they should
 * be returned by ViewMeta. Domain packages are also defined in test source
 * folder, and they reflect package hierarchy (where components to override ones
 * from ModelibraWicket should be placed) for ViewMeta underlying domain,
 * current model, and concept of entity in ViewModel :
 * 
 * 
 * <ul>
 * <li>domain code = Domain (see: {@link ViewMetaTest#beforeTests()},
 * {@link ViewMetaTest#initViewMeta(IDomain)})</li>
 * <li>model code = Model (see: {@link ViewMetaTest#beforeTests()},
 * {@link ViewMetaTest#initViewMeta(IDomain)})</li>
 * <li>concept code = Concept (see: {@link EntitiesStub})</li>
 * </ul>
 * 
 */
public class ViewMetaTest {

	private static ViewMeta viewMeta;

	private static ViewModel viewModel;

	@BeforeClass
	public static void beforeTests() {
		initViewMeta(getDomain("Domain", "Model"));
		initViewModel();
		mockWebApplication();
	}

	// getConceptComponentClass(String, String)

	@Test
	public void getConceptComponentClassNoClass() throws Exception {
		Class<?> componentClass = viewMeta.getConceptComponentClass("concept",
				"NoComponentClassWithThisName");
		assertNull(componentClass);
	}

	@Test
	public void getConceptComponentClassFromModelibraWicketConcept()
			throws Exception {
		Class<?> componentClass = viewMeta.getConceptComponentClass("concept",
				"MWConceptPanel");
		Class<?> expectedClass = org.modelibra.wicket.concept.MWConceptPanel.class;
		assertNotNull(componentClass);
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getConceptComponentClassFromModelibraWicketNeighbor()
			throws Exception {
		Class<?> componentClass = viewMeta.getConceptComponentClass("concept",
				"MWNeighborPanel");
		assertNotNull(componentClass);
		Class<?> expectedClass = org.modelibra.wicket.neighbor.MWNeighborPanel.class;
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getConceptComponentClassFromModelibraWicketSelection()
			throws Exception {
		Class<?> componentClass = viewMeta.getConceptComponentClass("concept",
				"MWConceptSelectionPanel");
		assertNotNull(componentClass);
		Class<?> expectedClass = org.modelibra.wicket.concept.selection.MWConceptSelectionPanel.class;
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getConceptComponentClassFromWicketModelConceptPackageOverridesMWConcept()
			throws Exception {
		Class<?> componentClass = viewMeta.getConceptComponentClass("concept",
				"ConceptPanel");
		assertNotNull(componentClass);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptPanel.class;
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getConceptComponentClassFromWicketModelConceptPackageOverridesMWNeighbor()
			throws Exception {
		Class<?> componentClass = viewMeta.getConceptComponentClass("concept",
				"NeighborPanel");
		assertNotNull(componentClass);
		Class<?> expectedClass = domain.wicket.model.concept.NeighborPanel.class;
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getConceptComponentClassFromWicketModelConceptPackageOverridesMWConceptSelection()
			throws Exception {
		Class<?> componentClass = viewMeta.getConceptComponentClass("concept",
				"ConceptSelectionPanel");
		assertNotNull(componentClass);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptSelectionPanel.class;
		assertEquals(expectedClass, componentClass);
	}

	// getHomeComponentClass(String)

	@Test
	public void getHomeComponentClassFromModelibraWicketConcept()
			throws Exception {
		Class<?> componentClass = viewMeta.getHomeComponentClass("MWAppHomePanel");
		Class<?> expectedClass = org.modelibra.wicket.app.home.MWAppHomePanel.class;
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getHomeComponentClassFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Class<?> componentClass = viewMeta.getHomeComponentClass("AppHomePanel");
		Class<?> expectedClass = domain.wicket.app.home.AppHomePanel.class;
		assertEquals(expectedClass, componentClass);
	}

	// getContainerComponentClass(String)

	@Test
	public void getContainerComponentClassFromModelibraWicketConcept() throws Exception {
		Class<?> componentClass = viewMeta.getContainerComponentClass("MWContainerPanel");
		Class<?> expectedClass = org.modelibra.wicket.container.MWContainerPanel.class;
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getContainerComponentClassFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Class<?> componentClass = viewMeta.getContainerComponentClass("ContainerPanel");
		Class<?> expectedClass = domain.wicket.container.ContainerPanel.class;
		assertEquals(expectedClass, componentClass);
	}
	
	// getSecurityComponentClass(String)

	@Test
	public void getSecurityComponentClassFromModelibraWicketConcept() throws Exception {
		Class<?> componentClass = viewMeta.getSecurityComponentClass("MWSecurityPanel");
		Class<?> expectedClass = org.modelibra.wicket.security.MWSecurityPanel.class;
		assertEquals(expectedClass, componentClass);
	}

	@Test
	public void getSecurityComponentClassFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Class<?> componentClass = viewMeta.getSecurityComponentClass("SecurityPanel");
		Class<?> expectedClass = domain.wicket.security.SecurityPanel.class;
		assertEquals(expectedClass, componentClass);
	}
	
	// getConceptComponent(String, String)	

	@Test
	public void getConceptComponentFromModelibraWicketConcept()
			throws Exception {
		Component component = viewMeta.getConceptComponent("MWConceptPanel", viewModel, null);
		Class<?> expectedClass = org.modelibra.wicket.concept.MWConceptPanel.class;
		assertNotNull(component);
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getConceptComponentFromModelibraWicketNeighbor()
			throws Exception {
		Component component = viewMeta.getConceptComponent("MWNeighborPanel", viewModel, null);
		assertNotNull(component);
		Class<?> expectedClass = org.modelibra.wicket.neighbor.MWNeighborPanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getConceptComponentFromModelibraWicketSelection()
			throws Exception {
		Component component = viewMeta.getConceptComponent("MWConceptSelectionPanel", viewModel, null);
		assertNotNull(component);
		Class<?> expectedClass = org.modelibra.wicket.concept.selection.MWConceptSelectionPanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getConceptComponentFromWicketModelConceptPackageOverridesMWConcept()
			throws Exception {
		Component component = viewMeta.getConceptComponent("ConceptPanel", viewModel, null);
		assertNotNull(component);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptPanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getConceptComponentFromWicketModelConceptPackageOverridesMWNeighbor()
			throws Exception {
		Component component = viewMeta.getConceptComponent("NeighborPanel", viewModel, null);
		assertNotNull(component);
		Class<?> expectedClass = domain.wicket.model.concept.NeighborPanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getConceptComponentFromWicketModelConceptPackageOverridesMWConceptSelection()
			throws Exception {
		Component component = viewMeta.getConceptComponent("ConceptSelectionPanel", viewModel, null);
		assertNotNull(component);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptSelectionPanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	// getHomeComponent(String)

	@Test
	public void getHomeComponentFromModelibraWicketConcept()
			throws Exception {
		Component component = viewMeta.getHomeComponent("MWAppHomePanel", viewModel, null);
		Class<?> expectedClass = org.modelibra.wicket.app.home.MWAppHomePanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getHomeComponentFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Component component = viewMeta.getHomeComponent("AppHomePanel", viewModel, null);
		Class<?> expectedClass = domain.wicket.app.home.AppHomePanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	// getContainerComponent(String)

	@Test
	public void getContainerComponentFromModelibraWicketConcept() throws Exception {
		Component component = viewMeta.getContainerComponent("MWContainerPanel", viewModel, null);	
		Class<?> expectedClass = org.modelibra.wicket.container.MWContainerPanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getContainerComponentFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Component component = viewMeta.getContainerComponent("ContainerPanel", viewModel, null);	
		Class<?> expectedClass = domain.wicket.container.ContainerPanel.class;
		assertEquals(expectedClass, component.getClass());
	}
	
	// getSecurityComponent(String)

	@Test
	public void getSecurityComponentFromModelibraWicketConcept() throws Exception {
		Component component = viewMeta.getSecurityComponent("MWSecurityPanel", viewModel, null);
		Class<?> expectedClass = org.modelibra.wicket.security.MWSecurityPanel.class;
		assertEquals(expectedClass, component.getClass());
	}

	@Test
	public void getSecurityComponentFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Component component= viewMeta.getSecurityComponent("SecurityPanel", viewModel, null);
		Class<?> expectedClass = domain.wicket.security.SecurityPanel.class;
		assertEquals(expectedClass, component.getClass());
	}
	
	// getPageClass(String, ViewModel, View)

	@Test
	public void getPageClassFromModelibraWicketConcept() throws Exception {
		Class<?> pageClass = viewMeta.getPageClass("MWConceptPage", viewModel,
				null);
		assertNotNull(pageClass);
		Class<?> expectedClass = org.modelibra.wicket.concept.MWConceptPage.class;
		assertEquals(expectedClass, pageClass);
	}

	@Test
	public void getPageClassFromModelibraWicketNeighbor() throws Exception {
		Class<?> pageClass = viewMeta.getPageClass("MWNeighborPage", viewModel,
				null);
		assertNotNull(pageClass);
		Class<?> expectedClass = org.modelibra.wicket.neighbor.MWNeighborPage.class;
		assertEquals(expectedClass, pageClass);
	}

	@Test
	public void getPageClassFromModelibraWicketSelection() throws Exception {
		Class<?> pageClass = viewMeta.getPageClass("MWConceptSelectionPage",
				viewModel, null);
		assertNotNull(pageClass);
		Class<?> expectedClass = org.modelibra.wicket.concept.selection.MWConceptSelectionPage.class;
		assertEquals(expectedClass, pageClass);
	}

	@Test
	public void getPageClassFromWicketModelConceptPackageOverridesMWConcept()
			throws Exception {
		Class<?> pageClass = viewMeta.getPageClass("ConceptPage", viewModel,
				null);
		assertNotNull(pageClass);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptPage.class;
		assertEquals(expectedClass, pageClass);
	}

	@Test
	public void getPageClassFromWicketModelConceptPackageOverridesMWNeighbor()
			throws Exception {
		Class<?> pageClass = viewMeta.getPageClass("NeighborPage", viewModel,
				null);
		assertNotNull(pageClass);
		Class<?> expectedClass = domain.wicket.model.concept.NeighborPage.class;
		assertEquals(expectedClass, pageClass);
	}

	@Test
	public void getPageClassFromWicketModelConceptPackageOverridesMWConceptSelection()
			throws Exception {
		Class<?> pageClass = viewMeta.getPageClass("ConceptSelectionPage",
				viewModel, null);
		assertNotNull(pageClass);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptSelectionPage.class;
		assertEquals(expectedClass, pageClass);
	}

	// getPage(Class,ViewModel,View)

	@Test
	public void getPageClassBased() throws Exception {
		Page page = viewMeta.getPage(ConceptPage.class, null, null);
		assertNotNull(page);
	}

	// getPage(String, ViewModel, View)

	@Test(expected = ModelibraRuntimeException.class)
	public void getPageNoConstructor() throws Exception {
		viewMeta.getPage("NoConstructorPage", viewModel, null);
	}

	@Test
	public void getPageFromModelibraWicketConcept() throws Exception {
		WebPage page = viewMeta.getPage("MWConceptPage", viewModel, null);
		assertNotNull(page);
		Class<?> expectedClass = org.modelibra.wicket.concept.MWConceptPage.class;
		assertEquals(expectedClass, page.getClass());
	}

	@Test
	public void getPageFromModelibraWicketNeighbor() throws Exception {
		WebPage page = viewMeta.getPage("MWNeighborPage", viewModel, null);
		assertNotNull(page);
		Class<?> expectedClass = org.modelibra.wicket.neighbor.MWNeighborPage.class;
		assertEquals(expectedClass, page.getClass());
	}

	@Test
	public void getPageFromModelibraWicketSelection() throws Exception {
		WebPage page = viewMeta.getPage("MWConceptSelectionPage", viewModel,
				null);
		assertNotNull(page);
		Class<?> expectedClass = org.modelibra.wicket.concept.selection.MWConceptSelectionPage.class;
		assertEquals(expectedClass, page.getClass());
		;
	}

	@Test
	public void getPageFromWicketModelConceptPackageOverridesMWConcept()
			throws Exception {
		WebPage page = viewMeta.getPage("ConceptPage", viewModel, null);
		assertNotNull(page);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptPage.class;
		assertEquals(expectedClass, page.getClass());
		;
	}

	@Test
	public void getPageFromWicketModelConceptPackageOverridesMWNeighbor()
			throws Exception {
		WebPage page = viewMeta.getPage("NeighborPage", viewModel, null);
		assertNotNull(page);
		Class<?> expectedClass = domain.wicket.model.concept.NeighborPage.class;
		assertEquals(expectedClass, page.getClass());
		;
	}

	@Test
	public void getPageFromWicketModelConceptPackageOverridesMWConceptSelection()
			throws Exception {
		WebPage page = viewMeta
				.getPage("ConceptSelectionPage", viewModel, null);
		assertNotNull(page);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptSelectionPage.class;
		assertEquals(expectedClass, page.getClass());
		;
	}

	// getPanel(String, ViewModel, View)

	@Test(expected = ModelibraRuntimeException.class)
	public void getPanelNoConstructor() throws Exception {
		viewMeta.getPanel("NoConstructorPanel", viewModel, null);
	}

	@Test
	public void getPanelFromModelibraWicketConcept() throws Exception {
		Panel panel = viewMeta.getPanel("MWConceptPanel", viewModel, null);
		assertNotNull(panel);
		Class<?> expectedClass = org.modelibra.wicket.concept.MWConceptPanel.class;
		assertEquals(expectedClass, panel.getClass());
		;
	}

	@Test
	public void getPanelFromModelibraWicketNeighbor() throws Exception {
		Panel panel = viewMeta.getPanel("MWNeighborPanel", viewModel, null);
		assertNotNull(panel);
		Class<?> expectedClass = org.modelibra.wicket.neighbor.MWNeighborPanel.class;
		assertEquals(expectedClass, panel.getClass());
		;
	}

	@Test
	public void getPanelFromModelibraWicketSelection() throws Exception {
		Panel panel = viewMeta.getPanel("MWConceptSelectionPanel", viewModel,
				null);
		assertNotNull(panel);
		Class<?> expectedClass = org.modelibra.wicket.concept.selection.MWConceptSelectionPanel.class;
		assertEquals(expectedClass, panel.getClass());
		;
	}

	@Test
	public void getPanelFromWicketModelConceptPackageOverridesMWConcept()
			throws Exception {
		Panel panel = viewMeta.getPanel("ConceptPanel", viewModel, null);
		assertNotNull(panel);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptPanel.class;
		assertEquals(expectedClass, panel.getClass());
		;
	}

	@Test
	public void getPanelFromWicketModelConceptPackageOverridesMWNeighbor()
			throws Exception {
		Panel panel = viewMeta.getPanel("NeighborPanel", viewModel, null);
		assertNotNull(panel);
		Class<?> expectedClass = domain.wicket.model.concept.NeighborPanel.class;
		assertEquals(expectedClass, panel.getClass());
		;
	}

	@Test
	public void getPanelFromWicketModelConceptPackageOverridesMWConceptSelection()
			throws Exception {
		Panel panel = viewMeta.getPanel("ConceptSelectionPanel", viewModel,
				null);
		assertNotNull(panel);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptSelectionPanel.class;
		assertEquals(expectedClass, panel.getClass());
		;
	}

	// getListView(String, ViewModel, View)

	@Test(expected = ModelibraRuntimeException.class)
	public void getListViewNoConstructor() throws Exception {
		viewMeta.getListView("NoConstructorListView", viewModel, null);
	}

	@Test
	public void getListViewFromModelibraWicketConcept() throws Exception {
		ListView listView = viewMeta.getListView("MWConceptListView",
				viewModel, null);
		assertNotNull(listView);
		Class<?> expectedClass = org.modelibra.wicket.concept.MWConceptListView.class;
		assertEquals(expectedClass, listView.getClass());
		;
	}

	@Test
	public void getListViewFromModelibraWicketNeighbor() throws Exception {
		ListView listView = viewMeta.getListView("MWNeighborListView",
				viewModel, null);
		assertNotNull(listView);
		Class<?> expectedClass = org.modelibra.wicket.neighbor.MWNeighborListView.class;
		assertEquals(expectedClass, listView.getClass());
		;
	}

	@Test
	public void getListViewFromModelibraWicketSelection() throws Exception {
		ListView listView = viewMeta.getListView("MWConceptSelectionListView",
				viewModel, null);
		assertNotNull(listView);
		Class<?> expectedClass = org.modelibra.wicket.concept.selection.MWConceptSelectionListView.class;
		assertEquals(expectedClass, listView.getClass());
		;
	}

	@Test
	public void getListViewFromWicketModelConceptPackageOverridesMWConcept()
			throws Exception {
		ListView listView = viewMeta.getListView("ConceptListView", viewModel,
				null);
		assertNotNull(listView);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptListView.class;
		assertEquals(expectedClass, listView.getClass());
		;
	}

	@Test
	public void getListViewFromWicketModelConceptPackageOverridesMWNeighbor()
			throws Exception {
		ListView listView = viewMeta.getListView("NeighborListView", viewModel,
				null);
		assertNotNull(listView);
		Class<?> expectedClass = domain.wicket.model.concept.NeighborListView.class;
		assertEquals(expectedClass, listView.getClass());
		;
	}

	@Test
	public void getListViewFromWicketModelConceptPackageOverridesMWConceptSelection()
			throws Exception {
		ListView listView = viewMeta.getListView("ConceptSelectionListView",
				viewModel, null);
		assertNotNull(listView);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptSelectionListView.class;
		assertEquals(expectedClass, listView.getClass());
		;
	}

	// getPagableListView - just delegates to getListView and casts to
	// PagableListView before returning.

	// getForm(String, ViewModel, View)

	@Test(expected = ModelibraRuntimeException.class)
	public void getFormNoConstructor() throws Exception {
		viewMeta.getForm("NoConstructorForm", viewModel, null);
	}

	@Test
	public void getFormFromModelibraWicketConcept() throws Exception {
		Form form = viewMeta.getForm("MWConceptForm", viewModel, null);
		assertNotNull(form);
		Class<?> expectedClass = org.modelibra.wicket.concept.MWConceptForm.class;
		assertEquals(expectedClass, form.getClass());
		;
	}

	@Test
	public void getFormFromModelibraWicketNeighbor() throws Exception {
		Form form = viewMeta.getForm("MWNeighborForm", viewModel, null);
		assertNotNull(form);
		Class<?> expectedClass = org.modelibra.wicket.neighbor.MWNeighborForm.class;
		assertEquals(expectedClass, form.getClass());
		;
	}

	@Test
	public void getFormFromModelibraWicketSelection() throws Exception {
		Form form = viewMeta.getForm("MWConceptSelectionForm", viewModel, null);
		assertNotNull(form);
		Class<?> expectedClass = org.modelibra.wicket.concept.selection.MWConceptSelectionForm.class;
		assertEquals(expectedClass, form.getClass());
		;
	}

	@Test
	public void getFormFromWicketModelConceptPackageOverridesMWConcept()
			throws Exception {
		Form form = viewMeta.getForm("ConceptForm", viewModel, null);
		assertNotNull(form);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptForm.class;
		assertEquals(expectedClass, form.getClass());
		;
	}

	@Test
	public void getFormFromWicketModelConceptPackageOverridesMWNeighbor()
			throws Exception {
		Form form = viewMeta.getForm("NeighborForm", viewModel, null);
		assertNotNull(form);
		Class<?> expectedClass = domain.wicket.model.concept.NeighborForm.class;
		assertEquals(expectedClass, form.getClass());
		;
	}

	@Test
	public void getFormFromWicketModelConceptPackageOverridesMWConceptSelection()
			throws Exception {
		Form form = viewMeta.getForm("ConceptSelectionForm", viewModel, null);
		assertNotNull(form);
		Class<?> expectedClass = domain.wicket.model.concept.ConceptSelectionForm.class;
		assertEquals(expectedClass, form.getClass());
		;
	}

	// following methods just delegates to already tested method with component
	// names hardcoded. To properly test these methods, two different viewMeta
	// should be used with different underlying domain (different domain code).
	// One case would be when component (corresponding to hardcoded name) is
	// defined in corresponding domain package, and another would be when it is
	// not defined in domain package. Apart from different viewMeta, new package
	// should be created (based on second viewMeta underlying domain code).
	// Since this method just delegates to already tested method, this seems to
	// much for testing delegation.

	// getHomePageClass()
	// getSigninPageClass()
	// getHomePage()
	// getSigninPage()
	// getHomePageMenuPanel(String)
	// getDmMenuPanel(ViewModel, View)
	// getSigninPanel(ViewModel, View)
	// getHomePageMenuPanel(String)

	// getHomePageClass(String)

	@Test
	public void getHomePageClassFromModelibraWicketConcept() throws Exception {
		Class<?> pageClass = viewMeta.getHomePageClass("MWAppHomePage");
		Class<?> expectedClass = org.modelibra.wicket.app.home.MWAppHomePage.class;
		assertEquals(expectedClass, pageClass);
	}

	@Test
	public void getHomePageClassFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Class<?> pageClass = viewMeta.getHomePageClass("AppHomePage");
		Class<?> expectedClass = domain.wicket.app.home.AppHomePage.class;
		assertEquals(expectedClass, pageClass);
	}

	// getSigninPageClass(String)

	@Test
	public void getSigninPageClassFromModelibraWicketConcept() throws Exception {
		Class<?> pageClass = viewMeta.getSigninPageClass("MWSecurityPage");
		Class<?> expectedClass = org.modelibra.wicket.security.MWSecurityPage.class;
		assertEquals(expectedClass, pageClass);
	}

	@Test
	public void getSigninPageClassFromWicketModelConceptPackageOverridesMWAppSignin()
			throws Exception {
		Class<?> pageClass = viewMeta.getSigninPageClass("SecurityPage");
		Class<?> expectedClass = domain.wicket.security.SecurityPage.class;
		assertEquals(expectedClass, pageClass);
	}

	// getHomePageMenuPanel(String, String)

	@Test(expected = ModelibraRuntimeException.class)
	public void getHomePageMenuPanelNoConstructor() throws Exception {
		viewMeta.getHomePageMenuPanel("NoConstructorPanel", null);
	}

	@Test
	public void getHomePageMenuPanelFromModelibraWicketConcept()
			throws Exception {
		Panel panel = viewMeta.getHomePageMenuPanel("MWAppHomePanel", null);
		Class<?> expectedClass = org.modelibra.wicket.app.home.MWAppHomePanel.class;
		assertEquals(expectedClass, panel.getClass());
	}

	@Test
	public void getHomePageMenuPanelFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Panel panel = viewMeta.getHomePageMenuPanel("AppHomePanel", null);
		Class<?> expectedClass = domain.wicket.app.home.AppHomePanel.class;
		assertEquals(expectedClass, panel.getClass());
	}

	// getMenuPanel(String, ViewModel, View)

	@Test(expected = ModelibraRuntimeException.class)
	public void getMenuPanelNoConstructor() throws Exception {
		viewMeta.getMenuPanel("NoConstructorPanel", viewModel, null);
	}

	@Test
	public void getMenuPanelFromModelibraWicketConcept() throws Exception {
		Panel panel = viewMeta
				.getMenuPanel("MWContainerPanel", viewModel, null);
		Class<?> expectedClass = org.modelibra.wicket.container.MWContainerPanel.class;
		assertEquals(expectedClass, panel.getClass());
	}

	@Test
	public void getMenuPanelFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Panel panel = viewMeta.getMenuPanel("ContainerPanel", viewModel, null);
		Class<?> expectedClass = domain.wicket.container.ContainerPanel.class;
		assertEquals(expectedClass, panel.getClass());
	}

	// getSigninPanel(String, ViewModel, View)

	@Test(expected = ModelibraRuntimeException.class)
	public void getSigninPanelNoConstructor() throws Exception {
		viewMeta.getSigninPanel("NoConstructorPanel", viewModel, null);
	}

	@Test
	public void getSigninPanelFromModelibraWicketConcept() throws Exception {
		Panel panel = viewMeta.getSigninPanel("MWSecurityPanel", viewModel,
				null);
		Class<?> expectedClass = org.modelibra.wicket.security.MWSecurityPanel.class;
		assertEquals(expectedClass, panel.getClass());
	}

	@Test
	public void getSigninPanelFromWicketModelConceptPackageOverridesMWAppHome()
			throws Exception {
		Panel panel = viewMeta.getSigninPanel("SecurityPanel", viewModel, null);
		Class<?> expectedClass = domain.wicket.security.SecurityPanel.class;
		assertEquals(expectedClass, panel.getClass());
	}

	/**
	 * Need to have application attached to current thread in order to
	 * instantiate WebPage.
	 */
	@SuppressWarnings("serial")
	private static void mockWebApplication() {

		new MockWebApplication(new DomainApp() {
			@Override
			public ViewMeta getViewMeta() {
				return viewMeta;
			}
		}, null);
	}

	/**
	 * Initializes viewMeta with domain based on only required domain
	 * configuration.
	 */
	private static void initViewMeta(IDomain domain) {
		viewMeta = new ViewMeta(domain);
		viewMeta.setModelCode("Model");
	}

	/**
	 * Creates domain with only required configuration
	 * 
	 * @param domainCode
	 * @param modelCode
	 * @return domain
	 */
	private static IDomain getDomain(String domainCode, String modelCode) {
		DomainConfig domainConfig = new DomainConfig();
		domainConfig.setCode(domainCode);

		ModelsConfig modelsConfig = new ModelsConfig();
		ModelConfig modelConfig = new ModelConfig();
		modelConfig.setCode(modelCode);
		modelsConfig.add(modelConfig);
		domainConfig.setModelsConfig(modelsConfig);

		return new Domain(domainConfig);
	}

	/**
	 * Initializes viewModel, setting EntitiesStub as entities.
	 */
	@SuppressWarnings("unchecked")
	private static void initViewModel() {
		viewModel = new ViewModel();
		Entities entities = new EntitiesStub();
		viewModel.setEntities(entities);
	}

	/**
	 * Entities stub only to provide concept code in lower letters ("concept").
	 * Used to set entities for view model.
	 * 
	 */
	@SuppressWarnings( { "serial", "unchecked" })
	static class EntitiesStub extends Entities {
		@Override
		public ConceptConfig getConceptConfig() {
			return new ConceptConfig() {
				@Override
				public String getCodeInLowerLetters() {
					return "concept";
				}
			};
		}
	};
}
