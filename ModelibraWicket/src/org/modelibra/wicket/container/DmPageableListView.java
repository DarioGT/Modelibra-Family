/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra.wicket.container;

import java.util.List;

import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntities;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Dm pageable list view.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-09-30
 */
@SuppressWarnings("serial")
public abstract class DmPageableListView extends PageableListView {

	/**
	 * Constructs a dmLite pageable list view.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param list
	 *            list
	 * @param pageBlockSize
	 *            page block size
	 */
	public DmPageableListView(final String wicketId, final List<?> list,
			final int pageBlockSize) {
		super(wicketId, list, pageBlockSize);
	}

	/**
	 * Constructs a dmLite pageable list view.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param entities
	 *            entities
	 * @param pageBlockSize
	 *            page block size
	 */
	public DmPageableListView(final String wicketId,
			final IEntities<?> entities, final int pageBlockSize) {
		super(wicketId, new PropertyModel(entities, "entityList"),
				pageBlockSize);
	}

	/**
	 * Constructs a dmLite pageable list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 * @param pageBlockSize
	 *            page block size
	 */
	public DmPageableListView(final ViewModel viewModel, final View view,
			final int pageBlockSize) {
		super(view.getWicketId(), new PropertyModel(viewModel.getEntities(),
				"list"), pageBlockSize);
	}

	/**
	 * Gets an application session.
	 * 
	 * @return application session
	 */
	public AppSession getAppSession() {
		return (AppSession) getSession();
	}

	/**
	 * Adds an error based on the error key.
	 * 
	 * @param key
	 *            error key
	 */
	protected void addErrorByKey(String key) {
		String validationError = LocalizedText.getText(this, key);
		error(validationError);
	}

	/**
	 * Adds errors based on error keys.
	 * 
	 * @param entities
	 *            entities
	 */
	protected void addErrorsByKeys(IEntities<?> entities) {
		List<String> errorKeys = entities.getErrors().getKeyList();
		for (String errorKey : errorKeys) {
			String errorMsg = LocalizedText.getErrorMessage(this, errorKey);
			error(errorMsg);
		}
	}

	/**
	 * Adds errors.
	 * 
	 * @param entities
	 *            entities
	 */
	protected void addErrors(IEntities<?> entities) {
		List<String> errorMsgs = entities.getErrors().getErrorList();
		for (String errorMsg : errorMsgs) {
			error(errorMsg);
		}
	}

}
