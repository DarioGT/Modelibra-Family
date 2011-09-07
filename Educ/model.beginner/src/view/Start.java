package view;

import javax.swing.JApplet;

import model.PersonalLibrary;

@SuppressWarnings("serial")
public class Start extends JApplet {

	public static final int MAIN_FRAME_X = 0;
	public static final int MAIN_FRAME_Y = 0;

	public static boolean isApplet = true;

	private MainFrame mainFrame;

	public Start() {
		super();
	}

	public void init() {
		mainFrame = new MainFrame();
		mainFrame.setLocation(MAIN_FRAME_X, MAIN_FRAME_Y);
	}

	public void start() {
		mainFrame.setVisible(true);
	}

	public void stop() {
		mainFrame.setVisible(false);
	}

	public void destroy() {
		mainFrame.dispose();
	}

	public void setApplet(boolean applet) {
		isApplet = applet;
	}

	public static void main(String[] args) {
		PersonalLibrary.get().init();
		Start app = new Start();
		app.setApplet(false);
		app.init();
		app.start();
	}

}
