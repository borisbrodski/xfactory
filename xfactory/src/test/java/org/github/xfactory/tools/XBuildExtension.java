package org.github.xfactory.tools;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.AbstractXFactory;
import org.github.xfactory.XFactory;
import org.hamcrest.Matcher;
import org.junit.Assert;

public class XBuildExtension {
	public < T, F extends AbstractXFactory< T >> T xbuild(F xfactory) {
        return XFactory.xbuild(xfactory);
    }

    public < T, F extends AbstractXFactory< T >> T xbuild(F xfactory, Procedure1< F > initBlock) {
        return XFactory.xbuild(xfactory, initBlock);
    }
    
	@SuppressWarnings("unchecked")
	public void operator_spaceship(Object o1, Object o2) {
		if (o2 instanceof Matcher<?>) {
			Assert.assertThat(o1, (Matcher<Object>) o2);
		} else {
			Assert.assertEquals(o2, o1);
		}
	}
}
