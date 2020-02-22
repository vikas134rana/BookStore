package com.bookstore.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGenerator;

public class CustomerDaoTest {

	private CustomerDAO customerDao;

	public CustomerDaoTest() {
		customerDao = new CustomerDAO();
	}

	@Test
	public void listCustomerTest() {
		List<Customer> customerList = customerDao.listAll();
		assertEquals(customerList.size(), 1);
	}

	@Test
	public void createCustomerTest() {
		String email = "aman@gmail.com";
		String fullName = "Aman Singh ";
		String address = "dedh Sarai";
		String city = "Delhi";
		String country = "India";
		String password = "pass";
		String phone = "9958355656";
		String zipcode = "110019";

		Customer customer = new Customer(email, fullName, address, city, country, null, password, phone, zipcode);

		customer = customerDao.create(customer);

		assert (customer.getCustomerId() > 0);
	}

	@Test
	public void updateCustomerTest() {
		int customerId = 1;
		String email = "vikas@gmail.com";
		String fullName = "Vikas Rana";
		String newAddress = "F108, Katwaria Sarai";
		String city = "Delhi";
		String country = "India";
		String password = "pass";
		String phone = "9958350998";
		String zipcode = "110016";

		Customer customer = new Customer(email, fullName, newAddress, city, country, new Date(), password, phone, zipcode);
		customer.setCustomerId(customerId);

		customer = customerDao.update(customer);

		assertEquals(newAddress, customer.getAddress());
	}

	@Test
	public void getCustomerTest() {
		int customerId = 1;
		Customer customer = customerDao.get(customerId);
		assertNotNull(customer);
	}

	@Test
	public void deleteCustomerTest() {
		int customerId = 2;
		customerDao.delete(customerId);
		Customer customer = customerDao.get(customerId);
		assertNull(customer);
	}

	@Test
	public void checkLoginCustomerTest() {
		String email = "vikas@gmail.com";
		String password = "pass";
		String encryptedPassword = HashGenerator.generateSHA256(password);
		System.out.println(encryptedPassword);
		boolean status = customerDao.checkLogin(email, encryptedPassword);
		assertTrue(status);
	}
}
