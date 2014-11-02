package org.github.xfactory.testset1

import org.github.xfactory.tools.PersistenceTestRule
import org.github.xfactory.tools.TestSet
import org.junit.Rule
import org.junit.Test

class XFactoryBookTest {
	@Rule
	public extension PersistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1)

	@Test
	def void testPersistMinimal() {
		val customer = xpersist(new XFactoryCustomer)

		flush
		clear
		
		entityManager.find(Customer, customer.id) => [
			name <=> customer.name
		]
	}

}
