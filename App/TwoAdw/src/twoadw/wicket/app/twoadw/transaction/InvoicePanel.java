package twoadw.wicket.app.twoadw.transaction;

import java.text.NumberFormat;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.modelibra.Oid;

import twoadw.website.invoice.Invoice;
import twoadw.website.invoiceproduct.InvoiceProduct;
import twoadw.wicket.website.products.ProductDetailsPage;

public class InvoicePanel extends Panel {

	private Invoice invoice;
	private InvoicePanel invoicePanel;

	public InvoicePanel(String id, final Invoice invoice) {
		super(id);
		this.invoice = invoice;
		final Form invoicePanelForm = new Form("invoicePanelForm");
		invoicePanelForm.add(new ListView("invoice", new PropertyModel(this,
				"invoice.getInvoiceProducts().getList()")) {
			@Override
			protected void populateItem(ListItem item) {
				final InvoiceProduct invoiceProduct = (InvoiceProduct) item.getModelObject();
				final PageParameters pars = new PageParameters();
				pars.add("name", invoiceProduct.getProduct().getName());
				item.add(new BookmarkablePageLink("bookmarkableDetails", ProductDetailsPage.class, pars)
				.add(new Label("name", invoiceProduct.getProduct().getName())));
				item.add(new Label("productNumber", invoiceProduct.getProduct().getProductNumber()));
				item.add(new AjaxFallbackLink("less", item.getModel()) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						InvoiceProduct selectedProduct = (InvoiceProduct) getModelObject();
		            	InvoiceProduct copyProduct = selectedProduct.copy();
		            	if (Long.valueOf(selectedProduct.getQuantity())-Long.valueOf(1)>=1){
		            	copyProduct.setQuantity(String.valueOf(Long.valueOf(selectedProduct.getQuantity())-Long.valueOf(1)));
		            	invoice.getInvoiceProducts().update(selectedProduct, copyProduct);
		            	setResponsePage(this.getPage());
		            	}
					}
				});
				Model quantityValue = new Model(invoiceProduct.getQuantity());
				//item.add(new Label("value", quantityValue)); remplacé par le champs TextField.
				final Model quantityModel = new Model() {
                    @Override
                    public Object getObject() {
                        return invoiceProduct.getQuantity();
                    }

                    @Override
                    public void setObject(Object object) {
                        String qtyString = (String) object;
                        boolean str = false;
                        if (qtyString != null){
                        	if (qtyString.length()<=10){
                				for (int i = 0; i < qtyString.length(); i++) {
                				    if (!Character.isDigit(qtyString.charAt(i))){
                				    	str = true;
                				    }
                				}
                        		if (str == false && Long.valueOf(qtyString)>=1) {
	                               	invoiceProduct.setQuantity(qtyString);
	                        	}
                        	}
                        }
                    }
                };
                item.add(new TextField("quantity", quantityModel) {
                public void onChangeEvent(AjaxRequestTarget target) {
	            	setResponsePage(this.getPage());
                }
                });
				item.add(new AjaxFallbackLink("more", item.getModel()) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						InvoiceProduct selectedProduct = (InvoiceProduct) getModelObject();
		            	InvoiceProduct copyProduct = selectedProduct.copy();
		            	copyProduct.setQuantity(String.valueOf(Long.valueOf(selectedProduct.getQuantity())+Long.valueOf(1)));
		            	invoice.getInvoiceProducts().update(selectedProduct, copyProduct);
		            	setResponsePage(this.getPage());
					}
				});
				item.add(new Label("price", "$"	+ invoiceProduct.getProduct().getPrice()));
				item.add(new Label("totalPrice", "$" + invoiceProduct.getProduct().getPrice()*(Integer.valueOf(invoiceProduct.getQuantity()).intValue())));
				item.add(new AjaxFallbackLink("remove", item.getModel()) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						InvoiceProduct selectedProduct = (InvoiceProduct) getModelObject();
						getInvoice().getInvoiceProducts().remove(selectedProduct);
						setResponsePage(this.getPage());
					}
				});
			}
		});
		invoicePanelForm.add(new Label("total", new Model() {
			@Override
			public Object getObject() {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				return nf.format(getInvoice().getTotal());
			}
		}));
		
		add(invoicePanelForm);
	}

	private Invoice getInvoice() {
		return invoice;
	}

}
