package twoadw.wicket.website.questions;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.website.answer.Answer;
import twoadw.website.answer.Answers;
import twoadw.website.product.Product;
import twoadw.website.question.Question;
import twoadw.website.question.Questions;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.twoadw.generic.reusable.RequiredTextField;
import twoadw.wicket.website.products.ProductDetailsPage;

public class ProductQAPanel extends Panel{
	public ProductQAPanel(String id,final Product product,final Boolean edit){
		super(TabbedPanel.TAB_PANEL_ID);
		add(new FeedbackPanel("feedback"));
		
		add(new ListView("productQuestions",TwoadwApp.get().getTwoadw().getWebsite().getQuestions().getProductQuestions(product).getList()){
			public void populateItem(final ListItem item1){
				final Question productQuestion=(Question)item1.getModelObject();
				item1.add(new Label("questionText",productQuestion.getQuestionText()));
				//Answer of this question
				item1.add(new ListView("answersList",productQuestion.getAnswers().getList()){
					public void populateItem(final ListItem item2){
						Answer productQuestionsAnswers=(Answer)item2.getModelObject();
						item2.add(new MultiLineLabel("answerText",productQuestionsAnswers.getAnswerText()));
						AjaxFallbackLink removeAnswer= new AjaxFallbackLink("removeAnswer",item1.getModel()){
							@Override
	    					public void onClick(AjaxRequestTarget target) {
								Answer productQuestionsAnswers=(Answer)item2.getModelObject();
								productQuestion.getAnswers().remove(productQuestionsAnswers);
								if (target != null) { // crash lors de l'actualisation de la page
	    							//target.addComponent(invoicePanel);
	    							target.appendJavascript("window.location.reload()");
							}
						}
						
					};
					removeAnswer.add(new SimpleAttributeModifier("onclick",
	    						"if(!confirm('remove Answer for "+productQuestion.getQuestionText()
	    						+ " ?')) return false;"));
					removeAnswer.setVisible(edit);
					item2.add(removeAnswer);
					}
					
				});
				final Form formAnswwer = new Form("formAnswer") ;
				final Answer newAnswer= new Answer(productQuestion);
				final TextArea answerText = new TextArea("answerText");
				answerText.setModel(new PropertyModel(newAnswer, "answerText"));
				answerText.add(StringValidator.maximumLength(102));
				formAnswwer.add(answerText);
				formAnswwer.add(new SubmitLink("addAnswer"){
					@Override
	    			public void onSubmit() {
						Answers answers=productQuestion.getAnswers();
						if(answers.add(newAnswer)){
							new ProductQAPanel(null,product,true);
						}
						else {
	    					List<String> errorKeys = answers.getErrors().getKeyList();
	    					for (String errorKey : errorKeys) {
	    						String errorMsg = LocalizedText.getErrorMessage(this,
	    								errorKey);
	    						formAnswwer.error(errorMsg);
	    					
	    					}
						}
					}
					
				});
				AjaxFallbackLink removeQuestion=new AjaxFallbackLink("removeQuestion",item1.getModel()){
					@Override
					public void onClick(AjaxRequestTarget target) {
						Question productQuestion=(Question)item1.getModelObject();
						TwoadwApp.get().getTwoadw().getWebsite().getQuestions().remove(productQuestion);
						setResponsePage(new ProductDetailsPage(product));
						//setResponsePage(this.getPage());
						if (target != null) { // crash lors de l'actualisation de la page
							//target.addComponent(invoicePanel);
							setResponsePage(new ProductDetailsPage(product));
						}
					}
				};
				removeQuestion.add(new SimpleAttributeModifier("onclick",
						"if(!confirm('remove Specification Category for "
								+ product.getName()
								+ " ?')) return false;"));
				
				removeQuestion.setVisible(edit);
				item1.add(removeQuestion);
				
				//ajout du formulaire de Answer
				formAnswwer.setVisible(edit);
				item1.add(formAnswwer);
			}
		});
		final Form formQuestion = new Form("formQuestion") ;
		final Question newQuestion=new Question(product);
		final TextArea questionText = new TextArea("questionText");
		questionText.setModel(new PropertyModel(newQuestion, "questionText"));
		questionText.setRequired(true);
		questionText.add(StringValidator.maximumLength(1020));
		formQuestion.add(questionText);
		formQuestion.add(new SubmitLink("addQuestions"){
			@Override
			public void onSubmit() {
				Questions questionProducts= TwoadwApp.get().getTwoadw().getWebsite().getQuestions();
				if(questionProducts.add(newQuestion)){
					setResponsePage(new ProductDetailsPage(product));
				} else {
					List<String> errorKeys = questionProducts.getErrors().getKeyList();
					for (String errorKey : errorKeys) {
						String errorMsg = LocalizedText.getErrorMessage(this,
								errorKey);
						formQuestion.error(errorMsg);
					
					}
				}
			}
			
		});
		//formQuestion.setVisible(edit);
		add(formQuestion);
	}

}
