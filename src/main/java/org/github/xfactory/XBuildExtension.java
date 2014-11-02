package org.github.xfactory;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * XFactory extension class for tests without live connection to the database.<br>
 * <br>
 * This class is designed to be imported as an extension to a test class without
 * live connection to the database. It contains methods for creating but not
 * persisting entities and POJOs. Each method in this class has a static and
 * non-static version allowing different import techniques:
 * <ul>
 * <li> In header
 * <pre>
 * import static extension org.github.xfactory.XBuildExtension.*
 *
 * class MyTest {
 *   &#0064;Test
 *   def void myTest() {
 *     val obj = xbuild(...)
 *     ...
 *   }
 * }
 * </pre>
 * <li> In a class as a field
 * <pre>
 * class MyTest {
 *   public extension XBuildExtension = new XBuildExtension
 *
 *   &#0064;Test
 *   def void myTest() {
 *     val obj = xbuild(...)
 *     ...
 *   }
 * }
 * </pre>
 * <li> In a class as a JUnit rule
 * <pre>
 * class UnitTestRule extends XBuildExtension { ... }
 *
 * class MyTest {
 *   &#0064;Rule
 *   public extension UnitTestRule = new UnitTestRule
 *
 *   &#0064;Test
 *   def void myTest() {
 *     val obj = xbuild(...)
 *     ...
 *   }
 * }
 * </pre>
 * </ul>
 *
 *
 *
 *
 * @author Boris Brodski
 */
public class XBuildExtension {
	/**
	 * Build a POJO or entity using Xfactory <code>xfactory</code>.
	 * @param xfactory an instance of an <code>Xfactory</code> to use
	 * @return built xobject
	 */
	public < T, F extends AbstractXFactory< T >> T xbuild(F xfactory) {
        return XFactory.xbuild(xfactory);
    }

	/**
	 * Build a POJO or entity using Xfactory <code>xfactory</code>.
	 *
	 * @param xfactory an instance of an <code>Xfactory</code> to use
	 * @param initBlock custom initialization block for for object
	 * @return built xobject
	 */
    public < T, F extends AbstractXFactory< T >> T xbuild(F xfactory, Procedure1< F > initBlock) {
        return XFactory.xbuild(xfactory, initBlock);
    }
}
