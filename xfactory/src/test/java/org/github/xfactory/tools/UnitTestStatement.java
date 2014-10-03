package org.github.xfactory.tools;

import org.github.xfactory.XFactory;
import org.junit.runners.model.Statement;

public class UnitTestStatement extends Statement {

	private Statement base;

	public UnitTestStatement(Statement base) {
		this.base = base;
	}

	@Override
	public void evaluate() throws Throwable {
		try {
			XFactory.initTest(new UnitTestInfrastructureProvider());
			base.evaluate();
			
		} finally {
			XFactory.doneTest();
		}
	}

}
