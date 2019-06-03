package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder order) {
		order.setOrderDate(new Date());
		return super.create(order);
	}

	@Override
	public BookOrder update(BookOrder order) {
		return super.update(order);
	}

	@Override
	public BookOrder get(Object orderId) {
		return super.find(BookOrder.class, orderId);
	}

	@Override
	public void delete(Object id) {
		super.delete(BookOrder.class, id);
	}

	@Override
	public List<BookOrder> listAll() {
		return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count() {
		return countWithNamedQuery("BookOrder.countAll");
	}

	public List<BookOrder> listByCustomer(int customerId) {
		return super.findWithNamedQuery("BookOrder.findByCustomer", "customer_id", String.valueOf(customerId));
	}

	public BookOrder listByIdAndCustomer(int orderId, int customerId) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("order_id", orderId);
		parameters.put("customer_id", customerId);

		List<BookOrder> orderList = super.findWithNamedQuery("BookOrder.findByIdAndCustomer", parameters);

		if (!orderList.isEmpty())
			return orderList.get(0);

		return null;
	}

}
