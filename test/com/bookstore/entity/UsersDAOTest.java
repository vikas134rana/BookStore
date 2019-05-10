package com.bookstore.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.UserDAO;

public class UsersDAOTest extends BaseDAOTest {

	private static UserDAO usersDAO;

	@BeforeClass
	public static void setUpClass() {
		BaseDAOTest.setUpClass();
		usersDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users user = new Users();
		user.setEmail("hulk@gmail.com");
		user.setFullName("Bruce Banner");
		user.setPassword("avengers");

		user = usersDAO.create(user);

		assert (user.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user = new Users();
		user = usersDAO.create(user);
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(5);
		user.setEmail("java@gmail.com");
		user.setFullName("James Gosling");
		user.setPassword("JavaIsBestLatest");

		String expectedPassword = user.getPassword();

		user = usersDAO.update(user);

		assertEquals(expectedPassword, user.getPassword());
	}

	@Test()
	public void testGetUsers() {
		int userId = 3;
		Users user = usersDAO.get(userId);
		assertNotNull(user);
	}

	@Test()
	public void testGetUsersNotExist() {
		int userId = 3333;
		Users user = usersDAO.get(userId);
		assertNull(user);
	}

	@Test()
	public void testDeleteUsers() {
		int userId = 5;
		usersDAO.delete(userId);
		Users user = usersDAO.get(userId);
		assertNull(user);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteUsersNotExist() {
		int userId = 5;
		usersDAO.delete(userId);
	}

	@Test()
	public void testListAllUsers() {
		List<Users> usersList = usersDAO.listAll();

		for (Users users : usersList) {
			System.out.println(users.getEmail());
		}

		assert (usersList.size() > 0);
	}

	@Test()
	public void testCountUsers() {
		long usersCount = usersDAO.count();
		System.out.println("Users Count: " + usersCount);
		assert (usersCount > 0);
	}

	@Test()
	public void testFindByEmailUsers() {
		String email = "vikas@gmail.com";
		Users user = usersDAO.findByEmail(email);
		assertNotNull(user);
	}

	@Test()
	public void testFindByWrongEmailUsers() {
		String email = "kamlesh420@gmail.com";
		Users user = usersDAO.findByEmail(email);
		assertNull(user);
	}

	@Test
	public void testCheckLoginUser() {
		String email = "vikas@gmail.com";
		String password = "pass";
		boolean loginResult = usersDAO.checkLogin(email, password);
		assertTrue(loginResult);
	}
	
	@Test
	public void testCheckLoginUserWrong() {
		String email = "gajodhar@gmail.com";
		String password = "pass";
		boolean loginResult = usersDAO.checkLogin(email, password);
		assertFalse(loginResult);
	}

	@Test
	public void generateMD5test() {
		String text = "pass";
		String encryptedText = HashGenerator.generateSHA256(text);
		System.out.println(encryptedText);
		assertNotNull(encryptedText);
	}
	
	@AfterClass
	public static void tearDownClass() {
		BaseDAOTest.tearDownClass();
	}

}
