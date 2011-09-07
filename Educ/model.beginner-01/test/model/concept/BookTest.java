package model.concept;

import static org.junit.Assert.assertEquals;
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
		assertNull("Book is loaned to a person that is not a member.", book
				.getPerson());
	}

}
