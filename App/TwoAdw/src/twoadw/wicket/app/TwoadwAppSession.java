package twoadw.wicket.app;

import java.util.ArrayList;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.markup.html.WebPage;
import org.modelibra.wicket.security.AppSession;

import twoadw.generic.globalconfiguration.GlobalConfiguration;
import twoadw.website.address.Address;
import twoadw.website.customer.Customer;
import twoadw.website.invoice.Invoice;
import twoadw.website.product.Product;
import twoadw.website.productcategory.ProductCategory;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.generic.informationpages.InformationPageView;
import twoadw.wicket.website.customers.CustomerInfoPage;
import twoadw.wicket.website.productcategories.CategoryListPage;
import twoadw.wicket.website.products.PopularProductListPage;
import twoadw.wicket.website.products.ProductDetailsPage;
import twoadw.wicket.website.questions.FaqPage;
import twoadw.wicket.website.rebates.RebateListPage;


@SuppressWarnings("serial")
public class TwoadwAppSession extends AppSession {

	private Invoice invoice;
	
	private Customer customer;
	
	private Address address;
	
	private Product selectedProduct;
	
	private ProductCategory selectedProductCategory;
	
	private GlobalConfiguration globalConfiguration;
	
	private ArrayList<String> arrayPages = new ArrayList<String>();

	
	public TwoadwAppSession(Request request) {
		super(request);
	}
	
	public void clearArrayPages() {
		this.arrayPages.clear();
	}
	
	public void addArrayPages(String string) {	
		this.arrayPages.add(string);
	}
	
	public void setSelectedProduct(Product product) {	
		this.selectedProduct=product;
	}
	
	public ArrayList<String> getArrayPages() {
		return this.arrayPages;
	}
	
	public Page getContextPage() {
		Page tempPage = new CategoryListPage();
		String lastIndex;
		
		if (arrayPages.size()>0){
			lastIndex = arrayPages.get(arrayPages.size()-1);
			
			if (lastIndex=="Checkout") tempPage = new CheckoutPage();
			if (lastIndex=="MyProfile") tempPage = new CustomerInfoPage();
			if (lastIndex=="Promotions") tempPage = new RebateListPage();
			if (lastIndex=="PopularProducts") tempPage = new PopularProductListPage();
			if (lastIndex=="FAQ") tempPage = new FaqPage();
			if (lastIndex=="Help") tempPage = new InformationPageView("help");
			if (lastIndex=="Contact") tempPage = new InformationPageView("contact");
			if (lastIndex=="Privacy") tempPage = new InformationPageView("privacy");
			if (lastIndex=="Terms") tempPage = new InformationPageView("terms");
			if (lastIndex=="AboutUs") tempPage = new InformationPageView("aboutus");
			if (lastIndex=="Product") tempPage = new ProductDetailsPage(selectedProduct);
			

		}		
		return tempPage;
	}
	
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Invoice getInvoice() {
		return invoice;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setAddress(Address address) {
		
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}
	
	public GlobalConfiguration getGlobalConfiguration() {
		globalConfiguration = TwoadwApp.get().getTwoadw().getGeneric().getGlobalConfigurations().getGlobalConfiguration("siteName", "2adw");
		return globalConfiguration;
	}
	
	public void setGlobalConfiguration(GlobalConfiguration globalConfiguration) {
		this.globalConfiguration = globalConfiguration;
	}
	
	
	
	
}
