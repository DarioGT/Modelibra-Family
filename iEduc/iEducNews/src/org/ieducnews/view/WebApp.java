package org.ieducnews.view;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.ieducnews.model.DomainModel;

public class WebApp extends WebApplication {

	private DomainModel domainModel;

	public Class<HomePage> getHomePage() {
		return HomePage.class;

	}

	public void setDomainModel(DomainModel domainModel) {
		this.domainModel = domainModel;
	}

	public DomainModel getDomainModel() {
		if (domainModel == null) {
			domainModel = new DomainModel();
			domainModel = domainModel.load();
		}
		return domainModel;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new WebAppSession(request);
	}

}
