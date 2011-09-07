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
package sales.cheesestore.discount;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;

import sales.cheesestore.cheese.Cheese;

/**
 * Discount specific entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-03-23
 */
public class Discounts extends GenDiscounts {

	private static final long serialVersionUID = 1237249993768L;

	private static Log log = LogFactory.getLog(Discounts.class);

	/* ======= base constructor ======= */

	/**
	 * Constructs discounts within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Discounts(IDomainModel model) {
		super(model);
	}

	/* ======= parent argument constructors ======= */

	/**
	 * Constructs discounts for the cheese parent.
	 * 
	 * @param cheese
	 *            cheese
	 */
	public Discounts(Cheese cheese) {
		super(cheese);
	}

	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */

	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyy.MM.dd-HH:mm");

	public CharSequence export() {
		StringBuilder b = new StringBuilder();
		for (Discount discount : this) {
			b.append(discount.getCheese().getName());
			b.append(',');
			b.append(discount.getPercentage());
			b.append(',');
			b.append(DATE_TIME_FORMAT.format(discount.getFrom()));
			b.append(',');
			b.append(DATE_TIME_FORMAT.format(discount.getUntil()));
			b.append(',');
			b.append(discount.getDescription());
			b.append('\n');
		}
		return b;
	}

}