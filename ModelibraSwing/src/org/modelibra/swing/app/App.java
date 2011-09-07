package org.modelibra.swing.app;

import org.modelibra.IDomain;
import org.modelibra.IDomainModel;
import org.modelibra.ModelSession;
import org.modelibra.util.NatLang;

public class App {

	private IDomain domain;

	private ModelSession modelSession;

	private NatLang natLang;

	public App(IDomain domain, NatLang natLang) {
		this.domain = domain;
		this.natLang = natLang;
	}

	public IDomain getDomain() {
		return domain;
	}

	public IDomainModel getDomainModel(String modelCode) {
		if (domain != null) {
			return domain.getModel(modelCode);
		}
		return null;
	}

	public void setModelSession(ModelSession modelSession) {
		this.modelSession = modelSession;
	}

	public ModelSession getModelSession() {
		return modelSession;
	}

	public NatLang getNatLang() {
		return natLang;
	}

}
