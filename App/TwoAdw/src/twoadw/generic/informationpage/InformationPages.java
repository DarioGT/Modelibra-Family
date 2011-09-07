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
package twoadw.generic.informationpage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * InformationPage specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class InformationPages extends GenInformationPages {
	
	private static final long serialVersionUID = 1236708441726L;

	private static Log log = LogFactory.getLog(InformationPages.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs informationPages within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public InformationPages(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	/**
	 * Creates informationPage.
	 * 
	 * @param codePage
	 *            codePage
	 * @param title
	 *            title
	 * @param page
	 *            page
	 * @return informationPage
	 */
	public InformationPage createInformationPage(String codePage, String title,
			String page, Boolean published) {
		InformationPage informationPage = new InformationPage(getModel());
		informationPage.setCodePage(codePage);
		informationPage.setTitle(title);
		informationPage.setPage(page);
		informationPage.setPublished(published);
		if (!add(informationPage)) {
			informationPage = null;
		}
		return informationPage;
	}
}