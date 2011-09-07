package org.modelibra.wicket.widget;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * AjaxPropertyTextAreaEditableLabel extends AjaxPropertyEditableMultilineLabel
 * to use Label instead of MultiLineLabel for display.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class AjaxPropertyTextAreaEditableLabel extends
		AjaxPropertyEditableMultilineLabel {

	private static final long serialVersionUID = 1L;

	public AjaxPropertyTextAreaEditableLabel(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
	}

	@Override
	protected Component newLabel(MarkupContainer parent, String componentId,
			IModel model) {
		Label label = new Label(componentId, model) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream,
					ComponentTag openTag) {
				if (getModelObject() == null) {
					replaceComponentTagBody(markupStream, openTag,
							defaultNullLabel());
				} else {
					super.onComponentTagBody(markupStream, openTag);
				}
			}
		};
		label.setOutputMarkupId(true);
		label.add(new LabelAjaxBehavior("onclick"));
		return label;
	}

}
