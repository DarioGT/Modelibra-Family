package model.concept;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Books extends ArrayList<Book> {

	@Override
	public boolean add(Book book) {
		if (book.getTitle() == null) {
			throw new RuntimeException("A book must have a title.");
		}
		if (getBook(book.getTitle()) == null) {			
			super.add(book);
			return true;
		}
		return false;
	}

	public Book getBook(String title) {
		for (Book book : this) {
			if (book.getTitle().equals(title)) {
				return book;
			}
		}
		return null;
	}

	public Books getAvailableBooks() {
		Books availableBooks = new Books();
		for (Book book : this) {
			if (book.isAvailable()) {
				availableBooks.add(book);
			}
		}
		return availableBooks;
	}

	public Books getUnavailableBooks() {
		Books unavailableBooks = new Books();
		for (Book book : this) {
			if (!book.isAvailable()) {
				unavailableBooks.add(book);
			}
		}
		return unavailableBooks;
	}

	public Books getBooksByAuthor(String author) {
		Books booksByAuthor = new Books();
		for (Book book : this) {
			if (book.getAuthor() != null && book.getAuthor().equals(author)) {
				booksByAuthor.add(book);
			}
		}
		return booksByAuthor;
	}

}
