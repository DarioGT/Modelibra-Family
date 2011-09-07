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
package org.modelibra.wicket.concept.selection;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.modelibra.IEntities;
import org.modelibra.ISelector;
import org.modelibra.PropertySelector;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmForm;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity property selection panel Used for selection of entities based on a
 * property chosen by a user. Entities provided in ViewModel are used as source
 * for selection. When form is submitted response page (see getNewPageInstance
 * method) is set.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class PropertySelectorPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	private ViewModel viewModel;

	private View view;

	public PropertySelectorPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
		View selectionFormView = new View();
		selectionFormView.setWicketId("selectionForm");
		add(new SelectionForm(viewModel, selectionFormView));
	}

	private class SelectionForm extends DmForm {

		private static final long serialVersionUID = 1L;

		private PropertySelectorBean propertySelectorBean;

		private TextField stringTextField;

		private TextField value1TextField;

		private TextField value2TextField;

		private DateTextField date1TextField;

		private DateTextField date2TextField;

		private DropDownChoice selectionRuleChoice;

		private CheckBox caseSensitiveChoice;

		private boolean isBetween;

		public SelectionForm(final ViewModel viewModel, final View view) {
			super(viewModel, view);
			IEntities<?> entities = viewModel.getEntities();
			ConceptConfig conceptConfig = entities.getConceptConfig();
			final PropertiesConfig propertiesConfig = conceptConfig
					.getPropertiesConfig();
			propertySelectorBean = new PropertySelectorBean();

			CompoundPropertyModel propertySelectorBeanModel = new CompoundPropertyModel(
					propertySelectorBean);

			// rule selection options for string and for number/date
			// property
			final List<String> stringRules = Arrays
					.asList(PropertySelectorBean.STRING_RULES);
			final List<String> numberRules = Arrays
					.asList(PropertySelectorBean.NUMBER_RULES);

			List<PropertyConfig> propertiesConfigList = propertiesConfig
					.getList();

			// remove reference properties since that selection is not
			// supported for now. Remove Boolean properties
			for (Iterator<PropertyConfig> iterator = propertiesConfigList
					.iterator(); iterator.hasNext();) {
				PropertyConfig propertyConfig = iterator.next();
				if (propertyConfig.isReference()
						|| propertyConfig.getPropertyClassObject().equals(
								Boolean.class)) {
					iterator.remove();
				}

			}

			DropDownChoice propertyChoice = new DropDownChoice(
					"propertyChoice", propertySelectorBeanModel
							.bind("propertyConfig"), propertiesConfigList,
					new ChoiceRenderer() {
						private static final long serialVersionUID = 1L;

						@Override
						public Object getDisplayValue(Object object) {
							PropertyConfig propertyConfig = (PropertyConfig) object;
							String key = propertyConfig.getConceptConfig()
									.getCodeWithFirstLetterAsUpper()
									+ "." + propertyConfig.getCode();
							return getLocalizer().getString(key,
									PropertySelectorPanel.this);
						}
					}) {
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean wantOnSelectionChangedNotifications() {
					return true;
				}

				@Override
				protected void onSelectionChanged(Object newSelection) {
					PropertyConfig propertyConfig = (PropertyConfig) newSelection;
					Class<?> propertyClass = propertyConfig
							.getPropertyClassObject();
					selectionRuleChoice.setEnabled(true);
					if (propertyClass.equals(String.class)) {
						selectionRuleChoice.setChoices(stringRules);
						// show string field and case sensitive choice
						caseSensitiveChoice.setVisible(true);
						stringTextField.setVisible(true);
						// hide other fields
						value1TextField.setVisible(false);
						value2TextField.setVisible(false);
						date1TextField.setVisible(false);
						date2TextField.setVisible(false);
					} else if (propertyClass.getSuperclass().equals(
							Number.class)) {
						value1TextField.setType(propertyClass);
						value2TextField.setType(propertyClass);
						selectionRuleChoice.setChoices(numberRules);
						// show date fields - second one if between rule is
						// selected
						value1TextField.setVisible(true);
						value2TextField.setVisible(isBetween);
						// hide other fields
						caseSensitiveChoice.setVisible(false);
						stringTextField.setVisible(false);
						date1TextField.setVisible(false);
						date2TextField.setVisible(false);
					} else if (propertyClass.equals(Date.class)) {
						selectionRuleChoice.setChoices(numberRules);
						// show date fields - second one if between rule is
						// selected
						date1TextField.setVisible(true);
						date2TextField.setVisible(isBetween);
						// hide other fields
						caseSensitiveChoice.setVisible(false);
						stringTextField.setVisible(false);
						value1TextField.setVisible(false);
						value2TextField.setVisible(false);
					}
				}
			};

			add(propertyChoice.setLabel(
					new StringResourceModel("SearchBy",
							PropertySelectorPanel.this, null))
					.setRequired(true));

			// selection rule choice
			selectionRuleChoice = new DropDownChoice("selectionRuleChoice",
					propertySelectorBeanModel.bind("rule"), stringRules,
					new ChoiceRenderer() {
						private static final long serialVersionUID = 1L;

						@Override
						public Object getDisplayValue(Object object) {
							String rule = (String) object;
							return getLocalizer().getString(rule,
									PropertySelectorPanel.this);
						}
					}) {
				private static final long serialVersionUID = 1L;

				@Override
				protected boolean wantOnSelectionChangedNotifications() {
					return true;
				}

				@Override
				protected void onSelectionChanged(Object newSelection) {
					String selectedRule = (String) newSelection;
					isBetween = selectedRule.equals(ISelector.BETWEEN);
					value2TextField.setVisible(isBetween
							&& value1TextField.isVisible());
					date2TextField.setVisible(isBetween
							&& date1TextField.isVisible());
				}

			};

			add(selectionRuleChoice.setLabel(
					new StringResourceModel("Rule", PropertySelectorPanel.this,
							null)).setRequired(true).setEnabled(false));

			// case sensitive choice
			caseSensitiveChoice = new CheckBox("caseSensitiveChoice",
					propertySelectorBeanModel.bind("caseSensitive"));
			add(caseSensitiveChoice.setVisible(false));

			// string text field
			stringTextField = new RequiredTextField("stringTextField",
					propertySelectorBeanModel.bind("text"));
			add(stringTextField.setLabel(
					new StringResourceModel("Text", PropertySelectorPanel.this,
							null)).setVisible(false));

			// value 1 text field
			value1TextField = new RequiredTextField("value1TextField",
					propertySelectorBeanModel.bind("value1"));
			add(value1TextField.setLabel(
					new StringResourceModel("Value1",
							PropertySelectorPanel.this, null))
					.setVisible(false));

			// value 2 text field
			value2TextField = new RequiredTextField("value2TextField",
					propertySelectorBeanModel.bind("value2"));
			add(value2TextField.setLabel(
					new StringResourceModel("Value2",
							PropertySelectorPanel.this, null))
					.setVisible(false));

			String datePattern = viewModel.getModel().getModelConfig()
					.getDatePattern();

			// date 1 text field
			date1TextField = new DateTextField("date1TextField",
					propertySelectorBeanModel.bind("date1"), datePattern);
			add(date1TextField.setRequired(true).setLabel(
					new StringResourceModel("Date1",
							PropertySelectorPanel.this, null))
					.setVisible(false));

			// date 2 text field
			date2TextField = new DateTextField("date2TextField",
					propertySelectorBeanModel.bind("date2"), datePattern);
			add(date2TextField.setRequired(true).setLabel(
					new StringResourceModel("Date2",
							PropertySelectorPanel.this, null))
					.setVisible(false));

			// feedback panel
			add(new FeedbackPanel("feedback"));
		}

		@Override
		protected void onSubmit() {
			PropertySelector propertySelector = propertySelectorBean
					.getPropertySelector();

			View pageView = new View();
			pageView.copyPropertiesFrom(view);
			View contextView = new View();
			contextView.setPage(getPage());
			pageView.setContextView(contextView);

			ViewModel edViewModel = new ViewModel();
			edViewModel.copyPropertiesFrom(viewModel);
			edViewModel.setEntities(viewModel.getEntities().selectBySelector(
					propertySelector));
			setResponsePage(getNewPageInstance(edViewModel, pageView));
		}
	}

	/**
	 * Gets new instance of the page that will be used as a response page for
	 * search button onSubmit. By default EntityDisplayTablePage is used.
	 * Override this method to provide custom page. Note that viewModel contains
	 * selected entities. Providing new instance of current page you get effect
	 * of narrowing search.
	 * 
	 * @param viewModel
	 * @param view
	 * @return new page instance
	 */
	protected Page getNewPageInstance(ViewModel viewModel, View view) {
		DomainApp app = (DomainApp) getApplication();
		String model = viewModel.getModel().getModelConfig().getCode();
		return app.getViewMeta(model).getPage("EntityDisplayTablePage",
				viewModel, view);
	}

}
