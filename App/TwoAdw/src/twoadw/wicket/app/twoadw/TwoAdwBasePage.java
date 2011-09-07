package twoadw.wicket.app.twoadw;

import java.text.NumberFormat;

import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.modelibra.wicket.app.domain.DomainPage;
import org.modelibra.wicket.concept.CountryLanguageChoicePanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import twoadw.Twoadw;
import twoadw.reference.Reference;
import twoadw.reference.countrylanguage.CountryLanguage;
import twoadw.reference.countrylanguage.CountryLanguages;
import twoadw.website.Website;
import twoadw.website.invoice.Invoice;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.twoadw.administrator.AdminPanel;
import twoadw.wicket.app.twoadw.administrator.CommercialPanel;
import twoadw.wicket.app.twoadw.administrator.ManagerPanel;
import twoadw.wicket.app.twoadw.generic.BreadcrumbPanel;
import twoadw.wicket.app.twoadw.generic.SigninPage;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.app.twoadw.transaction.InvoicePanel;
import twoadw.wicket.generic.informationpages.InformationPageView;
import twoadw.wicket.website.customers.CustomerInfoPage;
import twoadw.wicket.website.productcategories.CategoryListPage;
import twoadw.wicket.website.products.PopularProductListPage;
import twoadw.wicket.website.questions.FaqPage;
import twoadw.wicket.website.rebates.RebateListPage;

/**
 * @author dashorst
 */
public class TwoAdwBasePage extends TwoadwAppPage {
  
  private InvoicePanel invoicePanel;
  private CommercialPanel commercialPanel;
  private AdminPanel adminPanel;
  private ManagerPanel managerPanel;
  private Integer intRole;
  private String templatePath;

  public TwoAdwBasePage() {
	  
	  templatePath = getTemplates().getTemplate("name", getTwoadwAppSession().getGlobalConfiguration().getTemplate()).getDirectory();
	  
	 //Set le Css
	  add(HeaderContributor.forCss("css-specific/" + templatePath + "/style.css"));
	  
	//Vérifie si l'invoice est null, sinon créer une nouvelle  
	 if (getTwoadwAppSession().getInvoice() == null) {
		 final Invoice currentInvoice = new Invoice(getWebsite());
		 getTwoadwAppSession().setInvoice(currentInvoice);
	 }else {
		 final Invoice currentInvoice = getTwoadwAppSession().getInvoice();
		 
    } 
	 commercialPanel = new CommercialPanel("commercialPanel");
	 adminPanel = new AdminPanel("adminPanel");
	 managerPanel = new ManagerPanel("managerPanel");
	
	//Vérifie si l'utilisateur a les droits necessaire pour voir la Commercial Panel
	 intRole = 0;
	 if (getTwoadwAppSession().getCustomer() != null) {
		 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
		 if (intRole >= 2) {
			 managerPanel.setVisible(true);
			 if (intRole >= 3) {
				 commercialPanel.setVisible(true);
				 adminPanel.setVisible(false);
				 if (intRole > 3) adminPanel.setVisible(true);
			 }else {
				 commercialPanel.setVisible(false);
				 adminPanel.setVisible(false);
			 } 
		 }
		 
		 
	 } else {
		 commercialPanel.setVisible(false);
		 adminPanel.setVisible(false);
		 managerPanel.setVisible(false);
	 } 
	 add(commercialPanel);
	 add(adminPanel);
	 add(managerPanel);

	invoicePanel = new InvoicePanel("invoice", getInvoice());
	
	add(new Link("home", getModel()) {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			setResponsePage(new CategoryListPage());
		}
		
	});
    
    add(new Label("total", new Model() {
		@Override
		public Object getObject() {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			return nf.format(getInvoice().getTotal());
		}
	}));
	
