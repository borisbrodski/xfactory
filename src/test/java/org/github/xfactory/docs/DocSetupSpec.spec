package org.github.xfactory.docs

import org.github.xfactory.XFactory
import org.github.xfactory.XFactoryException
import org.github.xfactory.testset1.XFactoryCustomer
import org.github.xfactory.tools.PersistenceTestRule
import org.github.xfactory.tools.TestSet
import org.junit.Rule
import org.github.xfactory.InfrastructureProvider
import org.github.xfactory.testset1.Customer

/**
 * Lets add XFactory to your test project.
 */
describe "Setup XFactory" {
    @Rule
    public extension PersistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1)

    public InfrastructureProvider x // TODO Prevent unused import warning. Remove later.

    /*
     * To use XFactory in your tests you have call <i>XFactory.initTest()</i> method before each test.
     * This can be easily done like this:
     *
     * <pre class="lang-spec prettyprint">
     * class MyTest {
     *
     *     &#64;Before
     *     def void initXFactory() {
     *         XFactory.initTest(null); // no persistence configured here
     *     }
     *
     *     // ...
     * }
     * </pre>
     *
     * Passing <i>null</i> to the <i>XFactory.initTest()</i> method initialize XFactory for build-only
     * operations. (no persistence)
     */
    fact "XFactory without persistence" {
        XFactory.initTest(null)

        xbuild(new XFactoryCustomer)  // OK
        xpersist(new XFactoryCustomer) throws XFactoryException
    }

    /*
     * In order to enable object persistence feature of XFactory an implementation
     * of the InfrastructureProvider interface should be provided using <i>XFactory.initTest()</i> method.
     */
    fact "XFactory with persistence" {
        // Initialize and enable persistence
        XFactory.initTest(new InfrastructureProvider() {
            override persist(Object xobject) {
                // persist an object
            }

            override postPersist(Object xobject) {
            }

            override prePersist(Object xobject) {
            }
        })

        xbuild(new XFactoryCustomer)  // OK
        xpersist(new XFactoryCustomer) // OK
    }

    /*
     * Here is an example implementation of the <i>InfrastructureProvider</i> using hibernate
     */
    fact "XFactory with hibernate" {
        // Initialize and enable persistence
        val entityManager = getEntityManager

        XFactory.initTest(new InfrastructureProvider() {
            override persist(Object xobject) {
                if (!entityManager.contains(xobject)) {
                    entityManager.persist(xobject)
                }
            }

            override postPersist(Object xobject) {
                entityManager.flush
            }

            override prePersist(Object xobject) {
            }
        })

        val customer = xpersist(new XFactoryCustomer)

        entityManager.clear

        entityManager.find(Customer, customer.id) => [
            name => customer.name
        ]
    }
}
