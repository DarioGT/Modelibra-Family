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
package org.modelibra.wicket.security.registration;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.util.string.StringValueConversionException;
import org.modelibra.IDomain;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.IDomainModels;
import org.modelibra.DomainModel;
import org.modelibra.Oid;
import org.modelibra.config.DomainConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Abstract page used for Registration Confirmation. Subclasses need to
 * implement getApplicantEntities() method. There is no need for the html
 * template in a subclass. However if you want to provide additional content
 * then create new template and use wicket:extend tag.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public abstract class RegistrationConfirmationPage extends DmPage {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory
			.getLog(RegistrationConfirmationPage.class);

	public RegistrationConfirmationPage(PageParameters pageParameters) {
		try {
			long uniqueNumber = pageParameters.getLong("register");
			Oid oid = new Oid(uniqueNumber);

			DomainApp domainApp = (DomainApp) getApplication();
			IDomain domain = domainApp.getDomain();
			DomainConfig domainConfig = domain.getDomainConfig();
			String signinConcept = domainConfig.getSigninConcept();
			DomainModel signinModel = (DomainModel) getSigninModel(domain, signinConcept);
			IEntities signinEntities = null;
			IEntity<?> signinEntity = null;
			if (signinModel != null) {
				signinEntities = signinModel.getEntry(signinConcept);
				if (signinEntities != null) {
					signinEntity = ((DomainModel) signinModel).getModelMeta()
							.createEntity(signinEntities);
					if (signinEntity == null) {
						log
								.error(signinModel
										+ " sign in model does not have the sign in entity.");
					}
				} else {
					log
							.error(signinModel
									+ " sign in model does not have the sign in entities.");
				}
			} else {
				log.error(domainConfig.getCode()
						+ " domain does not have the sign in model.");
			}

			// Try to retrieve applicant based on page parameter "register/oid"
			IEntities applicants = getApplicantEntities();

			Class<?> applicantsClass = applicants.getClass();
			Class<?> signinEntitiesClass = signinEntities.getClass();
			if (applicantsClass.getSuperclass().equals(signinEntitiesClass)
					|| applicantsClass.equals(signinEntitiesClass)) {
				IEntity<?> applicant = applicants.retrieveByOid(oid);
				Component panel;
				if (applicant != null && signinEntity != null) {
					signinModel.getModelMeta().updateProperties(signinEntity,
							applicant);
					if (signinEntities.add(signinEntity)) {
						applicants.remove(applicant);
					}
					panel = getComponentForRegistered(applicant);
				} else {// there is no applicant
					panel = getComponentForNotRegistered();
				}
				add(panel);
			} else {
				log
						.error("Error in RegistrationConfirmationPage: applicant entities have to be subclass of signin concept: "
								+ signinConcept);
			}
		} catch (StringValueConversionException e) {
			// if user manually edits the url and break parameters redirect
			setResponsePage(getRedirectPageClass());
		}
	}

	/**
	 * Gets applicant entities. Applicant entities must be either same type or
	 * subclass of signin entities.
	 * 
	 * @return applicant entities
	 */
	protected abstract IEntities getApplicantEntities();

	/**
	 * Gets panel that will be used in this page if user is successfully
	 * registered. Override this method to provide different panel.
	 * 
	 * Note that component id need to be "confirmation" if original page html
	 * template is used or extended. If new template is used then only
	 * limitation regarding component id is that both ComponentForNotRegistered
	 * and ComponentForRegistered need to have the same id since this page uses
	 * only one of them in the same place.
	 * 
	 * @param entity
	 *            applicant entity that represents registered user
	 * 
	 * @return component
	 */
	protected Component getComponentForRegistered(IEntity<?> entity) {
		ViewModel viewModel = new ViewModel();
		viewModel.setEntity(entity);

		View view = new View();
		view.setWicketId("confirmation");
		return new RegisteredPanel(viewModel, view);
	};

	/**
	 * Gets panel that will be used in this page if user registration failed due
	 * to invalid code. Override this method to provide different panel.
	 * 
	 * Note that component id need to be "confirmation" if original page html
	 * template is used or extended. If new template is used then only
	 * limitation regarding component id is that both ComponentForNotRegistered
	 * and ComponentForRegistered need to have the same id since this page uses
	 * only one of them.
	 * 
	 * @return component
	 */
	protected Component getComponentForNotRegistered() {
		View view = new View();
		view.setWicketId("confirmation");
		return new NotRegisteredPanel(new ViewModel(), view);
	};

	/**
	 * Gets redirect page that will be used if user manually changes url, thus
	 * providing corrupted parameter. Override this method to provide different
	 * page.
	 * 
	 * @return redirect page
	 */
	protected Class<?> getRedirectPageClass() {
		return getApplication().getHomePage();
	};

	/**
	 * Gets the sign in model.
	 * 
	 * @param domain
	 *            domain
	 * @param signinConcept
	 *            sign in concept
	 * @return sign in model
	 */
	private IDomainModel getSigninModel(IDomain domain, String signinConcept) {
		IDomainModel referenceModel = (DomainModel) domain.getReferenceModel();
		IEntities<?> signinEntities = null;

		IDomainModels models = domain.getModels();
		List<IDomainModel> modelList = models.getList();
		for (IDomainModel model : modelList) {
			if (model.equals(referenceModel)) {
				continue;
			} else {
				signinEntities = model.getEntry(signinConcept);
				if (signinEntities != null) {
					return model;
				}
			}
		}

		if (referenceModel != null) {
			signinEntities = referenceModel.getEntry(signinConcept);
			if (signinEntities != null) {
				return referenceModel;
			}
		}

		return null;
	}

}
