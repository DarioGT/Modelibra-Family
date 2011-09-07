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

import model.concept.Book;
import model.concept.People;
import model.concept.Person;

@SuppressWarnings("serial")
public class PeopleFrame extends JFrame implements ListSelectionListener {

	private Person currentPerson;
	private JTable personTable;
	private int selectedPersonRow = -1;

	private JLabel messageLabel = new JLabel("");
	
	private JPanel buttonPanel = new JPanel();

	private People people;
	
	public PeopleFrame(People people, final Book bookToCheckOut, final BooksFrame booksFrame, Dimension size) {
		this(people, size);
		JButton checkOutButton = new JButton("Check Out Selection");
		buttonPanel.add(checkOutButton);
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPerson != null) {
					if (bookToCheckOut.checkOutTo(currentPerson)) {
						booksFrame.refresh();
						exit();
					} else {
						setMessage("Not checked out.");
					}
				}
			}
		});
	}

	public PeopleFrame(final People people, Dimension size) {
		this.people = people;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setSize(size);
		setTitle("People");

		final PersonTableModel personTableModel = new PersonTableModel();
		personTable = new JTable(personTableModel);
		personTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JScrollPane jScrollPane = new JScrollPane(personTable);
		JPanel messagePanel = new JPanel();
		cp.add(messagePanel, BorderLayout.NORTH);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		messagePanel.add(messageLabel);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.WHITE);
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Remove");
		JButton booksButton = new JButton("Books");
		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person person = new Person("", "");
				clearMessage();
				if (people.getPerson("", "") == null) {
					if (people.add(person)) {
						personTableModel.fireTableDataChanged();
						int ix = people.size() - 1;
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
				if (currentPerson != null) {
					clearMessage();
					if (people.remove(currentPerson)) {
						int ix = getSelectedRow();
						personTableModel.fireTableDataChanged();
						setSelectedRow(ix - 1);
					} else {
						setMessage("Not removed.");
					}
				}
			}
		});
		buttonPanel.add(booksButton);
		booksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPerson != null) {
					BooksFrame booksFrame = new BooksFrame(currentPerson
							.getBooks(), new Dimension(600, 400));
					displayDownRight(PeopleFrame.this, booksFrame);
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
			personTable.setRowSelectionInterval(ix, ix);
			personTable.scrollRectToVisible(personTable
					.getCellRect(ix, 0, true));
			selectedPersonRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedPersonRow;
	}

	private int getRowCount() {
		return personTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(personTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)
				&& (getSelectedRow() < getRowCount())) {
			Person person = people.get(getSelectedRow());
			if (person != currentPerson) {
				currentPerson = person;
			}
		}
	}

	private class PersonTableModel extends AbstractTableModel {

		private static final int COLUMN_COUNT = 3;

		public PersonTableModel() {
			super();
		}

		// implemented from TableModel
		public int getRowCount() {
			return people.size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			Person person = people.get(r);
			if (c == 0) {
				result = person.getFirstName();
			} else if (c == 1) {
				result = person.getLastName();
			} else if (c == 2) {
				result = person.getMaxNumberOfBooks();
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			if (c == 0) {
				return "First name";
			} else if (c == 1) {
				return "Last name";
			} else if (c == 2) {
				return "Max. number of books";
			}
			return "";
		}

		@Override
		public boolean isCellEditable(int r, int c) {
			return true;
		}

		@Override
		public Class<?> getColumnClass(int c) {
			if (getValueAt(0, c) != null) {
				return getValueAt(0, c).getClass();
			} else if (c == 2) {
				return Integer.class;
			}
			return String.class;
		}

		@Override
		public void setValueAt(Object value, int r, int c) {
			Person person = people.get(r);
			if (c == 0) {
				person.setFirstName((String) value);
			} else if (c == 1) {
				person.setLastName((String) value);
			} else if (c == 2) {
				person.setMaxNumberOfBooks((Integer) value);
			}
		}

	}

	public void exit() {
		dispose();
	}

}