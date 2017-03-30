package handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import shared.Book;

@ManagedBean(name = "cartHandler")
@SessionScoped
public class CartHandler {

	private Map<Book, Integer> map = new TreeMap<>(new BookComparator());

	private int ammount;

	public int ammount(Book book) {
		return map.getOrDefault(book, 0);
	}

	public int getAmmount(Book book) {
		return ammount(book);
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public int getAmmount() {
		return ammount;
	}

	public double getTotalPrice(Book book) {
		return map.get(book) * book.getPreis();
	}

	public double getTotalCost() {
		try {
		AtomicReference<Double> price = new AtomicReference<Double>();
		price.set((double) 0);
		map.forEach((k, v) -> {
			System.out.println(k+","+v);
			price.set(price.get()+k.getPreis() * v);
			});
		return price.get();
		}catch (NullPointerException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void addBook(Book book) {
		addBook(book, ammount);
	}

	public void addBook(Book book, int ammount) {
		System.out.println("addBook" + ammount + book.getName());
		map.put(book, ammount);
	}

	public void remove(Book book) {
		System.out.println("remove" + book.getName());
		map.remove(book);
	}

	public Collection<Book> getBooks() {
		List<Book> books = new ArrayList<>();
		books.addAll(map.keySet());
		return books;
	}

	public Map<Book, Integer> getMapBooks() {
		return map;
	}

	public void clean() {
		map.clear();
	}

	public static class BookComparator implements Comparator<Book> {
		@Override
		public int compare(Book b1, Book b2) {
			return Double.compare(b1.getIsbn(), b2.getIsbn());
		}
	}
}