    add(new Label("itemNumber", new Model() {
		@Override
		public Object getObject() {
			return getInvoice().getItemTotal();
		}
	}));

	
	add(new Link("customerLink") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("MyProfile");
			setResponsePage(new CustomerInfoPage());
		}
		@Override
		public boolean isVisible() {
			Boolean result = false;
			if (getTwoadwAppSession().getCustomer() != null) result = true;
			return result;
		}

	}.add(new Label("customer", new Model() {
		@Override
		public Object getObject() {
			String customer = null;
			if (getTwoadwAppSession().getCustomer() != null) {
				customer = "(";
				customer += getTwoadwAppSession().getCustomer().getCodeCustomer();
				customer += ")";
			}
			return customer;
		}
	})));
	
	add(new Link("login") {
		@Override
		public void onClick() {
			setResponsePage(new SigninPage(null));
		}
		@Override
		public boolean isVisible() {
			Boolean result = true;
			if (getTwoadwAppSession().getCustomer() != null) result = false;
			return result;
		}

	});
	
	add(new Link("logout") {
		@Override
		public void onClick() {
			getTwoadwAppSession().invalidate();
			setResponsePage(CategoryListPage.class);
		}
		@Override
		public boolean isVisible() {
			Boolean result = false;
			if (getTwoadwAppSession().getCustomer() != null) result = true;
			return result;
		}

	});
	
	add(new Link("checkout") {
		@Override
		public void onClick() {
			//current.replaceWith(invoicePanel);
	        //current = invoicePanel;
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("Checkout");
			setResponsePage(new CheckoutPage());
		}

	});
	
	add(new Link("aboutus") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("AboutUs");
			setResponsePage(new InformationPageView("aboutus"));
		}

	});
	
	add(new Link("terms") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("Terms");
			setResponsePage(new InformationPageView("terms"));
		}

	});
	
	add(new Link("privacy") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("Privacy");
			setResponsePage(new InformationPageView("privacy"));
		}

	});
	
	add(new Link("contact") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("Contact");
			setResponsePage(new InformationPageView("contact"));
		}

	});
	
	add(new Link("help") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("Help");
			setResponsePage(new InformationPageView("help"));
		}

	});
	
	add(new Link("faq") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("FAQ");
			setResponsePage(FaqPage.class);
		}

	});
	
	add(new Link("popularProducts") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("PopularProducts");
			setResponsePage(new PopularProductListPage());
		}

	});
	
	add(new Link("promotions") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			getTwoadwAppSession().addArrayPages("Promotions");
			setResponsePage(new RebateListPage());
		}

	});
	
	add(new Link("browse") {
		@Override
		public void onClick() {
			getTwoadwAppSession().clearArrayPages();
			setResponsePage(new CategoryListPage());
		}

	});
	

	
	add(new BreadcrumbPanel("BreadcrumbPanel", getTwoadwAppSession().getArrayPages()));
	
	
	
//i18n
	
	TwoadwApp twoadwApp=(TwoadwApp)getApplication();
	Twoadw twoadw=twoadwApp.getTwoadw();
	Website website=twoadw.getWebsite();
	
	Reference reference=twoadw.getReference(); 
	ViewModel countryLanguageViewModel = new ViewModel(reference);
	CountryLanguages countryLanguages = reference.getCountryLanguages();
	countryLanguageViewModel.setEntities(countryLanguages);
	String languageCode = null;
	CountryLanguage defaultLanguage = null;
	languageCode = getTwoadwAppSession().getLocale().getLanguage();
	defaultLanguage = countryLanguages.getCountryLanguage(languageCode);
	if (defaultLanguage == null) {
		defaultLanguage = countryLanguages.getCountryLanguage("en");
	}
	countryLanguageViewModel.setEntity(defaultLanguage);

	View countryLanguageView = new View();
	countryLanguageView.setWicketId("countryLanguageChoice");

	Panel countryLanguageChoice = new CountryLanguageChoicePanel(
			countryLanguageViewModel, countryLanguageView);
		add(countryLanguageChoice);
	
	if (!twoadw.getDomainConfig().isI18n()) {
		countryLanguageChoice.setVisible(false);
	}
	
	
	
	

  }


}
