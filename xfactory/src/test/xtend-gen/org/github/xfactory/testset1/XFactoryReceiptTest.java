package org.github.xfactory.testset1;

import javax.persistence.EntityManager;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.testset1.Receipt;
import org.github.xfactory.testset1.XFactoryReceipt;
import org.github.xfactory.tools.PersistenceTestRule;
import org.github.xfactory.tools.TestSet;
import org.junit.Rule;
import org.junit.Test;

@SuppressWarnings("all")
public class XFactoryReceiptTest {
  @Rule
  @Extension
  public PersistenceTestRule _persistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1);
  
  @Test
  public void testPersistMinimal() {
    XFactoryReceipt _xFactoryReceipt = new XFactoryReceipt();
    final Receipt receipt = this._persistenceTestRule.<Receipt, XFactoryReceipt>xpersist(_xFactoryReceipt);
    this._persistenceTestRule.flush();
    this._persistenceTestRule.clear();
    EntityManager _entityManager = this._persistenceTestRule.getEntityManager();
    Receipt _find = _entityManager.<Receipt>find(Receipt.class, receipt.id);
    final Procedure1<Receipt> _function = new Procedure1<Receipt>() {
      public void apply(final Receipt it) {
        Long _id = it.book.getId();
        Long _id_1 = receipt.book.getId();
        XFactoryReceiptTest.this._persistenceTestRule.operator_spaceship(_id, _id_1);
        XFactoryReceiptTest.this._persistenceTestRule.operator_spaceship(
          it.customer.id, receipt.customer.id);
      }
    };
    ObjectExtensions.<Receipt>operator_doubleArrow(_find, _function);
  }
}
