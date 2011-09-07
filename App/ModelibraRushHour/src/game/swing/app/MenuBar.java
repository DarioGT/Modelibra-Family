package game.swing.app;

import game.swing.rushhour.area.AreasWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Main menu bar used in the main window (AreasWindow).
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-21
 */
public class MenuBar extends JMenuBar implements ActionListener {

	private static final int EXIT = 0;

	private static final int ABOUT = 200;

	private static final int RULES = 201;

	private AreasWindow areasWindow;

	private JMenu game = new JMenu("Game");

	private JMenuItem gameExit = new JMenuItem("Exit");

	private JMenu help = new JMenu("Help");

	private JMenuItem helpAbout = new JMenuItem("About...");

	private JMenuItem helpRules = new JMenuItem("Rules...");

	public MenuBar(AreasWindow areasWindow) {
		super();
		this.areasWindow = areasWindow;
		init();
	}

	public AreasWindow getMainWindow() {
		return areasWindow;
	}

	private void init() {
		initMenus();
		initMenuItems();
	}

	private void initMenus() {
		game.add(gameExit);

		help.add(helpAbout);
		help.add(helpRules);

		add(game);
		add(help);
	}

	private void initMenuItems() {
		gameExit.setActionCommand("Exit");
		gameExit.addActionListener(this);

		helpAbout.setActionCommand("About");
		helpAbout.addActionListener(this);

		helpRules.setActionCommand("Rules");
		helpRules.addActionListener(this);
	}

	private int getMenuCommand(String aCommand) {
		int action = -1;
		if (aCommand.equals("Exit"))
			action = EXIT;
		else if (aCommand.equals("About"))
			action = ABOUT;
		else if (aCommand.equals("Rules"))
			action = RULES;
		return action;
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (this.getMenuCommand(command)) {
		case EXIT:
			areasWindow.close();
			break;
		case ABOUT:
			displayAbout();
			break;
		case RULES:
			displayRules();
			break;
		}
	}

	private void displayAbout() {
		About about = new About(areasWindow);
		about.setLocation(40, 120);
		about.setVisible(true);
	}

	private void displayRules() {
		Rules rules = new Rules(areasWindow);
		rules.setLocation(60, 120);
		rules.setVisible(true);
	}

}