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
package course.lecture.point;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import course.lecture.slide.Slide;

/* ======= import internal child entities classes ======= */

/* ======= import external parent entity and entities classes ======= */

/**
 * Point specific entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-28
 */
public class Point extends GenPoint {

	private static final long serialVersionUID = 1176413492941L;

	private static Log log = LogFactory.getLog(Point.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs point within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Point(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument(s) constructor ======= */

	/**
	 * Constructs point within its parent(s).
	 * 
	 * @param Slide
	 *            slide
	 */
	public Point(Slide slide) {
		super(slide);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private String type;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		if (type == null) {
			if (getExplanation() != null) {
				type = "explanation";
			} else if (getUrl() != null) {
				type = "link";
			} else if (getImage() != null) {
				type = "image";
			} else if (getCode() != null) {
				type = "code";
			} else {
				type = "text";
			}
		}
		return type;
	}

}