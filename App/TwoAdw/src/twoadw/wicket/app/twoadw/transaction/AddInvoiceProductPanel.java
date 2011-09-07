package twoadw.wicket.app.twoadw.transaction;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import twoadw.website.invoice.Invoice;
import twoadw.website.invoiceproduct.InvoiceProduct;
import twoadw.website.invoiceproduct.InvoiceProducts;
import twoadw.website.product.Product;
import twoadw.wicket.app.TwoadwApp;

public class AddInvoiceProductPanel extends Panel {
	private InvoiceProducts invoiceProducts;
	private InvoiceProduct invoiceProduct;
	private String quantity;
	private InvoicePanel invoicePanel;
	
	public AddInvoiceProductPanel(String id, final Invoice invoice, final Product product) {
		super(id);
		add(new FeedbackPanel("feedback"));
		
		NumberFormat n = NumberFormat.getCurrencyInstance();  

		
		Label price= new Label("price", 
				n.format(product.getRebatePrice(product)));	
		Label youSaved = new Label("youSaved", "You Save: ");
		Label saved = new Label("saved", 
				n.format(product.getPrice()-product.getRebatePrice(product)));
		if (product.getRebatePrice(product)==product.getPrice()){
			youSaved.setVisible(false);
			saved.setVisible(false);
		}
		add(price);
		add(youSaved);
		add(saved);
	
		//Form
		final TextField qte = new TextField("qte", new Model(""));
		qte.add(StringValidator.maximumLength(10));
		qte.setModelValue("1");
		qte.setRequired(true);
		Form form = new Form("form") ;

		add(form);
		form.add(qte);
		form.add(new SubmitLink("add"){
			@Override
			public void onSubmit() {
				Product selectedProduct = (Product) getModelObject();
				InvoiceProduct invoiceProduct = new InvoiceProduct(invoice,product);
				//currentInvoice.getInvoiceProducts().add(invoiceProduct);
				String quantity = "1";
				
				quantity=qte.getModelObjectAsString();

				
				//Si le champ n'est pas valide un nombre valide remplacer par 1 
				for (int i = 0; i < quantity.length(); i++) {
				    if (!Character.isDigit(quantity.charAt(i)))
				    	quantity="1";
				}
				
				//Rechercher si le produit existe déjà dans l'invoice, ajuster juste la quantity si trouvé
				if (invoice.getInvoiceProducts().getInvoiceProduct(invoice, product)!= null){
					String oldQuantity = invoice.getInvoiceProducts().getInvoiceProduct(invoice, product).getQuantity();
					String newQuantity = Integer.toString(
							Integer.parseInt(oldQuantity)
							+
							Integer.parseInt(quantity)
					);
					invoice.getInvoiceProducts().getInvoiceProduct(invoice, product).setQuantity(newQuantity);
				}
				else{
					invoice.getInvoiceProducts().createInvoiceProduct(invoice, product, product.getPrice(), quantity);
				}
				
				//ajouter 1 au sold number du product
				
				Product copyProduct = product.copy();
				copyProduct.setSoldNumber(product.getSoldNumber()+Integer.parseInt(quantity));
				TwoadwApp.get().getTwoadw().getWebsite().getProducts().update(product, copyProduct);
				setResponsePage(this.getPage());
			}
			});
			
			
		/*
		invoiceProducts = invoice.getInvoiceProducts();
		invoiceProduct = invoiceProducts.getInvoiceProduct(invoice, product);
		
		final TextField qte = new TextField("qte");
		qte.setModelObject(invoiceProduct.getQuantity());
		add(qte);
		
		add(new Link("add", qte.getModel()) {
			@Override
			public void onClick() {
				Product selectedProduct = (Product) getModelObject();
				InvoiceProduct invoiceProduct = new InvoiceProduct(invoice,product);
				//currentInvoice.getInvoiceProducts().add(invoiceProduct);
				String quantity = "1";
				
				if (qte.getModelObjectAsString() != "") {
					quantity=qte.getModelObjectAsString();
				} else {
					quantity="1";
				}
				
				
				System.out.println("txt="+qte.getModelObjectAsString());
				System.out.println("qte="+quantity);
				invoice.getInvoiceProducts().createInvoiceProduct(invoice, product, "1");
			
				
			}
		});*/
	}

}
