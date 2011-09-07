package org.modelibra.modeler.app.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.gen.ConvertMySQLToInnoDB;
import org.modelibra.modeler.gen.ReverseDatabase;
import org.modelibra.modeler.gen.Schema;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-08
 */
public abstract class DataSourceFrame extends JFrame {

	public static final Point POINT = new Point(80, 80);

	private static int lastDbms;

	private static String lastLogin;

	private static String lastPassword;

	private static String lastDriver;

	private static String lastSource;

	private int dataSourceCount = 0;

	private String login;

	private String password;

	private String dbms;

	private String driver;

	private String source;

	private String[] dbmss;

	private String[] hosts;

	private String[] drivers;

	private String[] sources;

	private JComboBox dataSourceChoice = new JComboBox();

	private JPanel northPanel = new JPanel();

	protected JPanel dataPanel = new JPanel();

	private JPanel sourcePanel = new JPanel();

	private JPanel driverPanel = new JPanel();

	private JPanel dbPanel = new JPanel();

	private JPanel loginPanel = new JPanel();

	private JLabel dbmsLabel = new JLabel(Para.getPara().getText("dbms"));

	private JLabel driverLabel = new JLabel(Para.getPara().getText("driver"));

	private JLabel sourceLabel = new JLabel(Para.getPara().getText("source"));

	private JLabel loginLabel = new JLabel(Para.getPara().getText("login"));

	private JLabel passwordLabel = new JLabel(Para.getPara()
			.getText("password"));

	private JTextField driverField = new JTextField(48);

	protected JTextField sourceField = new JTextField(48);

	private JTextField loginField = new JTextField(8);

	private JPasswordField passwordField = new JPasswordField(8);

	protected JPanel southPanel = new JPanel();

