package twoadw.wicket.app.twoadw;

import org.apache.wicket.markup.html.WebPage;

import twoadw.generic.Generic;
import twoadw.generic.globalconfiguration.GlobalConfigurations;
import twoadw.generic.informationpage.InformationPages;
import twoadw.generic.template.Templates;
import twoadw.website.Website;
import twoadw.website.customer.Customers;
import twoadw.website.invoice.Invoice;
import twoadw.website.invoice.Invoices;
import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.product.Products;
import twoadw.website.productcategory.ProductCategories;
import twoadw.website.question.Questions;
import twoadw.website.questioncategory.QuestionCategories;
import twoadw.website.questioncategory.QuestionCategory;
import twoadw.website.rebate.Rebates;
import twoadw.website.status.Statuss;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.TwoadwAppSession;

public abstract class TwoadwAppPage extends WebPage {

	private Website website;
	
	private Generic generic;

	public TwoadwAppPage() {
		website = TwoadwApp.get().getTwoadw().getWebsite();
		generic = TwoadwApp.get().getTwoadw().getGeneric();
	}

	public TwoadwAppSession getTwoadwAppSession() {
		return (TwoadwAppSession) getSession();
	}

	public Invoice getInvoice() {
		return getTwoadwAppSession().getInvoice();
	}

	public Website getWebsite() {
		return website;
	}

	public Products getProducts() {
		return website.getProducts();
	}
	

	public Invoices getInvoices() {
		return website.getInvoices();
	}

	public Customers getCustomers() {
		return website.getCustomers();
	}
	public ProductCategories getProductCategories() {
		return website.getProductCategories();
	}
		
	public Statuss getStatuss() {
		return website.getStatuss();
	}
	
	public InformationPages getInformationPages() {
		return generic.getInformationPages();
	}
	
	public Templates getTemplates() {
		return generic.getTemplates();
	}
	
	public GlobalConfigurations getGlobalConfigurations() {
		return generic.getGlobalConfigurations();
	}

	public Rebates getRebates() {
		return website.getRebates();
	}
	
	public Manufacturers getManufacturers() {
		return website.getManufacturers();
	}
	
	public Questions getQuestions() {
		return website.getQuestions();
	}
	
	public QuestionCategories getQuestionCategories() {
		return website.getQuestionCategories();
	}
	
	
	
}
