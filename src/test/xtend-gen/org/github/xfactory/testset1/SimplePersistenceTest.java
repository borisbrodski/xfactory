package org.github.xfactory.testset1;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.testset1.Book;
import org.github.xfactory.testset1.XFactoryBook;
import org.github.xfactory.tools.PersistenceTestRule;
import org.github.xfactory.tools.TestSet;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

@SuppressWarnings("all")
public class SimplePersistenceTest {
  @Rule
  @Extension
  public PersistenceTestRule _persistenceTestRule = new PersistenceTestRule(TestSet.TEST_SET1);
  
  @Test
  public void testXbuildMinimal() {
    XFactoryBook _xFactoryBook = new XFactoryBook();
    final Book book = this._persistenceTestRule.<Book, XFactoryBook>xbuild(_xFactoryBook);
    String _title = book.getTitle();
    Assert.assertEquals("Coolest book ever", _title);
    EntityManager _entityManager = this._persistenceTestRule.getEntityManager();
    _entityManager.flush();
    EntityManager _entityManager_1 = this._persistenceTestRule.getEntityManager();
    Query _createQuery = _entityManager_1.createQuery("FROM Book");
    List _resultList = _createQuery.getResultList();
    int _size = _resultList.size();
    Assert.assertEquals(
      "xbuild shouldn\'t persist entities", 
      0, _size);
  }
  
  @Test
  public void testXBuildCustomTitle() {
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
    final Book book = this._persistenceTestRule.<Book, XFactoryBook>xbuild(_xFactoryBook, _function);
    String _title = book.getTitle();
    Assert.assertEquals("test", _title);
    EntityManager _entityManager = this._persistenceTestRule.getEntityManager();
    _entityManager.flush();
    EntityManager _entityManager_1 = this._persistenceTestRule.getEntityManager();
    Query _createQuery = _entityManager_1.createQuery("FROM Book");
    List _resultList = _createQuery.getResultList();
    int _size = _resultList.size();
    Assert.assertEquals(
      "xbuild shouldn\'t persist entities", 
      0, _size);
  }
  
  @Test
  public void textXPersistMinimal() {
    XFactoryBook _xFactoryBook = new XFactoryBook();
    final Book book = this._persistenceTestRule.<Book, XFactoryBook>xpersist(_xFactoryBook);
    Long _id = book.getId();
    Assert.assertNotNull(_id);
    String _title = book.getTitle();
    Assert.assertEquals("Coolest book ever", _title);
    EntityManager _entityManager = this._persistenceTestRule.getEntityManager();
    _entityManager.flush();
    EntityManager _entityManager_1 = this._persistenceTestRule.getEntityManager();
    _entityManager_1.clear();
    EntityManager _entityManager_2 = this._persistenceTestRule.getEntityManager();
    Query _createQuery = _entityManager_2.createQuery("FROM Book");
    List _resultList = _createQuery.getResultList();
    final Procedure1<List<Book>> _function = new Procedure1<List<Book>>() {
      public void apply(final List<Book> it) {
        int _size = it.size();
        Assert.assertEquals(1, _size);
        Book _get = it.get(0);
        final Procedure1<Book> _function = new Procedure1<Book>() {
          public void apply(final Book it) {
            String _title = it.getTitle();
            Assert.assertEquals("Coolest book ever", _title);
          }
        };
        ObjectExtensions.<Book>operator_doubleArrow(_get, _function);
      }
    };
    ObjectExtensions.<List<Book>>operator_doubleArrow(
      ((List<Book>) _resultList), _function);
  }
}
