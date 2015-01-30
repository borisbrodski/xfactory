package org.github.xfactory.docs

import org.github.xfactory.testset1.XFactoryCustomer
import org.github.xfactory.tools.PersistenceTestRule
import org.github.xfactory.tools.TestSet
import org.junit.Rule

/**
 * Here you will learn how to write XFactories for the
 * entities (or other POJO-like objects, like DTOs).
 */
describe "Writing XFactories" {
    @Rule
    public extension PersistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1)

    /*
     * Suppose we have a simple entity class `Customer`:
     *
     * <pre class="lang-spec prettyprint">
     * class Customer {
     *     &#64;Id &#64;GeneratedValue
     *     public Long id;
     *
     *     &#64;Column(nullable = false)
     *     public String name;
     *
     *     public Date verified;
     *     public String verifiedBy;
     *
     *     // Getters & setters
     * }
     * </pre>
     *
     * Then our XFactory may look like this:
     *
     * <pre class="lang-spec prettyprint">
     * class XFactoryCustomer extends AbstractXFactory<Customer> {
     *     override minimal() {
     *         set [
     *             name = "Coolest book ever"
     *         ]
     *     }
     * }
     * </pre>
     *
     * We extend `AbstractXFactory` class passing the entity class as a generic parameter.
     * Then we have to implement `minimal` method setting all mandatory fields to some values.
     * Now we can use this XFactory to create and persist simple instances:
     */
    fact "Simple XFactory" {
        val createdInstance = xbuild(new XFactoryCustomer)
        val persistedInstance = xpersist(new XFactoryCustomer)

        createdInstance    should not be null
        createdInstance.id should be null       // not persisted

        persistedInstance    should not be null
        persistedInstance.id should not be null // persisted
    }

    /*
     * One of the key features of the XFactory is ability to wrap
     * factory logic into domain methods. It improves readability
     * and make a test more robust at the same time.<br>
     * <br>
     * Here is an example:
     *
     * <pre class="lang-spec prettyprint">
     * class XFactoryCustomer extends AbstractXFactory<Customer> {
     *     override minimal() {
     *         set [
     *             name = "Coolest book ever"
     *         ]
     *     }
     *     def makeVerified() {
     *         set [
     *             verified = 2.days.ago
     *             verifiedBy = "Admin"
     *         ]
     *     }
     * }
     * </pre>
     *
     * Now let's look at the snippet below. It calls `minimal` and then `makeVerified`
     * methods. We can read it like this:<br>
     * <i><pre>
     *   Build a valid and verified customer
     * </pre></i>
     * So we can specify our requirements on the object we build without
     * explicitly writing the implementation details of this requirements.
     * If in the future the logic of making a verified customers changes, we will have
     * to just fix the method: `XFactoryCustomer.makeVerified()` and all the tests
     * will be using the new logic automatically.
     */
    fact "XFactory with domain methods" {
        xbuild(new XFactoryCustomer) [
            minimal         // fill all mandatory field making object valid
            makeVerified    // make it verified
        ]
    }
}
