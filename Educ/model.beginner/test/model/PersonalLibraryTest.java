package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.concept.Book;
import model.concept.Books;
import model.concept.People;
import model.concept.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonalLibraryTest {

	private static PersonalLibrary personalLibrary;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personalLibrary = PersonalLibrary.get();
		Books books = personalLibrary.getBooks();
		books.add(new Book("War and Peace"));
		books.add(new Book("Great Expectations"));
		books.add(new Book("People of the Book"));
		People people = personalLibrary.getPeople();
		people.add(new Person("Robert", "Brown"));
		people.add(new Person("Tom", "Jacobs"));
	}

	@Before
	public void setUpBefore() throws Exception {
		Books books = personalLibrary.getBooks();
		books.add(new Book("War and Peace"));
		books.add(new Book("Great Expectations"));
		books.add(new Book("People of the Book"));
		People people = personalLibrary.getPeople();
		people.add(new Person("Robert", "Brown"));
		people.add(new Person("Tom", "Jacobs"));
	}

	@Test
	public void personalLibraryCreated() {
		PersonalLibrary personalLibrary = PersonalLibrary.get();
		assertEquals("My Library", personalLibrary.getName());
	}

	@Test
	public void personalLibraryStarted() {
		Books books = personalLibrary.getBooks();
		People people = personalLibrary.getPeople();

		Book book1 = books.getBook("War and Peace");
		Book book2 = books.getBook("Great Expectations");
		Person person1 = people.getPerson("Robert", "Brown");
		Person person2 = people.getPerson("Tom", "Jacobs");

		assertEquals(3, personalLibrary.getBooks().size());
		assertEquals(2, personalLibrary.getPeople().size());

		assertEquals(0, personalLibrary.getBooks().indexOf(book1));
		assertEquals(1, personalLibrary.getBooks().indexOf(book2));

		assertEquals(0, personalLibrary.getPeople().indexOf(person1));
		assertEquals(1, personalLibrary.getPeople().indexOf(person2));

		books.remove(book1);
		assertEquals(2, personalLibrary.getBooks().size());
		assertEquals(0, personalLibrary.getBooks().indexOf(book2));
	}

	@Test
	public void checkBooks() {
		Books books = personalLibrary.getBooks();
		People people = personalLibrary.getPeople();

		Book book1 = books.getBook("War and Peace");
		Book book2 = books.getBook("Great Expectations");
		Book book3 = books.getBook("People of the Book");
		Person person1 = people.getPerson("Robert", "Brown");
		Person person2 = people.getPerson("Tom", "Jacobs");

		assertTrue("Book check out failed.", book1.checkOutTo(person2));
		assertEquals(1, person2.getBooks().size());
		assertEquals("Jacobs, Tom", book1.getPerson().getName());
		assertFalse("Book has already been checked out.", book1
				.checkOutTo(person1));
		assertFalse("By default, a person cannot check out more than 1 book.",
				book2.checkOutTo(person2));
		assertTrue("Book check in failed.", book1.checkIn());
		assertEquals(0, person2.getBooks().size());
		assertFalse("Book has already been checked in.", book1.checkIn());
		assertFalse("Book was never checked out.", book2.checkIn());

		assertTrue("Book check out failed.", book2.checkOutTo(person2));
		assertEquals(1, person2.getBooks().size());
		person2.setMaxNumberOfBooks(2);
		assertTrue("Book check out failed.", book3.checkOutTo(person2));
		assertEquals(2, person2.getBooks().size());
		assertFalse("This person cannot check out more than 2 books.", book1
				.checkOutTo(person2));
		assertTrue("Book check in failed.", book3.checkIn());
		assertEquals(1, person2.getBooks().size());
		assertTrue("Book check in failed.", book2.checkIn());
		assertEquals(0, person2.getBooks().size());
	}

	@Test
	public void availableBooks() {
		Books books = personalLibrary.getBooks();
		People people = personalLibrary.getPeople();

		Book book1 = books.getBook("War and Peace");
		Book book2 = books.getBook("Great Expectations");
		Book book3 = books.getBook("People of the Book");
		Person person1 = people.getPerson("Robert", "Brown");
		Person person2 = people.getPerson("Tom", "Jacobs");

		assertEquals(3, personalLibrary.getBooks().getAvailableBooks().size());
		assertTrue("Book check out failed.", book1.checkOutTo(person1));
		assertEquals(2, personalLibrary.getBooks().getAvailableBooks().size());
		assertTrue("Book check out failed.", book2.checkOutTo(person2));
		assertEquals(1, personalLibrary.getBooks().getAvailableBooks().size());
		person2.setMaxNumberOfBooks(2);
		assertTrue("Book check out failed.", book3.checkOutTo(person2));
		assertEquals(0, personalLibrary.getBooks().getAvailableBooks().size());
		assertTrue("Book check in failed.", book1.checkIn());
		assertEquals(1, personalLibrary.getBooks().getAvailableBooks().size());
	}

	@After
	public void setUpAfter() throws Exception {
		Books books = personalLibrary.getBooks();
		books.removeAll(books);
		People people = personalLibrary.getPeople();
		people.removeAll(people);
	}

}
