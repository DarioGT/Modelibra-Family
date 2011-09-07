package sales;

import java.io.File;

import junit.framework.Assert;
import model.config.ModelProperties;

import org.junit.BeforeClass;
import org.junit.Test;

public class SalesDomainTest {

	private static SalesDomain salesDomain;

	@BeforeClass
	public static void beforeTests() throws Exception {
		salesDomain = SalesDomain.getOne();
		ModelProperties modelProperties = new ModelProperties(
				SalesModelTest.class);
		SalesModel salesModel = new SalesModel(new File(modelProperties
				.getFilePath()));
		salesModel = (SalesModel) salesModel.load();
		salesDomain.addModel("SalesModel", salesModel);
		// salesModel.output();

		Products products = salesModel.getProducts();
		Products selectedProducts = (Products) products.selectByProperty(
				"category", "Java");
		Products orderedSelectedProducts = (Products) selectedProducts
				.orderByProperty("name", true);
		ProductsViewModel productsViewModel = new ProductsViewModel();
		productsViewModel.addEntities("Products", orderedSelectedProducts);
		salesDomain.addModel("ProductsViewModel", productsViewModel);
		// productsViewModel.output();
	}

	@Test
	public void retrieveProduct() throws Exception {
		Products products = salesDomain.getSalesModel().getProducts();
		Product groovy = products
				.retrieveByProperty("name", "Groovy In Action");
		Assert.assertNotNull(groovy);
		Assert.assertEquals("Groovy In Action", groovy.getName());
	}

	@Test
	public void selectAffordableProducts() throws Exception {
		Products products = salesDomain.getSalesModel().getProducts();
		Products affordableProducts = (Products) products.selectByMethod(
				"isPriceLessThan", 50.00);
		for (Product product : affordableProducts) {
			Assert.assertTrue(product.getPrice() < 50.00);
		}
		// affordableProducts.output("Affordable Products");
	}

	@Test
	public void propagateFromModelToModel() throws Exception {
		Products products = salesDomain.getSalesModel().getProducts();
		Products viewProducts = salesDomain.getProductsViewModel()
				.getProducts();

		Product product = new Product();
		product.setName("The Java Programming Language");
		product.setCategory("Java");
		product.setQtyInStock(66);
		product.setPrice(66.60);
		Assert.assertTrue(viewProducts.add(product));
		Assert.assertTrue(viewProducts.contain(product));

		Product java = products.retrieveByProperty("name",
				"The Java Programming Language");
		Assert.assertNotNull(java);
		Assert.assertEquals("The Java Programming Language", java.getName());

		// productsViewModel.output();
	}
}
