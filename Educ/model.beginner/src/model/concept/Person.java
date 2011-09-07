package model.concept;

import model.PersonalLibrary;

public class Person {

	private String firstName; // id
	private String lastName; // id
	private int maxNumberOfBooks; // default

	private Books books; 

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		setMaxNumberOfBooks(1); 

		books = new Books();
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Books getBooks() {
		return books;
	}

	public boolean isWithoutBooks() {
		if (getBooks().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return getName() + " (" + getMaxNumberOfBooks()
				+ " max. number of books)";
	}

}
