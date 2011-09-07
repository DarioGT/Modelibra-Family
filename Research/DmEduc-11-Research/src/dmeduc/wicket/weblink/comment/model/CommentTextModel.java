package dmeduc.wicket.weblink.comment.model;

import java.util.ArrayList;
import java.util.List;

import modelibra.wicket.model.EntityPropertyModel;

import dmeduc.weblink.comment.Comment;

@SuppressWarnings("serial")
public class CommentTextModel extends CommentModel {

	public CommentTextModel(Comment comment) {
		super(comment);
	}

	@Override
	public List<EntityPropertyModel> getEntityPropertyModelList() {
		List<EntityPropertyModel> commentPropertyModels = new ArrayList<EntityPropertyModel>();
		EntityPropertyModel commentTextModel = new EntityPropertyModel(
				getComment(), "text");
		if (!isReadOnly()) {
			commentTextModel.setReadOnly(false);
		}
		commentPropertyModels.add(commentTextModel);
		return commentPropertyModels;
	}

}