	public DataSourceFrame() {
		super();
		try {
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void init() throws Exception {
		obtainJdbcConfigProperties();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});

		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(northPanel, BorderLayout.NORTH);
		cp.add(dataPanel, BorderLayout.CENTER);
		cp.add(southPanel, BorderLayout.SOUTH);

		northPanel.setBackground(Para.BACKGROUND_COLOR);

		dataSourceChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String choice = (String) dataSourceChoice.getSelectedItem();
				for (int i = 0; i < dataSourceCount; i++)
					if (choice.equals(dbmss[i] + " " + hosts[i])) {
						dbms = dbmss[i];
						driverField.setText(drivers[i]);
						sourceField.setText(sources[i]);
						break;
					}
			}
		});
		northPanel.add(dbmsLabel);
		northPanel.add(dataSourceChoice);

		dataPanel.setLayout(new BorderLayout());
		dataPanel.add(sourcePanel, BorderLayout.NORTH);

		sourcePanel.setLayout(new BorderLayout());
		sourcePanel.add(driverPanel, BorderLayout.NORTH);
		sourcePanel.add(dbPanel, BorderLayout.CENTER);
		sourcePanel.add(loginPanel, BorderLayout.SOUTH);

		driverPanel.setLayout(new BorderLayout());
		driverPanel.add(driverLabel, BorderLayout.NORTH);
		driverPanel.add(driverField, BorderLayout.SOUTH);

		dbPanel.setLayout(new BorderLayout());
		dbPanel.add(sourceLabel, BorderLayout.NORTH);
		dbPanel.add(sourceField, BorderLayout.SOUTH);

		loginPanel.add(loginLabel);
		loginPanel.add(loginField);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);

		southPanel.setBackground(Color.orange);

		// Default data source is the first one (0 index)
		dbms = dbmss[0];
		driver = drivers[0];
		source = sources[0];

		if (lastLogin == null) {
			// default parameters
			driverField.setText(driver);
			sourceField.setText(source);
			loginField.setText(login);
			passwordField.setText(password);
		} else {
			// last parameters
			dataSourceChoice.setSelectedIndex(lastDbms);
			driverField.setText(lastDriver);
			sourceField.setText(lastSource);
			loginField.setText(lastLogin);
			passwordField.setText(lastPassword);
		}

	}

	public String getDbms() {
		return dbms;
	}

	protected void action(String a) {
		char[] pwc = passwordField.getPassword();
		String pws = new String(pwc);
		if (a.equals("reverse")) {
			if (!ReverseDatabase.getSingleton().isConnected()) {
				ReverseDatabase.getSingleton().connect(driverField.getText(),
						sourceField.getText(), loginField.getText(), pws);
				if (!ReverseDatabase.getSingleton().isConnected())
					informUser(Para.getPara().getText("noConnection"));
			} else if ((!driver.equals(driverField.getText()))
					|| (!source.equals(sourceField.getText()))
					|| (!login.equals(loginField.getText()))
					|| (!password.equals(pws))) {
				ReverseDatabase.getSingleton().disconnect();
				ReverseDatabase.getSingleton().connect(driverField.getText(),
						sourceField.getText(), loginField.getText(), pws);
				if (!ReverseDatabase.getSingleton().isConnected())
					informUser(Para.getPara().getText("noConnection"));
			}
		} else if (a.equals("convertMySQL")) {
			if (!Schema.getSingleton().isConnected()) {
				ConvertMySQLToInnoDB.getSingleton().connect(
						driverField.getText(), sourceField.getText(),
						loginField.getText(), pws);
				if (!ConvertMySQLToInnoDB.getSingleton().isConnected())
					informUser(Para.getPara().getText("noConnection"));
			} else if ((!driver.equals(driverField.getText()))
					|| (!source.equals(sourceField.getText()))
					|| (!login.equals(loginField.getText()))
					|| (!password.equals(pws))) {
				ConvertMySQLToInnoDB.getSingleton().disconnect();
				ConvertMySQLToInnoDB.getSingleton().connect(
						driverField.getText(), sourceField.getText(),
						loginField.getText(), pws);
				if (!ConvertMySQLToInnoDB.getSingleton().isConnected())
					informUser(Para.getPara().getText("noConnection"));
			}
		} else {
			if (!Schema.getSingleton().isConnected()) {
				Schema.getSingleton().connect(driverField.getText(),
						sourceField.getText(), loginField.getText(), pws);
				if (!Schema.getSingleton().isConnected())
					informUser(Para.getPara().getText("noConnection"));
			} else if ((!driver.equals(driverField.getText()))
					|| (!source.equals(sourceField.getText()))
					|| (!login.equals(loginField.getText()))
					|| (!password.equals(pws))) {
				Schema.getSingleton().disconnect();
				Schema.getSingleton().connect(driverField.getText(),
						sourceField.getText(), loginField.getText(), pws);
				if (!Schema.getSingleton().isConnected())
					informUser(Para.getPara().getText("noConnection"));
			}
		}
		driver = driverField.getText();
		source = sourceField.getText();
		login = loginField.getText();
		password = pws;

		lastDbms = dataSourceChoice.getSelectedIndex();
		lastDriver = driver;
		lastSource = source;
		lastLogin = login;
		lastPassword = password;
	}

	private void close() {
		Schema.getSingleton().disconnect();
		this.dispose();
	}

	private void obtainJdbcConfigProperties() {
		login = Config.getConfig().getProperty("jdbc.login");
		password = Config.getConfig().getProperty("jdbc.password");

		String countString = Config.getConfig().getProperty("jdbc.count");
		if (countString != null) {
			try {
				Integer countInteger = new Integer(countString);
				dataSourceCount = countInteger.intValue();
			} catch (NumberFormatException e) {
				System.out.println("JDBC count property must be integer: "
						+ e.getMessage());
			}
		}
		if (dataSourceCount > 0) {
			dbmss = new String[dataSourceCount];
			hosts = new String[dataSourceCount];
			drivers = new String[dataSourceCount];
			sources = new String[dataSourceCount];
			String dbmsProperty;
			String hostProperty;
			String driverProperty;
			String sourceProperty;
			for (int i = 0; i < dataSourceCount; i++) {
				dbmsProperty = i + ".jdbc.dbms";
				dbmss[i] = Config.getConfig().getProperty(dbmsProperty);
				if (dbmss[i] == null)
					return;
				hostProperty = i + ".jdbc.host";
				hosts[i] = Config.getConfig().getProperty(hostProperty);
				if (hosts[i] == null)
					return;

				dataSourceChoice.addItem(dbmss[i] + " " + hosts[i]);
				driverProperty = i + ".jdbc.driver";
				drivers[i] = Config.getConfig().getProperty(driverProperty);
				if (drivers[i] == null)
					return;
				sourceProperty = i + ".jdbc.source";
				sources[i] = Config.getConfig().getProperty(sourceProperty);
				if (sources[i] == null)
					return;
			}
		}
	}

	protected void informUser(String msg) {
		JOptionPane.showMessageDialog(this, // context frame
				msg, // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

}