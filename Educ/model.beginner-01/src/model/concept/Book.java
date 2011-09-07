package model.concept;

import model.PersonalLibrary;

public class Book {

	private String title; // id
	private String author;

	private PersonalLibrary personalLibrary;
	private Person person;

	public Book(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	private void setPersonalLibrary(PersonalLibrary personalLibrary) {
		this.personalLibrary = personalLibrary;
	}

	public PersonalLibrary getPersonalLibrary() {
		return personalLibrary;
	}

	private void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public boolean isCataloged() {
		if (getPersonalLibrary() != null) {
			if (getPersonalLibrary().getBooks().getBook(getTitle()) != null) {
				return true;
			}
		}
		return false;
	}

	public boolean isAvailable() {
		if (getPerson() == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkOutTo(Person person) {
		if (isCataloged() && isAvailable() && person.isMember()) {
			if (person.getBooks().size() < person.getMaxNumberOfBooks()) {
				if (person.getBooks().add(this)) {
					setPerson(person);
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkIn() {
		if (!isAvailable()) {
			if (getPerson().getBooks().remove(this)) {
				setPerson(null);
				return true;
			}
		}
		return false;
	}

	public boolean catalog(PersonalLibrary personalLibrary) {
		if (!isCataloged()) {
			if (isAvailable()) {
				if (personalLibrary.getBooks().add(this)) {
					setPersonalLibrary(personalLibrary);
					return true;
				}
			}
		}
		return false;
	}

	public boolean discard() {
		if (isCataloged()) {
			if (isAvailable()) {
				if (getPersonalLibrary().getBooks().remove(this)) {
					setPersonalLibrary(null);
					return true;
				}
			}
		}
		return false;
	}

	public String toString() {
		return getTitle() + " by " + getAuthor();
	}

}
