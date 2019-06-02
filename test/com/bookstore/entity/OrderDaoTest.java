package com.bookstore.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.OrderDAO;

public class OrderDaoTest {

	private static OrderDAO orderDAO;

	@BeforeClass
	public static void setUp() {
		orderDAO = new OrderDAO();
	}

	@Test
	public void createOrderTest() {

		BookOrder order = new BookOrder();

		Customer customer = new Customer();
		customer.setCustomerId(1);
		order.setCustomer(customer);

		order.setPaymentMethod("Cash");
		order.setRecipientName("Kamlesh");
		order.setRecipientPhone("9876543456");
		order.setShippingAddress("gali no 3, wassepur");
		order.setStatus("Dispatched");
		order.setTotal(120);

		Set<OrderDetail> orderDetails = new HashSet<>();

		OrderDetail orderDetail = new OrderDetail();
		Book book = new Book();
		book.setBookId(1);
		orderDetails.add(orderDetail);

		orderDetail.setBook(book);
		orderDetail.setBookOrder(order);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(110.0f);

		order.setOrderDetails(orderDetails);

		order = orderDAO.create(order);

		assertNotNull(order);
	}

	@Test
	public void updateOrderTest() {

		BookOrder order = orderDAO.get(2);

		order.setPaymentMethod("Cash");
		order.setStatus("Delivered");
		
		Set<OrderDetail> orderDetails = order.getOrderDetails(); 
		
		OrderDetail orderDetail2 = new OrderDetail();
		Book book2 = new Book();
		book2.setBookId(2);
		orderDetails.add(orderDetail2);

		orderDetail2.setBook(book2);
		orderDetail2.setBookOrder(order);
		orderDetail2.setQuantity(3);
		orderDetail2.setSubtotal(190.0f);
		
		order = orderDAO.update(order);

		assertNotNull(order);
	}

	@Test
	public void getOrderTest() {
		BookOrder order = orderDAO.get(2);
		assertEquals(1, order.getOrderDetails().size());
	}

	@Test
	public void listOrderTest() {
		List<BookOrder> orders = orderDAO.listAll();
		assertEquals(1, orders.size());
	}

	@AfterClass
	public static void tearDown() {
		orderDAO.close();
	}

}
