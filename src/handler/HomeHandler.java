package handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import shared.Book;
import shared.SessionHelper;

@ManagedBean(name = "homeHandler")
@SessionScoped
public class HomeHandler {
	private Book book;
	
	@ManagedProperty(value="#{cartHandler}")
	private CartHandler cartHandler;
	
	public void setCartHandler(CartHandler cartHandler) {
		this.cartHandler = cartHandler;
	}
	
	public HomeHandler() {
		db.Warehouse warehouse = new db.Warehouse("no_path");
		book = warehouse.getRandomBook();
	}
	
	public String getTitle() {
		return book != null ? book.getName() : "kein Buch gefunden";
	}

	public String getDescription() {
		return book != null ? book.getDescription() : "kein Buch gefunden";
	}

	public String getIsbn() {
		return book != null ? String.valueOf(book.getIsbn()) : "kein Buch gefunden";
	}

	public String getDetail() {
		return book != null ? book.getAuthor() : "kein Buch gefunden";
	}

	public String getPrice() {
		return book != null ? String.valueOf(book.getPreis()) : "kein Buch gefunden";
	}

	//TODO: überhaupt benötigt?
	public String getTotalPrice() {
		return book != null ? "foo_bar" : "foo_bar";
	}

	public String getAuthor() {
		return book.getAuthor();
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}
	
	public void addToCart(int ammount) {
		cartHandler.addBook(book, ammount);
	}
}
