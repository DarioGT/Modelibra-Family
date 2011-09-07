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
package org.modelibra.persistency;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.util.OutTester;

/**
 * Migrates the first persistent model to the second persistent model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public abstract class Migrator {

	private static Log log = LogFactory.getLog(Migrator.class);

	/**
	 * Constructs the migrator.
	 * 
	 * @param firstModel
	 *            first persistent model
	 * @param secondModel
	 *            second persistent model
	 */
	public Migrator(IPersistentModel firstModel, IPersistentModel secondModel) {
		super();
		migrate(firstModel, secondModel);
	}

	/**
	 * Migrates first persistent model to the second persistent model.
	 * 
	 * @param firstModel
	 *            first persistent model
	 * @param secondModel
	 *            second persistent model
	 */
	private void migrate(IPersistentModel firstModel,
			IPersistentModel secondModel) {
		DomainModel firstDomainModel = (DomainModel) firstModel.getModel();
		String firstModelCode = firstDomainModel.getModelConfig().getCode();
		DomainModel secondDomainModel = (DomainModel) secondModel.getModel();
		String secondModelCode = secondDomainModel.getModelConfig().getCode();
		log.info("--- Migration from " + firstModelCode + " to "
				+ secondModelCode + " begins. ---");
		((PersistentModel) secondModel).setLoaded(false);
		List<IEntities<?>> firstModelEntryList = firstDomainModel
				.getEntryList();
		for (IEntities<?> firstModelEntry : firstModelEntryList) {
			String firstModelEntryCode = firstModelEntry.getConceptConfig()
					.getCode();
			IEntities secondModelEntry = secondModel.getModel().getEntry(
					firstModelEntryCode);
			secondModelEntry.getErrors().empty();
			synchronized (firstModelEntry) {
				for (Object firstModelEntryEntityObject : firstModelEntry) {
					IEntity<?> firstModelEntryEntity = (IEntity<?>) firstModelEntryEntityObject;
					if (!secondModelEntry.add(firstModelEntryEntity)) {
						log.info("Add error: "
								+ firstModelEntryCode
								+ " "
								+ firstModelEntryEntity.getOid()
										.getUniqueNumber());
					}
				}
			}
			List<String> errors = secondModelEntry.getErrors().getErrorList();
			if (errors.size() > 0) {
				OutTester.outputCollection(errors, firstModelEntryCode
						+ " Add Errors");
			}
		}
		secondModel.save();
		log.info("--- Migration ends. ---");
	}

}
