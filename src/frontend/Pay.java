package frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Warehouse;
import shared.Book;

/**
 * @author fluktuid
 */
@WebServlet("/pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	db.Warehouse warehouse;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		warehouse = new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF"));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.append(Helper.getHeader());
		pw.append(Helper.getQuote(getServletContext()));
		pw.append(Helper.getMenu(warehouse.getCategories()));
		Set<Book> books = warehouse.getRandomBooks(5);
		if (books != null)
			for (Book b : books)
				pw.append(b.toHtmlElement());
		pw.append(Helper.getCopyright());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
