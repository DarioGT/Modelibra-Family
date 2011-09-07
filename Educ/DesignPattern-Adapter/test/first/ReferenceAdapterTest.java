package first;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReferenceAdapterTest {

	private static Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public static void beforeTests() throws Exception {
		map.put("JDBC", "Java Database Connection");
		map.put("JDO", "Java Data Objects");
	}

	@Test
	public void mapAdaptedToReference() throws Exception {
		IReference reference = new ReferenceAdapter(map);
		assertFalse(reference.empty());
		assertTrue(reference.size() == 2);
		assertTrue(reference.contain("JDO"));
		((Reference) reference).output("Adapted References");
	}

}
