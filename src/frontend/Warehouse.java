package frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.Book;

/**
 * Diese Klasse stellt die Bearbeitungsseite für das 'Buchlager' dar.
 * 
 * @author fluktuid
 *
 */
@WebServlet("/warehouse")
public class Warehouse extends HttpServlet {
	private static final String BUTTON_VALUE = "bValue";
	db.Warehouse warehouse;

	private boolean storeBook(Book book) {
		warehouse = new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF"));
		System.out.println("storeBook");
		return warehouse.addBook(book);
	}

	private Book generateBook(HttpServletRequest request) throws IllegalArgumentException {
		try {
			String isbn_string = request.getParameter("isbn");
			double isbn = Double.valueOf(isbn_string.replaceAll("[^0-9]+", ""));
			String name = request.getParameter("name");
			String autor = request.getParameter("autor");
			String beschreibung = request.getParameter("beschreibung");
			String kategorie = request.getParameter("kategorie");
			String link = request.getParameter("link");
			double preis = Double.valueOf(request.getParameter("preis"));

			return new Book(isbn, name, autor, beschreibung, kategorie, preis,link);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		warehouse = new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF"));
		System.out.println("doGet");
		String deleteISBN = request.getParameter("remove");
		System.out.println("submit:" + request.getParameter("submit"));
		boolean wrongInput = false;
		if (deleteISBN != null && !deleteISBN.isEmpty()) {
			boolean deleted = warehouse.removeBook(Double.valueOf(deleteISBN));
			if (deleted)
				System.out.println("DELETED: " + deleteISBN);
		} else if (request.getParameter("submit") != null) {
			try {
				Book book = generateBook(request);
				storeBook(book);
			} catch (IllegalArgumentException ignore) {
				wrongInput = true;
			}
		}
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.append(Helper.getHeader());
//		if (wrongInput)
//			pw.append(wrongInputHtml());
		pw.append(generateForm());
		String cobh = countOfBooksHtml();
		System.out.println("cobh:" + cobh);
		pw.append(cobh);
		pw.append(Helper.getCopyright());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		doGet(request, response);
	}

	private String wrongInputHtml() {
		return "<font color=\"red\" size=\"5\">Es gab einen Fehler mit der Eingabe.</font>";
	}

	private String countOfBooksHtml() {
		Collection<Book> books = warehouse.getBooks();
		if(books==null)
			books = new ArrayList<Book>();
		StringBuilder builder = new StringBuilder();
		builder.append("<p>Es sind zur Zeit " + books.size() + " Bücher gespeicher.<br/> Diese sind:");
		builder.append("<ul>");
		for (Book b : books)
			builder.append("<li>").append(b.getName()).append(" (ISBN:").append(b.getIsbn()).append(")")
					.append("<form name=\"removeBookForm\" method=\"post\" action=\"warehouse\">")
					.append("<button class=\"red\" name=\"remove\" value=\"" + b.getIsbn()
							+ "\"> <i class=\"fa fa-remove\"></i> </button>")
					.append("</form>").append("</li>");
		builder.append("</ul>");
		builder.append("</p>");
		return builder.toString();
	}

	// gibt die Form zum Eingeben aus
	private String generateForm() {
		return "<form name=\"newBookForm\" method=\"post\" action=\"warehouse\">"
				+ "ISBN:         <input type=\"text\" name=\"isbn\"/> <br/>"
				+ "Name:         <input type=\"text\" name=\"name\"/> <br/>"
				+ "Autor:        <input type=\"text\" name=\"autor\"/> <br/>"
				+ "Beschreibung: <input type=\"text\" name=\"beschreibung\"/> <br/>"
				+ "Link:         <input type=\"text\" name=\"link\"/> <br/>"

				// +"<textarea id=\"textarea1\" placeholder=\"Placeholder
				// Text\"></textarea>"

				+ "Kategorie:    <input type=\"text\" name=\"kategorie\"/> <br/>"
				+ "Preis (in Bitcoin):        <input type=\"number\" name=\"preis\" step=\"0.0001\" value=\"0.0071\"/> <br/>"
				+ "<button class=\"green\" name=\"submit\" value=\"newBook\"><i class=\"fa fa-plus\"></i> absenden</button>"
				+ "</form>";
	}
}
