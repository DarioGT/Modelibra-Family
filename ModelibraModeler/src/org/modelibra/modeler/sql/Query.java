package org.modelibra.modeler.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-09
 */
public class Query extends AbstractTableModel {

	static final long serialVersionUID = 7168319479760000320L;

	private String tableName;

	private int columnCount;

	private String[] columnNames;

	private ArrayList<String[]> queryRows = new ArrayList<String[]>();

	private boolean select = false;

	private ResultSet selectResult;

	private ResultSetMetaData queryMetaData;

	private boolean success = true;

	public Query(Connection con, String sql) {
		super();
		try {
			if (sql.substring(0, 6).equalsIgnoreCase("select"))
				select = true;
			Statement statement = con.createStatement();
			if (!select) {
				statement.executeUpdate(sql);
				statement.close();
				return;
			}
			selectResult = statement.executeQuery(sql);
			queryMetaData = selectResult.getMetaData();
			tableName = queryMetaData.getTableName(1);
			columnCount = queryMetaData.getColumnCount();
			columnNames = new String[columnCount];

			// In SQL column index starts at 1 and not at 0 as in Java.
			for (int i = 1; i < columnCount + 1; i++) {
				columnNames[i - 1] = queryMetaData.getColumnName(i).trim();
			}

			while (selectResult.next()) {
				String[] row = new String[columnCount];
				for (int i = 1; i < columnCount + 1; i++) {
					String column = selectResult.getString(i);
					if (column != null) {
						column = column.trim();
					}
					row[i - 1] = column;
				}
				this.addRow(row);
			}
			selectResult.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("SQL problem! " + e);
			success = false;
		} catch (Exception e) {
			System.out.println("SQL command too short! " + e);
			success = false;
		}
	}

	public boolean isSelect() {
		return select;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getTableName() {
		return tableName;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public String getColumnName(int column) {
		return this.getColumnNames()[column];
	}

	public int getRowCount() {
		return queryRows.size();
	}

	public boolean isEmpty() {
		return (this.getRowCount() == 0);
	}

	public String[] getRow(int index) {
		return (String[]) queryRows.get(index);
	}

	public void addRow(String[] row) {
		queryRows.add(row);
	}

	public Object getValueAt(int row, int column) {
		return this.getRow(row)[column];
	}

}
