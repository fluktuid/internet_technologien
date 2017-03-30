package handler;

import java.util.ArrayList;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import shared.Book;
import shared.SessionHelper;

@ManagedBean(name = "randomHandler")
@RequestScoped
public class RandomHandler {
	private db.Warehouse warehouse;

	private DataModel<Book> books = new ListDataModel<Book>();

	private Book currentBook = new Book();
	
	@ManagedProperty(value="#{cartHandler}")
	private CartHandler cartHandler;
	
	public void setCartHandler(CartHandler cartHandler) {
		this.cartHandler = cartHandler;
	}

	public RandomHandler() {
		warehouse = new db.Warehouse("no_path");
		generateRandomBooks();
	}
	
	/**
	 * generiert eine Liste aus zufälligen Büchern
	 */
	private void generateRandomBooks() {
		books = new ListDataModel<Book>();
		Set<Book> bookSet = warehouse.getRandomBooks(5);
		ArrayList<Book> bookAL = new ArrayList<>();
		bookAL.addAll(bookSet);
		System.out.println("randomBookSize:"+bookAL.size());
		books.setWrappedData(bookAL);
	}
	
	/**
	 * returns 5 random books from warehouse
	 * 
	 * @return ein Datamodel, welches zufällige Bücher beinhaltet
	 */
	public DataModel<Book> getBooks() {
		return books;
	}

	public Book getCurrentBook() {
		return currentBook;
	}

	public void addToCart(int ammount) {
		currentBook = books.getRowData();
		cartHandler.addBook(currentBook, ammount);
	}
}
