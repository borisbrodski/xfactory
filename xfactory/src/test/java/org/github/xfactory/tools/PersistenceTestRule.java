package org.github.xfactory.tools;

import javax.persistence.EntityManager;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.AbstractXFactory;
import org.github.xfactory.XFactory;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class PersistenceTestRule extends XBuildExtension implements TestRule {
	private TestSet testSet;
	private PersistenceTestStatement statement;

	public PersistenceTestRule(TestSet testSet) {
		this.testSet = testSet;
	}

	public Statement apply(Statement base, Description description) {
		statement = new PersistenceTestStatement(base, testSet);
		return statement;
	}

	public <T, F extends AbstractXFactory<T>> T xpersist(F xfactory) {
		return XFactory.xpersist(xfactory);
	}

	public <T, F extends AbstractXFactory<T>> T xpersist(F xfactory,
			Procedure1<F> initBlock) {
		return XFactory.xpersist(xfactory, initBlock);
	}

	public EntityManager getEntityManager() {
		return statement.getEntityManager();
	}
}
