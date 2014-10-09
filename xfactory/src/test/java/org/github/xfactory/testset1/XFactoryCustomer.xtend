package org.github.xfactory.testset1

import org.github.xfactory.AbstractXFactory

class XFactoryCustomer extends AbstractXFactory<Customer> {
	
	override minimal() {
		set [
			name = "Coolest book ever"
		]
	}
	
}
