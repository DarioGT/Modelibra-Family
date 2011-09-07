package sales;

import java.io.File;
import java.util.Date;

import junit.framework.Assert;
import model.IModel;
import model.config.ModelProperties;
import model.test.ModelTestTemplate;

import org.junit.BeforeClass;
import org.junit.Test;

public class SalesModelTest extends ModelTestTemplate {

	private static SalesModel salesModel;

	protected IModel prepareModel() {
		ModelProperties modelProperties = new ModelProperties(
				SalesModelTest.class);
		salesModel = new SalesModel(new File(modelProperties.getFilePath()));
		return salesModel;
	}

	protected void prepareEntities() {
		//createEntities();
		//salesModel.save();

		salesModel = (SalesModel) salesModel.load();
		salesModel.output();
	}

	private void createEntities() {
		salesModel.createEntries();

		Clients clients = salesModel.getClients();
		Client client = new Client();
		client.setName("Google");
		clients.add(client);

		Products products = salesModel.getProducts();
		Product product01 = new Product();
		product01.setName("Groovy In Action");
		product01.setCategory("Groovy");
		product01.setQtyInStock(7);
		product01.setPrice(45.99);
		products.add(product01);
		Product product02 = new Product();
		product02.setName("Core Java Data Objects");
		product02.setCategory("JDO");
		product02.setQtyInStock(4);
		product02.setPrice(55.90);
		products.add(product02);
		Product product03 = new Product();
		product03.setName("Thinking In Java");
		product03.setCategory("Java");
		product03.setQtyInStock(9);
		product03.setPrice(35.00);
		products.add(product03);
		Product product04 = new Product();
		product04
				.setName("Concurrent Programming in Java: Design Principles and Patterns");
		product04.setCategory("Java");
		product04.setQtyInStock(23);
		product04.setPrice(64.99);
		products.add(product04);

		Orders clientOrders = client.getOrders();
		Order order = new Order();
		order.setClient(client);
		order.setOrderDate(new Date());
		clientOrders.add(order);

		OrderItems orderItems = order.getOrderItems();
		OrderItem orderItem01 = new OrderItem();
		orderItem01.setOrder(order);
		orderItem01.setProduct(product02);
		orderItem01.setSeqNo(1);
		orderItem01.setQtyOrdered(2);
		orderItems.add(orderItem01);
		product02.getOrderItems().add(orderItem01);
		OrderItem orderItem02 = new OrderItem();
		orderItem02.setOrder(order);
		orderItem02.setProduct(product01); // salesModel.output();
		orderItem02.setSeqNo(2);
		orderItem02.setQtyOrdered(1);
		orderItems.add(orderItem02);
		product01.getOrderItems().add(orderItem02);
	}

	@BeforeClass
	public static void beforeTests() throws Exception {
		SalesModelTest salesTest = new SalesModelTest();
		salesTest.prepareDataTemplate();
	}

	@Test
	public void retrieveProduct() throws Exception {
		Products products = salesModel.getProducts();
		Product groovy = products
				.retrieveByProperty("name", "Groovy In Action");
		Assert.assertNotNull(groovy);
		Assert.assertEquals("Groovy In Action", groovy.getName());
	}

	@Test
	public void selectProduct() throws Exception {
		Products products = salesModel.getProducts();
		Products selectedProducts = (Products) products.selectByProperty(
				"name", "Groovy In Action");
		Assert.assertTrue(selectedProducts.size() == 1);

		Product groovy = selectedProducts.retrieveByProperty("name",
				"Groovy In Action");
		Assert.assertNotNull(groovy);
		Assert.assertEquals("Groovy In Action", groovy.getName());
	}

	@Test
	public void selectProducts() throws Exception {
		Products products = salesModel.getProducts();
		Product product = new Product();
		product.setName("Who's Afraid of Java?");
		product.setCategory("Java");
		product.setQtyInStock(7);
		product.setPrice(22.22);
		products.add(product);
		Assert.assertTrue(products.contain(product));

		Products javaProducts = (Products) products.selectByProperty(
				"category", "Java");
		Assert.assertFalse(javaProducts.empty());
		// javaProducts.output("Java Books");

		Products jdoProducts = (Products) javaProducts.selectByProperty(
				"category", "JDO");
		Assert.assertTrue(jdoProducts.empty());

		Products groovyProducts = (Products) javaProducts.selectByProperty(
				"category", "Groovy");
		Assert.assertTrue(groovyProducts.empty());
	}

	@Test
	public void propagateRemovedProduct() throws Exception {
		Products products = salesModel.getProducts();
		Product product = new Product();
		product.setName("Java Now!");
		product.setCategory("Java");
		product.setQtyInStock(3);
		product.setPrice(31.90);
		products.add(product);
		Assert.assertTrue(products.contain(product));

		Products javaProducts = (Products) products.selectByProperty(
				"category", "Java");
		Assert.assertFalse(javaProducts.empty());

		Assert.assertTrue(javaProducts.remove(product));
		Product javaNow = javaProducts.retrieveByProperty("name", "Java Now!");
		Assert.assertNull(javaNow);
		Assert.assertFalse(javaProducts.contain(product));
		javaNow = products.retrieveByProperty("name", "Java Now!");
		Assert.assertNull(javaNow);
		Assert.assertFalse(products.contain(product));

		// products.output("Java Books");
	}

	@Test
	public void orderProducts() throws Exception {
		Products products = salesModel.getProducts();
		// products.output("Products");
		Products orderedProducts = (Products) products.orderByProperty("name",
				true);
		// orderedProducts.output("Ordered Products");
	}

	@Test
	public void propagateAddedProduct() throws Exception {
		Products products = salesModel.getProducts();
		Products orderedProducts = (Products) products.orderByProperty("name",
				true);
		Product product = new Product();
		product.setName("Core Java");
		product.setCategory("Java");
		product.setQtyInStock(2);
		product.setPrice(42.00);
		Assert.assertTrue(orderedProducts.add(product));
		Assert.assertTrue(orderedProducts.contain(product));

		Product java = products.retrieveByProperty("name", "Core Java");
		Assert.assertNotNull(java);
		Assert.assertEquals("Core Java", java.getName());
	}

	@Test
	public void propagateFromModelToModel() throws Exception {
		Products products = salesModel.getProducts();
		Products orderedProducts = (Products) products.orderByProperty("name",
				true);
		ProductsViewModel productsViewModel = new ProductsViewModel();
		productsViewModel.addEntities("Products", orderedProducts);

		Products viewProducts = productsViewModel.getProducts();
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
	}

}
