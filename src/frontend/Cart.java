package frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.Book;
import shared.IValueCookies;

@WebServlet("/cart")
public class Cart extends HttpServlet implements IValueCookies {
	private static final long serialVersionUID = 1L;
	db.Warehouse warehouse;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookie = manageCookie(request, response);
		warehouse = new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF"));

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.append(Helper.getHeader());
		pw.append(Helper.getQuote(getServletContext()));
		pw.append(Helper.getMenu(warehouse.getCategories()));
		if (cookie != null) {
			shared.Cart cart = getBooksFromCookie(cookie);
			Set<Book> books = cart.getBooks();
			if (books.size()>0) {
				for (Book b : books)
					pw.append(b.toHtmlElement()).append("<font size=\"3\">" + cart.getCount(b.getIsbn()) + "</font>");
				double gesPrice = books.stream().mapToDouble(e -> e.getPreis()).sum();
				pw.append("<font size=\"5\">").append(String.valueOf(gesPrice)).append("<i class=\"fa fa-btc\"></i>")
						.append("</font>");
				pw.append(Helper.getPayButton());
			} else {
				pw.append(Helper.noBookInCart());
			}
		}
		pw.append("</div>");
		pw.append(Helper.getCopyright());
	}

	/**
	 * Genertiert aus den im cookie gespeicherten ISBNs ein Set der jeweiligen
	 * Bücher
	 * 
	 * @param cookie
	 * @return das set der Books
	 */
	private shared.Cart getBooksFromCookie(Cookie cookie) {
		shared.Cart cart = new shared.Cart();
		String value = cookie.getValue();
		if(value==null || value.isEmpty())
			return cart;
		String[] bookIsbnStrings = value.split("\n");
		for (String s : bookIsbnStrings) {
			String[] varString = s.split(";");
			cart.addBook(warehouse.getBookByISBN(Double.valueOf(varString[0])), Integer.valueOf(varString[1]));
		}
		return cart;
	}

	private Cookie manageCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie c : cookies)
				if (c.getName().equals(COOKIE_NAME_CART))
					if (c.getValue() != null && !c.getValue().isEmpty())
						return c;
		Cookie c = new Cookie(COOKIE_NAME_CART, null);
		c.setMaxAge(-1); // Cookie wird mit Schließen des Browsers vernascht
		response.addCookie(c);
		return c;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
