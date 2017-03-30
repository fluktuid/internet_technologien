package shared;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.google.gson.annotations.SerializedName;

public class Order {

	@SerializedName("b")
	private Map<Book, Integer> books;
	@SerializedName("d")
	private Date date;
	private String name;
	private String city;

	public Order(Customer customer, Map<Book, Integer> books, Date date) {
		this.name = customer.getName();
		this.city = customer.getCity();
		this.books = books;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}

	public int getBookCount() {
		return books.keySet().size();
	}

	public Set<Book> getBooks() {
		return books.keySet();
	}

	public double getSum() {
//		return 1;
		AtomicReference<Double> sum = new AtomicReference<>();
		sum.set((double) 0);
		books.forEach((b, i) -> {
			sum.set(sum.get()+ b.getPreis() * i);
		});
		return sum.get();
		
	}
}
