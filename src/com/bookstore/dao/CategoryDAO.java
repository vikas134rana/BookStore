package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Category create(Category cat) {
		return super.create(cat);
	}

	@Override
	public Category update(Category cat) {
		return super.update(cat);
	}

	@Override
	public Category get(Object categoryId) {
		return super.find(Category.class, categoryId);
	}

	@Override
	public void delete(Object categoryId) {
		super.delete(Category.class, categoryId);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}

	public Category findByName(String name) {
		List<Category> categoryList = super.findWithNamedQuery("Category.findByName", "name", name);
		if (!categoryList.isEmpty())
			return categoryList.get(0);
		return null;
	}

}
