package org.github.xfactory.testset1

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
     *     public Boolean verified;
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
        createdInstance.id should be null

        persistedInstance    should not be null
        persistedInstance.id should not be null
    }
}
