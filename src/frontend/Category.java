package frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.Book;

/**
 * Diese Klasse ist nicht mehr verfügbar, da es category.jsp gibt
 * 
 * @deprecated
 */
@WebServlet("/category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Set<Book> books;
	db.Warehouse warehouse;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		warehouse = new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF"));

		response.setContentType("text/html");
		String category = request.getParameter("category");

		// Handling der leeren Category (allgemein) wird vom Warehouse erledigt
		books = warehouse.getBooks(category);

		PrintWriter pw = response.getWriter();

		pw.append(Helper.getNamespace());
		pw.append(Helper.getHeader());
		// pw.append(Helper.getStickyFooter("content", "blue"));
		pw.append(Helper.getQuote(getServletContext()));
		pw.append(Helper.getMenu(warehouse.getCategories()));

		if (books != null && books.size() > 0)
			for (Book b : books)
				pw.append(b.toHtmlElement());
		else if (books.size() == 0)
			pw.append(Helper.noBookInCategory());
		pw.append(Helper.getCopyright());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
