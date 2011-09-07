package model.concept;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class BookTest {

	@Test
	public void bookCreated() {
		Book book = new Book("War and Peace");
		assertEquals("War and Peace", book.getTitle());
		assertNull(book.getAuthor());
	}

	@Test
	public void bookLoaned() {
		Book book = new Book("War and Peace");
		Person person = new Person("Dzenan", "Ridjanovic");
		book.checkOutTo(person);
		assertNotNull("Book is not loaned to a person.", book.getPerson());
	}

}
