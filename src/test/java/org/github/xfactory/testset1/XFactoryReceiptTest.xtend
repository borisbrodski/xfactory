package org.github.xfactory.testset1

import org.github.xfactory.tools.PersistenceTestRule
import org.github.xfactory.tools.TestSet
import org.junit.Rule
import org.junit.Test

class XFactoryReceiptTest {
	@Rule
	public extension PersistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1)

	@Test
	def void testPersistMinimal() {
		val receipt = xpersist(new XFactoryReceipt)

		flush
		clear
		
		entityManager.find(Receipt, receipt.id) => [
			book.id <=> receipt.book.id
			customer.id <=> receipt.customer.id
		]
	}

}
