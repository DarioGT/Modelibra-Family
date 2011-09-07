package twoadw.wicket.generic.templates;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.generic.globalconfiguration.GlobalConfiguration;
import twoadw.generic.globalconfiguration.GlobalConfigurations;
import twoadw.generic.template.Template;
import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.product.Product;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.website.products.ProductDetailsPage;
import twoadw.wicket.website.products.ProductListPage;

public class TemplateViewPage extends TwoAdwBasePage{

	public TemplateViewPage() {
		
		PageableListView templateListView = new PageableListView("templateListView",
				getTemplates().getList(), 5) {
			@Override
			protected void populateItem(final ListItem item) {
				Template template = (Template) item.getModelObject();
				item.add(new Label("name", template.getName()));
				item.add(new Label("directory", template.getDirectory()));
				item.add(new ExternalLink("preview", "/ModelibraWicket/css-specific/"+template.getDirectory()+"/preview.jpg", "(Preview)"));
				Link selectTemplate = new Link("selectTemplate") {
					@Override
					public void onClick() {
						Template selectedTemplate = (Template) item.getModelObject();
						//System.out.println("selectedTemplate: "+selectedTemplate);
						
						GlobalConfigurations globalConfigurations = getGlobalConfigurations();
						GlobalConfiguration oldGC = globalConfigurations.getGlobalConfiguration("siteName", "2adw");
						GlobalConfiguration newGC = oldGC.copy();
						
						newGC.setTemplate(selectedTemplate.getName());
						
						//System.out.println("oldGC: "+oldGC);
						//System.out.println("newGC: "+newGC);

						
						if (globalConfigurations.update(oldGC, newGC)) {
							setResponsePage(new TemplateViewPage());
							
						} 
						
						
						
					}
				};
				item.add(selectTemplate);
			}
			
		};
		add(templateListView);
	}
}
