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
package dmeduc.weblink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import dmeduc.weblink.category.Categories;

/* ======= import non entry external child/parent required concept entities ======= */

/**
 * WebLink generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author unknown
 * @version 2008-12-02
 */
public abstract class GenWebLink extends DomainModel {

	private static final long serialVersionUID = 1228241321971L;

	private static Log log = LogFactory.getLog(GenWebLink.class);

	private Categories categories;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenWebLink(IDomain domain) {
		super(domain);
		categories = new Categories(this);
	}

	/**
	 * Gets Category entities.
	 * 
	 * @return Category entities
	 */
	public Categories getCategories() {
		return categories;
	}

}
