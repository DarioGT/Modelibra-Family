package dmeduc.wicket.weblink;

import modelibra.wicket.component.EntityFormPanel;
import modelibra.wicket.component.EntityListPanel;
import modelibra.wicket.component.EntityListWithPropertyNamesPanel;
import modelibra.wicket.component.EntityPanel;
import modelibra.wicket.component.EntityTablePanel;
import modelibra.wicket.component.widget.PropertyLabelPanel;
import modelibra.wicket.model.EntitiesModel;
import modelibra.wicket.model.EntityModel;
import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.LabelPanel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.comment.Comment;
import dmeduc.weblink.member.Member;
import dmeduc.wicket.app.DmEducApp;
import dmeduc.wicket.weblink.comment.model.CommentModel;
import dmeduc.wicket.weblink.comment.model.CommentTextModel;

public class ShowCasePage extends WebPage {
	public ShowCasePage() {
		// prepare common data
		DmEducApp dmEducApp = (DmEducApp) Application.get();
		WebLink webLink = dmEducApp.getDmEduc().getWebLink();
		Member member = webLink.getMembers().first();
		// comment with text longer than 48 to be used for shorttext
		Comment comment = webLink.getComments().getComment(1194622721907L);

		// (1) Current way in ModelibraWicket
		ViewModel memberViewModel = new ViewModel(webLink);
		View memberView = new View();
		memberView.setWicketId("memberLabelPanel");
		memberViewModel.setEntity(member);
		memberViewModel.setPropertyCode("lastName");
		add(new LabelPanel(memberViewModel, memberView));

		// (2) with EntityPropertyModel
		EntityPropertyModel model = new EntityPropertyModel(member, "lastName");
		add(new PropertyLabelPanel("memberPanel", model));

		// it can even be used with pure wicket components, like Label, since
		// logic is moved from component to model
		add(new Label("memberLabel", model));

		// one can pass another model (i.e. detachable), that wraps IEntity
		// instance, instead of IEntity, to EntityPropertyModel constructor.
		IModel memberModel = new Model(member);
		EntityPropertyModel modelAroundModel = new EntityPropertyModel(
				memberModel, "lastName");
		add(new Label("memberModelLabel", modelAroundModel));

		// short text
		// (1) Current way in ModelibraWicket
		ViewModel commentViewModel = new ViewModel(webLink);
		View commentView = new View();
		commentView.setWicketId("commentLabelPanel");
		commentView.getUserProperties().addUserProperty("shortText", true);
		commentViewModel.setEntity(comment);
		commentViewModel.setPropertyCode("text");
		add(new LabelPanel(commentViewModel, commentView));

		// (2) with EntityPropertyModel
		EntityPropertyModel commentTextModel = new EntityPropertyModel(comment,
				"text");
		commentTextModel.setShortText(true);
		add(new PropertyLabelPanel("commentTextPanel", commentTextModel));

		// same effect on wicket component since logic is moved from component
		// to model
		add(new Label("commentLabel", commentTextModel));

		// Comment entity panel
		EntityModel commentEntityModel = new EntityModel(comment);
		add(new EntityPanel("commentEntityPanel", commentEntityModel));

		// Comment with read-only text model
		CommentTextModel commentWithReadOnlyTextModel = new CommentTextModel(
				comment);
		add(new EntityPanel("commentWithReadOnlyTextPanel",
				commentWithReadOnlyTextModel));

		// Comment with text model
		CommentTextModel commentWithTextModel = new CommentTextModel(comment);
		commentWithTextModel.setReadOnly(false);
		add(new EntityPanel("commentWithTextPanel", commentWithTextModel));

		// Comment update form with comment model
		CommentModel commentUpdateModel = new CommentModel(comment.copy(), webLink
				.getComments());
		commentUpdateModel.setReadOnly(false);
		add(new EntityFormPanel("commentUpdateForm", commentUpdateModel));
		
		// Comment add form with comment model
		CommentModel commentAddModel = new CommentModel(new Comment(webLink), webLink
				.getComments());
		commentAddModel.setReadOnly(false);
		commentAddModel.setAdd(true);
		add(new EntityFormPanel("commentAddForm", commentAddModel));

		// Comment list
		add(new EntityListPanel("commentList", new EntitiesModel(webLink
				.getComments())));

		// Comment list with property names
		add(new EntityListWithPropertyNamesPanel(
				"commentListWithPropertyNames", new EntitiesModel(webLink
						.getComments())));

		// Comment table
		add(new EntityTablePanel("commentTable", new EntitiesModel(webLink
				.getComments())));
	}

}
