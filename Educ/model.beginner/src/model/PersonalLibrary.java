package model;

import model.concept.Book;
import model.concept.Books;
import model.concept.People;
import model.concept.Person;

public class PersonalLibrary {

	private String name;

	private Books books;
	private People people;
	
	private static PersonalLibrary personalLibrary;

	private PersonalLibrary(String name) {
		if (name == null) {
			throw new RuntimeException("A library must have a name.");
		}
		this.name = name;
		books = new Books();
		people = new People();
	}
	
	public static PersonalLibrary get() {
		if (personalLibrary == null) {
			personalLibrary = new PersonalLibrary("My Library");
		}
		return personalLibrary;
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
	
	public void init() {
		Book warAndPeace = new Book("War And Peace");
		Book greatExpectations = new Book("Great Expectations");
		warAndPeace.setAuthor("Tolstoy");
		greatExpectations.setAuthor("Dickens");	
		Person jimReed = new Person("Jim", "Reed");
		Person sueLee = new Person("Sue", "Lee");
		books.add(warAndPeace);
		books.add(greatExpectations);
		people.add(jimReed);
		people.add(sueLee);

		printStatus();
		System.out.println("*** Checked out War And Peace to Sue Lee");
		warAndPeace.checkOutTo(sueLee);
		printStatus();
		System.out.println("*** Checked in War And Peace");
		warAndPeace.checkIn();
		printStatus();
		System.out.println("*** Checked out Great Expectations to Jim Reed");
		greatExpectations.checkOutTo(jimReed);
		printStatus();
	}

	public static void main(String[] args) {
		PersonalLibrary personalLibrary = PersonalLibrary.get();
		personalLibrary.init();
	}

}
