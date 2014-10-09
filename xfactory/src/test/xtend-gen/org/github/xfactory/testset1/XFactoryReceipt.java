package org.github.xfactory.testset1;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.AbstractXFactory;
import org.github.xfactory.testset1.Book;
import org.github.xfactory.testset1.Customer;
import org.github.xfactory.testset1.Receipt;
import org.github.xfactory.testset1.XFactoryBook;
import org.github.xfactory.testset1.XFactoryCustomer;

@SuppressWarnings("all")
public class XFactoryReceipt extends AbstractXFactory<Receipt> {
  public void minimal() {
    final Procedure1<Receipt> _function = new Procedure1<Receipt>() {
      public void apply(final Receipt it) {
        XFactoryBook _xFactoryBook = new XFactoryBook();
        Book _xpersistBefore = XFactoryReceipt.this.<Book, XFactoryBook>xpersistBefore(_xFactoryBook);
        it.book = _xpersistBefore;
        XFactoryCustomer _xFactoryCustomer = new XFactoryCustomer();
        final Procedure1<XFactoryCustomer> _function = new Procedure1<XFactoryCustomer>() {
          public void apply(final XFactoryCustomer it) {
            it.minimal();
            final Procedure1<Customer> _function = new Procedure1<Customer>() {
              public void apply(final Customer it) {
                it.verified = Boolean.valueOf(true);
              }
            };
            it.set(_function);
          }
        };
        Customer _xpersistBefore_1 = XFactoryReceipt.this.<Customer, XFactoryCustomer>xpersistBefore(_xFactoryCustomer, _function);
        it.customer = _xpersistBefore_1;
      }
    };
    this.set(_function);
  }
}
