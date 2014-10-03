package org.github.xfactory.testset1

import org.github.xfactory.AbstractXFactory

class XFactoryBook extends AbstractXFactory<Book> {
	
	override minimal() {
		set [
			title = "Coolest book ever"
		]
	}
	
}
