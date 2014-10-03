package org.github.xfactory;

import javax.persistence.EntityManager;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

public class XFactory {
	private static ThreadLocal<InfrastructureProvider> infrastructureProviderThreadLocal = new ThreadLocal<InfrastructureProvider>();

	public static void initTest(InfrastructureProvider infrastructureProvider) {
		infrastructureProviderThreadLocal.set(infrastructureProvider);
	}

	public static void doneTest() {
		infrastructureProviderThreadLocal.set(null);
	}
	
	public static InfrastructureProvider getInfrastructureProvider() {
		return infrastructureProviderThreadLocal.get();
	}
	
	
	public static < T, F extends AbstractXFactory< T >> T xpersist(F xfactory) {
		return xpersist(xfactory, null);
	}
	
    public static < T, F extends AbstractXFactory< T >> T xpersist(F xfactory, Procedure1< F > initBlock) {
        T entity = xfactory.applyInitBlock(initBlock);
        xfactory.persist();

        // TODO Make flush optional
        EntityManager entityManager = getInfrastructureProvider().getEntityManager();
        entityManager.flush();

        return entity;
    }

    public static < T, F extends AbstractXFactory< T >> T xbuild(F xfactory) {
        return xbuild(xfactory, null);
    }

    public static < T, F extends AbstractXFactory< T >> T xbuild(F xfactory, Procedure1< F > initBlock) {
        return xfactory.applyInitBlock(initBlock);
    }
}
