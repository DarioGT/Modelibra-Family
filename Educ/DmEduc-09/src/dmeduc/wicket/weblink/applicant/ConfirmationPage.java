package dmeduc.wicket.weblink.applicant;

import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.util.LocalizedText;

/**
 * Confirmation page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-27
 */
@SuppressWarnings("serial")
public class ConfirmationPage extends DmPage {

	/**
	 * Constructs the Confirmation page.
	 */
	public ConfirmationPage() {
		ConfirmationPanel confirmationPanel = new ConfirmationPanel(
				"confirmation");
		add(confirmationPanel);
		String invalidMessage = LocalizedText.getText(confirmationPanel,
				"invalid");
		confirmationPanel.setInvalidMessage(invalidMessage);
	}

}
