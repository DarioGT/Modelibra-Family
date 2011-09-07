package game.swing.rushhour.parking;

import game.rushhour.parking.Parking;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A window with the parking and its tool bar.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class ParkingWindow extends JFrame {

	private JPanel parkingBoard;

	private ParkingActionSpace parkingActionSpace = new ParkingActionSpace(this);

	private ParkingSpace parkingSpace;

	private Parking parking;

	public ParkingWindow(Parking parking) {
		super();

		this.parking = parking;

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});

		parkingSpace = new ParkingSpace(this, parking);

		parkingBoard = new JPanel();
		parkingBoard.setLayout(new BorderLayout());
		parkingBoard.add(parkingSpace, "North");
		parkingBoard.add(parkingActionSpace, "South");

		setContentPane(parkingBoard);
		pack();
	}

	public Parking getParking() {
		return parking;
	}

	public void initializeParkingSpace() {
		parkingSpace.display();
	}

	public void close() {
		dispose();
	}

}