package shared;

import com.google.gson.Gson;
/**
 * Diese Klasse stellt ein Buch dar.
 * @author fluktuid
 *
 */
public class Book implements java.io.Serializable,Comparable{
	private static final long serialVersionUID = 2707016439797722453L;
	private double isbn;
	private String name;
	private String author;
	private String description;
	private String category;
	private double preis;
	private String imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51uwrSEjroL._AC_UL320_SR200,320_.jpg";
	
	
	public String getUrl() {
		return imageUrl;
	}

	public Book(double isbn,String name,String author,String description,String category, double preis,String imageUrl) {
		this(isbn,name,author,description,category,preis);
		this.imageUrl = imageUrl;
	}

	public Book(double isbn,String name,String author,String description,String category, double preis) {
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.description = description;
		this.category = category;
		this.preis = preis;
	}
	
	public Book() {
		//needed for jsf
	}

	public double getIsbn() {
		return isbn;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public double getPreis() {
		return preis;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String toHtmlElement() {
		StringBuilder builder = new StringBuilder();
		builder.append("<div align=\"left\">")
		.append("<table>").append("<tr>");
		//TODO: implement picture
//		if(picture!=null)
			builder.append("<td>")
			.append("<img src=\"https://images-na.ssl-images-amazon.com/images/I/51uwrSEjroL._AC_UL320_SR200,320_.jpg\" alt=\"Cover ").append(name)
			.append("\">").append("</td>");
		builder.append("<td>")
		.append("<p>").append(name).append("</p>")
		.append("<p>").append(description).append("</p>")
		.append("<p>").append(isbn).append("</p>")
		.append("<p><a href=\"detail?isbn=").append(isbn).append("\"><button class=\"large green\"> Details</button></a></p>")
		.append("</td>").append("</tr>").append("</table>").append("</div>")
		.append("<hr class=\"alt2\"/>").append("</div>");
		System.out.println("ISBN="+isbn);
		return builder.toString();
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public static class Builder {
		private int isbn;
		private String name;
		private String author;
		private String description;
		private String category;
		private double preis;
		private String url;
		
		public Builder() {
			
		}
		
		public Builder setIsbn(int isbn) {
			this.isbn = isbn;
			return this;
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setAuthor(String author) {
			this.author = author;
			return this;
		}
		
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public Builder setCategory(String category) {
			this.category = category;
			return this;
		}
		
		public Builder setPreis(double preis) {
			this.preis = preis;
			return this;
		}
		
		public Builder setImageUrl(String url) {
			this.url = url;
			return this;
		}
		public Book build() throws IllegalArgumentException{
			if(name==null|| author==null || description == null || category == null)
				throw new IllegalArgumentException("Nicht alle nötigen Argumente gesetzt");
			return new Book(isbn,name,author,description,category,preis,url);
		}
	}

	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Book))
			return -1;
		return Double.compare(isbn, ((Book)o).getIsbn());
	}
}
