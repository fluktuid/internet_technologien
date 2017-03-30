<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="frontend.Helper" import="shared.Book"
	import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//de" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Amazon Light</title>
</head>
<body>
	<%=Helper.getNamespace()%>
	<%=Helper.getHeader()%>
	<%=Helper.getQuote(this.getServletContext())%>
	<%=Helper.getMenu(new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF")).getCategories())%>
	<%=getBooksHtml(request.getParameter("category"))%>
	<%=Helper.getCopyright()%>

	<%!private String getBooksHtml(String category) {
		Set<Book> books = new db.Warehouse(this.getServletContext().getRealPath("/WEB-INF")).getBooks(category);
		StringBuilder builder = new StringBuilder();
		if (books != null && books.size() > 0)
			for (Book b : books)
				builder.append(b.toHtmlElement());
		else if (books.size() == 0)
			return Helper.noBookInCategory();
		return builder.toString();
	}%>
</body>
</html>