package org.modelibra.modeler.gen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.util.JdbcUtilities;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-09
 */
public class Schema {
	// A database schema is generated using JDBC types.
	public static final int NAME_LENGTH_LIMIT = 30;

	private static Schema instance;

	private String jdbc;

	private Connection con;

	private Statement stmt;

	private boolean connected;

	private Collection<Collection<String>> baseTypes = new ArrayList<Collection<String>>();

	private Schema() {
		super();
	}

	public static Schema getSingleton() {
		if (instance == null) {
			instance = new Schema();
		}
		return instance;
	}

	public void connect(String jdbc, String source, String login,
			String password) {
		this.jdbc = jdbc;
		try {
			if (!connected) {
				Class.forName(jdbc);
				con = DriverManager.getConnection(source, login, password);
				connected = true;
				loadDbmsTypes();
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
				baseTypes.clear();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()
					+ " // Problem with the connection closing! ");
		}
	}

	private void informUser(String msg) {
		JOptionPane.showMessageDialog(null, // context frame
				msg, // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

	private String askUserForDBMSType(String baseType, String tableName,
			String columnName, Object[] options) {
		String info = Para.getPara().getText("specifyDBMSType") + " "
				+ baseType + " (" + tableName + ", " + columnName + ")";
		String selection = (String) JOptionPane.showInputDialog(null, // context
				// frame
				info, // message
				info, // title
				JOptionPane.QUESTION_MESSAGE, // message type
				null, // icon
				options, // options
				options[0]); // initial option selected
		if (selection == null) {
			return (String) options[0];
		}
		return selection;
	}

	public void genDropSchema(DiagramModel diagramModel, boolean foreignKeys) {
		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			Collection boxes = diagramModel.getBoxes();
			BoxModel box;
			String sqlQuery;
			/*
			 * To avoid dependencies, foreign keys (indexes) are dropped before
			 * tables are dropped!
			 */
			if (foreignKeys) {
				for (Iterator x = boxes.iterator(); x.hasNext();) {
					box = (BoxModel) x.next();
					if (!box.isAbstractDef()) {
						dropForeignKeys(box);
					}
				}
			}

			int counter = 0;
			for (Iterator x = boxes.iterator(); x.hasNext();) {
				box = (BoxModel) x.next();
				if (!box.isAbstractDef()) {
					sqlQuery = sqlDropTable(box);
					System.out.println(sqlQuery);
					// So that it tries to delete the next table if this one no
					// longer exists
					try {
						stmt.executeUpdate(sqlQuery);
						counter++;
					} catch (SQLException e) {
						String tableName = box.getName();
						System.out.println("// Problem while dropping table "
								+ tableName + " : " + e.getMessage());
						String msg = Para.getPara().getText("tableNotDropped")
								+ " -- " + tableName;
						this.informUser(msg);
					}
				}
			}
			con.commit();
			if (counter > 0) {
				String msg = Para.getPara().getText("schemaDropped") + " -- "
						+ counter + " " + Para.getPara().getText("tables");
				this.informUser(msg);
			} else {
				this.informUser(Para.getPara().getText("schemaNotDropped"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()
					+ " // Problem with the JDBC schema generation! ");
			try {
				con.rollback();
				this.informUser(Para.getPara().getText("schemaNotDropped"));
			} catch (SQLException e1) {
				System.out.println(e1.getMessage()
						+ " // Problem with the connection rollback! ");
			}
		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage()
						+ " // Problem with the connection disconnect! ");
			}
		}
	}

	private void dropForeignKeys(BoxModel table) throws SQLException {
		if (table.hasLine()) {
			dropDirForeignKeys(table, 12);
			dropDirForeignKeys(table, 21);
		}
	}

	private void dropDirForeignKeys(BoxModel table, int dir)
			throws SQLException {
		String sqlQuery = "";
		String sqlIndex = "";
		BoxModel neighbor;
		String neighborDirName = "";
		String fkName;
		String ixName;
		int count = 1;
		Collection children = null;
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				sqlQuery = "ALTER TABLE " + table.getName() + " DROP ";
				if (child.isTwin()) {
					fkName = "FK_" + table.getName() + neighborDirName;
					if (fkName.length() > NAME_LENGTH_LIMIT) {
						fkName = fkName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlQuery = sqlQuery + "CONSTRAINT " + fkName;

					ixName = "IX_" + table.getName() + neighborDirName;
					if (ixName.length() > NAME_LENGTH_LIMIT) {
						ixName = ixName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlIndex = "DROP INDEX " + ixName + " ON "
							+ table.getName();
				} else {
					fkName = "FK_" + table.getName() + neighbor.getName();
					if (fkName.length() > NAME_LENGTH_LIMIT) {
						fkName = fkName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlQuery = sqlQuery + "CONSTRAINT " + fkName;

					ixName = "IX_" + table.getName() + neighbor.getName();
					if (ixName.length() > NAME_LENGTH_LIMIT) {
						ixName = ixName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlIndex = "DROP INDEX " + ixName + " ON "
							+ table.getName();
				}
				count++;
				System.out.println(sqlQuery);
				System.out.println(sqlIndex);
				try {
					stmt.executeUpdate(sqlQuery);
				} catch (SQLException e) {
					System.out.println("Problem while dropping constraint : "
							+ e.getMessage());
				}

				try {
					stmt.executeUpdate(sqlIndex);
				} catch (SQLException e) {
					System.out.println("Problem while dropping index : "
							+ e.getMessage());
				}
			}
		} // end for
	}

	private String sqlDropTable(BoxModel table) {
		String sqlQuery;
		sqlQuery = "DROP TABLE ";
		if (table.getName().equals("")) {
			sqlQuery = sqlQuery + "????";
		} else {
			sqlQuery = sqlQuery + table.getName();
		}
		return sqlQuery;
	}

	public void genCreateSchema(DiagramModel diagramModel, String source) {
		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			Collection boxes = diagramModel.getBoxes();
			BoxModel box;
			ItemModel item;
			String sqlQuery;
			int counter = 0;
			for (Iterator x = boxes.iterator(); x.hasNext();) {
				box = (BoxModel) x.next();
				if (!box.isAbstractDef()) {
					sqlQuery = sqlCreateTableBegin(box);
					Collection items = box.getItems();
					for (Iterator y = items.iterator(); y.hasNext();) {
						item = (ItemModel) y.next();
						sqlQuery = sqlQuery + sqlColumn(item);
					}
					sqlQuery = sqlQuery + sqlForeignKeyColumns(box);
					sqlQuery = sqlQuery + sqlPrimaryKey(box);
					sqlQuery = sqlQuery + sqlUniqueKey(box);
					sqlQuery = sqlQuery + sqlCreateTableEnd(box, source);
					System.out.println(sqlQuery);
					try {
						stmt.executeUpdate(sqlQuery);
						counter++;
					} catch (SQLException e) {
						String tableName = box.getName();
						System.out.println("// Problem while creating table "
								+ tableName + " : " + e.getMessage());
						String msg = Para.getPara().getText("tableNotCreated")
								+ " -- " + tableName;
						this.informUser(msg);
					}
				}
			}
			this.genCreateForeignKeys(diagramModel);
			con.commit();
			if (counter > 0) {
				String msg = Para.getPara().getText("schemaCreated") + " -- "
						+ counter + " " + Para.getPara().getText("tables");
				this.informUser(msg);
			} else {
				this.informUser(Para.getPara().getText("schemaNotCreated"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()
					+ " // Problem with the JDBC schema generation! ");
			try {
				con.rollback();
				this.informUser(Para.getPara().getText("schemaNotCreated"));
			} catch (SQLException e1) {
				System.out.println(e1.getMessage()
						+ " // Problem with the connection rollback! ");
			}
		} finally {
			try {
				con.setAutoCommit(true);
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()
						+ " // Problem with the statement closing! ");
			}
		}
	}

	private String sqlCreateTableBegin(BoxModel table) {
		String sqlQuery;
		sqlQuery = "CREATE TABLE ";
		if (table.getName().equals("")) {
			sqlQuery = sqlQuery + "????";
		} else {
			sqlQuery = sqlQuery + table.getName();
		}
		sqlQuery = sqlQuery + " (";
		return sqlQuery;
	}

	private String sqlColumn(ItemModel column) {
		String sqlQuery;
		sqlQuery = "  " + column.getName() + "  ";
		TypeModel type = column.getTypeModel();
		if (type != null) {
			BoxModel table = column.getBoxModel();
			sqlQuery = sqlQuery
					+ getGenericDbmsType(type.getDbmsType(), table.getName(),
							column.getName());
			if (type.getLength() != 0) {
				sqlQuery = sqlQuery + "(" + Integer.toString(type.getLength());
				if (type.getNoOfDec() != 0) {
					sqlQuery = sqlQuery + ","
							+ Integer.toString(type.getNoOfDec());
				}
				sqlQuery = sqlQuery + ")";
			}
		} else {
			sqlQuery = sqlQuery + "????";
		}
		if (column.getMin() == 0) {
			sqlQuery = sqlQuery + " NULL,";
		} else {
			sqlQuery = sqlQuery + " NOT NULL,";
		}
		return sqlQuery;
	}

	private String sqlPrimaryKey(BoxModel table) {
		String sqlQuery = "";
		ItemModel item = table.getOidItem();
		if (item != null) {
			sqlQuery = sqlQuery + " CONSTRAINT PK_" + table.getName();
			sqlQuery = sqlQuery + " PRIMARY KEY (" + item.getName() + ")";
		} else {
			sqlQuery = sqlQuery + " CONSTRAINT ???? PRIMARY KEY (????)";
		}
		return sqlQuery;
	}

	private String sqlUniqueKey(BoxModel table) {
		String sqlQuery = "";
		if (table.hasId()) {
			sqlQuery = sqlQuery + ",";
			sqlQuery = sqlQuery + " CONSTRAINT UK_" + table.getName()
					+ " UNIQUE (";
			boolean first = true;
			String fkNameList = this.sqlIdLine(table);
			if (!fkNameList.equals("")) {
				first = false;
				sqlQuery = sqlQuery + fkNameList;
			}
			Collection idColumns = table.getIdItems();
			ItemModel idColumn = null;
			for (Iterator x = idColumns.iterator(); x.hasNext();) {
				idColumn = (ItemModel) x.next();
				if (first) {
					first = false;
				} else {
					sqlQuery = sqlQuery + ", ";
				}
				sqlQuery = sqlQuery + idColumn.getName();
			}
			sqlQuery = sqlQuery + ")";
		}
		return sqlQuery;
	}

	private String sqlIdLine(BoxModel table) {
		Collection<String> fkNames = new ArrayList<String>();
		if (table.hasLine() && table.hasIdLine()) {
			fkNames = sqlDirIdLine(table, 12);
			fkNames.addAll(sqlDirIdLine(table, 21));
		}
		return this.nameList(fkNames);
	}

	private String nameList(Collection c) {
		String name;
		String names = "";
		boolean first = true;
		for (Iterator x = c.iterator(); x.hasNext();) {
			name = (String) x.next();
			if (first) {
				names = name;
				first = false;
			} else {
				names = names + ", " + name;
			}
		}
		return names;
	}

	private Collection<String> sqlDirIdLine(BoxModel table, int dir) {
		String fkColumnName;
		Collection<String> fkNames = new ArrayList<String>();
		BoxModel neighbor;
		String neighborName;
		String neighborDirName = "";
		Collection children;
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				neighborName = neighbor.getName();
				if (child.isTwin()) {
					fkColumnName = "oid" + neighborDirName;
				} else {
					fkColumnName = "oid" + neighborName;
				}
				if (dir == 12) {
					if (child.isDir12Id()) {
						fkNames.add(fkColumnName);
					}
				} else {
					if (child.isDir21Id()) {
						fkNames.add(fkColumnName);
					}
				}
			}
		} // end for
		return fkNames;
	}

	private String sqlForeignKeyColumns(BoxModel table) {
		String sqlQuery = "";
		if (table.hasLine()) {
			sqlQuery = sqlDirForeignKeyColumns(table, 12);
			sqlQuery = sqlQuery + sqlDirForeignKeyColumns(table, 21);
		}
		return sqlQuery;
	}

	private String sqlDirForeignKeyColumns(BoxModel table, int dir) {
		String fkColumnName;
		BoxModel neighbor;
		String neighborName;
		String neighborDirName = "";
		String neighborMin;
		TypeModel type;
		Collection children;
		String sqlQuery = "";
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				neighborName = neighbor.getName();
				if (child.isTwin()) {
					fkColumnName = "oid" + neighborDirName;
				} else {
					fkColumnName = "oid" + neighborName;
				}
				sqlQuery = sqlQuery + "  " + fkColumnName + "  ";

				// type = AppModel.getSingleton().getOidType();
				type = neighbor.getOidItem().getTypeModel();
				if (type != null) {
					sqlQuery = sqlQuery
							+ getGenericDbmsType(type.getDbmsType(), table
									.getName(), fkColumnName);
					if (type.getLength() != 0) {
						sqlQuery = sqlQuery + "("
								+ Integer.toString(type.getLength());
						sqlQuery = sqlQuery + ")";
					}
				} else {
					sqlQuery = sqlQuery + "????";
				}
				if (dir == 12) {
					neighborMin = child.getDir12Min();
				} else {
					neighborMin = child.getDir21Min();
				}

				String dir12Min = child.getDir12Min();
				String dir12Max = child.getDir12Max();
				String dir21Min = child.getDir21Min();
				String dir21Max = child.getDir21Max();

				boolean nullImpossible = false;
				if (dir == 12) {
					if ((dir12Min.equals("1") && dir12Max.equals("1")
							&& dir21Min.equals("1") && dir21Max.equals("1"))) {
						if (child.isDir21Id()) {
							nullImpossible = true;
						}
					} else if (!dir12Min.equals("0")) {
						nullImpossible = true;
					}
				}
				if (dir == 21) {
					if ((dir12Min.equals("1") && dir12Max.equals("1")
							&& dir21Min.equals("1") && dir21Max.equals("1"))) {
						if (child.isDir12Id()) {
							nullImpossible = true;
						} else if (!child.isDir21Id()) {
							nullImpossible = true;
						}
					} else if (!dir21Min.equals("0")) {
						nullImpossible = true;
					}
				}

				if (!neighborMin.equals("0") && nullImpossible) {
					sqlQuery = sqlQuery + " NOT NULL,";
				} else {
					sqlQuery = sqlQuery + " NULL,";
				}
			}
		} // end for
		return sqlQuery;
	}

	private String sqlCreateTableEnd(BoxModel table, String source) {
		String sqlQuery;
		sqlQuery = " )";
		if (source.toLowerCase().indexOf("mysql") != -1) {
			sqlQuery = sqlQuery + " TYPE = InnoDB ";
		}
		return sqlQuery;
	}

	public void genCreateForeignKeys(DiagramModel diagramModel)
			throws SQLException {
		try {
			Collection boxes = diagramModel.getBoxes();
			BoxModel box;
			/*
			 * Foreign keys are generated after all tables are generated to
			 * avoid FK dependencies (a table must exist before a FK referencing
			 * that table is generated).
			 */
			boolean exceptionEncountered = false;
			for (Iterator x = boxes.iterator(); x.hasNext();) {
				box = (BoxModel) x.next();
				if (!box.isAbstractDef()) {
					if (!createForeignKeys(box)) {
						exceptionEncountered = true;
					}
				}
			}
			if (!exceptionEncountered) {
				this.informUser(Para.getPara().getText("foreignKeysCreated"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()
					+ " // Problem with the foreign key (index) generation! ");
			this.informUser(Para.getPara().getText("foreignKeysNotCreated"));
			throw new SQLException(e.getMessage());
		}
	}

	private boolean createForeignKeys(BoxModel table) throws SQLException {
		boolean allKeysCreated = false;
		if (table.hasLine()) {
			if (createDirForeignKeys(table, 12)
					&& createDirForeignKeys(table, 21)) {
				allKeysCreated = true;
			}
		}
		return allKeysCreated;
	}

	private boolean createDirForeignKeys(BoxModel table, int dir)
			throws SQLException {
		boolean exceptionEncountered = false;
		String sqlQuery = "";
		String sqlIndex = "";
		BoxModel neighbor;
		String neighborDirName = "";
		String neighborDeleteRule;
		String fkName;
		String ixName;
		int count = 1;
		Collection children = null;
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				sqlQuery = "ALTER TABLE " + table.getName() + " ADD ";
				if (child.isTwin()) {
					fkName = "FK_" + table.getName() + neighborDirName;
					if (fkName.length() > NAME_LENGTH_LIMIT) {
						fkName = fkName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlQuery = sqlQuery + "CONSTRAINT " + fkName;
					sqlQuery = sqlQuery + " FOREIGN KEY (oid" + neighborDirName
							+ ")";
					sqlQuery = sqlQuery + " REFERENCES " + neighbor.getName()
							+ "(" + neighbor.getOidItem().getName() + ")";

					ixName = "IX_" + table.getName() + neighborDirName;
					if (ixName.length() > NAME_LENGTH_LIMIT) {
						ixName = ixName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlIndex = "CREATE INDEX " + ixName + " ON "
							+ table.getName() + " (oid" + neighborDirName
							+ " ASC)";
				} else {
					fkName = "FK_" + table.getName() + neighbor.getName();
					if (fkName.length() > NAME_LENGTH_LIMIT) {
						fkName = fkName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlQuery = sqlQuery + "CONSTRAINT " + fkName;
					sqlQuery = sqlQuery + " FOREIGN KEY (oid"
							+ neighbor.getName() + ")";
					sqlQuery = sqlQuery + " REFERENCES " + neighbor.getName()
							+ "(" + neighbor.getOidItem().getName() + ")";

					ixName = "IX_" + table.getName() + neighbor.getName();
					if (ixName.length() > NAME_LENGTH_LIMIT) {
						ixName = ixName.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count;
					}
					sqlIndex = "CREATE INDEX " + ixName + " ON "
							+ table.getName() + " (oid" + neighbor.getName()
							+ " ASC)";
				}
				count++;
				if (dir == 12) {
					neighborDeleteRule = child.getDir21DeleteRule();
				} else {
					neighborDeleteRule = child.getDir12DeleteRule();
				}
				if (neighborDeleteRule.equals("CASCADE")) {
					if (!jdbc.equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
						sqlQuery = sqlQuery + " ON DELETE CASCADE"; // Does not
						// work in
						// Access!
					}
				}
				System.out.println(sqlQuery);
				System.out.println(sqlIndex);

				try {
					stmt.executeUpdate(sqlIndex);
				} catch (SQLException e) {
					exceptionEncountered = true;
					System.out.println("Problem while creating index : "
							+ e.getMessage());
				}
				try {
					stmt.executeUpdate(sqlQuery);
				} catch (SQLException e) {
					exceptionEncountered = true;
					System.out.println("// Problem while creating constraint "
							+ fkName + " : " + e.getMessage());
					String msg = Para.getPara().getText("foreignKeyNotCreated")
							+ " -- " + fkName + " (" + table.getName() + ")";
					this.informUser(msg);
				}

			}
		} // end for
		return !exceptionEncountered;
	}

	private void loadDbmsTypes() throws SQLException {
		if (isConnected()) {
			DatabaseMetaData dbMetaData = con.getMetaData();
			ResultSet dbmsTypesResult = dbMetaData.getTypeInfo();
			while (dbmsTypesResult.next()) {
				String dbmsTypeName = dbmsTypesResult.getString(1);
				String jdbcTypeName = JdbcUtilities
						.getColumnJdbcType(dbmsTypesResult.getInt(2));
				addBaseTypeToList(dbmsTypeName, jdbcTypeName);
			}
			dbmsTypesResult.close();
		}
	}

	private void addBaseTypeToList(String dbmsTypeName, String jdbcTypeName) {
		Collection<String> type = new ArrayList<String>();
		type.add(dbmsTypeName); // 1 : dbms type
		type.add(jdbcTypeName); // 2 : corresponding JDBC type
		baseTypes.add(type);
	}

	// In order to ask once, clear the other matching DBMS types for given base
	// type
	private String getGenericDbmsType(String baseType, String tableName,
			String columnName) {
		ArrayList<String> dbmsTypesForBaseType = new ArrayList<String>();
		Iterator iterator = baseTypes.iterator();
		while (iterator.hasNext()) {
			ArrayList type = (ArrayList) iterator.next();
			String rowDbmsType = (String) type.get(0);
			String rowJdbcType = (String) type.get(1);
			if (rowJdbcType.equalsIgnoreCase(baseType)) {
				dbmsTypesForBaseType.add(rowDbmsType);
			}
		}
		int count = dbmsTypesForBaseType.size();
		if (count == 1) {
			return (String) dbmsTypesForBaseType.get(0);
		} else if (count > 1) {
			String selection = askUserForDBMSType(baseType, tableName,
					columnName, dbmsTypesForBaseType.toArray());
			// Remove the other dbms types related to this jdbc type
			Iterator it = baseTypes.iterator();
			while (it.hasNext()) {
				ArrayList row = (ArrayList) it.next();
				String rowDbmsTypeName = (String) row.get(0);
				String rowJdbcTypeName = (String) row.get(1);
				// Do not remove the choices for Integers because of
				// auto-increment systems
				if (rowJdbcTypeName.equalsIgnoreCase(baseType)
						&& !rowDbmsTypeName.equals(selection)
				// && !rowJdbcTypeName.equalsIgnoreCase("INTEGER")
				) {
					it.remove();
				}
			}
			return selection;
		}
		/*
		 * If we get here, no JDBC type has been found for the dbms type. We
		 * check if the dbms has a type with this name. If not, the schema
		 * generation *MIGHT* fail.
		 */
		Iterator baseTypesIt = baseTypes.iterator();
		boolean matchingDbmsTypeForBaseTypeFound = false;
		while (baseTypesIt.hasNext()) {
			ArrayList genericBaseType = (ArrayList) baseTypesIt.next();
			String dbmsTypeForBaseType = (String) genericBaseType.get(0);
			if (dbmsTypeForBaseType.equals(baseType)) {
				matchingDbmsTypeForBaseTypeFound = true;
				break;
			}
		}
		if (!matchingDbmsTypeForBaseTypeFound) {
			// Message : Pas de type SGBD trouv� pour le type de base INTEGER
			// (table, colonne)
			// No DBMS type found for base type INTEGER (table, column)
			String info = Para.getPara().getText("noDBMSTypeForBaseType") + " "
					+ baseType + " (" + tableName + ", " + columnName + ")";

			informUser(info);
		}
		return baseType;
	}

}
