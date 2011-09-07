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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IDomainModel;
import org.modelibra.Oid;

/* ======= import model class ======= */

import twoadw.website.Website;

/* ======= import property classes ======= */

	import java.util.Date;	

/* ======= import internal parent entity class ======= */


/* ======= import internal child entities classes ======= */

	import twoadw.website.productmanufacturer.ProductManufacturers;	
	import twoadw.website.productrebate.ProductRebates;	
	import twoadw.website.specificationcategory.SpecificationCategories;	
	import twoadw.website.productimage.ProductImages;	
	import twoadw.website.productcomment.ProductComments;	
	import twoadw.website.question.Questions;	

/* ======= import external parent entity and entities classes ======= */


/* ======= import external child entities classes ======= */

	import twoadw.website.invoiceproduct.InvoiceProducts;	
	import twoadw.website.assignproductcategory.AssignProductCategories;	

/* ======= import external many-to-many internal parent entities classes ======= */

			import twoadw.website.invoice.Invoices;
				import twoadw.website.productcategory.ProductCategories;
	
/**
 * Product generated entity. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenProduct extends Entity<Product> {

	private static final long serialVersionUID = 1234213171465L;

	private static Log log = LogFactory.getLog(GenProduct.class);

	/* ======= entity properties (without the code, reference and derived properties) ======= */
	
    	    private String productNumber;
	
    	    private String name;
	
    	    private String shortDescription;
	
    	    private String longDescription;
	
    	    private Double price;
	
    	    private Date startDate;
	
    	    private Boolean published;
	
    	    private Long soldNumber;
	
    	    private Boolean frontpage;
	
        
    /* ======= reference properties ======= */
	
        
    /* ======= internal parent neighbors ======= */
	
    
	/* ======= internal child neighbors ======= */
	
    	    private ProductManufacturers productManufacturers;
	
    	    private ProductRebates productRebates;
	
    	    private SpecificationCategories specificationCategories;
	
    	    private ProductImages productImages;
	
    	    private ProductComments productComments;
	
    	    private Questions questions;
	
        
    /* ======= external parent neighbors ======= */
	
    
	/* ======= external child neighbors ======= */
	
    	    private InvoiceProducts invoiceProducts;
	
    	    private AssignProductCategories assignProductCategories;
	
        
	/* ======= base constructor ======= */
	
	/**
	 * Constructs product within the domain model.
	 * 
	 * @param model
	 *            model
	 */
	public GenProduct(IDomainModel model) {
		super(model);
		// internal child neighbors only
			    	setProductManufacturers(new ProductManufacturers((Product) this));
    		    	setProductRebates(new ProductRebates((Product) this));
    		    	setSpecificationCategories(new SpecificationCategories((Product) this));
    		    	setProductImages(new ProductImages((Product) this));
    		    	setProductComments(new ProductComments((Product) this));
    		    	setQuestions(new Questions((Product) this));
    		}
	
	/* ======= parent argument(s) constructor ======= */
	
	    
    /* ======= entity property (without the code, reference and derived properties) set and get methods ======= */
	
    		/**
		 * Sets productNumber.
		 * 
		 * @param productNumber
		 *            productNumber
		 */
		public void setProductNumber(String productNumber) {
			this.productNumber = productNumber;
		}
		
		/**
		 * Gets productNumber.
		 * 
		 * @return productNumber
		 */
		public String getProductNumber() {
			return productNumber;
		}  
		
				    		/**
		 * Sets name.
		 * 
		 * @param name
		 *            name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Gets name.
		 * 
		 * @return name
		 */
		public String getName() {
			return name;
		}  
		
				    		/**
		 * Sets shortDescription.
		 * 
		 * @param shortDescription
		 *            shortDescription
		 */
		public void setShortDescription(String shortDescription) {
			this.shortDescription = shortDescription;
		}
		
		/**
		 * Gets shortDescription.
		 * 
		 * @return shortDescription
		 */
		public String getShortDescription() {
			return shortDescription;
		}  
		
				    		/**
		 * Sets longDescription.
		 * 
		 * @param longDescription
		 *            longDescription
		 */
		public void setLongDescription(String longDescription) {
			this.longDescription = longDescription;
		}
		
		/**
		 * Gets longDescription.
		 * 
		 * @return longDescription
		 */
		public String getLongDescription() {
			return longDescription;
		}  
		
				    		/**
		 * Sets price.
		 * 
		 * @param price
		 *            price
		 */
		public void setPrice(Double price) {
			this.price = price;
		}
		
		/**
		 * Gets price.
		 * 
		 * @return price
		 */
		public Double getPrice() {
			return price;
		}  
		
				    		/**
		 * Sets startDate.
		 * 
		 * @param startDate
		 *            startDate
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		
		/**
		 * Gets startDate.
		 * 
		 * @return startDate
		 */
		public Date getStartDate() {
			return startDate;
		}  
		
				    		/**
		 * Sets published.
		 * 
		 * @param published
		 *            published
		 */
		public void setPublished(Boolean published) {
			this.published = published;
		}
		
		/**
		 * Gets published.
		 * 
		 * @return published
		 */
		public Boolean getPublished() {
			return published;
		}  
		
							/**
		     * Sets published.
		     * 
		     * @param published
		     *            published
		     */
			public void setPublished(boolean published) {
				setPublished(new Boolean(published));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isPublished() {
				return getPublished().booleanValue();
			}
			
		    		/**
		 * Sets soldNumber.
		 * 
		 * @param soldNumber
		 *            soldNumber
		 */
		public void setSoldNumber(Long soldNumber) {
			this.soldNumber = soldNumber;
		}
		
		/**
		 * Gets soldNumber.
		 * 
		 * @return soldNumber
		 */
		public Long getSoldNumber() {
			return soldNumber;
		}  
		
				    		/**
		 * Sets frontpage.
		 * 
		 * @param frontpage
		 *            frontpage
		 */
		public void setFrontpage(Boolean frontpage) {
			this.frontpage = frontpage;
		}
		
		/**
		 * Gets frontpage.
		 * 
		 * @return frontpage
		 */
		public Boolean getFrontpage() {
			return frontpage;
		}  
		
							/**
		     * Sets frontpage.
		     * 
		     * @param frontpage
		     *            frontpage
		     */
			public void setFrontpage(boolean frontpage) {
				setFrontpage(new Boolean(frontpage));
			}

			/**
	          * Checks if it is <code>true</code> or <code>false</code>.
	          * 
	          * @return <code>true</code> or <code>false</code>
	          */
			public boolean isFrontpage() {
				return getFrontpage().booleanValue();
			}
			
		        
    /* ======= reference property set and get methods ======= */
    
        
    /* ======= derived property abstract get methods ======= */
    
        
    /* ======= internal parent set and get methods ======= */
    
    	
	/* ======= internal child set and get methods ======= */
    
        	/**
		 * Sets productManufacturers.
		 * 
		 * @param productManufacturers
		 *            productManufacturers
		 */
    	public void setProductManufacturers(ProductManufacturers productManufacturers) {
			this.productManufacturers = productManufacturers;
			if (productManufacturers != null) {
				productManufacturers.setProduct((Product) this);
			}
		}

		/**
		 * Gets productManufacturers.
		 * 
		 * @return productManufacturers
		 */
		public ProductManufacturers getProductManufacturers() {
			return productManufacturers;
		}
		
	    	/**
		 * Sets productRebates.
		 * 
		 * @param productRebates
		 *            productRebates
		 */
    	public void setProductRebates(ProductRebates productRebates) {
			this.productRebates = productRebates;
			if (productRebates != null) {
				productRebates.setProduct((Product) this);
			}
		}

		/**
		 * Gets productRebates.
		 * 
		 * @return productRebates
		 */
		public ProductRebates getProductRebates() {
			return productRebates;
		}
		
	    	/**
		 * Sets specificationCategories.
		 * 
		 * @param specificationCategories
		 *            specificationCategories
		 */
    	public void setSpecificationCategories(SpecificationCategories specificationCategories) {
			this.specificationCategories = specificationCategories;
			if (specificationCategories != null) {
				specificationCategories.setProduct((Product) this);
			}
		}

		/**
		 * Gets specificationCategories.
		 * 
		 * @return specificationCategories
		 */
		public SpecificationCategories getSpecificationCategories() {
			return specificationCategories;
		}
		
	    	/**
		 * Sets productImages.
		 * 
		 * @param productImages
		 *            productImages
		 */
    	public void setProductImages(ProductImages productImages) {
			this.productImages = productImages;
			if (productImages != null) {
				productImages.setProduct((Product) this);
			}
		}

		/**
		 * Gets productImages.
		 * 
		 * @return productImages
		 */
		public ProductImages getProductImages() {
			return productImages;
		}
		
	    	/**
		 * Sets productComments.
		 * 
		 * @param productComments
		 *            productComments
		 */
    	public void setProductComments(ProductComments productComments) {
			this.productComments = productComments;
			if (productComments != null) {
				productComments.setProduct((Product) this);
			}
		}

		/**
		 * Gets productComments.
		 * 
		 * @return productComments
		 */
		public ProductComments getProductComments() {
			return productComments;
		}
		
	    	/**
		 * Sets questions.
		 * 
		 * @param questions
		 *            questions
		 */
    	public void setQuestions(Questions questions) {
			this.questions = questions;
			if (questions != null) {
				questions.setProduct((Product) this);
			}
		}

		/**
		 * Gets questions.
		 * 
		 * @return questions
		 */
		public Questions getQuestions() {
			return questions;
		}
		
		
	/* ======= external parent set and get methods ======= */
    
    	
	/* ======= external one-to-many child set and get methods ======= */
    
    	
	/* ======= external (part of) many-to-many child set and get methods ======= */
    
    					/**
			 * Sets invoiceProducts.
			 * 
			 * @param invoiceProducts
			 *            invoiceProducts
			 */
	    	public void setInvoiceProducts(InvoiceProducts invoiceProducts) {
				this.invoiceProducts = invoiceProducts;
				if (invoiceProducts != null) {
					invoiceProducts.setProduct((Product) this);
				}
			}
	
			/**
			 * Gets invoiceProducts.
			 * 
			 * @return invoiceProducts
			 */
			public InvoiceProducts getInvoiceProducts() {
			    if (invoiceProducts == null) {
					Website website = (Website) getModel();
					Invoices invoices = website.getInvoices();
					setInvoiceProducts(invoices.getProductInvoiceProducts((Product) this));
				}
				return invoiceProducts;
			}
			
								/**
			 * Sets assignProductCategories.
			 * 
			 * @param assignProductCategories
			 *            assignProductCategories
			 */
	    	public void setAssignProductCategories(AssignProductCategories assignProductCategories) {
				this.assignProductCategories = assignProductCategories;
				if (assignProductCategories != null) {
					assignProductCategories.setProduct((Product) this);
				}
			}
	
			/**
			 * Gets assignProductCategories.
			 * 
			 * @return assignProductCategories
			 */
			public AssignProductCategories getAssignProductCategories() {
			    if (assignProductCategories == null) {
					Website website = (Website) getModel();
					ProductCategories productCategories = website.getProductCategories();
					setAssignProductCategories(productCategories.getProductAssignProductCategories((Product) this));
				}
				return assignProductCategories;
			}
			
			    
}