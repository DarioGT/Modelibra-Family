package org.modelibra.modeler.sql;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.modelibra.modeler.app.context.SQLFrame;
import org.modelibra.modeler.app.pref.Para;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-26
 */
public class QueryTable extends JFrame implements ListSelectionListener {
	
	static final long serialVersionUID = 7168319479760000330L;

	private static final String SQL = "SQL";

	private static final String TITLE = "George Koch: ORACLE7, McGraw-Hill, 1993";

	private static final int MIN_WIDTH = 390;

	private static final int MIN_HEIGHT = 290;

	private static final int MAX_WIDTH = 780;

	private static final int MAX_HEIGHT = 580;

	private Query query;

	private JTableHeader header;

	private JTable table;

	private JPanel northPanel = new JPanel();

	private JLabel info = new JLabel(TITLE);

	private JPanel tablePanel = new JPanel();

	private JPanel southPanel = new JPanel();

	private JButton copyButton = new JButton(Para.getPara().getText("copy")
			+ " SQL");

	private SQLFrame sqlFrame;

	private int selectedRow = -1;

	public QueryTable(Query query) {
		super();
		this.query = query;
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public QueryTable(SQLFrame sqlFrame, Query query) {
		this(query);
		this.sqlFrame = sqlFrame;
	}

	private void init() throws Exception {
		this.setTitle("SQL");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});

		table = new JTable(query);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getSelectionModel().addListSelectionListener(this);
		header = table.getTableHeader();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(header, BorderLayout.NORTH);
		tablePanel.add(table, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(tablePanel);
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);

		northPanel.setBackground(Color.orange);
		southPanel.setBackground(Color.orange);
		if (query.getTableName().equalsIgnoreCase(SQL)) {
			northPanel.add(info);
			southPanel.add(copyButton);
			try {
				TableColumn sqlColumn = table.getColumn("sql");
				sqlColumn.setPreferredWidth(2040); // 255 * 8
			} catch (Exception e) {
				System.out.println("Not valid SQL table! " + e);
			}
		}
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});

		pack();
		if (this.getWidth() > MAX_WIDTH) {
			this.setSize(MAX_WIDTH, this.getHeight());
		}
		if (this.getHeight() > MAX_HEIGHT) {
			this.setSize(this.getWidth(), MAX_HEIGHT);
		}
		if (this.getWidth() < MIN_WIDTH) {
			this.setSize(MIN_WIDTH, this.getHeight());
		}
		if (this.getHeight() < MIN_HEIGHT) {
			this.setSize(this.getWidth(), MIN_HEIGHT);
		}

	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		selectedRow = table.getSelectedRow();
	}

	private void copy() {
		if (selectedRow >= 0) {
			try {
				TableColumn sqlColumn = table.getColumn("sql");
				Object obj = table.getValueAt(selectedRow, sqlColumn
						.getModelIndex());
				String sql = (String) obj;
				sqlFrame.setSql(sql);
			} catch (Exception e) {
				System.out.println("Not valid SQL table! " + e);
			}
		}
	}

	private void close() {
		this.dispose();
	}

}
