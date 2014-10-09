package org.github.xfactory.testset1;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.AbstractXFactory;
import org.github.xfactory.testset1.Customer;

@SuppressWarnings("all")
public class XFactoryCustomer extends AbstractXFactory<Customer> {
  public void minimal() {
    final Procedure1<Customer> _function = new Procedure1<Customer>() {
      public void apply(final Customer it) {
        it.name = "Coolest book ever";
      }
    };
    this.set(_function);
  }
}
