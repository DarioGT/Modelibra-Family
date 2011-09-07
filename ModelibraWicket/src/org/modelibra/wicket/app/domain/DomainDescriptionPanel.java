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
package org.modelibra.wicket.app.domain;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;

/**
 * Domain description (with the meta model image) panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-03
 */
public class DomainDescriptionPanel extends DmPanel {

	private static final long serialVersionUID = 103010L;

	/**
	 * Constructs the domain description panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 */
	public DomainDescriptionPanel(final String wicketId) {
		super(wicketId);
		String modelibraDescription = LocalizedText.getApplicationPropertiesText(this,
				"Modelibra.description");
		add(new Label("ModelibraDescription", modelibraDescription));
		add(new Image("ModelibraMeta1"));
		add(new Image("ModelibraMeta2"));
	}

	/**
	 * Constructs the domain description panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param description
	 *            description
	 */
	public DomainDescriptionPanel(final String wicketId,
			final String description) {
		super(wicketId);
		add(new Label("ModelibraDescription", description));
		add(new Image("ModelibraMeta"));
	}

}
