package twoadw.wicket.website.productcomments;

import java.util.ArrayList;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import twoadw.website.product.Product;
import twoadw.website.productcomment.ProductComment;
import twoadw.website.productcomment.ProductComments;

public class ProductCommentsPanel extends Panel{
	private ProductComment newComment;
	
	public ProductCommentsPanel(String id, final Product product) {
		super(TabbedPanel.TAB_PANEL_ID);

		 add(new ListView("modComments", product.getProductComments().getList())
		 {
		        public void populateItem(final ListItem item) { 
		        	ProductComment comment = (ProductComment) item.getModelObject();
		        	item.add(new Label("commentTitle", comment.getCommentTitle()));
		        	item.add(new MultiLineLabel("commentText", comment.getCommentText()));
		        	item.add(new Label("creationDate", new PropertyModel(comment, "creationDate")));
		        }
		 });
		 
	    final ArrayList<ProductComment> ajaxComments = new ArrayList<ProductComment>();
	    final WebMarkupContainer parent = new WebMarkupContainer(
	        "comments");
	    parent.setOutputMarkupId(true);
	    add(parent);
	    parent.add(new ListView("list", ajaxComments) {
	      @Override
	      protected void populateItem(ListItem item) {
	    	  ProductComment comment = (ProductComment) item.getModelObject();
	    	item.add(new Label("ajaxCommentTitle", comment.getCommentTitle()));
	        item.add(new Label("ajaxCommentText", comment.getCommentText()));
	      }
	    });

	    Form form = new Form("formComment");
	    final TextField editTitle = new TextField("editTitle", new Model(""));
	    editTitle.setOutputMarkupId(true);
	    form.add(editTitle);
	    
	    final TextArea editText = new TextArea("editText", new Model(""));
	    editText.setOutputMarkupId(true);
	    form.add(editText);
	    
	    form.add(new AjaxSubmitLink("save") {
	      @Override
	      protected void onSubmit(AjaxRequestTarget target, Form form) {
	    	ProductComments comments = product.getProductComments();
	    	newComment = comments.createProductComment(product, editText.getModelObjectAsString(), editTitle.getModelObjectAsString());
	    	ajaxComments.add(newComment);
	        editTitle.setModel(new Model(""));
	        editText.setModel(new Model(""));
	        target.addComponent(parent);
	        target.focusComponent(editTitle);
	      }
	    });
	    parent.add(form);
		
		
	}

}
