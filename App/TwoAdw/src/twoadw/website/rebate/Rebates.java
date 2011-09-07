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
package twoadw.website.rebate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomainModel;
import org.modelibra.type.EasyDate;

/* ======= import internal parent entity class ======= */


/* ======= import external parent entity and entities classes ======= */


/**
 * Rebate specific entities.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Rebates extends GenRebates {
	
	private static final long serialVersionUID = 1236704376850L;

	private static Log log = LogFactory.getLog(Rebates.class);

	/* ======= base constructor ======= */
	
	/**
	 * Constructs rebates within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Rebates(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument constructors ======= */
	
	    
    /* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
	/**
	 * Creates rebate.
	 * 
	 * @param rebateName
	 *            rebateName
	 * @return rebate
	 */
	public Rebate createRebate(String rebateName, String description, Double rebateValue, Boolean postalRebate,
			Boolean percentRebate, Long rebatePriority, EasyDate start, EasyDate finish) {
		Rebate rebate = new Rebate(getModel());
		rebate.setRebateName(rebateName);
		rebate.setDescription(description);
		rebate.setRebateValue(rebateValue);
		rebate.setPostalRebate(postalRebate);
		rebate.setPercentRebate(percentRebate);
		rebate.setRebatePriority(rebatePriority);
		rebate.setStart(start.getDate());
		rebate.setFinish(finish.getDate());
		if (!add(rebate)) {
			rebate = null;
		}
		return rebate;
	}
}