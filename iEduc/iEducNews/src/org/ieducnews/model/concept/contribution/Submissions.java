package org.ieducnews.model.concept.contribution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.ieducnews.model.type.EasyDate;

public class Submissions implements Serializable {

	private static final long serialVersionUID = 1;

	private List<Submission> submissionsList = new ArrayList<Submission>();

	public boolean add(Submission submission) {
		if (submission.getName() == null) {
			return false;
		} else {
			Submission retrievedSubmission = retrieveByName(submission
					.getName());
			if (retrievedSubmission != null) {
				return false;
			}
		}
		if (submission.isWebLink()) {
			WebLink webLink = (WebLink) submission;
			if (webLink.getLink() == null) {
				return false;
			}
		} else if (submission.isQuestion()) {
			Question question = (Question) submission;
			if (question.getText() == null) {
				return false;
			}
		}
		return submissionsList.add(submission);
	}

	public boolean remove(Submission submission) {
		return submissionsList.remove(submission);
	}

	public boolean remove(String name) {
		for (Submission submission : submissionsList) {
			if (submission.getName().equals(name)) {
				return remove(submission);
			}
		}
		return false;
	}

	public boolean contains(Submission submission) {
		return submissionsList.contains(submission);
	}

	public boolean contains(String name) {
		for (Submission submission : submissionsList) {
			if (submission.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return submissionsList.size();
	}

	public boolean isEmpty() {
		return submissionsList.isEmpty();
	}

	public List<Submission> getList() {
		return new ArrayList<Submission>(submissionsList);
	}

	private void setList(List<Submission> list) {
		submissionsList = list;
	}

	public Submission retrieveByName(String name) {
		for (Submission submission : submissionsList) {
			if (submission.getName().equals(name)) {
				return submission;
			}
		}
		return null;
	}

	public Submissions selectByDate(Date date) {
		Submissions selectedSubmissions = new Submissions();
		for (Submission submission : submissionsList) {
			Date creationDate = submission.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			if (easyCreationDate.equals(date)) {
				selectedSubmissions.add(submission);
			}
		}
		return selectedSubmissions;
	}

	public Submissions selectByYear(int year) {
		Submissions selectedSubmissions = new Submissions();
		for (Submission submission : submissionsList) {
			Date creationDate = submission.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			if (easyCreationDate.getYear() == year) {
				selectedSubmissions.add(submission);
			}
		}
		return selectedSubmissions;
	}

	public Submissions selectByMonth(int year, int month) {
		Submissions selectedSubmissions = new Submissions();
		for (Submission submission : submissionsList) {
			Date creationDate = submission.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			if (easyCreationDate.getYear() == year
					&& easyCreationDate.getMonth() == month) {
				selectedSubmissions.add(submission);
			}
		}
		return selectedSubmissions;
	}

	public Submissions selectByYesterday(Date date) {
		Submissions selectedSubmissions = new Submissions();
		for (Submission submission : submissionsList) {
			Date creationDate = submission.getCreationDate();
			EasyDate easyCreationDate = new EasyDate(creationDate);
			EasyDate easyDate = new EasyDate(date);
			Date yesterday = easyDate.getYesterday();
			if (easyCreationDate.equals(yesterday)) {
				selectedSubmissions.add(submission);
			}
		}
		return selectedSubmissions;
	}

	public Submissions orderByName(boolean ascending) {
		Submissions orderedSubmissions = new Submissions();
		List<Submission> list = getList();
		Collections.sort(list, NAME_COMPARATOR);
		if (!ascending) {
			Collections.reverse(list);
		}
		orderedSubmissions.setList(list);
		return orderedSubmissions;
	}

	public Submissions orderByName() {
		return orderByName(true);
	}

	public Submissions orderByCreationDate(boolean ascending) {
		Submissions orderedSubmissions = new Submissions();
		List<Submission> list = getList();
		Collections.sort(list, CREATION_DATE_COMPARATOR);
		if (!ascending) {
			Collections.reverse(list);
		}
		orderedSubmissions.setList(list);
		return orderedSubmissions;
	}

	public Submissions orderByCreationDate() {
		return orderByCreationDate(true);
	}

	private static final Comparator<Submission> NAME_COMPARATOR = new NameComparator();

	private static final Comparator<Submission> CREATION_DATE_COMPARATOR = new CreationDateComparator();

	private static class NameComparator implements Comparator<Submission> {
		public int compare(Submission submission1, Submission submission2) {
			return submission1.getName().compareTo(submission2.getName());
		}
	}

	private static class CreationDateComparator implements
			Comparator<Submission> {
		public int compare(Submission submission1, Submission submission2) {
			return submission1.getCreationDate().compareTo(
					submission2.getCreationDate());
		}
	}

	public void output(String title) {
		System.out.println(title);
		for (Submission submission : submissionsList) {
			submission.output();
		}
	}

	public void outputWithEasyDate(String title) {
		System.out.println(title);
		for (Submission submission : submissionsList) {
			submission.outputWithEasyDate();
		}
	}

}
