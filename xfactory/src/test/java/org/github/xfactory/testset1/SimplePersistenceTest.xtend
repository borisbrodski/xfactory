package org.github.xfactory.testset1

import java.util.List
import org.github.xfactory.tools.PersistenceTestRule
import org.github.xfactory.tools.TestSet
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.*

class SimplePersistenceTest {
	@Rule
	public extension PersistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1)

	@Test
	def testXbuildMinimal() {
		val book = xbuild(new XFactoryBook)

		assertEquals("Coolest book ever", book.title)

		entityManager.flush
		assertEquals(
			"xbuild shouldn't persist entities",
			0,
			entityManager.createQuery("FROM Book").resultList.size
		)
	}

	@Test
	def testXBuildCustomTitle() {
		val book = xbuild(new XFactoryBook) [
			set [
				title = "test"
			]
		]

		assertEquals("test", book.title)

		entityManager.flush
		assertEquals(
			"xbuild shouldn't persist entities",
			0,
			entityManager.createQuery("FROM Book").resultList.size
		)
	}

	@Test
	def void textXPersistMinimal() {
		val book = xpersist(new XFactoryBook)

		assertNotNull(book.id)
		assertEquals("Coolest book ever", book.title)

		entityManager.flush
		entityManager.clear
		entityManager.createQuery("FROM Book").resultList as List<Book> => [
			assertEquals(1, size)
			get(0) => [
				assertEquals("Coolest book ever", title)
			]
		]
	}
}
