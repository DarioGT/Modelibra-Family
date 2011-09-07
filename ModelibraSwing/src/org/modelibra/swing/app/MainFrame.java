package org.modelibra.swing.app;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.modelibra.swing.widget.ModelibraFrame;

@SuppressWarnings("serial")
public class MainFrame extends ModelibraFrame {

	public static final int WIDTH = 240;
	public static final int HEIGHT = 80;
	public static final Dimension FRAME_SIZE = new Dimension(WIDTH, HEIGHT);

	private MainMenuBar mainMenuBar;

	public MainFrame(App app) {
		super(app);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setTitle(app.getNatLang().getText("product"));

		mainMenuBar = new MainMenuBar(this);
		setJMenuBar(mainMenuBar);

		setSize(FRAME_SIZE);
		// pack();
	}

	public MainMenuBar getMainMenuBar() {
		return mainMenuBar;
	}

	public void exit() {
		dispose();
		System.exit(0);
	}

}
