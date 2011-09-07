package model;

import model.concept.Book;
import model.concept.Books;
import model.concept.People;
import model.concept.Person;

public class PersonalLibrary {

	private String name;

	private Books books;
	private People people;

	public PersonalLibrary(String name) {
		if (name == null) {
			throw new RuntimeException("A library must have a name.");
		}
		this.name = name;
		books = new Books();
		people = new People();
	}

	public String getName() {
		return name;
	}

	public Books getBooks() {
		return books;
	}

	public People getPeople() {
		return people;
	}

	public String toString() {
		return getName() + ": " + getBooks().size() + " books; "
				+ getPeople().size() + " people.";
	}

	private void printStatus() {
		System.out.println("--- Status Report of PersonalLibrary \n"
				+ toString() + " ---");
		for (Book book : getBooks()) {
			System.out.println(book);
		}
		for (Person person : getPeople()) {
			int count = person.getBooks().size();
			System.out.println(person + " (has " + count + " of my books)");
		}
		System.out.println("Books Available: "
				+ getBooks().getAvailableBooks().size());
		System.out.println("--- End of Status Report ---");
		System.out.println("");
	}

	public static void main(String[] args) {
		PersonalLibrary personalLibrary = new PersonalLibrary("My Library");
		Book warAndPeace = new Book("War And Peace");
		Book greatExpectations = new Book("Great Expectations");
		warAndPeace.setAuthor("Tolstoy");
		greatExpectations.setAuthor("Dickens");
		Person jimReed = new Person("Jim", "Reed");
		Person sueLee = new Person("Sue", "Lee");
		warAndPeace.catalog(personalLibrary);
		greatExpectations.catalog(personalLibrary);
		jimReed.register(personalLibrary);
		sueLee.register(personalLibrary);

		personalLibrary.printStatus();
		System.out.println("*** Checked out War And Peace to Sue Lee");
		warAndPeace.checkOutTo(sueLee);
		personalLibrary.printStatus();
		System.out.println("*** Checked in War And Peace");
		warAndPeace.checkIn();
		personalLibrary.printStatus();
		System.out.println("*** Checked out Great Expectations to Jim Reed");
		greatExpectations.checkOutTo(jimReed);
		personalLibrary.printStatus();
	}

}
