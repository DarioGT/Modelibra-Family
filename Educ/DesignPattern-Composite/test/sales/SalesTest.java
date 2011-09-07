package sales;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.AddAction;
import model.IAction;
import model.IModel;
import model.Model;
import model.ModelTestTemplate;
import model.Transaction;

import org.junit.BeforeClass;
import org.junit.Test;

public class SalesTest extends ModelTestTemplate {

	private static IModel sales;

	protected IModel prepareModel() {
		sales = new Model(new SalesFactory());
		sales.createEntities("Clients");
		sales.createEntities("Products");
		return sales;
	}

	protected void prepareEntities() {
		
	}

	Clients getClients() {
		return (Clients) sales.getEntities("Clients");
	}

	Products getProducts() {
		return (Products) sales.getEntities("Products");
	}

	@BeforeClass
	public static void beforeTests() throws Exception {
		SalesTest salesTest = new SalesTest();
		salesTest.prepareDataTemplate();
	}

	@Test
	public void addClientProductTransaction() throws Exception {
		Transaction transaction = new Transaction();
		
		Clients clients = getClients();
		Client client = new Client();
		client.setName("Google");
		IAction clientAdd = new AddAction(clients, client);
		transaction.add(clientAdd);
		
		Products products = getProducts();
		Product product = new Product();
		product.setName("Core Java Data Objects");
		product.setCategory("JDO");
		IAction productAdd = new AddAction(products, product);
		transaction.add(productAdd);
		
		boolean executed = transaction.execute();
		assertTrue(executed);
		clients.output("--- Clients ---");
		products.output("--- Products ---");
		
		boolean undone = transaction.undo();
		assertTrue(undone);
		clients.output("--- Clients ---");
		products.output("--- Products ---");
	}
	
	@Test
	public void addTwoInvalidProductsTransaction() throws Exception {
		Transaction transaction = new Transaction();
		
		Products products = getProducts();
		Product product01 = new Product();
		product01.setOid(2010L);
		product01.setName("Core Java Data Objects");
		product01.setCategory("JDO");
		IAction product01Add = new AddAction(products, product01);
		transaction.add(product01Add);
		
		Product product02 = new Product();
		product02.setOid(2010L);
		product02.setName("Core Java Data Objects");
		product02.setCategory("JDO");
		IAction product02Add = new AddAction(products, product02);
		transaction.add(product02Add);
		
		boolean executed = transaction.execute();
		assertFalse(executed);
		products.output("--- Products ---");
		if (executed) {
			transaction.undo();
			products.output("--- Products ---");
		}
	}
	
	@Test
	public void addTwoProductsTransaction() throws Exception {
		Transaction transaction = new Transaction();
		
		Products products = getProducts();
		Product product01 = new Product();
		product01.setName("Core Java Data Objects");
		product01.setCategory("JDO");
		IAction product01Add = new AddAction(products, product01);
		transaction.add(product01Add);
		
		Product product02 = new Product();
		product02.setName("Groovy In Action");
		product02.setCategory("Groovy");
		IAction product02Add = new AddAction(products, product02);
		transaction.add(product02Add);
		
		boolean executed = transaction.execute();
		assertTrue(executed);
		products.output("--- Products ---");
		if (executed) {
			assertTrue(transaction.undo());
			products.output("--- Products ---");
		}
	}

}
