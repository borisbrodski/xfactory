package org.github.xfactory;

import javax.persistence.EntityManager;

class InfrastructureManager {
	private static ThreadLocal<InfrastructureProvider> infrastructureProviderThreadLocal = new ThreadLocal<InfrastructureProvider>();

	static void setInfrastructureProvider(InfrastructureProvider infrastructureProvider) {
		infrastructureProviderThreadLocal.set(infrastructureProvider);
	}

	static void resetInfrastructureProvider() {
		infrastructureProviderThreadLocal.set(null);
	}

	public static EntityManager getEntityManager() {
		EntityManager entityManager = infrastructureProviderThreadLocal.get().getEntityManager();
		if (entityManager == null) {
			throw new XFactoryException("No entity manager available. Check your current implementation of the InfrastructureProvider.");
		}
		return entityManager;
	}
}
