package org.github.xfactory.tools;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.github.xfactory.AbstractXFactory;
import org.github.xfactory.XFactory;

public class XBuildExtension {
	public < T, F extends AbstractXFactory< T >> T xbuild(F xfactory) {
        return XFactory.xbuild(xfactory);
    }

    public < T, F extends AbstractXFactory< T >> T xbuild(F xfactory, Procedure1< F > initBlock) {
        return XFactory.xbuild(xfactory, initBlock);
    }

}
