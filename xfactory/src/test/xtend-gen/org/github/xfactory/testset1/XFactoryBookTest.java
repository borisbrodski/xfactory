package org.github.xfactory.testset1;

import javax.persistence.EntityManager;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.testset1.Customer;
import org.github.xfactory.testset1.XFactoryCustomer;
import org.github.xfactory.tools.PersistenceTestRule;
import org.github.xfactory.tools.TestSet;
import org.junit.Rule;
import org.junit.Test;

@SuppressWarnings("all")
public class XFactoryBookTest {
  @Rule
  @Extension
  public PersistenceTestRule _persistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1);
  
  @Test
  public void testPersistMinimal() {
    XFactoryCustomer _xFactoryCustomer = new XFactoryCustomer();
    final Customer customer = this._persistenceTestRule.<Customer, XFactoryCustomer>xpersist(_xFactoryCustomer);
    this._persistenceTestRule.flush();
    this._persistenceTestRule.clear();
    EntityManager _entityManager = this._persistenceTestRule.getEntityManager();
    Customer _find = _entityManager.<Customer>find(Customer.class, customer.id);
    final Procedure1<Customer> _function = new Procedure1<Customer>() {
      public void apply(final Customer it) {
        XFactoryBookTest.this._persistenceTestRule.operator_spaceship(
          it.name, customer.name);
      }
    };
    ObjectExtensions.<Customer>operator_doubleArrow(_find, _function);
  }
}
