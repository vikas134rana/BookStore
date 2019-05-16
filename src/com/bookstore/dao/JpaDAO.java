package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDAO<E> {

	private static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStore");
	}

	public JpaDAO() {
		super();
	}

	public E create(E e) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.persist(e);
		entityManager.flush();
		entityManager.refresh(e);

		entityManager.getTransaction().commit();

		entityManager.close();
		return e;
	}

	public E update(E e) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(e);
		entityManager.getTransaction().commit();
		entityManager.close();
		return e;
	}

	public E find(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		E entity = entityManager.find(type, id);
		if (entity != null) {
			entityManager.refresh(entity);
		}
		entityManager.close();
		return entity;
	}

	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		E entity = entityManager.getReference(type, id);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<E> findWithNamedQuery(String query) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query queryObj = entityManager.createNamedQuery(query);
		List<E> list = queryObj.getResultList();
		entityManager.close();
		return list;
	}

	public List<E> findWithNamedQuery(String query, int startPosition, int maxResult) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query queryObj = entityManager.createNamedQuery(query);
		queryObj.setFirstResult(startPosition);
		queryObj.setMaxResults(maxResult);
		List<E> list = queryObj.getResultList();
		entityManager.close();
		return list;
	}

	public List<E> findWithNamedQuery(String query, String paramName, String paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query queryObj = entityManager.createNamedQuery(query);
		queryObj.setParameter(paramName, paramValue);
		List<E> list = queryObj.getResultList();
		entityManager.close();
		return list;
	}

	public long countWithNamedQuery(String query) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query queryObj = entityManager.createNamedQuery(query);
		long count = (long) queryObj.getSingleResult();
		entityManager.close();
		return count;
	}

	public List<E> findWithNamedQuery(String query, Map<String, Object> parameters) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query queryObj = entityManager.createNamedQuery(query);

		for (Entry<String, Object> entry : parameters.entrySet()) {
			queryObj.setParameter(entry.getKey(), entry.getValue());
		}

		List<E> list = queryObj.getResultList();
		entityManager.close();
		return list;
	}

	public void close() {
		if (entityManagerFactory != null)
			entityManagerFactory.close();
	}

	public long countWithNamedQuery(String query, Map parameters) {
		return 0;
	}

}
