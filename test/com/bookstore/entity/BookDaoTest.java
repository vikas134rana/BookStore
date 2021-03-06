package com.bookstore.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.BookDAO;

public class BookDaoTest {

	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpClass() {
		bookDAO = new BookDAO();
	}

	@Test
	public void createBookTest() throws ParseException, IOException {

		/*-Book book = new Book();
		
		Category cat = new Category("Programming");
		cat.setCategoryId(1);
		
		book.setCategory(cat);
		book.setTitle("Effective Java 3rd Edition");
		book.setAuthor("Joshua Bloch");
		book.setDescription("The Definitive Guide to Java Platform Best Practices–Updated for Java 7, 8, and 9");
		book.setIsbn("978-0134685991");
		book.setPrice(30.16f);
		
		Date publishDate = new SimpleDateFormat("dd/mm/yyyy").parse("11/05/2018");
		book.setPublishDate(publishDate);
		
		byte[] allBytes = Files.readAllBytes(Paths.get("C:\\Users\\Vikas\\Desktop\\Bookstore Content\\book\\effectivejava.jpg"));
		book.setImage(allBytes);
		
		book = bookDAO.create(book);*/

		Book book = new Book();

		Category cat = new Category("Historical");
		cat.setCategoryId(1);

		book.setCategory(cat);
		book.setTitle("The Perfect Murder");
		book.setAuthor("Ruskin Bond");
		book.setDescription("In that dim corner of the cafe was planned the perfect murder. ");
		book.setIsbn("978-8129144973");
		book.setPrice(3.2f);

		Date publishDate = new SimpleDateFormat("dd/mm/yyyy").parse("14/07/2016");
		book.setPublishDate(publishDate);

		byte[] allBytes = Files.readAllBytes(Paths.get("C:\\Users\\Vikas\\Desktop\\Bookstore Content\\book\\the_perfect_murder.jpg"));
		book.setImage(allBytes);

		book = bookDAO.create(book);

		assert (book.getBookId() > 0);
	}

	@Test
	public void updateBookTest() throws ParseException, IOException {
		Book book = new Book();

		float newPrice = 24.21f;

		String publishDateString = "16/04/2018";

		Category cat = new Category("Programming");
		cat.setCategoryId(10);

		book.setBookId(1);
		book.setCategory(cat);
		book.setTitle("Effective Java 3rd Edition");
		book.setAuthor("Joshua Bloch");
		book.setDescription("The Definitive Guide to Java Platform Best Practices–Updated for Java 7, 8, and 9");
		book.setIsbn("978-0134685991");
		book.setPrice(newPrice);

		Date publishDate = new SimpleDateFormat("dd/mm/yyyy").parse(publishDateString);
		book.setPublishDate(publishDate);

		byte[] allBytes = Files.readAllBytes(Paths.get("C:\\Users\\Vikas\\Desktop\\Bookstore Content\\book\\effectivejava.jpg"));
		book.setImage(allBytes);

		book = bookDAO.update(book);

		assert (book.getPrice() == newPrice);
	}

	@Test
	public void getBookTest() {
		int bookId = 1;
		Book book = bookDAO.get(bookId);
		assertNotNull(book);
	}

	@Test
	public void getBookTestInvalid() {
		int bookId = 999;
		Book book = bookDAO.get(bookId);
		assertNull(book);
	}

	@Test
	public void listAllBookTest() {
		List<Book> bookList = bookDAO.listAll();
		assert (bookList.size() > 0);
	}

	@Test
	public void countBookTest() {
		long count = bookDAO.count();
		assert (count == 2);
	}

	@Test
	public void findByTitleTest() {
		String title = "The Perfect Murder";
		Book book = bookDAO.findByTitle(title);
		assertEquals(book.getTitle(), title);
	}

	@Test
	public void findByTitleTestInvalid() {
		String title = "Some Wrong title";
		Book book = bookDAO.findByTitle(title);
		assertNotEquals(book.getTitle(), title);
	}

	@Test
	public void findByCategoryTest() {
		int categoryId = 1;
		List<Book> bookList = bookDAO.findBookByCategory(categoryId);
		assertEquals(bookList.size(), 3);
	}

	@Test
	public void searchBookTest() {
		String searchText = "head";
		List<Book> bookList = bookDAO.searchBooks(searchText);
		assertEquals(bookList.size(), 2);
	}

	@Test
	public void countBookByCategoryTest() {
		int categoryId = 1;
		long bookCount = bookDAO.countBookByCategory(categoryId);
		assert (bookCount > 2);
	}

	@Test
	public void newBooksTest() {
		List<Book> books = bookDAO.listNewBooks();

		System.out.println("#### " + " New Books " + " ####");
		for (Book book : books) {
			System.out.println(book.getBookId() + " " + book.getTitle() + " " + book.getAuthor());
		}
	}

	@Test
	public void bestSellingBooksTest() {
		List<Book> books = bookDAO.listBestSellingBooks();
		System.out.println("#### " + " Best Selling Books " + " ####");
		for (Book book : books) {
			System.out.println(book.getBookId() + " " + book.getTitle() + " " + book.getAuthor());
		}
	}

	@Test
	public void mostFavouredBooksTest() {
		List<Book> books = bookDAO.listMostFavouredBooks();
		System.out.println("#### " + " Most Favoured " + " ####");
		for (Book book : books) {
			System.out.println(book.getBookId() + " " + book.getTitle() + " " + book.getAuthor());
		}
	}

	@AfterClass
	public static void tearDownClass() {
		bookDAO.close();
	}

}
