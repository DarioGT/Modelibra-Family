package game.swing.app;

import game.swing.rushhour.area.AreasWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * About dmLite Rush Hour.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-09-19
 */
public class About extends JDialog implements ActionListener {

	private String product = " Modelibra Rush Hour based on Rush Hour by ThinkFun";

	private String version = " Version 2.0";

	private String date = " January 2008";

	private String author = " Dzenan Ridjanovic";

	private JPanel northPanel = new JPanel();

	private JPanel southPanel = new JPanel();

	private JPanel centerPanel = new JPanel();

	private JPanel westPanel = new JPanel();

	private JPanel eastPanel = new JPanel();

	private JLabel label1 = new JLabel();

	private JLabel label2 = new JLabel();

	private JLabel label3 = new JLabel();

	private JLabel label4 = new JLabel();

	private JButton button = new JButton("OK");

	private BorderLayout borderLayout = new BorderLayout();

	private GridLayout gridLayout = new GridLayout();

	public About(AreasWindow aParent) {
		super(aParent); // different from the default constructor
		try {
			init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pack();
	}

	private void init() throws Exception {
		setTitle("About Modelibra Rush Hour");
		setResizable(false);

		Container cp = this.getContentPane();
		cp.setLayout(borderLayout);
		cp.add(northPanel, "North");
		cp.add(southPanel, "South");
		cp.add(centerPanel, "Center");
		cp.add(westPanel, "West");
		cp.add(eastPanel, "East");

		northPanel.setBackground(Color.blue);
		southPanel.setBackground(Color.yellow);
		westPanel.setBackground(Color.green);
		eastPanel.setBackground(Color.red);

		centerPanel.setLayout(gridLayout);
		centerPanel.setBackground(Color.orange);
		gridLayout.setRows(4);
		gridLayout.setColumns(1);
		label1.setText(product);
		label2.setText(version);
		label3.setText(date);
		label4.setText(author);
		centerPanel.add(label1);
		centerPanel.add(label2);
		centerPanel.add(label3);
		centerPanel.add(label4);

		southPanel.add(button);
		button.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			dispose();
		}
	}

}
