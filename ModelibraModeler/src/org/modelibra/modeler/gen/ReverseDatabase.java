package org.modelibra.modeler.gen;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.util.JdbcUtilities;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic 
 * @version 2006-12-09
 */
public class ReverseDatabase {

	private static ReverseDatabase instance;

	private Connection con;

	private boolean connected;

	private Collection<Collection<String>> jdbcTypes = new ArrayList<Collection<String>>();

	public static ReverseDatabase getSingleton() {
		if (instance == null) {
			instance = new ReverseDatabase();
		}
		return instance;
	}

	public void connect(String jdbc, String source, String login,
			String password) {
		try {
			if (!connected) {
				Class.forName(jdbc);
				con = DriverManager.getConnection(source, login, password);
				connected = true;
				loadJdbcTypes();
			}
		} catch (ClassNotFoundException e) {
			connected = false;
			con = null;
			System.out.println(e.getMessage() + " // JDBC driver not found! ");
		} catch (SQLException e) {
			connected = false;
			con = null;
			System.out
					.println(e.getMessage() + " // Connection not obtained! ");
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public Connection getConnection() {
		return con;
	}

	public void disconnect() {
		try {
			if (connected) {
				con.close();
				connected = false;
				con = null;
				jdbcTypes.clear();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()
					+ " // Problem with the connection closing! ");
		}
	}

	/*
	 * private void informUser(String msg) { JOptionPane.showMessageDialog(null, //
	 * context frame msg, // message Para.getPara().getText("info"), // title
	 * JOptionPane.INFORMATION_MESSAGE); // message type }
	 */

	private void loadJdbcTypes() throws SQLException {
		if (isConnected()) {
			DatabaseMetaData dbMetaData = con.getMetaData();
			ResultSet jdbcTypesResult = dbMetaData.getTypeInfo();
			while (jdbcTypesResult.next()) {
				String dbmsTypeName = jdbcTypesResult.getString(1);
				String jdbcTypeName = JdbcUtilities
						.getColumnJdbcType(jdbcTypesResult.getInt(2));
				addGenericJdbcTypeToList(dbmsTypeName, jdbcTypeName);
			}
			jdbcTypesResult.close();
		}
	}

	private void addGenericJdbcTypeToList(String dbmsTypeName,
			String jdbcTypeName) {
		Collection<String> type = new ArrayList<String>();
		type.add(dbmsTypeName);
		type.add(jdbcTypeName);
		jdbcTypes.add(type);
	}

	public void reverseDatabase(DiagramModel diagram, String dbms) {
		ArrayList appTypes = (ArrayList) diagram.getAppModel().getTypes();

		try {
			ArrayList<String> dbTableNames = new ArrayList<String>();
			ResultSet tableNames = null;

			if (getConnection() != null) {
				Manager.getSingleton().startTransaction("revert database"); // Transaction

				DatabaseMetaData dbMetaData = con.getMetaData();
				if (!dbms.equalsIgnoreCase("Oracle")) {
					String tType[] = { "TABLE" };

					tableNames = dbMetaData.getTables(null, null, "%", tType);

					while (tableNames.next()) {
						String currentTableName = tableNames
								.getString("TABLE_NAME");
						dbTableNames.add(currentTableName);

						// MessageManager.getMessageManager().printMessage("Table
						// : "+current_table);
						// String current_tableType = tables.getString(4);
						// //Table type (table, sequence...)
						// System.out.println(current_tableType);
						// 1 : directory path to get to the database
						// d:\dir1\dir2
						// 2 : ??? null everywhere
						// 3 : Table name
						// 4 : Table type (table, sequence, etc.)
						// 5 : ??? null everywhere
					}
				} else {
					Statement stmt = getConnection().createStatement();
					tableNames = stmt
							.executeQuery("SELECT TABLE_NAME FROM USER_TABLES");
					while (tableNames.next()) {
						String currentTableName = tableNames.getString(1);
						dbTableNames.add(currentTableName);
					}
				}
				tableNames.close();

				// Position
				int x = 0;
				int y = 0;
				for (int i = 0; i < dbTableNames.size(); i++) {
					ArrayList<String> columnNames = new ArrayList<String>();
					ArrayList<String> columnTypes = new ArrayList<String>();

					String tableName = (String) dbTableNames.get(i);

					boolean exists = false;
					for (int k = 0; k < diagram.getBoxes().size(); k++) {
						BoxModel existingBoxModel = diagram.getBox(k);
						if (existingBoxModel.getName().equalsIgnoreCase(
								tableName)) {
							exists = true;
							break;
						}
					}

					if (!exists) {
						// get rows
						Statement stmt = con.createStatement();
						ResultSet rows = stmt.executeQuery("SELECT * FROM "
								+ tableName);
						ResultSetMetaData rsmd = rows.getMetaData();

						boolean hasOid = false;
						for (int j = 1; j <= rsmd.getColumnCount(); j++) {
							String columnName = rsmd.getColumnName(j);
							String columnType = JdbcUtilities
									.getColumnJdbcType(rsmd.getColumnType(j));
							columnNames.add(columnName);
							columnTypes.add(columnType);

							if (columnName.equalsIgnoreCase("oid")
									|| (tableName.equalsIgnoreCase("Sequence") && columnName
											.equalsIgnoreCase("seqName"))) {
								hasOid = true;
							}
						}
						rows.close();

						BoxModel table = new BoxModel(diagram, new Point(x, y),
								!hasOid);

						for (int j = 0; j < columnNames.size(); j++) {
							String columnName = (String) columnNames.get(j);
							String jdbcType = (String) columnTypes.get(j);

							ItemModel column = new ItemModel(table);

							TypeModel columnTypeModel = null;

							for (int k = 0; k < appTypes.size(); k++) {
								TypeModel type = (TypeModel) appTypes.get(k);
								if (type.getDbmsType().equalsIgnoreCase(
										jdbcType)) {
									if (!(type.getName()
											.equalsIgnoreCase("oid") && !columnName
											.equalsIgnoreCase("oid"))) {
										columnTypeModel = type;
										break;
									} else if (!type.getName()
											.equalsIgnoreCase("oid")) {
										columnTypeModel = type;
										break;
									}
								}
							}
							if (columnTypeModel == null) {
								columnTypeModel = new TypeModel(diagram
										.getAppModel());
								columnTypeModel.setName(jdbcType);
								columnTypeModel.setDbmsType(jdbcType);
								columnTypeModel.setAlias(JdbcUtilities
										.getJavaTypeForJdbcType(jdbcType));
							}

							column.setTypeModel(columnTypeModel);
							column.setName(columnName);
							if (columnName.equalsIgnoreCase("oid")
									|| (tableName.equalsIgnoreCase("Sequence") && columnName
											.equalsIgnoreCase("seqName"))) {
								column.setOid(true);
							}
							table.add(column);

						}
						table.setName(tableName);
						diagram.add(table);
						// Position
						int increaseX = table.getWidth() + 10;
						int increaseY = 0;
						x += increaseX;
						if (x >= 600) {
							increaseY = table.getHeight() + 10;
							y += increaseY;
							x = 0;
						}
						diagram.setSize(diagram.getWidth() + increaseX, diagram
								.getHeight()
								+ increaseY);
					}
				}
				generateLinesForForeignKeysOi(diagram);
				Manager.getSingleton().commit();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void generateLinesForForeignKeysOi(DiagramModel diagram) {
		for (int i = 0; i < diagram.getBoxes().size(); i++) {
			BoxModel table = diagram.getBox(i);
			String tableName = table.getName();
			for (int j = 0; j < table.getItems().size(); j++) {
				for (int i2 = 0; i2 < diagram.getBoxes().size(); i2++) {
					BoxModel aTable = diagram.getBox(i2);
					for (int j2 = 0; j2 < aTable.getItems().size(); j2++) {
						ItemModel aColumn = aTable.getItem(j2);
						String aColumnName = aColumn.getName();
						// If table is parent of aTable
						if (aColumnName.equalsIgnoreCase("oid" + tableName)) {
							// remove foreign key column
							aTable.remove(aColumn);
						}
					}
				}
			}
		}
	}

}