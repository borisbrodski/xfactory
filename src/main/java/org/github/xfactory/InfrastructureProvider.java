package org.github.xfactory;


/**
 * Interface to the environment.
 *
 * @author Boris Brodski
 */
public interface InfrastructureProvider {

	/**
	 * A POJO <code>rootPojo</code> with possible more dependent POJOs is about to be persistent.
	 *
	 * @param xobject the POJO instance passed to {@link XFactory#xpersist(AbstractXFactory)} method
	 */
	public void prePersist(Object xobject);

	/**
	 * A POJO <code>rootPojo</code> with possible more dependent POJOs was successfully persisted.
	 *
	 * @param xobject the POJO instance passed to {@link XFactory#xpersist(AbstractXFactory)} method
	 */
	public void postPersist(Object xobject);

	/**
	 * Persist POJO in the database or other type of storage.
	 *
	 * @param xobject POJO to persist
	 */
	public void persist(Object xobject);
}
