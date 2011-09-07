package org.ieducnews.model.concept.member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.ieducnews.model.util.CompositeComparator;

public class Members implements Serializable {

	private static final long serialVersionUID = 1;

	private List<Member> membersList = new ArrayList<Member>();

	public boolean add(Member member) {
		if (member.getAccount() == null) {
			return false;
		} else {
			Member retrievedMember = retrieveByAccount(member.getAccount());
			if (retrievedMember != null) {
				return false;
			}
		}
		return membersList.add(member);
	}

	public boolean remove(Member member) {
		return membersList.remove(member);
	}

	public boolean remove(String account) {
		for (Member member : membersList) {
			if (member.getAccount().equals(account)) {
				return remove(member);
			}
		}
		return false;
	}

	public boolean contains(Member member) {
		return membersList.contains(member);
	}

	public boolean contains(String account) {
		for (Member member : membersList) {
			if (member.getAccount().equals(account)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return membersList.size();
	}

	public boolean isEmpty() {
		return membersList.isEmpty();
	}

	public List<Member> getList() {
		return new ArrayList<Member>(membersList);
	}

	private void setList(List<Member> list) {
		membersList = list;
	}

	public Member retrieveByAccount(String account) {
		for (Member member : membersList) {
			if (member.getAccount().equals(account)) {
				return member;
			}
		}
		return null;
	}

	public Members selectByApproved(boolean approved) {
		Members selectedMembers = new Members();
		for (Member member : membersList) {
			if (member.getApproved().equals(approved)) {
				selectedMembers.add(member);
			}
		}
		return selectedMembers;
	}

	public Members selectByRole(Member.SecurityRole role) {
		Members selectedMembers = new Members();
		for (Member member : membersList) {
			if (member.getRole().equals(role)) {
				selectedMembers.add(member);
			}
		}
		return selectedMembers;
	}

	public Members orderByAccount(boolean ascending) {
		Members orderedMembers = new Members();
		List<Member> list = getList();
		Collections.sort(list, new AccountComparator());
		if (!ascending) {
			Collections.reverse(list);
		}
		orderedMembers.setList(list);
		return orderedMembers;
	}

	public Members orderByAccount() {
		return orderByAccount(true);
	}

	public Members orderByLastFirstName(boolean ascending) {
		Members orderedMembers = new Members();
		List<Member> list = getList();
		CompositeComparator compositeComparator = new CompositeComparator();
		Collections.sort(list, compositeComparator.createComparator(
				new LastNameComparator(), new FirstNameComparator()));
		if (!ascending) {
			Collections.reverse(list);
		}
		orderedMembers.setList(list);
		return orderedMembers;
	}

	public Members orderByVote(boolean ascending) {
		Members orderedMembers = new Members();
		List<Member> list = getList();
		Collections.sort(list, new KarmaComparator());
		if (!ascending) {
			Collections.reverse(list);
		}
		orderedMembers.setList(list);
		return orderedMembers;
	}

	public Members orderByVote() {
		return orderByVote(true);
	}

	public Members orderByLastFirstName() {
		return orderByLastFirstName(true);
	}

	private class AccountComparator implements Comparator<Member> {
		public int compare(Member member1, Member member2) {
			return member1.getAccount().compareTo(member2.getAccount());
		}
	}

	private class LastNameComparator implements Comparator<Member> {
		public int compare(Member member1, Member member2) {
			return member1.getLastName().compareTo(member2.getLastName());
		}
	}

	private class FirstNameComparator implements Comparator<Member> {
		public int compare(Member member1, Member member2) {
			return member1.getFirstName().compareTo(member2.getFirstName());
		}
	}

	private class KarmaComparator implements Comparator<Member> {
		public int compare(Member member1, Member member2) {
			return member1.getKarma().compareTo(member2.getKarma());
		}
	}

	public void output(String title) {
		System.out.println(title);
		for (Member member : membersList) {
			member.output();
		}
	}

}
