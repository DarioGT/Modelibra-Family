package org.modelibra.swing.widget;

import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.modelibra.IEntities;
import org.modelibra.swing.app.IConstants;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public abstract class ModelibraPanel extends JPanel implements IConstants {

	/**
	 * Displays a child frame down and right of this panel.
	 * 
	 * @param child
	 *            frame
	 */
	public void displayDownRight(JFrame child) {
		Point locationOnScreen = getLocationOnScreen();
		double parentX = locationOnScreen.getX();
		double parentY = locationOnScreen.getY();

		double childX = parentX + FRAME_DISPLAY_INCREMENT;
		double childY = parentY + FRAME_DISPLAY_INCREMENT;

		Point childLocation = new Point();
		childLocation.setLocation(childX, childY);
		child.setLocation(childLocation);
		child.setVisible(true);
	}

	protected String getErrorMsgs(IEntities<?> entities) {
		List<String> errorMsgList = entities.getErrors().getErrorList();
		String errorMsgs = " ";
		for (String errorMsg : errorMsgList) {
			errorMsgs = errorMsgs + errorMsg + " ";
		}
		return errorMsgs;
	}

	protected String getErrorMsgsByKeys(IEntities<?> entities, NatLang natLang) {
		List<String> errorKeyList = entities.getErrors().getKeyList();
		String i18nMsg;
		String errorMsgs = " ";
		for (String errorKey : errorKeyList) {
			i18nMsg = natLang.getText(errorKey);
			errorMsgs = errorMsgs + i18nMsg + " ";
		}
		return errorMsgs;
	}

}
