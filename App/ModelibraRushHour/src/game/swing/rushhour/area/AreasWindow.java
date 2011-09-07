package game.swing.rushhour.area;

import game.rushhour.RushHourDb;
import game.rushhour.area.Area;
import game.rushhour.area.Areas;
import game.swing.app.MenuBar;
import game.swing.rushhour.parking.ParkingsWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 * Main window. Displays all areas in a table. For a selected area, there is a
 * button to display its parkings in a different window.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class AreasWindow extends JFrame implements ListSelectionListener {

	private RushHourDb rushHourDb;

	private Areas areas;

	private Area currentArea;

	private MenuBar mainMenu = new MenuBar(this);

	private JTable areaTable;

	private int selectedAreaRow = -1;

	private AreaTableModel areaTableModel;

	private JPanel buttonPanel = new JPanel();

	private JButton parkingsButton = new JButton("Parkings");

	public AreasWindow() {
		super();

		rushHourDb = new RushHourDb();
		areas = rushHourDb.getRushHour().getAreas();
		areaTableModel = new AreaTableModel(areas);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		setSize(240, 320);
		setJMenuBar(mainMenu);

		areaTable = new JTable(areaTableModel);
		areaTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(areaTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(parkingsButton);
		parkingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentArea.getParkings().size() > 0) {
					displayParkingsWindow(currentArea);
				}
			}
		});
	}

	public RushHourDb getRushHourDb() {
		return rushHourDb;
	}

	public MenuBar getMainMenu() {
		return mainMenu;
	}

	private void displayParkingsWindow(Area area) {
		ParkingsWindow parkingsWindow = new ParkingsWindow(area);
		parkingsWindow.setTitle("Parkings");
		parkingsWindow.setLocation(20, 40);
		parkingsWindow.setVisible(true);
	}

	public void close() {
		rushHourDb.close();
		dispose();
		System.exit(0); // for application only
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			areaTable.setRowSelectionInterval(ix, ix);
			areaTable.scrollRectToVisible(areaTable.getCellRect(ix, 0, true));
			selectedAreaRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedAreaRow;
	}

	private int getRowCount() {
		return areaTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(areaTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			currentArea = areas.getArea(getSelectedRow());
		}
	}

	private class AreaTableModel extends AbstractTableModel {

		private static final int COLUMN_COUNT = 1;

		private Areas areas;

		public AreaTableModel(Areas areas) {
			super();
			this.areas = areas;
		}

		// implemented from TableModel
		public int getRowCount() {
			return areas.size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			Area area = areas.getArea(r);
			if (c == 0) {
				result = area.getCode();
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return "Area";
			}
			return "";
		}

		// overriden from AbstractTableModel
		public boolean isCellEditable(int r, int c) {
			return false;
		}

		// overriden from AbstractTableModel
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

	}

}