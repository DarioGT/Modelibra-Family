package dmeduc.wicket.weblink.comment;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.modelibra.wicket.concept.EntityAddFormPanel;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.security.AccessPoint;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dmeduc.DmEduc;
import dmeduc.weblink.WebLink;
import dmeduc.weblink.comment.Comments;
import dmeduc.weblink.member.Member;
import dmeduc.wicket.app.DmEducApp;

/**
 * Contact Us page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public class ContactUsPage extends DmPage {

	/**
	 * Constructs the Contact Us page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public ContactUsPage(final ViewModel viewModel, final View view) {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		DmEduc dmEduc = dmEducApp.getDmEduc();
		WebLink webLink = dmEduc.getWebLink();

		// Guest user
		if (!getAppSession().isUserSignedIn()) {
			Member guest = new Member(webLink);
			guest.setCode(AccessPoint.GUEST);
			guest.setPassword(AccessPoint.GUEST);
			getAppSession().authenticate(guest, AccessPoint.CODE,
					AccessPoint.PASSWORD);
		}

		// Contact Us
		add(new FeedbackPanel("contactUsFeedback"));

		ViewModel contactModel = new ViewModel(webLink);
		Comments comments = webLink.getComments();
		contactModel.setEntities(comments);

		View contactView = new View();
		contactView.setPage(this);
		contactView.setWicketId("contactUs");
		contactView.setContextView(view);
		add(new EntityAddFormPanel(contactModel, contactView));
	}

	/**
	 * Constructs a link to this page.
	 * 
	 * @param linkId
	 *            link Wicket id
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public static PageLink link(final String linkId, final ViewModel viewModel,
			final View view) {
		PageLink link = new PageLink(linkId, new IPageLink() {
			public Page getPage() {
				return new ContactUsPage(viewModel, view);
			}

			public Class<? extends Page> getPageIdentity() {
				return ContactUsPage.class;
			}
		});
		return link;
	}

}
