package org.github.xfactory.tools;

public enum TestSet {
	TEST_SET1("testset1");
	private String name;

	private TestSet(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}