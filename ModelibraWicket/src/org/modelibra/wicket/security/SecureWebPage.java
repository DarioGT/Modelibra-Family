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

import org.modelibra.wicket.container.DmPage;

/**
 * Web page accessible by the signed in members.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-05-11
 */
@SuppressWarnings("serial")
public abstract class SecureWebPage extends DmPage implements
		ISimpleAuthorization {

	/**
	 * Constructs a secure web page.
	 */
	public SecureWebPage() {
		super();
	}

}
