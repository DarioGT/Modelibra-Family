package modelibra.swing.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelibra.swing.app.config.Para;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class About extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(About.class);

	public static final Color ABOUT_COLOR = Color.LIGHT_GRAY;
	private static final String IMAGE_PATH = "image/modelibra.jpg";

	private String aboutTitle = Para.getOne().getText("aboutTitle");
	private String ok = Para.getOne().getText("ok");
	private String modelibra = Para.getOne().getText("modelibra");
	private String product = Para.getOne().getText("product");
	private String version = Para.getOne().getText("version");
	private String date = Para.getOne().getText("date");
	private String author = Para.getOne().getText("creator");

	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel eastPanel = new JPanel();

	private JPanel imagePanel = new JPanel();
	private JPanel textPanel = new JPanel();

	private ImageIcon imageIcon = Para.getOne().getImageIcon(IMAGE_PATH);

	private JLabel label0 = new JLabel();
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JLabel label3 = new JLabel();
	private JLabel label4 = new JLabel();
	private JLabel imageLabel = new JLabel(imageIcon);

	private JButton button = new JButton(ok);

	private BorderLayout borderLayout1 = new BorderLayout();
	private BorderLayout borderLayout2 = new BorderLayout();
	private GridLayout gridLayout = new GridLayout();
	private FlowLayout flowLayout = new FlowLayout();

	public About(JFrame parent) {
		super(parent);
		try {
			setTitle(aboutTitle);
			setResizable(false);

			Container cp = this.getContentPane();
			cp.setLayout(borderLayout1);
			cp.add(northPanel, BorderLayout.NORTH);
			cp.add(southPanel, BorderLayout.SOUTH);
			cp.add(centerPanel, BorderLayout.CENTER);
			cp.add(westPanel, BorderLayout.WEST);
			cp.add(eastPanel, BorderLayout.EAST);

			centerPanel.setLayout(borderLayout2);
			centerPanel.add(imagePanel, BorderLayout.NORTH);
			centerPanel.add(textPanel, BorderLayout.SOUTH);

			imagePanel.setBackground(ABOUT_COLOR);
			imagePanel.add(imageLabel);

			textPanel.setLayout(gridLayout);
			textPanel.setBackground(ABOUT_COLOR);
			gridLayout.setRows(5);
			gridLayout.setColumns(1);
			label0.setText(modelibra);
			label1.setText(product);
			label2.setText(version);
			label3.setText(date);
			label4.setText(author);
			textPanel.add(label0);
			textPanel.add(label1);
			textPanel.add(label2);
			textPanel.add(label3);
			textPanel.add(label4);

			southPanel.setLayout(flowLayout);
			southPanel.add(button);
			button.addActionListener(this);
		} catch (Exception e) {
			log.error("Error in About.constructor: " + e.getMessage());
		}
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			setVisible(false);
			dispose();
		}
	}

}
