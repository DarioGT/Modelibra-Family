package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setTitle("Modelibra Beginner");
		setSize(new Dimension(300, 200));

		setJMenuBar(new MainMenuBar(this));

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		cp.add(northPanel, BorderLayout.NORTH);
		cp.add(southPanel, BorderLayout.SOUTH);
		cp.add(centerPanel, BorderLayout.CENTER);
	}

	void exit() {
		dispose();
		if (!Start.isApplet) {
			System.exit(0);
		}
	}

}
