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

import dmeduc.weblink.url.Urls;

/* ======= import non entry external child/parent required concept entities ======= */

/**
 * WebLink generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author unknown
 * @version 2008-11-25
 */
public abstract class GenWebLink extends DomainModel {

	private static final long serialVersionUID = 1227641810949L;

	private static Log log = LogFactory.getLog(GenWebLink.class);

	private Urls urls;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenWebLink(IDomain domain) {
		super(domain);
		urls = new Urls(this);
	}

	/**
	 * Gets Url entities.
	 * 
	 * @return Url entities
	 */
	public Urls getUrls() {
		return urls;
	}

}
