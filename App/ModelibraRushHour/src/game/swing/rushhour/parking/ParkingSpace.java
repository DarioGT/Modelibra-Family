package game.swing.rushhour.parking;

import game.rushhour.car.Car;
import game.rushhour.car.Cars;
import game.rushhour.parking.Parking;
import game.swing.rushhour.car.Vehicle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * A view of the parking. Parking space that consists of vehicle spaces of the
 * same size.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class ParkingSpace extends JPanel {

	public static final int GRID_SIZE_IN_VEHICLE_SPACES = 6;

	public static final int GRID_LENGTH_IN_PIXELS = GRID_SIZE_IN_VEHICLE_SPACES
			* VehicleSpace.VEHICLE_SPACE_LENGTH;

	public static final Dimension GRID_SIZE_IN_PIXELS = new Dimension(
			GRID_LENGTH_IN_PIXELS, GRID_LENGTH_IN_PIXELS);

	public static final int GRID_GAP = 0;

	public static final int EXIT_ROW = 2;

	public static final int EXIT_COLUMN = 5;

	private ParkingWindow parkingWindow;

	private Parking parking;

	private VehicleSpace[][] vehicleSpaces = new VehicleSpace[GRID_SIZE_IN_VEHICLE_SPACES][GRID_SIZE_IN_VEHICLE_SPACES];

	private Vehicle[] vehicles;

	public ParkingSpace(ParkingWindow parkingWindow, Parking parking) {
		super();

		this.parkingWindow = parkingWindow;
		this.parking = parking;

		setLayout(new GridLayout(GRID_SIZE_IN_VEHICLE_SPACES,
				GRID_SIZE_IN_VEHICLE_SPACES, GRID_GAP, GRID_GAP));
		setPreferredSize(GRID_SIZE_IN_PIXELS);
		setBackground(Color.white);
		createVehicleSpaces();

		createVehicles();
		displayVehicles();
	}

	public ParkingWindow getParkingWindow() {
		return parkingWindow;
	}

	public Parking getParking() {
		return parking;
	}

	public VehicleSpace[][] getVehicleSpaces() {
		return vehicleSpaces;
	}

	public VehicleSpace getVehicleSpace(int aRow, int aColumn) {
		return vehicleSpaces[aRow][aColumn];
	}

	public Vehicle[] getVehicles() {
		return vehicles;
	}

	private void createVehicleSpaces() {
		VehicleSpace vehicleSpace = null;
		for (int row = 0; row < vehicleSpaces.length; row++) {
			for (int column = 0; column < vehicleSpaces.length; column++) {
				vehicleSpace = new VehicleSpace(this, row, column);
				vehicleSpaces[row][column] = vehicleSpace;
				this.add(vehicleSpace);
			}
		}
	}

	private void createVehicles() {
		Cars cars = parking.getCars();
		vehicles = new Vehicle[cars.size()];
		int i = 0;
		for (Car car : cars) {
			vehicles[i] = new Vehicle(this, car);
			i++;
		}
	}

	private void displayVehicles() {
		for (int i = 0; i < vehicles.length; i++) {
			vehicles[i].display();
		}
	}

	public VehicleSpace getNextVerticalVehicleSpace(VehicleSpace vehicleSpace) {
		int row = vehicleSpace.getRow();
		if (row == GRID_SIZE_IN_VEHICLE_SPACES - 1) {
			return null;
		} else {
			row++;
			return vehicleSpaces[row][vehicleSpace.getColumn()];
		}
	}

	public VehicleSpace getNextHorizontalVehicleSpace(VehicleSpace vehicleSpace) {
		int column = vehicleSpace.getColumn();
		if (column == GRID_SIZE_IN_VEHICLE_SPACES - 1) {
			return null;
		} else {
			column++;
			return vehicleSpaces[vehicleSpace.getRow()][column];
		}
	}

	private void erase() {
		parking.returnCarsToStartSpaces();
		parking.setSelectedCar(null);
		VehicleSpace vehicleSpace = null;
		for (int row = 0; row < vehicleSpaces.length; row++) {
			for (int column = 0; column < vehicleSpaces.length; column++) {
				vehicleSpace = vehicleSpaces[row][column];
				vehicleSpace.erase();
			}
		}
	}

	public void display() {
		if (parking != null) {
			erase();
			displayVehicles();
		}
	}

	public Vehicle findVehicle(Car car) {
		Vehicle vehicle = null;
		for (int i = 0; i < vehicles.length; i++) {
			vehicle = vehicles[i];
			if (vehicle.getCar() == car) {
				return vehicle;
			}
		}
		return null;
	}

}