package model.concept;

public class Book {

	private String title; // id
	private String author;

	private Person person;

	public Book(String title) {
		this.title = title;
	}

	public void setTitle(String title) {
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

	private void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public boolean isAvailable() {
		if (getPerson() == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkOutTo(Person person) {
		if (isAvailable()) {
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

	public String toString() {
		return getTitle() + " by " + getAuthor();
	}

}
