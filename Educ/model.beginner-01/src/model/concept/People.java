package model.concept;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class People extends ArrayList<Person> {
	
	@Override
	public boolean add(Person person) {
		if (person.getFirstName() == null || person.getLastName() == null) {
			throw new RuntimeException(
					"A person must have both first and last names.");
		}
		if (getPerson(person.getFirstName(), person.getLastName()) == null) {			
			super.add(person);
			return true;
		}
		return false;
	}

	public Person getPerson(String firstName, String lastName) {
		for (Person person : this) {
			if (person.getFirstName().equals(firstName)
					&& person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
	}

	public People getMembers() {
		People members = new People();
		for (Person member : this) {
			if (member.isMember()) {
				members.add(member);
			}
		}
		return members;
	}

	public People getNonMembers() {
		People nonMembers = new People();
		for (Person member : this) {
			if (!member.isMember()) {
				nonMembers.add(member);
			}
		}
		return nonMembers;
	}
	
	public People getMembersWithoutBooks() {
		People membersWithoutBooks = new People();
		for (Person member : this) {
			if (member.isMember() && member.isWithoutBooks()) {
				membersWithoutBooks.add(member);
			}
		}
		return membersWithoutBooks;
	}

}
