package org.github.xfactory.testset1

import org.github.xfactory.tools.UnitTestRule
import org.junit.Rule
import org.junit.Test
import static org.junit.Assert.*


class SimpleUnitTest {
	@Rule
	public extension UnitTestRule = new UnitTestRule
	
	@Test
	def testMinimal() {
		val book = xbuild(new XFactoryBook)
		
		assertEquals("Coolest book ever", book.title)
	}

	@Test
	def testCustomTitle() {
		val book = xbuild(new XFactoryBook) [
			set [
				title = "test"
			]
		]
		
		assertEquals("test", book.title)
	}
}