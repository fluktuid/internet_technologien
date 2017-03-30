package shared;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Cart {

	// key: BuchISBN; value: count
	Map<Double, Integer> countMap = new TreeMap<>();
	Set<Book> bookSet = new TreeSet<>(new db.Warehouse.BookComparator());

	public Cart() {

	}

	public Cart(Collection<Book> books) {
		bookSet.addAll(books);
	}

	/*
	 * adds Book to cart
	 * 
	 * @param book
	 *            the Book to add
	 * @param count
	 *            the count how often the book should be buyed
	 * @return
	 */
	public boolean addBook(Book book, int count) {
		countMap.put(book.getIsbn(), count);
		return bookSet.add(book);
	}

	/**
	 * updates Book in cart
	 * 
	 * @param book
	 *            the book to update
	 * @param count
	 *            the coun how often the book should be buyed
	 * @see Cart#addBook(Book,int)
	 * @return if update was successfull (or false if book was added)
	 */
	public boolean updateBook(Book book, int count) {
		return !addBook(book, count);
	}

	/**
	 * removes the matching Book from Set
	 * 
	 * @param book
	 *            the book to remove
	 * @return if book was correctly removed
	 */
	public boolean removeBook(Book book) {
		countMap.remove(book.getIsbn());
		return bookSet.remove(book);
	}

	/**
	 * returns the count value of the book
	 * 
	 * @param isbn
	 * @return if in cart the count else -1
	 */
	public int getCount(double isbn) {
		Integer count = countMap.get(isbn);
		return count != null ? count.intValue() : -1;
	}

	public Set<Book> getBooks() {
		return bookSet;
	}
}
