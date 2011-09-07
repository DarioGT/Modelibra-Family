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
package twoadw.website.product;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codesmell.wicket.lightbox.LightBoxImage;
import org.modelibra.IDomainModel;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */

import twoadw.website.invoiceproduct.InvoiceProduct;
	import twoadw.website.productmanufacturer.ProductManufacturers;	
import twoadw.website.productrebate.ProductRebate;
	import twoadw.website.productrebate.ProductRebates;	
	import twoadw.website.specificationcategory.SpecificationCategories;	
import twoadw.website.productimage.ProductImage;
	import twoadw.website.productimage.ProductImages;	
	import twoadw.website.productcomment.ProductComments;	
import twoadw.website.question.Questions;	

/* ======= import external parent entity and entities classes ======= */


/**
 * Product specific entity.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public class Product extends GenProduct {

	private static final long serialVersionUID = 1234213171467L;

	private static Log log = LogFactory.getLog(Product.class);
    
	/* ======= base constructor ======= */
	
	/**
	 * Constructs product within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public Product(IDomainModel model) {
		super(model);
	}
	
	/* ======= parent argument(s) constructor ======= */
	
		
	/* ============================= */
	/* ======= SPECIFIC CODE ======= */
	/* ============================= */
    public double getRebatePrice(Product product) {
		double price = product.getPrice();

		for (ProductRebate productRebate : product.getProductRebates().orderByProperty("rebatePriority", true)) {
			if (productRebate.getRebate().getStart().before(new Date()) 
					&& productRebate.getRebate().getFinish().after(new Date()) == true
					&& productRebate.getRebate().getPostalRebate() == false ){
				if (productRebate.getRebate().getPercentRebate()== true){
					price = price * (1 - (productRebate.getRebate().getRebateValue())/100);
				}else{
					price = price - productRebate.getRebate().getRebateValue();
				}
			}
		}
		return price;
	}
    
    public List<LightBoxImage> getLightBoxImageList(Product product) {
    	List<LightBoxImage> list = new LinkedList<LightBoxImage>();
		for (ProductImage productImage : product.getProductImages()) {
			if (productImage.getImageUrlFullSize()!=null){
				LightBoxImage newImage = new LightBoxImage("/ModelibraWicket/" + productImage.getImageUrlFullSize(),"/ModelibraWicket/" + productImage.getImageUrl96x96());
				list.add(newImage);
			}else{
				LightBoxImage newImage = new LightBoxImage("/ModelibraWicket/" + productImage.getImageUrl180x130(),"/ModelibraWicket/" + productImage.getImageUrl96x96());
				list.add(newImage);
			}
		}
		return list;
	}
}