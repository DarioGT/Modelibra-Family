package org.modelibra.modeler.app.context;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.gen.ConvertMySQLToInnoDB;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public class ConvertMySQLToInnoDBFrame extends DataSourceFrame {
	
	static final long serialVersionUID = 7168319479760000130L;

	private JButton generationButton = new JButton(Para.getPara().getText(
			"convert"));

	public ConvertMySQLToInnoDBFrame() {
		super();
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void init() throws Exception {
		this.setTitle(Para.getPara().getText("convertMySQLToInnoDB"));

		southPanel.add(generationButton);
		generationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action("convertMySQL");
			}
		});

		this.pack();
	}

	protected void action(String a) {
		super.action(a);
		if (a.equals("convertMySQL"))
			ConvertMySQLToInnoDB.getSingleton().convert();
	}
}
