package org.github.xfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

public abstract class AbstractXFactory<T> {
	private T entity;
	private boolean isPersisted;
    private List< AbstractXFactory< ? > > persistBeforeList = new ArrayList< AbstractXFactory< ? > >();
    private List< AbstractXFactory< ? > > childFactories = new ArrayList< AbstractXFactory< ? > >();
    private Class<T> xobjectClass;

	public AbstractXFactory() {
		initXobjectClass();
		createXObject();
	}

	public abstract void minimal();

	public void set(Procedure1<T> setter) {
		setter.apply(entity);
	}

	public T xobject() {
		return entity;
	}

	@SuppressWarnings("unchecked")
	public <E> E xobject(Class<E> clazz) {
		for (AbstractXFactory<?> childFactory : childFactories) {
			if (childFactory.getXobjectClass() == clazz) {
				return (E) childFactory.xobject();
			}
		}
		return null;
	}

    public < T1, F extends AbstractXFactory< T1 >> T1 xpersistBefore(F fa) {
        return xpersistBefore(fa, null);
    }

    public < T1, F extends AbstractXFactory< T1 >> T1 xpersistBefore(F fa, Procedure1< F > conf) {
        persistBeforeList.add(fa);
        childFactories.add(fa);
        return XFactory.xbuild(fa, conf);
    }


	@SuppressWarnings("unchecked")
	<F extends AbstractXFactory<T>> T applyInitBlock(Procedure1<F> initBlock) {
		if (initBlock != null) {
			initBlock.apply((F) this);
		} else {
			minimal();
		}
		return entity;
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
		InfrastructureProvider infrastructureProvider = XFactory
				.getInfrastructureProvider();
		EntityManager entityManager = infrastructureProvider.getEntityManager();
		if (!entityManager.contains(entity)) {
			// TODO Call infrastructureProvider.prePersist(entity);
			entityManager.persist(entity);
		}
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
			entity = constructor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Error creating a new instance of the "
					+ xobjectClass.getCanonicalName()
					+ " using default constructor", e);
		}
	}

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
		return getClass().getSimpleName() + " [" + entity + "]";
	}

}
