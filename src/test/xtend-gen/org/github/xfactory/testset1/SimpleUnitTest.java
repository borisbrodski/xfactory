package org.github.xfactory.testset1;

import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.testset1.Book;
import org.github.xfactory.testset1.XFactoryBook;
import org.github.xfactory.tools.UnitTestRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

@SuppressWarnings("all")
public class SimpleUnitTest {
  @Rule
  @Extension
  public UnitTestRule _unitTestRule = new UnitTestRule();
  
  @Test
  public void testMinimal() {
    XFactoryBook _xFactoryBook = new XFactoryBook();
    final Book book = this._unitTestRule.<Book, XFactoryBook>xbuild(_xFactoryBook);
    String _title = book.getTitle();
    Assert.assertEquals("Coolest book ever", _title);
  }
  
  @Test
  public void testCustomTitle() {
    XFactoryBook _xFactoryBook = new XFactoryBook();
    final Procedure1<XFactoryBook> _function = new Procedure1<XFactoryBook>() {
      public void apply(final XFactoryBook it) {
        final Procedure1<Book> _function = new Procedure1<Book>() {
          public void apply(final Book it) {
            it.setTitle("test");
          }
        };
        it.set(_function);
      }
    };
    final Book book = this._unitTestRule.<Book, XFactoryBook>xbuild(_xFactoryBook, _function);
    String _title = book.getTitle();
    Assert.assertEquals("test", _title);
  }
}
