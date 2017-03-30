package frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.JsonModifier;
import shared.Book;
import shared.IValueCookies;

@WebServlet("/recommendation")
public class Recommendation extends HttpServlet implements IValueCookies {
	private static final long serialVersionUID = 1L;
	db.Warehouse warehouse;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		warehouse = new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF"));

		final Cookie cookie = manageCookie(request, response);

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.append(Helper.getHeader());
		pw.append(Helper.getQuote(getServletContext()));
		pw.append(Helper.getMenu(warehouse.getCategories()));

		Book b = warehouse.getBookByISBN(Double.valueOf(cookie.getValue()));
		pw.append(b != null ? b.toHtmlElement() : warehouse.getRandomBook().toHtmlElement());

		pw.append(Helper.getCopyright());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private Cookie manageCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie c : cookies)
				if (c.getName().equals(COOKIE_NAME_RECOMMENDATION))
					if (c.getValue() != null && !c.getValue().isEmpty())
						return c;
		Cookie c = new Cookie(COOKIE_NAME_RECOMMENDATION, String.valueOf(warehouse.getRandomBook().getIsbn()));
		c.setMaxAge(-1); // Cookie wird mit Schließen des Browsers vernascht
		response.addCookie(c);
		return c;
	}
}
