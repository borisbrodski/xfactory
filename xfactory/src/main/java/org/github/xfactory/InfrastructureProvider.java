package org.github.xfactory;

import javax.persistence.EntityManager;

public interface InfrastructureProvider {
	public EntityManager getEntityManager();
}
