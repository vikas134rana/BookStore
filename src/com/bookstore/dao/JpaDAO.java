package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaDAO<E> {

	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public E create(E e) {
		entityManager.getTransaction().begin();

		entityManager.persist(e);
		entityManager.flush();
		entityManager.refresh(e);

		entityManager.getTransaction().commit();

		return e;
	}

	public E update(E e) {
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
		return e;
	}

	public E find(Class<E> type, Object id) {
		E entity = entityManager.find(type, id);
		if (entity != null) {
			entityManager.refresh(entity);
		}
		return entity;
	}

	public void delete(Class<E> type, Object id) {
		entityManager.getTransaction().begin();
		E entity = entityManager.getReference(type, id);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	public List<E> findWithNamedQuery(String query) {
		Query queryObj = entityManager.createNamedQuery(query);
		return queryObj.getResultList();
	}

	public List<E> findWithNamedQuery(String query, String paramName, String paramValue) {
		Query queryObj = entityManager.createNamedQuery(query);
		queryObj.setParameter(paramName, paramValue);
		return queryObj.getResultList();
	}

	public long countWithNamedQuery(String query) {
		Query queryObj = entityManager.createNamedQuery(query);
		return (long) queryObj.getSingleResult();
	}

	public List<E> findWithNamedQuery(String query, Map<String, Object> parameters) {
		Query queryObj = entityManager.createNamedQuery(query);

		for (Entry<String, Object> entry : parameters.entrySet()) {
			queryObj.setParameter(entry.getKey(), entry.getValue());
		}

		return queryObj.getResultList();
	}

	public long countWithNamedQuery(String query, Map parameters) {
		return 0;
	}

}
