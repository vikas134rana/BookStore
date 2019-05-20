package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;
import com.bookstore.entity.Users;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterOn(new Date());
		return super.create(customer);
	}

	@Override
	public Customer update(Customer customer) {
		return super.update(customer);
	}

	@Override
	public Customer get(Object customerId) {
		return super.find(Customer.class, customerId);
	}

	@Override
	public void delete(Object customerId) {
		super.delete(Customer.class, customerId);
	}

	@Override
	public List<Customer> listAll() {
		return findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}

	public Customer findByEmail(String email) {
		List<Customer> customerList = super.findWithNamedQuery("Customer.findByEmail", "email", email);
		if (customerList.size() == 1)
			return customerList.get(0);
		return null;
	}

	public boolean checkLogin(String email, String password) {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);

		List<Customer> customerList = super.findWithNamedQuery("Customer.login", parameters);

		if (customerList.size() == 1)
			return true;

		return false;
	}

}
