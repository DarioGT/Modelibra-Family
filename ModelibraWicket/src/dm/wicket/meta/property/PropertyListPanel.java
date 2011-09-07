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
package dm.wicket.meta.property;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Displays property list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
public class PropertyListPanel extends DmPanel {

	private static final long serialVersionUID = 110110146L;

	private static Log log = LogFactory.getLog(PropertyListPanel.class);

	public PropertyListPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		ViewModel propertiesViewModel = new ViewModel();
		propertiesViewModel.copyPropertiesFrom(viewModel);
		View propertiesViewContext = new View();
		propertiesViewContext.copyPropertiesFrom(view);
		propertiesViewContext.setWicketId("propertyList");
		PropertyList propertyList = new PropertyList(propertiesViewModel,
				propertiesViewContext);
		add(propertyList);
	}

}
