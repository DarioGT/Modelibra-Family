package org.modelibra.modeler.app.context;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.gen.Schema;
import org.modelibra.modeler.sql.Query;
import org.modelibra.modeler.sql.QueryTable;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-24
 */
public class SQLFrame extends DataSourceFrame {

	static final long serialVersionUID = 7168319479760000360L;

	private static final Point POINT = new Point(0, 0);

	private static int lastChoice = 0;

	private int sqlCount = 0;

	private String[] sqlCommands;

	private String[] sqlExamples;

	private JComboBox sqlChoice = new JComboBox();

	private JPanel sqlPanel = new JPanel();

	private JPanel choicePanel = new JPanel();

	private JLabel sqlLabel = new JLabel(Para.getPara().getText("example"));

	private JButton clearButton = new JButton(Para.getPara().getText("clear"));

	private JButton restoreButton = new JButton(Para.getPara().getText(
			"restore"));

	private JTextArea sqlArea = new JTextArea(16, 64);

	private JScrollPane scrollPane;

	private JButton sqlButton = new JButton(Para.getPara().getText("execute"));

	public SQLFrame() {
		super();
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void init() throws Exception {
		obtainSqlConfigProperties();

		this.setTitle("SQL");

		sqlArea.setLineWrap(true);
		sqlArea.setEditable(true);

		if (sqlCount > 16)
			sqlChoice.setMaximumRowCount(16);
		else
			sqlChoice.setMaximumRowCount(sqlCount);
		sqlChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String choice = (String) sqlChoice.getSelectedItem();
				lastChoice = sqlChoice.getSelectedIndex();
				for (int i = 0; i < sqlCount; i++)
					if (choice.equals(sqlCommands[i])) {
						sqlArea.setText(sqlExamples[i]);
						break;
					}
			}
		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlArea.setText("");
			}
		});
		restoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSql(sqlExamples[lastChoice]);
			}
		});

		scrollPane = new JScrollPane(sqlArea);

		choicePanel.setBackground(Para.BACKGROUND_COLOR);
		choicePanel.add(sqlLabel);
		choicePanel.add(sqlChoice);
		choicePanel.add(clearButton);
		choicePanel.add(restoreButton);

		sqlPanel.setLayout(new BorderLayout());
		sqlPanel.add(choicePanel, BorderLayout.NORTH);
		sqlPanel.add(scrollPane, BorderLayout.SOUTH);

		dataPanel.add(sqlPanel, BorderLayout.SOUTH);

		southPanel.add(sqlButton);
		sqlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action("sql");
			}
		});

		sqlChoice.setSelectedIndex(lastChoice);
		sqlArea.setText(sqlExamples[lastChoice]);

		this.pack();
	}

	protected void action(String a) {
		super.action(a);
		if (a.equals("sql")) {
			Connection con = Schema.getSingleton().getConnection();
			if (con == null)
				return;
			String sql = sqlArea.getText();
			Query query = new Query(con, sql);
			if (!query.isSuccess()) {
				this.informUser(Para.getPara().getText("sqlError"));
			} else if (!query.isSelect()) {
				this.informUser(Para.getPara().getText("success"));
			} else {
				if (query.isEmpty()) {
					this.informUser(Para.getPara().getText("emptyResult"));
				} else {
					QueryTable queryTable = new QueryTable(this, query);
					queryTable.setLocation(POINT);
					queryTable.setVisible(true);
				}
			}
		}
	}

	private void obtainSqlConfigProperties() {
		String countString = Config.getConfig().getProperty("sql.count");
		if (countString != null) {
			try {
				Integer countInteger = new Integer(countString);
				sqlCount = countInteger.intValue();
			} catch (NumberFormatException e) {
				System.out.println("SQL count property must be integer: "
						+ e.getMessage());
			}
		}
		if (sqlCount > 0) {
			sqlCommands = new String[sqlCount];
			sqlExamples = new String[sqlCount];
			String sqlCommandProperty;
			String sqlExampleProperty;
			for (int i = 0; i < sqlCount; i++) {
				sqlCommandProperty = i + ".sql.command";
				sqlCommands[i] = Config.getConfig().getProperty(
						sqlCommandProperty);
				if (sqlCommands[i] == null)
					return;
				sqlExampleProperty = i + ".sql.example";
				sqlExamples[i] = Config.getConfig().getProperty(
						sqlExampleProperty);
				if (sqlExamples[i] == null)
					return;
				sqlChoice.addItem(sqlCommands[i]);
			}
		}
	}

	public void setSql(String sql) {
		sqlArea.setText(sql);
	}

}