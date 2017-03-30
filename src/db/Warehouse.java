package db;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.util.ArrayList;
import java.util.Comparator;

import shared.Book;
import shared.Category;

/**
 * Diese Klasse stellt das Backend dar, über das auf die vorhandenen Bücher
 * zugegriffen wird.
 * 
 * @author fluktuid
 *
 */
@WebServlet("/warehousebackend")
public class Warehouse extends HttpServlet {
	private static final long serialVersionUID = -7557901593589348896L;
	private Set<Book> books;
	private String path;

	public Warehouse(String path) {
		this.path = path;
		try {
			String data = new Db().loadData(path);
			books = JsonModifier.toBooks(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns a hashset of stored books
	 * 
	 * @return books
	 */
	public Set<Book> getBooks() {
		return books;
	}

	/**
	 * fügt dem lokalen Speicher n Bücher hinzu
	 * 
	 * @param book
	 *            Bücher
	 * @return added
	 */
	public void addBooks(Book... books) {
		for (Book b : books)
			addBook(b);
	}

	/**
	 * fügt dem lokalen Speicher ein Buch hinzu
	 * 
	 * @param book
	 *            Buch
	 * @return added hinzugefügt
	 */
	public boolean addBook(Book book) {
		boolean added = books.add(book);
		new Db().saveData(path, JsonModifier.toJson(books));
		return added;
	}

	/**
	 * returns matching book
	 * 
	 * @param category
	 * @return
	 */
	public Set<Book> getBooks(String category) {
		if (category == null || category.isEmpty())
			return books;
		TreeSet<Book> coll = new TreeSet<>(new BookComparator());
		for (Book b : books)
			if (b.getCategory().equals(category))
				coll.add(b);
		return coll;
	}

	/**
	 * gibt das zu einer ISBN gehörige Buch zurück
	 * @param isbn
	 * @return das Buch mit der gegebenen ISBN
	 */
	public Book getBookByISBN(double isbn) {
		for (Book b : books)
			if (b.getIsbn() == isbn)
				return b;
		return null;
	}

	public boolean removeBook(double isbn) {
		boolean removed = books.remove(getBookByISBN(isbn));
		new Db().saveData(path, JsonModifier.toJson(books));
		return removed;
	}

	/**
	 * 
	 * @return ein Set aus den verwendeten Kategorien
	 */
	public Set<Category> getCategories() {
		final Set<Category> categories = new TreeSet<Category>(new CategoryComparator());

		// fügt neue Kategorien hinzu, wenn der Name dieser nicht leer ist.
		books.stream().forEach(e -> {
			String category = e.getCategory();
			if (!category.isEmpty())
				categories.add(new Category(category));
		});
		return categories;
	}

	/**
	 * Comparator um die Kategorien alphabetisch in einem Treeset zu sortieren
	 * 
	 * @author fluktuid
	 *
	 */
	private static class CategoryComparator implements Comparator<Category> {
		@Override
		public int compare(Category c1, Category c2) {
			return c1.getCategoryName().compareTo(c2.getCategoryName());
		}

	}
	/*
	 * Comparator um die Bücher nach ISBN in einem Treeset zu sortieren
	 */
	public static class BookComparator implements Comparator<Book> {
		@Override
		public int compare(Book b1, Book b2) {
			return Double.compare(b1.getIsbn(), b2.getIsbn());
		}
	}

	/**
	 * Gibt bis zu "count" viele zufällige Bücher zurück
	 * 
	 * @param count Anzahl der Bücher
	 * @return zufällige Bücher
	 */
	public Set<Book> getRandomBooks(int count) {
		Set<Book> chosen = new TreeSet<>(new BookComparator());
		ArrayList<Book> bks = new ArrayList<>(books);

		try {
			// fügt so lange Bücher hinzu, bis 5 in chosen oder alle aus books
			// in
			// chosen sind
			while (chosen.size() < count && chosen.size() < books.size()) {
				int rndm = getRandomNr(0, books.size());
				chosen.add(bks.get(rndm));
			}
		} catch (Exception ignore) {
		}
		return chosen;
	}

	/**
	 * gibt ein zufälliges Buch zurück
	 * @return ein zufälliges Buch
	 */
	public Book getRandomBook() {
		ArrayList<Book> bks = new ArrayList<>(books);
		int rndm = getRandomNr(0, books.size() - 1);
		return bks.get(rndm);
	}

	/**
	 * generiert eine zufällige Zahl >0
	 * @param minimum Minimalwert
	 * @param maximum Maximalwert
	 * @return zufällige Zahl zwischen Minimal- & Maximalwert
	 */
	private int getRandomNr(int minimum, int maximum) {
		java.util.Random rn = new java.util.Random();
		int n = maximum - minimum + 1;
		int i = rn.nextInt() % n;
		int randomNum = minimum + i;
		return Math.abs(randomNum);
	}
}
