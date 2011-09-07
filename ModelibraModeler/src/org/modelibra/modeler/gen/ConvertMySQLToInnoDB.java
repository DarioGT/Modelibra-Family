package org.modelibra.modeler.gen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.modelibra.modeler.app.pref.Para;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public class ConvertMySQLToInnoDB {

	private boolean connected = false;

	private static ConvertMySQLToInnoDB instance = null;

	private Connection con;

	private static final String SQL_START = "ALTER TABLE ";

	private static final String SQL_END = " TYPE = InnoDB";

	public static ConvertMySQLToInnoDB getSingleton() {
		if (instance == null) {
			instance = new ConvertMySQLToInnoDB();
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
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()
					+ " // Problem with the connection closing! ");
		}
	}

	public void convert() {
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			String tType[] = { "TABLE" };

			ResultSet tableNames = dbmd.getTables(null, null, "%", tType);

			while (tableNames.next()) {
				String tableName = tableNames.getString("TABLE_NAME");
				Statement stmt = con.createStatement();
				stmt.execute(SQL_START + tableName + SQL_END);
				stmt.close();
			}

			tableNames.close();
			informUser(Para.getPara().getText("success"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			informUser(Para.getPara().getText("conversionFailed"));
		}
	}

	private void informUser(String msg) {
		JOptionPane.showMessageDialog(null, // context frame
				msg, // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

}