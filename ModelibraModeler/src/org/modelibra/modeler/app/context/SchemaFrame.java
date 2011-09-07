package org.modelibra.modeler.app.context;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.gen.Schema;
import org.modelibra.modeler.model.DiagramModel;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public class SchemaFrame extends DataSourceFrame {
	
	static final long serialVersionUID = 7168319479760000350L;

	private DiagramModel diagramModel;

	private JButton createButton = new JButton(Para.getPara().getText("create"));

	private JButton dropButton = new JButton(Para.getPara().getText("drop"));

	public SchemaFrame(DiagramModel diagramModel) {
		super();
		this.diagramModel = diagramModel;
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void init() throws Exception {
		this.setTitle(Para.getPara().getText("schema"));

		southPanel.add(createButton);
		southPanel.add(dropButton);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action("create");
			}
		});
		dropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action("drop");
			}
		});

		this.pack();
	}

	protected void action(String a) {
		super.action(a);
		if (a.equals("create"))
			Schema.getSingleton().genCreateSchema(diagramModel, getDbms());
		else if (a.equals("drop")) {
			if (getDbms().equals("MySQL"))
				Schema.getSingleton().genDropSchema(diagramModel, true);
			else
				Schema.getSingleton().genDropSchema(diagramModel, true);
		}
	}

}