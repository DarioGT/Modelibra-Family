package org.modelibra.swing.domain.model.concept.entity.neighbor;

import org.modelibra.DomainModel;
import org.modelibra.Entity;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.exception.ModelibraRuntimeException;

public class ParentLookupBridge {

	private IEntity<?> entity;

	private NeighborConfig parentNeighborConfig;

	public ParentLookupBridge(IEntity<?> entity,
			NeighborConfig parentNeighborConfig) {
		if (parentNeighborConfig.isChild()) {
			throw new ModelibraRuntimeException(
					"Only parent neighbor configuration allowed.");
		}
		this.entity = entity;
		this.parentNeighborConfig = parentNeighborConfig;
	}

	public void setParentEntity(IEntity<?> parentEntity) {
		getEntity().setParentNeighbor(getParentNeighborName(), parentEntity);
	}

	public IEntity<?> getParentEntity() {
		if (parentNeighborConfig.isParent()) {
			return getEntity().getParentNeighbor(getParentNeighborName());
		}
		return null;
	}

	public String getParentEntityEssentialString() {
		Entity<?> parentEntity = (Entity<?>) getParentEntity();
		if (parentEntity != null) {
			return parentEntity.toEssentialPropertiesString();
		}
		return null;
	}

	public IEntities<?> getLookupEntities() {
		ConceptConfig parentConceptConfig = parentNeighborConfig
				.getDestinationConceptConfig();

		// to support lookup of non-entry concepts
		IEntities<?> lookupEntities = ((DomainModel) getEntity().getModel())
				.getSpecificModelEntities(parentConceptConfig.getEntitiesCode());
		return lookupEntities;
	}

	public IEntity<?> getLookupEntity(String entityEssentialString) {
		IEntities<?> lookupEntities = getLookupEntities();
		if (lookupEntities != null) {
			for (IEntity<?> entity : lookupEntities) {
				Entity<?> lookupEntity = (Entity<?>) entity;
				if (lookupEntity.toEssentialPropertiesString().equals(
						entityEssentialString)) {
					return lookupEntity;
				}
			}
		}
		return null;
	}

	public IEntity<?> getEntity() {
		return entity;
	}

	public NeighborConfig getParentNeighborConfig() {
		return parentNeighborConfig;
	}

	public String getParentNeighborName() {
		return parentNeighborConfig.getCode();
	}

}
