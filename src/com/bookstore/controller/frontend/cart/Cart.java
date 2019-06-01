package com.bookstore.controller.frontend.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class Cart {

	private Map<Book, Integer> cartMap = new HashMap<>();

	public void addItem(Book book) {
		if (cartMap.containsKey(book)) {
			int quantity = cartMap.get(book) + 1;
			cartMap.put(book, quantity);
		} else {
			cartMap.put(book, 1);
		}
	}

	public void removeItem(Book book) {
		cartMap.remove(book);
	}

	public int getTotalQuantity() {
		return cartMap.values().stream().mapToInt(i -> i).sum();
	}

	public float getTotalAmount() {
		float total = 0.0f;

		for (Entry<Book, Integer> entry : cartMap.entrySet()) {
			float subTotal = entry.getKey().getPrice() * entry.getValue();
			total += subTotal;
		}
		return total;
	}

	public Map<Book, Integer> getItems() {
		return cartMap;
	}

	public void clear() {
		cartMap.clear();
	}

	public int getSize() {
		return cartMap.size();
	}

	public boolean isEmpty() {
		return cartMap.size() == 0;
	}

	public void updateCart(int[] bookIds, int[] quantities) {

		for (int i = 0; i < bookIds.length; i++) {
			Book book = new BookDAO().get(bookIds[i]);
			cartMap.put(book, quantities[i]);
		}
	}
}
