package model.concept;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class People extends ArrayList<Person> {

	public Person getPerson(String firstName, String lastName) {
		for (Person person : this) {
			if (person.getFirstName().equals(firstName)
					&& person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
	}
	
	public People getPeopleWithoutBooks() {
		People peopleWithoutBooks = new People();
		for (Person person : this) {
			if (person.isWithoutBooks()) {
				peopleWithoutBooks.add(person);
			}
		}
		return peopleWithoutBooks;
	}

}
