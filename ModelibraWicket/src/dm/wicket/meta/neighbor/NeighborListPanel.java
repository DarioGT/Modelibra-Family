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
package dm.wicket.meta.neighbor;

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
public class NeighborListPanel extends DmPanel {

	private static final long serialVersionUID = 110110156L;

	private static Log log = LogFactory.getLog(NeighborListPanel.class);

	public NeighborListPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		ViewModel neighborsViewModel = new ViewModel();
		neighborsViewModel.copyPropertiesFrom(viewModel);
		View neighborsView = new View();
		neighborsView.copyPropertiesFrom(view);
		neighborsView.setWicketId("neighborList");
		NeighborList neighborList = new NeighborList(neighborsViewModel,
				neighborsView);
		add(neighborList);
	}
}
