package game.swing.app;

import game.swing.rushhour.area.AreasWindow;

import javax.swing.JApplet;

/**
 * Applet or application.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-09-19
 */
public class Start extends JApplet {

	AreasWindow areasWindow;

	public void init() {
		areasWindow = new AreasWindow();
	}

	public void start() {
		areasWindow.setTitle("Modelibra Rush Hour");
		areasWindow.setVisible(true);
	}

	public void stop() {
		areasWindow.setVisible(false);
	}

	public void destroy() {
		areasWindow.dispose();
	}

	public static void main(String[] args) {
		Start app = new Start();
		app.init();
		app.start();
	}

}
