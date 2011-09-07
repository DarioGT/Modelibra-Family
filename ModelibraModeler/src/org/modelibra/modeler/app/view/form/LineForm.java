package org.modelibra.modeler.app.view.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.action.History;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-07
 */
public class LineForm extends JFrame implements Observer {
	
	static final long serialVersionUID = 7168319479760000270L;

	private JPanel dataPanel = new JPanel();

	private JLabel dir12Label = new JLabel(Para.getPara().getText("dir") + "12");

	private JLabel dir12NameLabel = new JLabel(Para.getPara().getText("name"));

	private JLabel dir12MinLabel = new JLabel(Para.getPara().getText("min"));

	private JLabel dir12MaxLabel = new JLabel(Para.getPara().getText("max"));

	private JLabel dir12InsertRuleLabel = new JLabel(Para.getPara().getText(
			"insert"));

	private JLabel dir12DeleteRuleLabel = new JLabel(Para.getPara().getText(
			"delete"));

	private JLabel dir12UpdateRuleLabel = new JLabel(Para.getPara().getText(
			"update"));

	private JLabel dir21Label = new JLabel(Para.getPara().getText("dir") + "21");

	private JLabel dir21NameLabel = new JLabel(Para.getPara().getText("name"));

	private JLabel dir21MinLabel = new JLabel(Para.getPara().getText("min"));

	private JLabel dir21MaxLabel = new JLabel(Para.getPara().getText("max"));

	private JLabel dir21InsertRuleLabel = new JLabel(Para.getPara().getText(
			"insert"));

	private JLabel dir21DeleteRuleLabel = new JLabel(Para.getPara().getText(
			"delete"));

	private JLabel dir21UpdateRuleLabel = new JLabel(Para.getPara().getText(
			"update"));

	private JLabel empty12Label = new JLabel(" ");

	private JLabel empty21Label = new JLabel(" ");

	private JTextField dir12NameField = new JTextField();

	private JTextField dir12MinField = new JTextField();

	private JTextField dir12MaxField = new JTextField();

	private JTextField dir21NameField = new JTextField();

	private JTextField dir21MinField = new JTextField();

	private JTextField dir21MaxField = new JTextField();

	private JTextField dir12InsertRuleField = new JTextField();

	private JTextField dir12DeleteRuleField = new JTextField();

	private JTextField dir12UpdateRuleField = new JTextField();

	private JTextField dir21InsertRuleField = new JTextField();

	private JTextField dir21DeleteRuleField = new JTextField();

	private JTextField dir21UpdateRuleField = new JTextField();

	private JPanel buttonPanel = new JPanel();

	private JButton updateButton = new JButton(Para.getPara().getText("update"));

	private LineModel lineModel;

