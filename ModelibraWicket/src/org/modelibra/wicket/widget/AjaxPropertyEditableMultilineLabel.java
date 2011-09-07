package org.modelibra.wicket.widget;

import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableMultiLineLabel;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * AjaxPropertyEditableMultilineLabel extends AjaxEditableMultiLineLabel just to
 * set the textarea size according to the property config.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class AjaxPropertyEditableMultilineLabel extends
		AjaxEditableMultiLineLabel {

	private static final long serialVersionUID = 1L;

	public AjaxPropertyEditableMultilineLabel(final ViewModel viewModel,
			final View view) {
		super(view.getWicketId(), new PropertyModel(viewModel.getEntity(),
				viewModel.getPropertyCode()));

		int propertySize = viewModel.getPropertyConfig().getDisplayLengthInt();
		int noOfRows = (propertySize / DomainApp.AREA_COLUMN_SIZE) + 1;

		setCols(DomainApp.AREA_COLUMN_SIZE);
		setRows(noOfRows);
	}

}
