package handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import shared.Book;
import shared.SessionHelper;

@RequestScoped
@ManagedBean(name = "detailHandler")
public class DetailHandler {

	@ManagedProperty(value = "#{categoryHandler.currentBook}")
	private Book book = new Book();

	@ManagedProperty(value = "#{cartHandler}")
	private CartHandler cartHandler;

	public void setCartHandler(CartHandler cartHandler) {
		this.cartHandler = cartHandler;
	}

	public void addBook(int ammount) {
		cartHandler.addBook(book, ammount);
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

	public String getTotalPrice() {
		return book != null ? "foo_bar" : "foo_bar";
	}
	
	public String getUrl() {
		return book.getUrl();
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

	/**
	 * fügt das Buch der Ansicht in angegebener Menge dem Einkaufswagen hinzu
	 * 
	 * @param ammount
	 *            Häufigkeit, in welcher das Buch hinzugefügt werden soll
	 */
	public void addToCart(int ammount) {
		cartHandler.addBook(book, Math.abs(ammount));
		System.out.println("addToCart:" + "," + book + "," + Math.abs(ammount));
	}
}
