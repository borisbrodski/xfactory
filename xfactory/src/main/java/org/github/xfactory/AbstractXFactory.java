package org.github.xfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

public abstract class AbstractXFactory<T> {
	private T entity;
	private boolean isPersisted;

	public AbstractXFactory() {
		createXObject();
	}

	public abstract void minimal();

	public T getEntity() {
		return entity;
	}

	public void set(Procedure1<T> setter) {
		setter.apply(entity);
	}

	public T getXObject() {
		return entity;
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

	private Class<T> getXobjectClass() {
		Class<?> directChild = this.getClass();

		ParameterizedType type = (ParameterizedType) directChild
				.getGenericSuperclass();
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
		return clazz;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + entity + "]";
	}

}
