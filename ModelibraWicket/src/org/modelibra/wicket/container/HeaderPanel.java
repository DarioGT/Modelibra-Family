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

import org.apache.wicket.markup.html.basic.Label;

/**
 * Header panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-19
 */
public class HeaderPanel extends DmPanel {

	private static final long serialVersionUID = 101011L;

	/**
	 * Constructs a header panel.
	 * 
	 * @param wicketId
	 *            Wicket id
	 */
	public HeaderPanel(final String wicketId, final String title) {
		super(wicketId);
		add(new Label("title", title));
	}

}
