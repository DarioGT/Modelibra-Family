package first;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class ClientsTest {

	private static Clients clients;

	@BeforeClass
	public static void beforeTests() throws Exception {
		SalesModel salesModel = new SalesModel(new SalesModelFactory());

		clients = salesModel.createClients();
		assertNotNull(clients);
		boolean clientsAdded = salesModel.addClients(clients);
		assertTrue(clientsAdded);
	}

	@Test
	public void clientCreated() throws Exception {
		assertTrue(clients.isEmpty());

		Client client = new Client();
		client.setOid(3000L);
		client.setName("Google");
		IAction clientAdd = new AddAction(clients, client);
		boolean addedClient = clientAdd.execute();
		assertTrue(addedClient);

		assertFalse(clients.isEmpty());

		clients.output("validClient");
		assertTrue(clients.contain(client));

		IAction clientRemove = new RemoveAction(clients, client);
		boolean removedClient = clientRemove.execute();
		assertTrue(removedClient);
		assertFalse(clients.contain(client));
	}

}
