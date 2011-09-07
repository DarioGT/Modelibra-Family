package view.concept;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.PersonalLibrary;
import model.concept.Book;
import model.concept.Books;

@SuppressWarnings("serial")
public class BooksFrame extends JFrame implements ListSelectionListener {

	private Book currentBook;
	private JTable bookTable;
	private int selectedBookRow = -1;

	private JLabel messageLabel = new JLabel("");

	private Books books;

	public BooksFrame(final Books books, Dimension size) {
		this.books = books;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setSize(size);
		setTitle("Books");

		final BookTableModel bookTableModel = new BookTableModel();
		bookTable = new JTable(bookTableModel);
		bookTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JScrollPane jScrollPane = new JScrollPane(bookTable);
		JPanel messagePanel = new JPanel();
		cp.add(messagePanel, BorderLayout.NORTH);
		cp.add(jScrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		cp.add(buttonPanel, BorderLayout.SOUTH);

		messagePanel.add(messageLabel);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.WHITE);
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Remove");
		JButton checkInButton = new JButton("Check In");
		JButton checkOutButton = new JButton("Check Out");
		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book book = new Book("");
				clearMessage();
				if (books.getBook("") == null) {
					if (books.add(book)) {
						bookTableModel.fireTableDataChanged();
						int ix = books.size() - 1;
						setSelectedRow(ix);
					} else {
						setMessage("Not added.");
					}
				}
			}
		});
		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentBook != null) {
					clearMessage();
					if (books.remove(currentBook)) {
						int ix = getSelectedRow();
						bookTableModel.fireTableDataChanged();
						setSelectedRow(ix - 1);
					} else {
						setMessage("Not removed.");
					}
				}
			}
		});
		buttonPanel.add(checkInButton);
		checkInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentBook != null) {
					if (currentBook.checkIn()) {
						bookTableModel.fireTableDataChanged();
					} else {
						setMessage("Not checked in.");
					}
				}
			}
		});
		buttonPanel.add(checkOutButton);
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentBook != null && currentBook.isAvailable()) {
					PeopleFrame peopleFrame = new PeopleFrame(PersonalLibrary.get().getPeople(), 
							currentBook, BooksFrame.this, new Dimension(600, 400));
					displayDownRight(BooksFrame.this, peopleFrame);
				}
			}
		});
	}
	
	private void displayDownRight(JFrame parent, JFrame child) {
		Point parentLocation = parent.getLocation();
		double parentX = parentLocation.getX();
		double parentY = parentLocation.getY();
		
		int parentWidth = parent.getWidth();
		double childX = parentX + parentWidth;
		double childY = parentY + 20;

		Point childLocation = new Point();
		childLocation.setLocation(childX, childY);
		child.setLocation(childLocation);
		child.setVisible(true);
	}

	private void setMessage(String message) {
		messageLabel.setText(message);
	}

	private void clearMessage() {
		setMessage("");
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			bookTable.setRowSelectionInterval(ix, ix);
			bookTable.scrollRectToVisible(bookTable.getCellRect(ix, 0, true));
			selectedBookRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedBookRow;
	}

	private int getRowCount() {
		return bookTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(bookTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)
				&& (getSelectedRow() < getRowCount())) {
			Book book = books.get(getSelectedRow());
			if (book != currentBook) {
				currentBook = book;
			}
		}
	}

	private class BookTableModel extends AbstractTableModel {

		private static final int COLUMN_COUNT = 3;

		public BookTableModel() {
			super();
		}

		// implemented from TableModel
		public int getRowCount() {
			return books.size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			Book book = books.get(r);
			if (c == 0) {
				result = book.getTitle();
			} else if (c == 1) {
				result = book.getAuthor();
			} else if (c == 2) {
				result = book.isAvailable();
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			if (c == 0) {
				return "Title";
			} else if (c == 1) {
				return "Author";
			} else if (c == 2) {
				return "Available?";
			}
			return "";
		}

		@Override
		public boolean isCellEditable(int r, int c) {
			if (c == 2) {
				return false;
			}
			return true;
		}

		@Override
		public Class<?> getColumnClass(int c) {
			if (getValueAt(0, c) != null) {
				return getValueAt(0, c).getClass();
			}
			return String.class;
		}

		@Override
		public void setValueAt(Object value, int r, int c) {
			Book book = books.get(r);
			if (c == 0) {
				book.setTitle((String) value);
			} else if (c == 1) {
				book.setAuthor((String) value);
			}
		}

	}

	public void exit() {
		dispose();
	}
	
	public void refresh() {
		((AbstractTableModel) bookTable.getModel()).fireTableDataChanged();
	}

}