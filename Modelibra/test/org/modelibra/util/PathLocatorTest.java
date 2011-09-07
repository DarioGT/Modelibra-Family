package org.modelibra.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.URL;

import org.junit.Test;
import org.modelibra.config.Config;

public class PathLocatorTest {

	@Test
	public void getPath() {
		PathLocator pathLocator = new PathLocator();

		URL classBasedUrl = pathLocator.getClassBasedUrl(Config.class,
				"modelibra.properties");
		assertNotNull(classBasedUrl);

	}

	@Test
	public void load() throws Exception {
		PathLocator pathLocator = new PathLocator();

		String classBasedPath = pathLocator.getClassBasedPath(Config.class,
				"modelibra.properties");
		assertNotNull(classBasedPath);
	}

	@Test
	public void getPathWithWrongPropertiesFileName() {
		PathLocator pathLocator = new PathLocator();

		URL classBasedUrl = pathLocator.getClassBasedUrl(Config.class,
				"dmConfig.properties");
		assertNull(classBasedUrl);

	}
	
	@Test
	public void findAbsolutePath() throws Exception {
		PathLocator pathLocator = new PathLocator();
		
		String file = new File(PathLocatorTest.class.getResource("").getPath()).getAbsolutePath();
		String result = pathLocator.findAbsolutePath(PathLocatorTest.class, "class", "path");
		System.out.println("file=[" + file+ "].");
		System.out.println(result);
		
	}
}
