package handler;

import java.util.ArrayList;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;

import frontend.Helper;
import shared.Book;
import shared.Category;

@ManagedBean(name = "categoryHandler")
@SessionScoped
public class CategoryHandler implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private db.Warehouse warehouse;

	private DataModel<Book> books = new ListDataModel<Book>();
	private Book currentBook = new Book();
	private String category = "";

	public CategoryHandler() {
		warehouse = new db.Warehouse("no_path");
	}

	/**
	 * returns all Books containing the warehouse
	 * 
	 * @return
	 */
	public DataModel<Book> getBooks() {
		Set<Book> bookSet = warehouse.getBooks(category);
		if (bookSet.size() == 0)
			bookSet = warehouse.getBooks();
		ArrayList<Book> bookAL = new ArrayList<>();
		bookAL.addAll(bookSet);
		books.setWrappedData(bookAL);
		return books;
	}

	/**
	 * gibt alle Bücher abhängig von der Kategorie zurück
	 * 
	 * @deprecated
	 * @param category
	 *            Kategorie der Bücher
	 * @return ein DataModel aus den Büchern der Kategorie
	 */
	public DataModel<Book> getBooks(String category) {
		Set<Book> bookSet = warehouse.getBooks(category);
		books.setWrappedData(new ArrayList<>(bookSet));
		return books;
	}

	/**
	 * Gibt ein zufälliges Zitat aus
	 * 
	 * @return ein zufälliges Zitat
	 */
	public String getQuote() {
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		try {
			return Helper.getQuote(context);
		} catch (javax.el.ELException e) {
			return getQuote();
		}
	}

	/**
	 * gibt alle Kategorien aus
	 * 
	 * @return ein Array aus allen Kategorien
	 */
	public String[] getCategories() {
		Set<Category> categorySet = warehouse.getCategories();
		return categorySet.stream().map(e -> e.getCategoryName()).toArray(String[]::new);
	}
	public void setCategories() {
		System.out.println("setCategories");
	}

	/*
	 * bereitet den Detailview vor und gibt den Link zurück
	 * 
	 * @return Link zum DetailView
	 */
	public String getDetailView() {
		if (books.getRowCount() < 1)
			getBooks();
		currentBook = books.getRowData();
		return "/detail.jsf";
	}

	public Book getCurrentBook() {
		return currentBook;
	}

	public String setCategory() {
		return setCategory("");
	}

	public String setCategory(String category) {
		this.category = category;
		return "/  category.xhtml";
	}
}