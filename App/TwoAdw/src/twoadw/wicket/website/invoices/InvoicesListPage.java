package twoadw.wicket.website.invoices;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;

import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.invoice.Invoice;
import twoadw.website.invoiceproduct.InvoiceProduct;
import twoadw.website.product.Product;
import twoadw.website.productcategory.ProductCategory;
import twoadw.website.status.Status;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.website.productcategories.CategoryTreePanel;
import twoadw.wicket.website.products.ProductDetailsPage;
import twoadw.wicket.website.products.ProductListPage;

public class InvoicesListPage extends TwoAdwBasePage {

	private InvoiceSearchPanel invoiceSearchPanel;
	
	ArrayList<Invoice> invoicesList = new ArrayList<Invoice>();
	
	public InvoicesListPage(String status, String invoiceNumber){
		
		invoiceSearchPanel = new InvoiceSearchPanel("invoiceSearchPanel");
		invoiceSearchPanel.setOutputMarkupId(true);
		add(invoiceSearchPanel);
		
		//Build the array	
		if (status==null & invoiceNumber==null) {
			//Toute les invoices
			addAll();
		} else {
			if (status!=null) {
				addInvoicesStatus(status);
				
			}
			
			if (invoiceNumber!=null) {
				addInvoicesNumber(invoiceNumber);
			}
		}		
		
		//Print la liste
		PageableListView categoryList = new PageableListView("invoiceList",
				invoicesList, 6) {  
				@Override
				protected void populateItem(final ListItem item) {
					Invoice invoice = (Invoice) item.getModelObject();
					
					item.add(new Link("invoiceLink", item.getModel()) {
						@Override
						public void onClick() {
							Invoice selectedInvoice = (Invoice) item.getModelObject();
							//setResponsePage(new ProductListPage(selectedInvoice));
						}
						
						
					}.add(new Label("iNumber", invoice.getInvoiceNumber())));
					item.add(new Label("date",invoice.getDate().toString()));
					
					item.add(new Label("codeCustomer",invoice.getAddress().getCustomer().getCodeCustomer()));
					item.add(new Label("firstName",invoice.getAddress().getCustomer().getFirstName()));
					item.add(new Label("lastName",invoice.getAddress().getCustomer().getLastName()));
					
					item.add(new Label("street",invoice.getAddress().getStreet()));
					item.add(new Label("city",invoice.getAddress().getCity()));
					item.add(new Label("zipCode",invoice.getAddress().getZipCode()));
					item.add(new Label("state",invoice.getAddress().getState()));
					item.add(new Label("country",invoice.getAddress().getCountry()));
					item.add(new Label("telephone",invoice.getAddress().getTelephone()));
					
					item.add(new ListView("products", 
							invoice.getInvoiceProducts().getList()) {
						@Override
						protected void populateItem(ListItem item2) {
							InvoiceProduct iProduct = (InvoiceProduct) item2.getModelObject();
							
							item2.add(new Label("quantity", iProduct.getQuantity()));
							item2.add(new Label("productName", iProduct.getProduct().getName()));
							item2.add(new Label("productNumber", iProduct.getProduct().getProductNumber()));
							
						}
					});
					
				}
				
		};
		add(categoryList);
		
	}
	


	private void addAll() {
		//Fill the array
		for (Invoice invoice : getInvoices()) {
    		addToList(invoice);
		}
		
	}

	protected void addInvoicesStatus(String status) {
		
		//Get the objects form the args
		Status selectedStatus = getStatuss().getStatusNameStatus(status);
		
		//Fill the array
		for (Invoice invoice : getInvoices()) {
    		if (invoice.getInvoiceStatuss().getInvoiceStatus(invoice, selectedStatus)!=null) addToList(invoice);
		}
	}
	
	protected void addInvoicesNumber(String iNumber) {
		
		//Get the objects form the args
		Invoice selectedInvoice = getInvoices().getInvoice("invoiceNumber", iNumber);
		
		//Si trouvé
		if (selectedInvoice!=null) addToList(selectedInvoice);

	}

	private void addToList(Invoice invoice) {
		Boolean found = false;
		
		// Vérifie que l'invoice n'est pas déjà dans la liste, sinon ajouter la l'invoice
		for (Invoice testInvoice : invoicesList) {
    		if (testInvoice==invoice) found = true;
		}
		
		if (found==false) invoicesList.add(invoice);
		
	}
}
