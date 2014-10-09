package org.github.xfactory.testset1

import org.github.xfactory.AbstractXFactory

class XFactoryReceipt extends AbstractXFactory<Receipt> {
	
	override minimal() {
		set [
			book = xpersistBefore(new XFactoryBook)
			customer = xpersistBefore(new XFactoryCustomer) [
				minimal
				set [
					verified = true
				]
			]
		]
	}
	
}
