package org.github.xfactory;


class InfrastructureManager {
	private static ThreadLocal<InfrastructureProvider> infrastructureProviderThreadLocal = new ThreadLocal<InfrastructureProvider>();

	static void setInfrastructureProvider(InfrastructureProvider infrastructureProvider) {
		infrastructureProviderThreadLocal.set(infrastructureProvider);
	}

	static void resetInfrastructureProvider() {
		infrastructureProviderThreadLocal.set(null);
	}

	public static InfrastructureProvider getInfrastructureProvider() {
		InfrastructureProvider infrastructureProvider = infrastructureProviderThreadLocal.get();
		if (infrastructureProvider == null) {
			throw new XFactoryException("InfrastructureProvider not set. Use XFactory.initTest() method to provide your implementation of the InfrastructureProvider to the XFactory");
		}
		return infrastructureProvider;
	}
}
