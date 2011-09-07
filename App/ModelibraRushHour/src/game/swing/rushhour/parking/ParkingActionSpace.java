package game.swing.rushhour.parking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * A tool bar with the Restart (parking) game.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class ParkingActionSpace extends JToolBar {

	private JButton restartButton = new JButton("Restart");

	private ParkingWindow parkingWindow;

	private class ParkingInit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			parkingWindow.initializeParkingSpace();
		}
	}

	public ParkingActionSpace(ParkingWindow parkingWindow) {
		super();

		restartButton.addActionListener(new ParkingInit());
		restartButton.setToolTipText("Initialize Parking Space");

		add(restartButton);
		addSeparator();

		setFloatable(false);

		this.parkingWindow = parkingWindow;
	}

	public ParkingWindow getParkingWindow() {
		return parkingWindow;
	}

}
