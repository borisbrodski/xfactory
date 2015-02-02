package org.github.xfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * Abstract class for all factories.
 *
 * @author Boris Brodski
 *
 * @param <T> POJO or Entity class to build
 */
public abstract class AbstractXFactory<T> {
	private T xobject;
	private boolean isPersisted;
    private List< AbstractXFactory< ? > > persistBeforeList = new ArrayList< AbstractXFactory< ? > >();
    private List< AbstractXFactory< ? > > childFactories = new ArrayList< AbstractXFactory< ? > >();
    private Class<T> xobjectClass;

	public AbstractXFactory() {
		initXobjectClass();
		createXObject();
	}

	/**
	 * Initialize POJO or entity to satisfy elementary domain constraints.<br>
	 * Create child objects as needed using
	 * <ul>
	 * <li> <code>{@link #xpersistBefore(AbstractXFactory)}</code>
	 * <li> <code>{@link #xpersistAfter(AbstractXFactory)}</code>
	 * </ul>
	 * <i>For entities</i>: all database constraints should be fulfilled.
	 */
	public abstract void minimal();

	public void set(Procedure1<T> setter) {
		setter.apply(xobject);
	}

	/**
	 * Return a current instance of created object of type <code>T</code>.
	 *
	 * @return POJO or entity of type <code>T</code>
	 */
	public T xobject() {
		return xobject;
	}

	/**
	 * Search this and all child XFactories for an object of type <code>clazz</code>.
	 *
	 * @param clazz the type of the xobject to search
	 * @return found object or <code>null</code> if no such object was found
	 */
	@SuppressWarnings("unchecked")
	public <E> E xobject(Class<E> clazz) {
		for (AbstractXFactory<?> childFactory : childFactories) {
			if (childFactory.getXobjectClass() == clazz) {
				return (E) childFactory.xobject();
			}
		}
		return null;
	}

	/**
	 * Build or persist and object using XFactory <code>fa</code> before
	 * this object get built or persisted.
	 *
	 * @param fa XFactory to build or persist a child object
	 * @return child object
	 */
    public < T1, F extends AbstractXFactory< T1 >> T1 xpersistBefore(F fa) {
        return xpersistBefore(fa, null);
    }

	/**
	 * Build or persist and object using XFactory <code>fa</code> before
	 * this object get built or persisted.
	 *
	 * @param fa XFactory to build or persist a child object
	 * @param conf XFactory configuration block for the child object
	 * @return child object
	 */
    public < T1, F extends AbstractXFactory< T1 >> T1 xpersistBefore(F fa, Procedure1< F > conf) {
        persistBeforeList.add(fa);
        childFactories.add(fa);
        return XFactory.xbuild(fa, conf);
    }

	/**
	 * Build or persist and object using XFactory <code>fa</code> after
	 * this object get built or persisted.
	 *
	 * @param fa XFactory to build or persist a child object
	 * @return child object
	 */
    public < T1, F extends AbstractXFactory< T1 >> T1 xpersistAfter(F fa) {
    	throw new InternalError("Implement me");
    }

	@SuppressWarnings("unchecked")
	<F extends AbstractXFactory<T>> T applyInitBlock(Procedure1<F> initBlock) {
		if (initBlock != null) {
			initBlock.apply((F) this);
		} else {
			minimal();
		}
		return xobject;
	}

	void persist() {
		if (!isPersisted) {
			isPersisted = true;
			persistBefore();
			save();
			persistAfter();
		}
	}

	private void save() {
		InfrastructureProvider infrastructureProvider = InfrastructureManager.getInfrastructureProvider();
		infrastructureProvider.persist(xobject);
	}

	private void persistBefore() {
        for (AbstractXFactory< ? > abstractFactory : persistBeforeList) {
            abstractFactory.persist();
        }

		// TODO
		// for (AbstractXFactory< ? > abstractFactory : persistBeforeList) {
		// abstractFactory.persist();
		// }
	}

	private void persistAfter() {
		// TODO
		// for (AbstractXFactory< ? > abstractFactory : persistAfterList) {
		// abstractFactory.persist();
		// }
	}

	private void createXObject() {
		Class<T> xobjectClass = getXobjectClass();
		try {
			Constructor<T> constructor = xobjectClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			xobject = constructor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Error creating a new instance of the "
					+ xobjectClass.getCanonicalName()
					+ " using default constructor", e);
		}
	}

	/**
	 * Return class of the object to build.
	 *
	 * return Class object of the type <code>T</code>
	 */
	public Class<T> getXobjectClass() {
		return xobjectClass;
	}

	@SuppressWarnings("unchecked")
	private void initXobjectClass() {
		Class<?> directChild = this.getClass();

		ParameterizedType type = (ParameterizedType) directChild
				.getGenericSuperclass();

		xobjectClass = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + xobject + "]";
	}

}
