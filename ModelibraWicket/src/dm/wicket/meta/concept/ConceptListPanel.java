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
package dm.wicket.meta.concept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Displays concept list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
public class ConceptListPanel extends DmPanel {

	private static final long serialVersionUID = 110110136L;

	private static Log log = LogFactory.getLog(ConceptListPanel.class);

	public ConceptListPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		ViewModel conceptsViewModel = new ViewModel();
		conceptsViewModel.copyPropertiesFrom(viewModel);
		IEntities concepts = conceptsViewModel.getEntities();
		if (concepts != null) {
			View conceptsView = new View();
			conceptsView.copyPropertiesFrom(view);
			conceptsView.setWicketId("conceptList");
			ConceptList conceptList = new ConceptList(conceptsViewModel,
					conceptsView);
			add(conceptList);
		}
	}
}
