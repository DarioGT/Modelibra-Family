package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog implements ActionListener {

	public static final Color ABOUT_COLOR = Color.ORANGE;

	private JButton button;

	public AboutDialog(MainFrame mainFrame) {
		super(mainFrame);

		String about = "About";
		setTitle(about);
		setResizable(false);

		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel westPanel = new JPanel();
		JPanel eastPanel = new JPanel();

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(northPanel, BorderLayout.NORTH);
		cp.add(southPanel, BorderLayout.SOUTH);
		cp.add(centerPanel, BorderLayout.CENTER);
		cp.add(westPanel, BorderLayout.WEST);
		cp.add(eastPanel, BorderLayout.EAST);

		JPanel textPanel = new JPanel();

		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(textPanel, BorderLayout.SOUTH);

		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.setBackground(ABOUT_COLOR);
		JLabel label0 = new JLabel();
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		JLabel label4 = new JLabel();
		String modelibra = "Modelibra";
		String product = "Beginner";
		String version = "1.0";
		String date = "2009-03-15";
		String creator = "Dzenan Ridjanovic";
		label0.setText(modelibra);
		label1.setText(product);
		label2.setText(version);
		label3.setText(date);
		label4.setText(creator);
		textPanel.add(label0);
		textPanel.add(label1);
		textPanel.add(label2);
		textPanel.add(label3);
		textPanel.add(label4);

		southPanel.setLayout(new FlowLayout());
		String ok = "OK";
		button = new JButton(ok);
		southPanel.add(button);
		button.addActionListener(this);

		pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			setVisible(false);
			dispose();
		}
	}

}
