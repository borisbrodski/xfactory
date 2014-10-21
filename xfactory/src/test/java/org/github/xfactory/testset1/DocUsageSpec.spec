package org.github.xfactory.testset1

import org.github.xfactory.tools.PersistenceTestRule
import org.github.xfactory.tools.TestSet
import org.junit.Rule

/**
 * Here you will learn how to use XFactories to
 * create and persist entities (or other POJO-like objects, like DTOs).
 *
 * *Note:* Prior to use XFactories you will have to implement one XFactory class
 * per POJO containing mandatory `minimal` method and optional business initialization
 * methods, like `XFactoryBook.makeBestSeller()`.
 */
describe "Using XFactories to create and persist entities" {
	@Rule
	public extension PersistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1)

	context "Create and persist instances of an entity" {
		/**
		 * To get a brand new instance of an entity you have
		 * simply to pass an instance of the corresponding factory
		 * to the `xbuild(...)` method.
		 */
		fact "Create a new instance of an entity" {
			val book = xbuild(new XFactoryBook)

			book should not be null
			book.title should not be null
		}

		/**
		 * Persisting a new entity in the database is as simple as
		 * calling `xpersist(...)` extension method instead of the `xbuild(...)`
		 * method. The XFactory implementation should be done is such a way,
		 * that the created entities satisfy all the database constraints.
		 */
		fact "Persist a new instance of an entity" {
			val book = xpersist(new XFactoryBook)

			book should not be null
			book.id should not be null
		}

		/**
		 * In order to satisfy database or some business constraints
		 * child entities can be created in the background.
		 */
		fact "Child entities get created automatically" {
			val receipt = xpersist(new XFactoryReceipt)

			receipt.book.id should not be null
			receipt.customer.id should not be null
		}

	}

	/**
	 * Passing a closure as a second argument to the `xbuild` or `xpersist`
	 * method allows you to modify entity instantiation in many ways.
	 */
	context "Take control of the entity instantiation" {

		/**
		 * Passing a closure as a second argument to the `xbuild` or `xpersist`
		 * method allows customization of the initialization procedure.
		 */
		fact "Missing call to minimal() leaves uninitialized entity" {
			val book = xbuild(new XFactoryBook) []

			book.title should be null
		}

		/**
		 * Passing a closure as a second argument to the `xbuild` or `xpersist`
		 * method allows customization of the initialization procedure.
		 */
		fact "Calling minimal() method manually initialize the entity in the standard way" {
			val book = xbuild(new XFactoryBook) [
				minimal
			]

			book.title should not be null
		}

		/**
		 * The `set [...]` provides direct access to the attribute of the entity.
		 * The call to the `minimal` method ensures, that all other initialization
		 * are done.
		 */
		fact "Changing entity attribute" {
			val book = xbuild(new XFactoryBook) [
				minimal
				set [
					title = "Another title"
				]
			]

			book.title should be "Another title"
		}

		/**
		 * Alternative, you can grab the new instance of the new entity
		 * using `xobject` method and initialize it directly or pass it
		 * to some external initialization method.
		 */
		 fact "Getting instance of the new entity" {
			val book = xbuild(new XFactoryBook) [
				minimal
				xobject.title = "Yet another title"
			]

			book.title should be "Yet another title"
		 }

		/**
		 * The method `xobject(Class<E>)` also provides access to the first
		 * created child entity of the specified type.
		 */
		 fact "Getting instance of the a child entity by type" {
			val receipt = xbuild(new XFactoryReceipt) [
				minimal
				xobject(Book).title = "Title 1"
			]

			receipt.book.title should be "Title 1"
		 }
	}
}