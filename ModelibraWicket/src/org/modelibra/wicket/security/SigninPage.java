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
package org.modelibra.wicket.security;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.modelibra.IDomain;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.IDomainModels;
import org.modelibra.DomainModel;
import org.modelibra.config.DomainConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Validates a sign in user.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public final class SigninPage extends DmPage {

	private static final long serialVersionUID = 106020L;

	private static Log log = LogFactory.getLog(SigninPage.class);

	/**
	 * Constructs a sign in page.
	 */
	public SigninPage() {
		DomainApp app = (DomainApp) getApplication();
		IDomain domain = app.getDomain();
		DomainConfig domainConfig = domain.getDomainConfig();
		String signinConcept = domainConfig.getSigninConcept();
		IDomainModel signinModel = getSigninModel(domain, signinConcept);
		IEntities signinEntities = null;
		IEntity signinEntity = null;
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
				log.error(signinModel
						+ " sign in model does not have the sign in entities.");
			}
		} else {
			log.error(domainConfig.getCode()
					+ " domain does not have the sign in model.");
		}

		ViewModel signInViewModel = new ViewModel();
		signInViewModel.setModel(signinModel);
		signInViewModel.setEntities(signinEntities);
		signInViewModel.setEntity(signinEntity);

		View signinView = new View();
		signinView.setPage(this);
		signinView.setContextView(signinView);
		signinView.setUpdate(true);
		signinView.setWicketId("signinPanel");
		String modelCode = signinModel.getModelConfig().getCode();
		add(app.getViewMeta(modelCode).getSigninPanel(signInViewModel,
				signinView));

		add(new FeedbackPanel("signinFeedback"));
	}

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
		IEntities signinEntities = null;

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
