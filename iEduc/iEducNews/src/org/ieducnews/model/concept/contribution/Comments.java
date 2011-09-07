package org.ieducnews.model.concept.contribution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.ieducnews.model.type.EasyDate;

public class Comments implements Serializable {

	private static final long serialVersionUID = 1;

	private List<Comment> commentsList = new ArrayList<Comment>();

	public boolean add(Comment comment) {
		if (comment.getText() == null) {
			return false;
		}
		return commentsList.add(comment);
	}

	public boolean remove(Comment comment) {
		return commentsList.remove(comment);
	}

	public boolean contains(Comment comment) {
		return commentsList.contains(comment);
	}

	public int size() {
		return commentsList.size();
	}

	public boolean isEmpty() {
		return commentsList.isEmpty();
	}

	public List<Comment> getList() {
		return new ArrayList<Comment>(commentsList);
	}

	private void setList(List<Comment> list) {
		commentsList = list;
	}
	
	public Comment retrieveByKeyword(String keyword) {
		for (Comment comment : commentsList) {
			if (comment.getText().contains(keyword)) {
				return comment;
			}
		}
		return null;
	}

	public Comments selectByDate(Date date) {
		Comments selectedComments = new Comments();
		for (Comment comment : commentsList) {
			Date creationDate = comment.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			if (easyCreationDate.equals(date)) {
				selectedComments.add(comment);
			}
		}
		return selectedComments;
	}

	public Comments selectByYear(int year) {
		Comments selectedComments = new Comments();
		for (Comment comment : commentsList) {
			Date creationDate = comment.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			if (easyCreationDate.getYear() == year) {
				selectedComments.add(comment);
			}
		}
		return selectedComments;
	}

	public Comments selectByMonth(int year, int month) {
		Comments selectedComments = new Comments();
		for (Comment comment : commentsList) {
			Date creationDate = comment.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			if (easyCreationDate.getYear() == year
					&& easyCreationDate.getMonth() == month) {
				selectedComments.add(comment);
			}
		}
		return selectedComments;
	}

	public Comments selectByYesterday(Date date) {
		Comments selectedComments = new Comments();
		for (Comment comment : commentsList) {
			Date creationDate = comment.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			EasyDate easyDate = new EasyDate(date);
			Date yesterday = easyDate.getYesterday();
			if (easyCreationDate.equals(yesterday)) {
				selectedComments.add(comment);
			}
		}
		return selectedComments;
	}

	public Comments orderByCreationDate(boolean ascending) {
		Comments orderedComments = new Comments();
		List<Comment> list = getList();
		Collections.sort(list, CREATION_DATE_COMPARATOR);
		if (!ascending) {
			Collections.reverse(list);
		}
		orderedComments.setList(list);
		return orderedComments;
	}

	public Comments orderByCreationDate() {
		return orderByCreationDate(true);
	}

	private static final Comparator<Comment> CREATION_DATE_COMPARATOR = new CreationDateComparator();

	private static class CreationDateComparator implements Comparator<Comment> {
		public int compare(Comment comment1, Comment comment2) {
			return comment1.getCreationDate().compareTo(
					comment2.getCreationDate());
		}
	}

	public void output(String title) {
		System.out.println(title);
		for (Comment comment : commentsList) {
			comment.output();
		}
	}

	public void outputWithEasyDate(String title) {
		System.out.println(title);
		for (Comment comment : commentsList) {
			comment.outputWithEasyDate();
		}
	}

}
