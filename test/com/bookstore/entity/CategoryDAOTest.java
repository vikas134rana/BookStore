package com.bookstore.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.CategoryDAO;

public class CategoryDAOTest extends BaseDAOTest {

	public static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUp() throws Exception {
		BaseDAOTest.setUpClass();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@Test
	public void testCreateCategory() {
		String name = "Fantasy";
		Category cat = new Category(name);
		cat = categoryDAO.create(cat);
		assert (cat.getCategoryId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateCategoryWithNoName() {
		String name = "";
		Category cat = new Category(name);
		cat = categoryDAO.create(cat);
	}

	@Test
	public void testUpdateCategory() {
		int categoryId = 4;
		String name = "War";
		Category cat = new Category(categoryId, name);
		cat = categoryDAO.update(cat);
		assert (cat.getName().equals(name));
	}

	@Test
	public void testGetCategory() {
		int categoryId = 4;
		Category cat = categoryDAO.get(categoryId);
		assertNotNull(cat);
	}

	@Test
	public void testGetCategoryInvalidId() {
		int categoryId = 999;
		Category cat = categoryDAO.get(categoryId);
		assertNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		int categoryId = 3;
		categoryDAO.delete(categoryId);
		Category cat = categoryDAO.get(categoryId);
		assertNull(cat);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteCategoryNotExist() {
		int categoryId = 999;
		categoryDAO.delete(categoryId);
	}

	@Test
	public void testListAllCategory() {
		List<Category> categoryList = categoryDAO.listAll();
		assert (categoryList.size() > 0);
	}

	@Test
	public void testCountCategory() {
		long count = categoryDAO.count();
		assert (count > 0);
	}

	@Test
	public void testFindByNameCategory() {
		String name = "Programing";
		Category cat = categoryDAO.findByName(name);
		assertNotNull(cat);
	}

	@Test
	public void testFindByNameCategoryNotExist() {
		String name = "ABC";
		Category cat = categoryDAO.findByName(name);
		assertNull(cat);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownClass();
	}
}
