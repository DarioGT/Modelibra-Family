package first;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ReferenceTest {

	@Test
	public void referenceCreated() throws Exception {
		IReference reference = new Reference();
		reference.add("JDO", "Java Data Objects");
		reference.add("JDBC", "Java Database Connection");
		reference.add("DBMS", "Database Management System");
		assertFalse(reference.empty());
		assertTrue(reference.size() == 3);
		assertTrue(reference.contain("DBMS"));
		((Reference) reference).output("References");
	}

}
