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
package org.modelibra.wicket.concept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.Model;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ModelibraException;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity lookup table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityLookupTableListView extends EntityDisplayTableListView {

	private static final long serialVersionUID = 102240L;

	static Log log = LogFactory.getLog(EntityLookupTableListView.class);

	private ViewModel baseViewModel;

	private View baseView;

	private String modelCode;

	/**
	 * Constructs an entity lookup table list view.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityLookupTableListView(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.baseViewModel = viewModel;
		this.baseView = view;
		modelCode = baseViewModel.getModel().getModelConfig().getCode();
	}

	/**
	 * Populates a list view line.
	 * 
	 * @param item
	 *            list item (line)
	 */
	protected void populateItem(ListItem item) {
		super.populateItem(item);
		IEntity lookupEntity = (IEntity) item.getModelObject();

		ViewModel lookupModel = new ViewModel();
		lookupModel.copyPropertiesFrom(baseViewModel);
		lookupModel.setEntity(lookupEntity);

		View lookupView = new View();
		lookupView.copyPropertiesFrom(baseView);
		lookupView.setWicketId("entityLookup");
		item.add(new LookupCheckBox(lookupModel, lookupView));
	}

	/**
	 * Lookup check box.
	 */
	private class LookupCheckBox extends CheckBox {

		static final long serialVersionUID = 200501L;

		private ViewModel lookupViewModel;

		/**
		 * Constructs a lookup check box.
		 * 
		 * @param viewModel
		 *            view model
		 * @param view
		 *            view
		 */
		public LookupCheckBox(final ViewModel viewModel, final View view) {
			super(view.getWicketId(), new Model(Boolean.FALSE));
			this.lookupViewModel = viewModel;
		}

		/**
		 * What to do when selection changes.
		 */
		public void onSelectionChanged() {
			try {
				IEntity lookupEntity = lookupViewModel.getEntity();
				PropertyConfig baseEntityPropertyConfig = baseViewModel
						.getPropertyConfig();
				String action = baseViewModel.getAction();

				if (baseEntityPropertyConfig.isReference()) {
					String neighborCode = baseEntityPropertyConfig
							.getReferenceNeighbor();
					DomainApp app = (DomainApp) getApplication();
					if (action.equals("add")) {
						IEntity entity = baseViewModel.getEntity();
						entity.setParentNeighbor(neighborCode, lookupEntity);
						ViewModel entityAddFormPageModel = new ViewModel();
						entityAddFormPageModel
								.copyPropertiesFrom(baseViewModel);
						entityAddFormPageModel.setEntities(baseViewModel
								.getContextViewModel().getEntities());
						entityAddFormPageModel.setLookupEntities(null);
						View entityAddFormPageView = new View();
						entityAddFormPageView.copyPropertiesFrom(baseView);
						entityAddFormPageView.setUpdate(true);
						setResponsePage(baseView.getContextView().getPage());
					} else {
						IEntity entity = baseViewModel.getUpdateEntity();
						entity.setParentNeighbor(neighborCode, lookupEntity);
						ViewModel entityEditFormPageModel = new ViewModel();
						entityEditFormPageModel
								.copyPropertiesFrom(baseViewModel);
						entityEditFormPageModel.setEntities(baseViewModel
								.getContextViewModel().getEntities());
						entityEditFormPageModel.setLookupEntities(null);
						View entityEditFormPageView = new View();
						entityEditFormPageView.copyPropertiesFrom(baseView);
						entityEditFormPageView.setUpdate(true);
						setResponsePage(baseView.getContextView().getPage());
					}
				} else {
					throw new ModelibraException(
							"Cannot lookup neighbor for property that is not reference: "
									+ baseEntityPropertyConfig.getCode());
				}
			} catch (ModelibraException e) {
				log.error("Error in EntityLookupTableListView: "
						+ e.getMessage());
			}
		}

		/**
		 * Checks if to notify about selection change.
		 * 
		 * @return <code>true</code> if to notify about selection change
		 */
		protected boolean wantOnSelectionChangedNotifications() {
			return true;
		}

	}

}
