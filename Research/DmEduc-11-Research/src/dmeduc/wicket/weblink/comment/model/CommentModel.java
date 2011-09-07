package dmeduc.wicket.weblink.comment.model;

import modelibra.wicket.model.EntityModel;
import dmeduc.weblink.comment.Comment;
import dmeduc.weblink.comment.Comments;

@SuppressWarnings("serial")
public class CommentModel extends EntityModel {

	public CommentModel(Comment comment) {
		super(comment);
	}

	public CommentModel(Comment comment, Comments comments) {
		super(comment, comments);
	}

	public Comment getComment() {
		return (Comment) getEntity();
	}

	public Comments getComments() {
		return (Comments) getEntities();
	}

}
