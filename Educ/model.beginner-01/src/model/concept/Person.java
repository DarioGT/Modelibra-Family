package model.concept;

import model.PersonalLibrary;

public class Person {

	private String firstName; // id
	private String lastName; // id
	private int maxNumberOfBooks; // default

	private PersonalLibrary personalLibrary;
	private Books books; 

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		setMaxNumberOfBooks(1); 

		books = new Books();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return getLastName() + ", " + getFirstName();
	}

	public void setMaxNumberOfBooks(int maxNumberBooks) {
		this.maxNumberOfBooks = maxNumberBooks;
	}

	public int getMaxNumberOfBooks() {
		return maxNumberOfBooks;
	}

	private void setPersonalLibrary(PersonalLibrary personalLibrary) {
		this.personalLibrary = personalLibrary;
	}

	public PersonalLibrary getPersonalLibrary() {
		return personalLibrary;
	}

	public Books getBooks() {
		return books;
	}

	public boolean isMember() {
		if (getPersonalLibrary() == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isWithoutBooks() {
		if (getBooks().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean register(PersonalLibrary personalLibrary) {
		if (!isMember()) {
			if (isWithoutBooks()) {
				if (personalLibrary.getPeople().add(this)) {
					setPersonalLibrary(personalLibrary);
					return true;
				}
			}
		}
		return false;
	}

	public boolean unregister() {
		if (isMember()) {
			if (isWithoutBooks()) {
				if (getPersonalLibrary().getPeople().remove(this)) {
					setPersonalLibrary(null);
					return true;
				}
			}
		}
		return false;
	}

	public String toString() {
		return getName() + " (" + getMaxNumberOfBooks()
				+ " max. number of books)";
	}

}
