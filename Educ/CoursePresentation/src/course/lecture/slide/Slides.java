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
package course.lecture.slide;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import course.lecture.presentation.Presentation;

/* ======= import external parent entity and entities classes ======= */

/**
 * Slide specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class Slides extends GenSlides {

	private static final long serialVersionUID = 1176413490737L;

	private static Log log = LogFactory.getLog(Slides.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs slides within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Slides(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs slides for the presentation parent.
	 * 
	 * @param presentation
	 *            presentation
	 */
	public Slides(Presentation presentation) {
		super(presentation);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

}