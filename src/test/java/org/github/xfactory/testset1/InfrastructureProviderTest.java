package org.github.xfactory.testset1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.github.xfactory.XFactory;
import org.github.xfactory.XFactoryException;
import org.junit.Test;

public class InfrastructureProviderTest {
	@Test
	public void testNoEntityManager() {
		XFactory.initTest(null);

		assertNotNull(XFactory.xbuild(new XFactoryBook()));
		try {
			XFactory.xpersist(new XFactoryBook());
			fail("XFactoryException expected");
		} catch (XFactoryException e) {
			assertEquals("InfrastructureProvider not set. Use XFactory.initTest() method to provide your implementation of the InfrastructureProvider to the XFactory",
					e.getMessage());
		}
	}
}
