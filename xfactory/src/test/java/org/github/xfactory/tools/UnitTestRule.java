package org.github.xfactory.tools;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class UnitTestRule extends XBuildExtension implements TestRule {

	public Statement apply(Statement base, Description description) {
		return new UnitTestStatement(base);
	}

}
