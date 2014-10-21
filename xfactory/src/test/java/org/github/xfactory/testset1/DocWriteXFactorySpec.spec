package org.github.xfactory.testset1

import org.github.xfactory.tools.PersistenceTestRule
import org.github.xfactory.tools.TestSet
import org.junit.Rule

/**
 * Here you will learn how to write XFactories for the
 * entities (or other POJO-like objects, like DTOs).
 */
describe "Writing XFactories" {
	@Rule
	public extension PersistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1)

	/*
	 * sdfsdf
	 *
	 * <pre class="prettyprint lang-spec">describe "A Stack"{
	 * int i
     * }</pre>
	 * <pre class="lang-spec prettyprint">describe "A Stack"{
     *   fact "initial size is 0"
     *   fact "increases its size when pushing an element"
     * }</pre>
     *
     * ss
	 */
	fact "Create a simple XFactory" {
		val book = xbuild(new XFactoryBook)

		book should not be null
		book.title should not be null
	}
}