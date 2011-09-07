package dmeduc.wicket.weblink.question;

import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.EntityDisplayListPanel;
import org.modelibra.wicket.container.DmMenuPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.category.Categories;
import dmeduc.weblink.category.Category;
import dmeduc.weblink.question.Questions;
import dmeduc.wicket.app.DmEducApp;

/**
 * FAQ page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class FaqPage extends DmPage {

	/**
	 * Constructs the FAQ page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public FaqPage(final ViewModel viewModel, final View view) {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();

		// Menu
		View menuView = new View();
		menuView.setWicketId("faqMenu");
		menuView.setPage(this);
		menuView.setContextView(view);

		add(new DmMenuPanel(viewModel, menuView));

		// FAQ Category Questions
		ViewModel faqModel = new ViewModel(webLink);
		Categories categories = webLink.getCategories();
		Category faqCategory = categories.getCategoryByName("FAQ");
		if (faqCategory != null) {
			Questions faqCategoryQuestions = faqCategory.getQuestions();
			faqModel.setEntities(faqCategoryQuestions);
		}

		View faqView = new View();
		faqView.setPage(this);
		faqView.setWicketId("faqCategoryQuestionsList");

		Panel faq;
		if (faqCategory != null) {
			faq = new EntityDisplayListPanel(faqModel, faqView);
		} else {
			faq = new Panel("faqCategoryQuestionsList");
			faq.setVisible(false);
		}
		add(faq);
	}

}
