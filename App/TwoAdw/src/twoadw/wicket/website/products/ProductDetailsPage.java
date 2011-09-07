package twoadw.wicket.website.products;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.codesmell.wicket.lightbox.LightBox2Config;
import org.codesmell.wicket.lightbox.LightBox2Panel;


import twoadw.website.assignproductcategory.AssignProductCategories;
import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.invoice.Invoice;
import twoadw.website.product.Product;
import twoadw.website.productcategory.ProductCategory;
import twoadw.website.productcomment.ProductComment;
import twoadw.website.productcomment.ProductComments;
import twoadw.website.productimage.ProductImage;
import twoadw.website.productmanufacturer.ProductManufacturer;
import twoadw.website.productmanufacturer.ProductManufacturers;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.transaction.AddInvoiceProductPanel;
import twoadw.wicket.generic.informationpages.InformationPageView;
import twoadw.wicket.website.manufacturers.ProductManufacturerPanel;
import twoadw.wicket.website.productcategories.CategoryTreePanel;
import twoadw.wicket.website.productcomments.ProductCommentsPanel;
import twoadw.wicket.website.productimages.AddImagePanel;
import twoadw.wicket.website.products.ProductTabbedPanel.MyTab;
import twoadw.wicket.website.questions.ProductQAPanel;
import twoadw.wicket.website.specifications.ProductSpecificationPanel;


public class ProductDetailsPage extends TwoAdwBasePage {
	private CategoryTreePanel categoryTreePanel;
	private Invoice currentInvoice = getTwoadwAppSession().getInvoice();
	private AddInvoiceProductPanel addInvoiceProductPanel;
	private ProductGeneralInfoPanel productGeneralInfoPanel;


	public ProductDetailsPage(PageParameters parameters) {
		String productName = parameters.getString("name");
		Product product = TwoadwApp.get().getTwoadw().getWebsite().getProducts().getProduct("name", productName);
		addComponents(product);
		
	}

	public ProductDetailsPage(Product product) {
		addComponents(product);
	}

	private void addComponents(final Product product) {
		 
		AssignProductCategories apcs = product.getAssignProductCategories();
		ProductManufacturers productManufacturers = product.getProductManufacturers();
		
		categoryTreePanel = new CategoryTreePanel("category", getProductCategories());
	    categoryTreePanel.setOutputMarkupId(true);
		add(categoryTreePanel);
		
		Link modify = new Link("modify") {
			@Override
			public void onClick() {
				setResponsePage(new InputProductPage(null, product));
			}

		};
		modify.setVisible(false);
		if (getTwoadwAppSession().getCustomer()!=null) if(Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole())>=3) modify.setVisible(true);
		add(modify);
		
		 ListView images = new ListView("images", product.getProductImages().getList())
		 {
		        public void populateItem(final ListItem item) { 
		        	ProductImage productImage = (ProductImage) item.getModelObject();
		        	item.add(new ContextImage("imageUrl", new Model(productImage.getImageUrl180x130())));
		        }
		 };
		 images.setViewSize(1);
		 add(images);
		 LightBox2Panel lbp = new LightBox2Panel("lbp", new Model(new LightBox2Config(product.getLightBoxImageList(product))));
		 add(lbp);


		//INFORMATION ABOUT THE PRODUCT
		add(new Label("name", new PropertyModel(product, "name")));

		
		
		addInvoiceProductPanel = new AddInvoiceProductPanel("addInvoiceProductPanel", currentInvoice, product);
		add(addInvoiceProductPanel);
		
		add(new MultiLineLabel("shortDescription", new PropertyModel(
				product, "shortDescription")).add(new AttributeModifier("style",
				true, new Model("color:grey"))));
		

			
		final List tabs = new ArrayList();
		 tabs.add(new ProductTabbedPanel.MyTab("General Information")
		  {
		   private static final long serialVersionUID = 1L;
		   @Override
		   public Panel createPanel()
		   {
		    return new ProductGeneralInfoPanel(null, product);
		   }
		  });
		  tabs.add(new ProductTabbedPanel.MyTab("Specifications")
		  {
		   private static final long serialVersionUID = 1L;
		   @Override
		   public Panel createPanel()
		   {
			   Boolean edit = false;
			   if (getTwoadwAppSession().getCustomer()!=null) edit = true;
			   return new ProductSpecificationPanel(null, product, edit);
		   }
		  });
		  tabs.add(new ProductTabbedPanel.MyTab("Manufacturer")
		  {
		   private static final long serialVersionUID = 1L;
		   @Override
		   public Panel createPanel()
		   {
			   return new ProductManufacturerPanel(null, product);
		   }
		  });
		  tabs.add(new ProductTabbedPanel.MyTab("Comments")
		  {
		   private static final long serialVersionUID = 1L;
		   @Override
		   public Panel createPanel()
		   {
			   return new ProductCommentsPanel(null, product);
		   }
		  });
		  tabs.add(new ProductTabbedPanel.MyTab("Q&A")
		  {
		   private static final long serialVersionUID = 1L;
		   @Override
		   public Panel createPanel()
		   {
			   Boolean edit = false;
			   if (getTwoadwAppSession().getCustomer()!=null) 
				   if(Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole())>3) edit = true;
			   return new ProductQAPanel(null, product, edit);
		   }
		  });
		  MyTab adminTab = new ProductTabbedPanel.MyTab("Administator")
		  {
		   private static final long serialVersionUID = 1L;
		   @Override
		   public Panel createPanel()
		   {
			   return new ProductAdministatorPanel(null, product, null);
		   }
		  };
		  if (getTwoadwAppSession().getCustomer()!=null) if(Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole())>3) tabs.add(adminTab);
		  
		   
		  final ProductTabbedPanel tabpanel = new ProductTabbedPanel("tabpanel", tabs);
		  add(tabpanel);

		  
		  
		 


	   
	   
	}
	
}


