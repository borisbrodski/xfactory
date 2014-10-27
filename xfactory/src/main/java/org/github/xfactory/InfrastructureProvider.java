package org.github.xfactory;

import javax.persistence.EntityManager;

/**
 * Interface providing different aspects of the infrastructure to the Xfactory.
 *
 * @author Boris Brodski
 */
public interface InfrastructureProvider {
	/**
	 * An instance of the entity manager (for tests with live DB-connection).
	 *
	 * @return entity manager or <code>null</code>
	 */
	public EntityManager getEntityManager();
}
