package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.concept.Book;
import model.concept.Person;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonalLibraryTest {

	private static PersonalLibrary personalLibrary;
	private static Book book1;
	private static Book book2;
	private static Book book3;
	private static Person person1;
	private static Person person2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personalLibrary = new PersonalLibrary("My Library");
		book1 = new Book("War and Peace");
		book2 = new Book("Great Expectations");
		book3 = new Book("People of the Book");
		person1 = new Person("Robert", "Brown");
		person2 = new Person("Tom", "Jacobs");
	}

	@Before
	public void setUp() throws Exception {
		book1.catalog(personalLibrary);
		book2.catalog(personalLibrary);
		book3.catalog(personalLibrary);
		person1.register(personalLibrary);
		person2.register(personalLibrary);
	}

	@Test
	public void personalLibraryCreated() {
		PersonalLibrary personalLibrary = new PersonalLibrary("My Library");
		assertEquals("My Library", personalLibrary.getName());
		assertEquals(0, personalLibrary.getBooks().size());
		assertEquals(0, personalLibrary.getPeople().size());
	}

	@Test
	public void personalLibraryStarted() {
		assertEquals(3, personalLibrary.getBooks().size());
		assertEquals(2, personalLibrary.getPeople().size());

		assertEquals(0, personalLibrary.getBooks().indexOf(book1));
		assertEquals(1, personalLibrary.getBooks().indexOf(book2));

		assertEquals(0, personalLibrary.getPeople().indexOf(person1));
		assertEquals(1, personalLibrary.getPeople().indexOf(person2));

		book1.discard();
		assertEquals(2, personalLibrary.getBooks().size());
		assertEquals(0, personalLibrary.getBooks().indexOf(book2));
	}

	@Test
	public void checkBooks() {
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

}
