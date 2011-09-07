package game.swing.rushhour.parking;

import game.rushhour.area.Area;
import game.rushhour.parking.Parking;
import game.rushhour.parking.Parkings;

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
 * Displays all parkings of the same area in a table. For a selected parking, there is a
 * button to display it in a different window.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class ParkingsWindow extends JFrame implements ListSelectionListener {

	private Area area;

	private Parkings parkings;

	private Parking currentParking;

	private JTable parkingTable;

	private int selectedParkingRow = -1;

	private ParkingTableModel parkingTableModel;

	private JPanel buttonPanel = new JPanel();

	private JButton parkingButton = new JButton("Parking");

	public ParkingsWindow(Area area) {
		super();

		this.area = area;
		parkings = area.getParkings();
		parkingTableModel = new ParkingTableModel(parkings);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		setSize(240, 320);

		parkingTable = new JTable(parkingTableModel);
		parkingTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(parkingTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(parkingButton);
		parkingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayParkingWindow(currentParking);
			}
		});
	}

	private void displayParkingWindow(Parking parking) {
		ParkingWindow parkingWindow = new ParkingWindow(parking);
		parkingWindow.setTitle("Area: " + area.getCode() + " -- " + "Parking: "
				+ parking.getNumber());
		parkingWindow.setLocation(40, 80);
		parkingWindow.setVisible(true);
	}

	private void close() {
		dispose();
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			parkingTable.setRowSelectionInterval(ix, ix);
			parkingTable.scrollRectToVisible(parkingTable.getCellRect(ix, 0,
					true));
			selectedParkingRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedParkingRow;
	}

	private int getRowCount() {
		return parkingTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(parkingTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			currentParking = parkings.getParking(getSelectedRow() + 1);
		}
	}

	private class ParkingTableModel extends AbstractTableModel {

		private static final int COLUMN_COUNT = 2;

		private Parkings parkings;

		public ParkingTableModel(Parkings parkings) {
			super();
			this.parkings = parkings;
		}

		// implemented from TableModel
		public int getRowCount() {
			return parkings.size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			Parking parking = parkings.getParking(r + 1);
			if (c == 0) {
				result = area.getCode();
			} else if (c == 1) {
				result = parking.getNumber();
			}
			return result;
		}

		// overriden from AbstractTableModel
		public String getColumnName(int c) {
			if (c == 0) {
				return "Area";
			} else if (c == 1) {
				return "Parking";
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