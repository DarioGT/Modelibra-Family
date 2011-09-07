package org.modelibra.modeler.app.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.modelibra.modeler.app.pref.Para;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-12
 */
public class AboutApp extends JDialog {

	static final long serialVersionUID = 7168319479760000010L;

	private static final String IMAGE = "img/aboutMagic.gif";

	private JPanel imagePanel = new JPanel();

	private JPanel textPanel = new JPanel();

	private ImageIcon imageIcon = Para.getPara().getImageIconSiblingToClassFile(IMAGE);

	private JLabel label0 = new JLabel();

	private JLabel label1 = new JLabel();

	private JLabel label2 = new JLabel();

	private JLabel label3 = new JLabel();

	private JLabel label4 = new JLabel();

	private JLabel imageLabel = new JLabel(imageIcon);

	public AboutApp(AppFrame parent) {
		super(parent);
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.pack();
	}

	private void init() throws Exception {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setTitle(Para.getPara().getText("aboutTitle"));
		// this.setResizable(false); while on, produces error when About...
		// chosen!

		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(imagePanel, BorderLayout.NORTH);
		cp.add(textPanel, BorderLayout.SOUTH);

		imagePanel.setBorder(BorderFactory.createLineBorder(Color.orange, 8));
		imagePanel.setBackground(Para.BACKGROUND_COLOR);
		imagePanel.add(imageLabel);

		textPanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 8));
		textPanel.setLayout(new GridLayout(5, 1));
		textPanel.setBackground(Color.orange);
		label0.setText(Para.getPara().getText("empty"));
		label1.setText(Para.getPara().getText("product"));
		label2.setText(Para.getPara().getText("version"));
		label3.setText(Para.getPara().getText("date"));
		label4.setText(Para.getPara().getText("authors"));
		textPanel.add(label0);
		textPanel.add(label1);
		textPanel.add(label2);
		textPanel.add(label3);
		textPanel.add(label4);
	}

}
