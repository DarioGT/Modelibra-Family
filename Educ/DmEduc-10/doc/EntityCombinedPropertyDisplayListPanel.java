package modelibra.wicket.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.IEntity;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity combined property display list panel.
 * 
 * @author Nijaz Nanic
 * @version 2008-10-14
 */
@SuppressWarnings("serial")
public class EntityCombinedPropertyDisplayListPanel extends DmPanel {

	public EntityCombinedPropertyDisplayListPanel(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);

		String sectionTitle = LocalizedText.getApplicationPropertiesText(this,
				view.getTitle());
		add(new Label("sectionTitle", sectionTitle));

		View entityCompositePropertyView = new View();
		entityCompositePropertyView.copyPropertiesFrom(view);
		entityCompositePropertyView.setWicketId("compositePropertyList");

		ListView entityCompositePropertyDisplayListView = new CompositePropertyDisplayListView(
				viewModel, entityCompositePropertyView);
		add(entityCompositePropertyDisplayListView);

		DomainApp app = (DomainApp) getApplication();
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				viewModel.getEntities().getConceptConfig())) {
			entityCompositePropertyDisplayListView.setVisible(false);
		}
	}

	private class CompositePropertyDisplayListView extends DmListView {
		
		private ViewModel viewModel;

		private View view;

		public CompositePropertyDisplayListView(final ViewModel viewModel,
				final View view) {
			super(viewModel, view);
			this.viewModel = viewModel;
			this.view = view;
		}

		protected void populateItem(final ListItem item) {
			IEntity<?> entity = (IEntity<?>) item.getModelObject();
			DomainApp app = (DomainApp) getApplication();
			PropertiesConfig propertiesConfig = entity.getConceptConfig()
					.getPropertiesConfig();
			String combinedProperty = "";
			Object separator = view.getUserProperties().getUserProperty(
					"separatorText");
			String separatorText = " ";
			if (separator != null) {
				separatorText = (String) separator;
			}
			boolean firstUserProperty = true;
			for (PropertyConfig propertyConfig : propertiesConfig) {
				String propertyCode = propertyConfig.getCode();
				Object userProperty = viewModel.getUserProperties().getUserProperty(
						propertyCode);
				if (userProperty != null) {
					Object property = entity.getProperty(propertyCode);
					if (app.getAccessPoint().isPropertyDisplayAllowed(
							getAppSession(), propertyConfig)) {
						if (firstUserProperty) {
							combinedProperty = property.toString();
							firstUserProperty = false;
						} else {
							combinedProperty = combinedProperty
									+ separatorText + property.toString();
						}
					}
				}
			}
			item.add(new Label("combinedProperty", combinedProperty));
		}
		
	}

}
