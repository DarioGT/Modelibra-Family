package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.PersonalLibrary;
import view.concept.BooksFrame;
import view.concept.PeopleFrame;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

	public static final int MAIN_FRAME_X = 0;
	public static final int MAIN_FRAME_Y = 0;
	public static final int PEOPLE_FRAME_WIDTH = 512;
	public static final int PEOPLE_FRAME_HEIGHT = 160;

	private JMenu menuFile;
	private JMenuItem menuFileExit;

	private JMenu menuModel;
	private JMenuItem menuBooks;
	private JMenuItem menuPeople;

	private JMenu menuHelp;
	private JMenuItem menuHelpAbout;

	private MainFrame mainFrame;

	public MainMenuBar(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		menuFile = new JMenu("File");
		menuFileExit = new JMenuItem("Exit");

		menuModel = new JMenu("Data");
		menuBooks = new JMenuItem("Books");
		menuPeople = new JMenuItem("People");

		menuHelp = new JMenu("Help");
		menuHelpAbout = new JMenuItem("About...");

		menuFileExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.exit();
			}
		});

		menuBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BooksFrame booksFrame = new BooksFrame(PersonalLibrary.get()
						.getBooks(), new Dimension(600, 400));
				displayDownRight(mainFrame, booksFrame);
			}
		});
		menuPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PeopleFrame peopleFrame = new PeopleFrame(PersonalLibrary.get()
						.getPeople(), new Dimension(600, 400));
				displayDownRight(mainFrame, peopleFrame);
			}
		});

		menuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCenter(mainFrame, new AboutDialog(mainFrame));
			}
		});

		menuFile.add(menuFileExit);
		menuModel.add(menuBooks);
		menuModel.add(menuPeople);
		menuHelp.add(menuHelpAbout);

		add(menuFile);
		add(menuModel);
		add(menuHelp);
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	private void displayCenter(JFrame parent, JDialog child) {
		Dimension childSize = child.getPreferredSize();
		Point childLocation = getCentralLocation(parent.getLocation(), parent
				.getSize(), childSize);
		child.setLocation(childLocation);
		child.setModal(true);
		child.setVisible(true);
	}

	private Point getCentralLocation(Point parentLocation,
			Dimension parentSize, Dimension childSize) {
		Point point = null;
		int x = ((parentSize.width - childSize.width) / 2) + parentLocation.x;
		int y = ((parentSize.height - childSize.height) / 2) + parentLocation.y;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		point = new Point(x, y);
		return point;
	}

	private void displayDownRight(JFrame parent, JFrame child) {
		Point parentLocation = parent.getLocation();
		double parentX = parentLocation.getX();
		double parentY = parentLocation.getY();

		int parentWidth = parent.getWidth();
		double childX = parentX + parentWidth;
		double childY = parentY + 20;

		Point childLocation = new Point();
		childLocation.setLocation(childX, childY);
		child.setLocation(childLocation);
		child.setVisible(true);
	}

}
