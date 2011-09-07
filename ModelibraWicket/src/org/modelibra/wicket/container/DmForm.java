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

import org.apache.wicket.markup.html.form.Form;
import org.modelibra.IEntities;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Dm form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
public abstract class DmForm extends Form {

	private static final long serialVersionUID = 101820L;

	/**
	 * Constructs a dm form.
	 * 
	 * @param wicketId
	 *            Wicket id
	 */
	public DmForm(final String wicketId) {
		super(wicketId);
	}

	/**
	 * Constructs a dm form.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public DmForm(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
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
	 * Adds errors based on error keys from the entities.
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
	 * Adds errors from the entities.
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
