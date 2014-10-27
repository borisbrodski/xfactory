package org.github.xfactory;

import javax.persistence.EntityManager;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * Main XFactory interface class.
 * <ul>
 * <li> <code>initTest()</code> prepare current thread for a new test to be run
 * <li> <code>doneTest()</code> clean current thread after test run
 * <li> <code>xbuild</code> Build an instance of a POJO or entity
 * <li> <code>xpersist</code> Build and persist an instance of an entity
 * </ul>
 *
 * @author Boris Brodski
 */
public class XFactory {

	/**
	 * Initialize XFactory for a test run.
	 *
	 * @param infrastructureProvider implementation of the infrastructure provide for the
	 * upcoming test.
	 */
	public static void initTest(InfrastructureProvider infrastructureProvider) {
		InfrastructureManager.setInfrastructureProvider(infrastructureProvider);
	}

	public static void doneTest() {
		InfrastructureManager.resetInfrastructureProvider();
	}

	public static < T, F extends AbstractXFactory< T >> T xpersist(F xfactory) {
		return xpersist(xfactory, null);
	}

    public static < T, F extends AbstractXFactory< T >> T xpersist(F xfactory, Procedure1< F > initBlock) {
        T entity = xfactory.applyInitBlock(initBlock);
        xfactory.persist();

        // TODO Make flush optional
        EntityManager entityManager = InfrastructureManager.getEntityManager();
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
