package frontend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.Book;

@WebServlet("/detail")
public class Detail extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String isbn_string = request.getParameter("isbn");
		
		//Handling der leeren Category (allgemein) wird vom Warehouse erledigt
		
		PrintWriter pw = response.getWriter();
		pw.append(Helper.getHeader());
		pw.append(Helper.getQuote(getServletContext()));
		pw.append(historyBack());
		try {
			int isbn = Integer.valueOf(isbn_string);
	
		pw.append(new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF"))
				.getBookByISBN(isbn).toHtmlElement());
		} catch (Exception ignore) {	}
		pw.append(Helper.getCopyright());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static String historyBack() {
		return "<button onclick=\"goBack()\">Go Back</button><script>function goBack() {window.history.back();}</script>";
	}
}
