package org.github.xfactory.tools;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.github.xfactory.InfrastructureProvider;
import org.github.xfactory.XFactory;
import org.junit.runners.model.Statement;

public class PersistenceTestStatement extends Statement implements InfrastructureProvider {

    private Statement base;
    private TestSet testSet;
    private static Map<TestSet, EntityManagerFactory> entityManagerFactoryMap = new HashMap<TestSet, EntityManagerFactory>();
    private EntityManager entityManager;

    public PersistenceTestStatement(Statement base, TestSet testSet) {
        this.base = base;
        this.testSet = testSet;
    }

    @Override
    public void evaluate() throws Throwable {
        boolean ok = false;
        try {
            initEntityManager();
            beginTransaction();
            XFactory.initTest(this);
            base.evaluate();
            ok = true;
        } finally {
            XFactory.doneTest();
            try {
                rollbackTransaction();
            } catch (Throwable e) {
                if (ok) {
                    ok = false;
                    throw e;
                } else {
                    e.printStackTrace();
                }
            } finally {
                try {
                    closeEntityManager();
                } catch (Throwable e) {
                    if (ok) {
                        throw e;
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void beginTransaction() {
    	if (entityManager != null) {
    		entityManager.getTransaction().begin();
    	}
    }

    private void rollbackTransaction() {
    	if (entityManager != null) {
    		entityManager.getTransaction().rollback();
    	}
    }

    private void closeEntityManager() {
        if (entityManager != null) {
        	entityManager.close();
        }
    }

    private void initEntityManager() {
        entityManager = getEntityManagerFactory().createEntityManager();
    }

    private synchronized EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory entityManagerFactory = entityManagerFactoryMap.get(testSet);
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("testset1");
            entityManagerFactoryMap.put(testSet, entityManagerFactory);
        }
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

	@Override
	public void prePersist(Object rootXObject) {
	}

	@Override
	public void postPersist(Object rootXobject) {
		getEntityManager().flush();
	}

	@Override
	public void persist(Object xobject) {
		if (!getEntityManager().contains(xobject)) {
			getEntityManager().persist(xobject);
		}
	}
}
