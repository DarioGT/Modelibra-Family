package twoadw.wicket.website.manufacturers;

import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import twoadw.website.product.Product;
import twoadw.website.productmanufacturer.ProductManufacturer;

public class ProductManufacturerPanel extends Panel{

	public ProductManufacturerPanel(String id, Product product) {
		super(TabbedPanel.TAB_PANEL_ID);
		
		
		//TODO Ajouter les tag <li> dans les Label pour ne pas qu'il s'affiche lors du render

		add(new ListView("manufacturers", product.getProductManufacturers().getList())
		{
			public void populateItem(final ListItem item) { 
	        	ProductManufacturer productManufacturer = (ProductManufacturer) item.getModelObject();
	        	Label manufacturerName = new Label("manufacturerName", productManufacturer.getManufacturer().getName());
	        	item.add(manufacturerName);
	        	
	        	Label websiteLabel = new Label("websiteLabel","Website");
	        	ExternalLink websiteUrl = new ExternalLink("websiteUrl", productManufacturer.getManufacturer().getWebsiteUrl(), productManufacturer.getManufacturer().getWebsiteUrl());
	        	//Vérifie si le champ est vide, sinon n'affiche pas le label
	        	if (productManufacturer.getManufacturer().getWebsiteUrl()==null) {
	        		//websiteLabel.setVisible(false);
	        		websiteUrl.setVisible(false);
	        	}
	        	item.add(websiteLabel);
	        	item.add(websiteUrl);
	        	
	        	Label supportLabel = new Label("supportLabel","Support");
	        	ExternalLink supportUrl = new ExternalLink("supportUrl", productManufacturer.getManufacturer().getSupportUrl(), productManufacturer.getManufacturer().getSupportUrl());
	        	if (productManufacturer.getManufacturer().getSupportUrl()==null) {
	        		//supportLabel.setVisible(false);
	        		supportUrl.setVisible(false);
	        	}
	        	item.add(supportLabel);
	        	item.add(supportUrl);
	        	
	        	Label contactNameLabel = new Label("contactNameLabel","Contact Name");
	        	Label contactName = new Label("contactName", productManufacturer.getManufacturer().getContactName());
	        	if (productManufacturer.getManufacturer().getContactName()==null) {
	        		//contactNameLabel.setVisible(false);
	        		contactName.setVisible(false);
	        	}
	        	item.add(contactNameLabel);
	        	item.add(contactName);
	        	
	        	Label contactEmailLabel = new Label("contactEmailLabel","Contact Email");
	        	Label contactEmail = new Label("contactEmail", productManufacturer.getManufacturer().getContactEmail());
	        	if (productManufacturer.getManufacturer().getContactEmail()==null) {
	        		//contactEmailLabel.setVisible(false);
	        		contactEmail.setVisible(false);
	        	}
	        	item.add(contactEmailLabel);
	        	item.add(contactEmail);
	        	
	        	Label mpnLabel = new Label("mpnLabel","Mfc Part Number");
	        	Label mpn = new Label("mpn", productManufacturer.getPartNumber());
	        	if (productManufacturer.getPartNumber()==null) {
	        		//mpnLabel.setVisible(false);
	        		mpn.setVisible(false);
	        	}
	        	item.add(mpnLabel);
	        	item.add(mpn);
	        	
	        }
		}
		);
		
	}

}
