package org.ieducnews.model.concept.member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Votes implements Serializable {

	private static final long serialVersionUID = 1;

	private List<Vote> votesList = new ArrayList<Vote>();

	public boolean add(Vote vote) {
		return votesList.add(vote);
	}

	public boolean remove(Vote vote) {
		return votesList.remove(vote);
	}

	public boolean contains(Vote vote) {
		return votesList.contains(vote);
	}

	public int size() {
		return votesList.size();
	}

	public boolean isEmpty() {
		return votesList.isEmpty();
	}

	public List<Vote> getList() {
		return new ArrayList<Vote>(votesList);
	}

	public Votes selectByUp(boolean up) {
		Votes selectedVotes = new Votes();
		for (Vote vote : votesList) {
			if (vote.getUp().equals(up)) {
				selectedVotes.add(vote);
			}
		}
		return selectedVotes;
	}

	public void output(String title) {
		System.out.println(title);
		for (Vote vote : votesList) {
			vote.output();
		}
	}

}
