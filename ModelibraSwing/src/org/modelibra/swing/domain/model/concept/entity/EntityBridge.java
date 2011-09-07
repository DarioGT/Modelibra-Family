package org.modelibra.swing.domain.model.concept.entity;

import java.util.Date;
import java.util.List;

import org.modelibra.DomainModel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.ModelMeta;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.swing.domain.model.concept.entity.property.PropertyBridge;
import org.modelibra.type.PropertyClass;
import org.modelibra.util.Transformer;

public class EntityBridge {

	public static final String DEFAULT_URL = "http://www.modelibra.swing.org/";

	public static final String DEFAULT_EMAIL = "modelibra.swing@email.org";

	private IEntities<?> entities;

	private List<PropertyConfig> propertyConfigList;

	private List<NeighborConfig> parentNeighborConfigList;

	public EntityBridge(IEntities<?> entities,
			List<PropertyConfig> propertyConfigList,
			List<NeighborConfig> parentConfigList) {
		this.entities = entities;
		this.propertyConfigList = propertyConfigList;
		this.parentNeighborConfigList = parentConfigList;
	}

	public IEntity<?> createDefaultEntity() {
		ModelMeta modelMeta = new ModelMeta(entities.getModel());
		IEntity<?> newEntity = modelMeta.createEntity(entities);
		for (PropertyConfig propertyConfig : propertyConfigList) {
			if (!propertyConfig.isDerived()) {
				String defaultString = propertyConfig.getDefaultValue();
				if (defaultString != null) {
					PropertyBridge propertyBridge = new PropertyBridge(newEntity,
							propertyConfig);
					propertyBridge.setProperty(defaultString);
				} else if (!propertyConfig.isRequired()) {
					newEntity.setProperty(propertyConfig.getCode(), null);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())) {
					if (propertyConfig.isValidateClassType()) {
						if (propertyConfig.getValidationType().equals(
								PropertyClass.getUrl())) {
							newEntity.setProperty(propertyConfig.getCode(),
									DEFAULT_URL);
						} else if (propertyConfig.getValidationType().equals(
								PropertyClass.getEmail())) {
							newEntity.setProperty(propertyConfig.getCode(),
									DEFAULT_EMAIL);
						}
					} else {
						newEntity.setProperty(propertyConfig.getCode(), "");
					}
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getInteger())
						|| propertyConfig.getPropertyClass().equals(
								PropertyClass.getLong())) {
					newEntity.setProperty(propertyConfig.getCode(), 0);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getBoolean())) {
					newEntity.setProperty(propertyConfig.getCode(), Boolean.FALSE);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getFloat())
						|| propertyConfig.getPropertyClass().equals(
								PropertyClass.getDouble())) {
					newEntity.setProperty(propertyConfig.getCode(), 0);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getDate())) {
					newEntity.setProperty(propertyConfig.getCode(), new Date());
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getUrl())) {
					newEntity.setProperty(propertyConfig.getCode(), Transformer
							.url(DEFAULT_URL));
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getEmail())) {
					newEntity.setProperty(propertyConfig.getCode(), Transformer
							.email(DEFAULT_EMAIL));
				}
			}
		}

		if (parentNeighborConfigList != null) {
			for (NeighborConfig parentNeighborConfig : parentNeighborConfigList) {
				if (parentNeighborConfig.isParent() && parentNeighborConfig.isMandatory()) {
					if (newEntity.getParentNeighbor(parentNeighborConfig
							.getCode()) == null) {
						IEntity<?> firstParent = null;
						IEntities<?> lookupEntities = getLookupEntities(
								newEntity, parentNeighborConfig);
						if (lookupEntities != null) {
							if (lookupEntities.size() > 0) {
								firstParent = lookupEntities.first();
							}
						}
						if (firstParent != null) {
							newEntity.setParentNeighbor(parentNeighborConfig
									.getCode(), firstParent);
						}
					}
				}
			}
		}

		return newEntity;
	}

	private IEntities<?> getLookupEntities(IEntity<?> newEntity,
			NeighborConfig parentNeighborConfig) {
		ConceptConfig parentConceptConfig = parentNeighborConfig
				.getDestinationConceptConfig();

		// to support lookup of non-entry concepts
		IEntities<?> lookupEntities = ((DomainModel) newEntity.getModel())
				.getSpecificModelEntities(parentConceptConfig.getEntitiesCode());
		return lookupEntities;
	}

}
