package org.github.xfactory.testset1

import org.github.xfactory.AbstractXFactory
import org.github.xfactory.tools.XtendTestExtension

class XFactoryCustomer extends AbstractXFactory<Customer> {
	extension XtendTestExtension = new XtendTestExtension

	override minimal() {
		set [
			name = "Coolest book ever"
		]
	}

	def makeVerified() {
		set [
			verified = 2.days.ago
			verifiedBy = "Admin"
		]
	}

}
