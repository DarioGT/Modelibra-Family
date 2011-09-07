package game.swing.rushhour.parking;

import game.rushhour.car.Car;
import game.rushhour.parking.CarSpace;
import game.rushhour.parking.Parking;
import game.swing.rushhour.car.Vehicle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * A view of the car space.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class VehicleSpace extends JPanel implements MouseListener {

	public static final int VEHICLE_SPACE_LENGTH = 60;

	public static final Dimension VEHICLE_SPACE_SIZE = new Dimension(
			VEHICLE_SPACE_LENGTH, VEHICLE_SPACE_LENGTH);

	public static final Color DEFAULT_SPACE_COLOR = Color.white;

	public static final Color SELECTION_SIGN_COLOR = Color.black;

	private ParkingSpace parkingSpace;

	private Vehicle vehicle;

	private CarSpace carSpace;

	private Color color;

	private boolean used;

	private boolean selected;

	private JPanel selectionSign = new JPanel();

	public VehicleSpace(ParkingSpace parkingSpace, int row, int column) {
		super();

		setSize(VEHICLE_SPACE_SIZE);
		setColor(DEFAULT_SPACE_COLOR);
		addMouseListener(this);

		selectionSign.setBackground(DEFAULT_SPACE_COLOR);
		add(selectionSign);

		this.parkingSpace = parkingSpace;
		setCarSpace(row, column);
	}

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public CarSpace getCarSpace() {
		return carSpace;
	}

	private void setCarSpace(int row, int column) {
		carSpace = new CarSpace(row, column);
	}

	public int getRow() {
		return carSpace.getRow();
	}

	public int getColumn() {
		return carSpace.getColumn();
	}

	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		display();
	}

	public void erase() {
		setColor(VehicleSpace.DEFAULT_SPACE_COLOR);
		setVehicle(null);
		setUsed(false);
		setSelected(false);
		selectionSign.setBackground(DEFAULT_SPACE_COLOR);
		repaint();
	}

	public void display() {
		if (vehicle != null) {
			if (selected) {
				selectionSign.setBackground(SELECTION_SIGN_COLOR);
			} else {
				selectionSign.setBackground(vehicle.getCar().getColor());
			}
			setColor(vehicle.getCar().getColor());
			repaint();
		}
	}

	public void mousePressed(MouseEvent e) {
		Vehicle selectedVehicle = null;
		Car car = null;
		CarSpace clickSpace = getCarSpace();
		Parking parking = getParkingSpace().getParking();
		if (parking == null) {
			return;
		}
		car = parking.getSelectedCar();
		selectedVehicle = parkingSpace.findVehicle(car);
		if (isUsed()) {
			if (selectedVehicle != null) {
				selectedVehicle.deselect();
			}
			vehicle.select();
			parking.setSelectedCar(getVehicle().getCar());
		} else {
			if (car != null) {
				if (car.getOrientation().equals("Horizontal")) {
					if (car.getRearPlusOneSpace().equals(clickSpace)) {
						selectedVehicle.moveRight();
					} else if (car.getHeadMinusOneSpace().equals(clickSpace)) {
						selectedVehicle.moveLeft();
					}
				} else if (car.getOrientation().equals("Vertical")) {
					if (car.getRearPlusOneSpace().equals(clickSpace)) {
						selectedVehicle.moveDown();
					} else if (car.getHeadMinusOneSpace().equals(clickSpace)) {
						selectedVehicle.moveUp();
					}
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}