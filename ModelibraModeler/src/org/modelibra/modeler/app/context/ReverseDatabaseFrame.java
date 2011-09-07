package org.modelibra.modeler.app.context;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.gen.ReverseDatabase;
import org.modelibra.modeler.model.DiagramModel;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public class ReverseDatabaseFrame extends DataSourceFrame {
	
	static final long serialVersionUID = 7168319479760000340L;

	private DiagramModel diagramModel;

	private JButton generationButton = new JButton(Para.getPara().getText(
			"generation"));

	public ReverseDatabaseFrame(DiagramModel diagramModel) {
		super();
		this.diagramModel = diagramModel;
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void init() throws Exception {
		this.setTitle(Para.getPara().getText("reverseDb"));

		southPanel.add(generationButton);
		generationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action("reverse");
			}
		});

		this.pack();
	}

	protected void action(String a) {
		super.action(a);
		if (a.equals("reverse"))
			ReverseDatabase.getSingleton().reverseDatabase(diagramModel,
					getDbms());
	}
}