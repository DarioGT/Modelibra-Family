package org.modelibra.wicket.widget;

import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * AjaxCodeEditorPanel extends AjaxPropertyEditableMultilineLabel just to provide
 * different markup tag for label (pre).
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class AjaxCodeEditorPanel extends AjaxPropertyEditableMultilineLabel {

	private static final long serialVersionUID = 1L;

	public AjaxCodeEditorPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
	}

}
