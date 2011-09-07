package modelibra.wicket.properties;

import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converters.AbstractConverter;
import org.apache.wicket.util.string.StringValueConversionException;
import org.apache.wicket.util.string.Strings;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.type.DateConverter;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.comment.Comment;
import dmeduc.weblink.interest.Interest;
import dmeduc.weblink.member.Member;
import dmeduc.wicket.app.DmEducApp;

public class ShowCase extends WebPage {

	@SuppressWarnings("serial")
	public ShowCase() {
		DmEducApp dmEducApp = (DmEducApp) getApplication();
		WebLink webLink = dmEducApp.getDmEduc().getWebLink();
		Member member = webLink.getMembers().first();
		Comment comment = webLink.getComments().first();

		// PropertiesView

		// demonstrates following abilities of PropertiesView:
		// - if no model is provided for added component it will inherit
		// corresponding PropertyModel from item (see ComponentInheritedModel).
		// Many components can inherit same model this way
		Interest interest = member.getInterests().first();
		IEntityModel interestModel = new EntityModelImpl(interest);
		add(new PropertiesView("interestProperties", interestModel) {

			@Override
			protected void populateItem(PropertyItem item) {
				// add value
				item.add(new Label("value"));
			}
		});

		// demonstrates following abilities of PropertiesView:
		// - sorting properties to be used by overriding comparator method
		// - populating item with other components
		// - item's ability to provide property key that can be used to provide
		// localized property label
		IEntityModel compCommentModel = new EntityModelImpl(comment);

		add(new PropertiesView("commentProperties", compCommentModel) {

			@Override
			protected Comparator<PropertyConfig> comparator() {
				return new Comparator<PropertyConfig>() {

					@Override
					public int compare(PropertyConfig config1,
							PropertyConfig config2) {
						return config1.getCode().compareTo(config2.getCode());
					}

				};
			}

			@Override
			protected void populateItem(PropertyItem item) {
				// add label
				String propertyKey = item.getPropertyKey();
				ResourceModel labelModel = new ResourceModel(propertyKey);
				item.add(new Label("label", labelModel));
				// add value
				item.add(new Label("value"));
			}

		});

		IEntityModel memberModel = new EntityModelImpl(member);
		// demonstrates following abilities of PropertiesView:
		// - ability to use different markup (table)
		// - ability to determine component based on property (in generic
		// application there could be a factory that creates component based on
		// property configuration). For brevity all components, are created
		// inline as anonymous classes, but they could be standalone
		// components
		add(new PropertiesView("memberProperties", memberModel) {

			@Override
			protected void populateItem(PropertyItem item) {
				// add label
				String propertyKey = item.getPropertyKey();
				ResourceModel labelModel = new ResourceModel(propertyKey);
				item.add(new Label("label", labelModel));

				// add value
				if (item.getPropertyConfig().isScramble()) {
					item.add(new Label("value", "*********"));
				} else if (item.getPropertyClass().equals(Date.class)) {
					// for date properties, provide converter based on model
					// DatePattern
					item.add(new Label("value") {
						@SuppressWarnings("unchecked")
						@Override
						public IConverter getConverter(Class type) {
							String datePattern = getEntity().getConceptConfig()
									.getModelConfig().getDatePattern();
							return new DateConverter(datePattern);
						}
					});
				} else if (item.getPropertyClass().equals(Boolean.class)) {
					// lets use more user friendly values for boolean properties
					item.add(new Label("value") {
						@SuppressWarnings("unchecked")
						@Override
						public IConverter getConverter(Class type) {
							return BooleanConverter.INSTANCE;
						}
					});
				} else {
					item.add(new Label("value"));
				}
			}

		});

		// NeighborsView - similar to PropertiesView, but used for representing
		// entity neighbors (child or parent). NeighborItem is populated.
		// Note that it is combined with above properties view to compose table.
		add(new NeighborsView("memberNeighbors", memberModel) {

			@Override
			protected void populateItem(NeighborItem item) {
				// add label
				String propertyKey = item.getPropertyKey();
				ResourceModel labelModel = new ResourceModel(propertyKey);
				item.add(new Label("label", labelModel));

				// add value
				if (item.getNeighborConfig().isChild()) {
					// since it is child neighbor, item model is collection of
					// entities. Lets show number of child entities
					PropertyModel sizeModel = new PropertyModel(
							item.getModel(), "size");
					item.add(new Label("value", sizeModel));
				} else {
					// for parent neighbor, we will use actual entity as model
					// object for Label - remember PropertyModel is inherited
					// from NeighborItem the same way as for PropertyItem
					item.add(new Label("value"));
				}
			}

		});

		// Interest table

		// neighbors
		// note that this anonymous NeighborsView is same as one above (for
		// member). Thus, it could be reused if it was not anonymous.
		add(new NeighborsView("interestTableNeighbors", interestModel) {

			@Override
			protected void populateItem(NeighborItem item) {
				// add label
				String propertyKey = item.getPropertyKey();
				ResourceModel labelModel = new ResourceModel(propertyKey);
				item.add(new Label("label", labelModel));
				// add value
				if (item.getNeighborConfig().isChild()) {
					PropertyModel sizeModel = new PropertyModel(
							item.getModel(), "size");
					item.add(new Label("value", sizeModel));
				} else {
					item.add(new Label("value"));
				}
			}

		});

		// properties
		add(new PropertiesView("interestTableProperties", interestModel) {

			@Override
			protected void populateItem(PropertyItem item) {
				// add label
				String propertyKey = item.getPropertyKey();
				ResourceModel labelModel = new ResourceModel(propertyKey);
				item.add(new Label("label", labelModel));
				// add value
				item.add(new Label("value"));
			}
		});

	}

	static class BooleanConverter extends AbstractConverter {
		private static final long serialVersionUID = 1L;

		public static final IConverter INSTANCE = new BooleanConverter();

		@Override
		public Object convertToObject(String value, Locale locale) {
			try {
				return Strings.toBoolean(value);
			} catch (StringValueConversionException e) {
				throw newConversionException("Cannot convert '" + value
						+ "' to Boolean", value, locale);
			}
		}

		@Override
		public String convertToString(Object value, Locale locale) {
			if (value instanceof Boolean) {
				Boolean b = (Boolean) value;
				return b ? "yes" : "no";
			}

			return value.toString();
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Class getTargetType() {
			return Boolean.class;
		}
	}

}
