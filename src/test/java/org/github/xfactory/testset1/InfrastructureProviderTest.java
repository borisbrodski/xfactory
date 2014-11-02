package org.github.xfactory.testset1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.github.xfactory.InfrastructureProvider;
import org.github.xfactory.XFactory;
import org.github.xfactory.XFactoryException;
import org.junit.Test;

public class InfrastructureProviderTest {
	@Test
	public void testNoEntityManager() {
		XFactory.initTest(new InfrastructureProvider() {
			@Override
			public EntityManager getEntityManager() {
				return null;
			}
		});

		assertNotNull(XFactory.xbuild(new XFactoryBook()));
		try {
			XFactory.xpersist(new XFactoryBook());
			fail("XFactoryException expected");
		} catch (XFactoryException e) {
			assertEquals("No entity manager available. Check your current implementation of the InfrastructureProvider.", e.getMessage());
		}
	}
}
