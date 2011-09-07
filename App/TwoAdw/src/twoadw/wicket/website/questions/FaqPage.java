package twoadw.wicket.website.questions;

import java.util.ArrayList;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.Model;

import twoadw.website.answer.Answer;
import twoadw.website.answer.Answers;
import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.product.Product;
import twoadw.website.productcategory.ProductCategory;
import twoadw.website.productcomment.ProductComment;
import twoadw.website.productimage.ProductImage;
import twoadw.website.qqcategory.QQCategory;
import twoadw.website.question.Question;
import twoadw.website.questioncategory.QuestionCategory;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.generic.DetailPagingNavigator;
import twoadw.wicket.app.twoadw.transaction.AddInvoiceProductPanel;
import twoadw.wicket.website.productcategories.CategoryTreePanel;
import twoadw.wicket.website.products.InputProductPage;
import twoadw.wicket.website.products.ProductDetailsPage;
import twoadw.wicket.website.products.ProductListPage;

public class FaqPage extends TwoAdwBasePage{
	private CategoryTreePanel categoryTreePanel;

	public FaqPage(){
		PageableListView faqListView = new PageableListView("QcategoryList",
				getQuestionCategories().getQuestionCategories("published", true).getList(), 3) {
			@Override
			protected void populateItem(final ListItem item) {
				QuestionCategory qCategory = (QuestionCategory) item.getModelObject();
				item.add(new Label("name", qCategory.getName()));	
				
				item.add(new ListView("QuestionList",qCategory.getQQCategories().getList()) {
					@Override
					protected void populateItem(ListItem item2) {
						QQCategory qQCategory = (QQCategory) item2.getModelObject();
						item2.add(new Label("questionText", qQCategory.getQuestion().getQuestionText()));	
						item2.add(new ListView("AnswerList",qQCategory.getQuestion().getAnswers().getAnswers("published", true).getList()) {
							@Override
							protected void populateItem(ListItem item3) {
								Answer answer = (Answer) item3.getModelObject();
								item3.add(new Label("answerText", answer.getAnswerText()));			
							}
						});
					}
				});
				
				
			}
		};
		
		add(faqListView);
		
		DetailPagingNavigator detailPagingNavigatornew = new DetailPagingNavigator("navigator", faqListView);
		
		if (faqListView.getPageCount()==1) detailPagingNavigatornew.setVisible(false);
		add(detailPagingNavigatornew);
		categoryTreePanel = new CategoryTreePanel("category", getProductCategories());
	    categoryTreePanel.setOutputMarkupId(true);
		add(categoryTreePanel);
		
	}

}
