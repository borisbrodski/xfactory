package org.github.xfactory.tools;

import javax.persistence.EntityManager;

import org.github.xfactory.InfrastructureProvider;

public class UnitTestInfrastructureProvider implements InfrastructureProvider {

	public EntityManager getEntityManager() {
		return null;
	}

}
