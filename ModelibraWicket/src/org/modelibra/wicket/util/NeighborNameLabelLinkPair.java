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
package org.modelibra.wicket.util;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

/**
 * Neighbor name label/link pair.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-03-27
 */
@SuppressWarnings("serial")
public class NeighborNameLabelLinkPair implements Serializable {

	private Label neighborNameLabel;

	private Link neighborLink;

	/**
	 * Constructs a neighbor name label/link pair.
	 */
	public NeighborNameLabelLinkPair() {
		super();
	}

	/**
	 * Sets a neighbor name label.
	 * 
	 * @param neighborNameLabel
	 *            neighbor name label
	 */
	public void setNeighborNameLabel(Label neighborNameLabel) {
		this.neighborNameLabel = neighborNameLabel;
	}

	/**
	 * Gets a neighbor name label.
	 * 
	 * @return neighbor name label
	 */
	public Label getNeighborNameLabel() {
		return neighborNameLabel;
	}

	/**
	 * Sets a neighbor link.
	 * 
	 * @param neighborLink
	 *            neighbor link
	 */
	public void setNeighborLink(Link neighborLink) {
		this.neighborLink = neighborLink;
	}

	/**
	 * Gets a neighbor link.
	 * 
	 * @return neighbor link
	 */
	public Link getNeighborLink() {
		return neighborLink;
	}

}