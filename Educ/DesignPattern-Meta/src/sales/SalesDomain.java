package sales;

import model.Domain;

public class SalesDomain extends Domain {

	private static SalesDomain salesDomain = new SalesDomain();

	private SalesDomain() {
	}

	public static SalesDomain getOne() {
		return salesDomain;
	}
	
	public SalesModel getSalesModel() {
		return (SalesModel) salesDomain.getModel("SalesModel");
	}
	
	public ProductsViewModel getProductsViewModel() {
		return (ProductsViewModel) salesDomain.getModel("ProductsViewModel");
	}

}
