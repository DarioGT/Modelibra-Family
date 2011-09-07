package org.modelibra.modeler.app.pref;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-09-17
 */
public class Config extends Properties {
	
	static final long serialVersionUID = 7168319479760000110L;

	private static Config config;

	private static final String CONFIG_FILE_NAME = "config.properties";

	private static final String MODELIBRA_TYPES_FILE_NAME = "modelibra.types";

	private static final String JDBC_TYPES_FILE_NAME = "jdbc.types";

	private static final String ORACLE_TYPES_FILE_NAME = "oracle.types";

	private static final String MYSQL_TYPES_FILE_NAME = "mysql.types";

	private static final String ACCESS_TYPES_FILE_NAME = "access.types";

	URL modelibraTypesUrl;

	URL jdbcTypesUrl;

	URL oracleTypesUrl;

	URL mysqlTypesUrl;

	URL accessTypesUrl;

	private Config() {
		super();
		load();
	}

	public static Config getConfig() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

	public void load() {
		try {
			URL configUrl = null;
			String sourceLocation = Para.getPara().getText("source.location");
			if (sourceLocation.equals("client")) {
				configUrl = Para.getPara().getUrlSiblingToClassFile(CONFIG_FILE_NAME);
				modelibraTypesUrl = Para.getPara().getUrlSiblingToClassFile(
						MODELIBRA_TYPES_FILE_NAME);
				jdbcTypesUrl = Para.getPara().getUrlSiblingToClassFile(JDBC_TYPES_FILE_NAME);
				oracleTypesUrl = Para.getPara().getUrlSiblingToClassFile(
						ORACLE_TYPES_FILE_NAME);
				mysqlTypesUrl = Para.getPara()
						.getUrlSiblingToClassFile(MYSQL_TYPES_FILE_NAME);
				accessTypesUrl = Para.getPara().getUrlSiblingToClassFile(
						ACCESS_TYPES_FILE_NAME);
			} else {
				String serverSource = Para.getPara().getText("source.server");
				configUrl = new URL(serverSource + CONFIG_FILE_NAME);
				modelibraTypesUrl = new URL(serverSource + MODELIBRA_TYPES_FILE_NAME);
				jdbcTypesUrl = new URL(serverSource + JDBC_TYPES_FILE_NAME);
				oracleTypesUrl = new URL(serverSource + ORACLE_TYPES_FILE_NAME);
				mysqlTypesUrl = new URL(serverSource + MYSQL_TYPES_FILE_NAME);
				accessTypesUrl = new URL(serverSource + ACCESS_TYPES_FILE_NAME);
			}
			InputStream inputStream = configUrl.openStream();
			clear();
			load(inputStream);
			inputStream.close();
			// Transfer.importTypes(jdbcTypesUrl); // default types
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage() + " // no URL");
			informUser(Para.getPara().getText("checkURL"));
		} catch (IOException e) {
			System.out.println(e.getMessage() + " // no file!");
			informUser(Para.getPara().getText("checkURL"));
		}
	}

	public String getProperty(String key) {
		String property = super.getProperty(key);
		if (property == null)
			return "????";
		else
			return property;
	}

	public URL getModelibraTypesUrl() {
		return modelibraTypesUrl;
	}

	public URL getJdbcTypesUrl() {
		return jdbcTypesUrl;
	}

	public URL getOracleTypesUrl() {
		return oracleTypesUrl;
	}

	public URL getMySQLTypesUrl() {
		return mysqlTypesUrl;
	}

	public URL getAccessTypesUrl() {
		return accessTypesUrl;
	}

	private static void informUser(String msg) {
		JOptionPane.showMessageDialog(null, // context frame
				msg, // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

}