	public LineForm(LineModel aLineModel) {
		super();
		try {
			lineModel = aLineModel;
			this.installObservers();
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.pack();
	}

	private void init() throws Exception {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		this.setTitle(Para.getPara().getText("line"));

		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		cp.add(dataPanel, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		dataPanel.setLayout(new GridLayout(14, 2));

		dataPanel.add(dir12Label);
		dataPanel.add(empty12Label);
		dataPanel.add(dir12NameLabel);
		dataPanel.add(dir12NameField);
		dataPanel.add(dir12MinLabel);
		dataPanel.add(dir12MinField);
		dataPanel.add(dir12MaxLabel);
		dataPanel.add(dir12MaxField);
		dataPanel.add(dir12InsertRuleLabel);
		dataPanel.add(dir12InsertRuleField);
		dataPanel.add(dir12DeleteRuleLabel);
		dataPanel.add(dir12DeleteRuleField);
		dataPanel.add(dir12UpdateRuleLabel);
		dataPanel.add(dir12UpdateRuleField);

		dataPanel.add(dir21Label);
		dataPanel.add(empty21Label);
		dataPanel.add(dir21NameLabel);
		dataPanel.add(dir21NameField);
		dataPanel.add(dir21MinLabel);
		dataPanel.add(dir21MinField);
		dataPanel.add(dir21MaxLabel);
		dataPanel.add(dir21MaxField);
		dataPanel.add(dir21InsertRuleLabel);
		dataPanel.add(dir21InsertRuleField);
		dataPanel.add(dir21DeleteRuleLabel);
		dataPanel.add(dir21DeleteRuleField);
		dataPanel.add(dir21UpdateRuleLabel);
		dataPanel.add(dir21UpdateRuleField);

		this.updateFields();

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lineModel != null) {
					Manager.getSingleton()
							.startTransaction("update line rules"); // Transaction
					lineModel.setDir12Name(dir12NameField.getText());
					lineModel.setDir12Min(dir12MinField.getText());
					lineModel.setDir12Max(dir12MaxField.getText());
					lineModel
							.setDir12InsertRule(dir12InsertRuleField.getText());
					lineModel
							.setDir12DeleteRule(dir12DeleteRuleField.getText());
					lineModel
							.setDir12UpdateRule(dir12UpdateRuleField.getText());
					lineModel.setDir21Name(dir21NameField.getText());
					lineModel.setDir21Min(dir21MinField.getText());
					lineModel.setDir21Max(dir21MaxField.getText());
					lineModel
							.setDir21InsertRule(dir21InsertRuleField.getText());
					lineModel
							.setDir21DeleteRule(dir21DeleteRuleField.getText());
					lineModel
							.setDir21UpdateRule(dir21UpdateRuleField.getText());
					Manager.getSingleton().commit(); // Transaction
														// ------------------------------
				}
			}
		});
	}

	private void installObservers() {
		if (lineModel != null) {
			lineModel.getDiagramModel().addObserver(this);
			lineModel.addObserver(this);
		}
	}

	private void deinstallObservers() {
		if (lineModel != null) {
			lineModel.deleteObserver(this);
			lineModel.getDiagramModel().deleteObserver(this);
		}
	}

	public LineModel getLineModel() {
		return lineModel;
	}

	public void setEntityModel(LineModel aLineModel) {
		if (lineModel != null) {
			lineModel.deleteObserver(this);
		}
		this.emptyFields();
		lineModel = aLineModel;
		if (lineModel != null) {
			lineModel.addObserver(this);
			this.updateFields();
		}
	}

	private void updateFields() {
		dir12NameField.setText(lineModel.getDir12Name());
		dir12MinField.setText(lineModel.getDir12Min());
		dir12MaxField.setText(lineModel.getDir12Max());
		dir12InsertRuleField.setText(lineModel.getDir12InsertRule());
		dir12DeleteRuleField.setText(lineModel.getDir12DeleteRule());
		dir12UpdateRuleField.setText(lineModel.getDir12UpdateRule());

		dir21NameField.setText(lineModel.getDir21Name());
		dir21MinField.setText(lineModel.getDir21Min());
		dir21MaxField.setText(lineModel.getDir21Max());
		dir21InsertRuleField.setText(lineModel.getDir21InsertRule());
		dir21DeleteRuleField.setText(lineModel.getDir21DeleteRule());
		dir21UpdateRuleField.setText(lineModel.getDir21UpdateRule());
	}

	private void emptyFields() {
		dir12NameField.setText("");
		dir12MinField.setText("");
		dir12MaxField.setText("");
		dir12InsertRuleField.setText("");
		dir12DeleteRuleField.setText("");
		dir12UpdateRuleField.setText("");

		dir21NameField.setText("");
		dir21MinField.setText("");
		dir21MaxField.setText("");
		dir21InsertRuleField.setText("");
		dir21DeleteRuleField.setText("");
		dir21UpdateRuleField.setText("");
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		ModelEvent modelEvent = (ModelEvent) arg;
		String opType = modelEvent.getOpType();
		// String propertyName = modelEvent.getPropertyName();
		Object opArgument = modelEvent.getOpArgument();

		if ((o instanceof LineModel) && (opType.equals("set"))) {
			this.updateFields();
		} else if ((o instanceof DiagramModel) && (opType.equals("add"))
				&& (opArgument instanceof LineModel)) {
			LineModel lineModel = (LineModel) opArgument;
			this.setEntityModel(lineModel);
		} else if ((o instanceof DiagramModel) && (opType.equals("remove"))
				&& (opArgument instanceof LineModel)) {
			this.setEntityModel(null);
		}
		
		exit();
	}

	public void exit() {
		History.getSingleton().reset();
		this.deinstallObservers();
		this.dispose();
	}

}
