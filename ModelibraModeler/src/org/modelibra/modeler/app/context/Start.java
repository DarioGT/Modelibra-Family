package org.modelibra.modeler.app.context;

import javax.swing.JApplet;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.pref.Para;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public class Start extends JApplet {
	
	static final long serialVersionUID = 7168319479760000370L;

	public static boolean applet = true;

	private Para para = Para.getPara();

	private Config config = Config.getConfig();

	{
		String appletKey = config.getProperty("applet");
		this.setApplet(appletKey);
		String langKey = config.getProperty("language");
		para.setNaturalLanguage(langKey);
	}

	private AppFrame appFrame = new AppFrame();

	public Start() {
		super();
		if (!applet) {
			this.init();
		}
	}

	// overriden from Applet
	public String getAppletInfo() {
		return para.getText("appTitle") + ", " + para.getText("date")
				+ " <-- D. Ridjanovi\u0107"; // Ridjanovic(h)
	}

	public void init() {
		appFrame.setLocation(0, 0);
		if (!applet) {
			this.start();
		}
	}

	public void start() {
		appFrame.setVisible(true);
	}

	public void stop() {
		appFrame.setVisible(false);
	}

	public void destroy() {
		appFrame.dispose();
	}

	private void setApplet(String aValue) {
		if ((aValue == null) || (aValue.equals("yes"))) {
			applet = true;
		} else {
			applet = false;
		}
	}

	// used by app only
	public static void main(String[] args) {
		new Start();
	}

}
