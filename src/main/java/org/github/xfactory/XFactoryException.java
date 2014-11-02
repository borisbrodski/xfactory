package org.github.xfactory;

/**
 * Unified runtime exception of the XFactory framework. See exception message for details.
 *
 * @author Boris Brodski
 */
public class XFactoryException extends RuntimeException {
	private static final long serialVersionUID = 42L;

	public XFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public XFactoryException(String message) {
		super(message);
	}

	public XFactoryException(Throwable cause) {
		super(cause);
	}

}
