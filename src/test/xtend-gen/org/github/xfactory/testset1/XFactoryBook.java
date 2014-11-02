package org.github.xfactory.testset1;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.AbstractXFactory;
import org.github.xfactory.testset1.Book;

@SuppressWarnings("all")
public class XFactoryBook extends AbstractXFactory<Book> {
  public void minimal() {
    final Procedure1<Book> _function = new Procedure1<Book>() {
      public void apply(final Book it) {
        it.setTitle("Coolest book ever");
      }
    };
    this.set(_function);
  }
}
