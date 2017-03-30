package frontend;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import shared.Book;
import shared.Category;
import db.Quote;

/**
 * Diese Klasse hilft den Html Code zu generieren.
 * @author fluktuid
 *
 */
public class Helper {
	
	public static String getNamespace() {
		return "<html xmlns=\"http://www.w3.org/1999/xhtml\" "
				+ "xmlns:f=\"http://java.sun.com/jsf/core\" "
				+ "xmlns:h=\"http://java.sun.com/jsf/html\">";
	}
	
	public static String getHeader() {
		return "<h:header>"
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>"
				+ "<script src=\"html_kickstart/js/kickstart.js\"></script>"
				+ "<link rel=\"stylesheet\" href=\"html_kickstart/css/kickstart.css\" media=\"all\" /> <!-- KICKSTART -->"
				+ "</h:header>";
	}
	public static String getQuote(ServletContext context) {
		try {
			Quote q = new Quote(context);
			StringBuilder builder = new StringBuilder();
			builder.append("<h:div align=\"left\" style=\"width: 65%\"> <blockquote class=\"small\" >")
				.append("<p>")
				.append(q.getText())
				.append('(').append(q.getWhom()).append(')')
				.append("</p>")
				.append("</blockquote></h:div>");
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return getQuoteOld();
		}
	}
	
	public static String getQuoteOld() {
		return "<!-- Blockquote Small --><div align=\"left\" style=\"width: 65%\"> <blockquote class=\"small\" >"
				+ "<p>"
				+ "Stöbern Sie hier und heute nach neuen Büchern. Ob Kinderbücher, Sachbücher oder Krimis; Hier kommt jeder auf seine \"Kosten\". "
				+ "Apropos Kosten: wir überzeugen nicht nur mit einem breiten Protfolio an Büchern sondern auch mit unseren Preisen! "
				+ "Günstig wie nie - gut wie nie.<span>Shop Nr. im deutschsprachigen Raum... (Harald Martenstein; April 2017)</span>"
				+ "</p>"
				+ "</blockquote></div>";
	}
	
	/**
	 * gibt ein Menü zurück, welches alle verwendeten Kategorien enthält
	 * @param categories die zu verwendenden Kategorien
	 * @return den HTML Code als string
	 */
	public static String getMenu(Set<Category> categories) {
		StringBuilder builder = new StringBuilder();
		builder.append("<h:div align=\"center\"><ul class=\"menu\">")
				.append("<li class=\"current\"><a href=\"category.jsp\"><i class=\"fa fa-book\"></i> Katalog</a>")
				.append("<ul>");
		for(Category c : categories)
			builder.append("<li><a href=\"category.jsp?category="+c.getCategoryId()+"\"> "+c.getCategoryName()+"</a></li>");
		
		builder.append("</ul></li>")
				.append("<li><a href=\"random\"<i class=\"fa fa-random\"></i> Zuf&auml;llig</a>"
				+ "</li><li><a href=\"recommendation\"><i class=\"fa fa-area-chart\"></i> Empfehlungen</a></li>"
				+ "<li><a href=\"cart\"><i class=\"fa fa-shopping-cart\"></i> Warenkorb</a></li>"
				+ "<li><a href=\"pay\"><i class=\"fa fa-btc\"></i> zur Kasse</a></li></ul></h:div>");
		return builder.toString();
	}
	
	/**
	 * @deprecated use getMenu(Set<Category> categories)
	 * @return
	 */
	static String getMenu() {
		return "<!-- Menu Horizontal --><div align=\"center\"><ul class=\"menu\">"
				+ "<li class=\"current\"><a href=\"category\"><i class=\"fa fa-book\"></i> Katalog</a>"
				+ "<ul><li><a href=\"category?category=comic_&_manga\"> Comic &amp; Manga</a></li>"
				+ "<li><a href=\"category?category=fachbuecher\" name=\"sub\" value=\"nosub\"> Fachb&uuml;cher</a></li>"
				+ "<li><a href=\"category?category=fantasy\" name=\"sub\" value=\"nosub\"> Fantasy</a></li>"
				+ "<li><a href=\"category?category=science_fiction\" name=\"sub\" value=\"nosub\"> Science Fiction</a></li>"
				+ "<li><a href=\"category?category=freizeit\"> Freizeit</a></li>"
				+ "<li><a href=\"category?category=fremdsprachige_buecher\"> Fremdsprachige B&uuml;cher</a></li>"
				+ "<li><a href=\"category?category=jugendbuecher\"> Jugendb&uuml;cher</a></li>"
				+ "<li><a href=\"category?category=kinderbuecher\"> Kinderb&uuml;cher</a></li>"
				+ "<li><a href=\"category?category=kochen_u_backen\"> Kochen &amp; Backen</a></li>"
				+ "<li><a href=\"category?category=krimis\"> Krimis</a></li>"
				+ "<li><a href=\"category?category=thriller\"> Thriller</a></li>"
				+ "<li><a href=\"category?category=ratgeber\"> Ratgeber</a></li>"
				+ "<li><a href=\"category?category=reise_u_abenteuer\"> Reise &amp; Abenteuer</a></li>"
				+ "<li><a href=\"category?category=romane\"> Romane</a></li>"
				+ "<li><a href=\"category?category=erzaehlungen\"> Erz&auml;hlungen</a></li>"
				+ "<li><a href=\"category?category=sachbuecher\"> Sachb&uuml;cher</a></li>"
				+ "<li><a href=\"category?category=schule_u_lernen\"> Schule &amp; Lernen</a></li></ul></li>"
				+ "<li><a href=\"random\"<i class=\"fa fa-random\"></i> Zuf&auml;llig</a>"
				+ "</li><li><a href=\"recommendation\"><i class=\"fa fa-area-chart\"></i> Empfehlungen</a></li>"
				+ "<li><a href=\"cart\"><i class=\"fa fa-shopping-cart\"></i> Warenkorb</a></li>"
				+ "<li><a href=\"pay\"><i class=\"fa fa-btc\"></i> zur Kasse</a></li></ul></div>";
	}
	
	public static String noBookInCategory() {
		return "<p> In dieser Kategorie gibt es zur Zeit noch keine Bücher.</p>";	//TODO: FontAwesome Emoticon hinzufügen :D
	}
	public static String noBookInCart() {
		return "<p> In Ihrem Einkaufswagen gibt es zur Zeit noch keine Bücher.</p>";	//TODO: FontAwesome Emoticon hinzufügen :D
	}
	
	public static String getCopyright() {
		return "<h:div>&copy; Okko Veenhuis, Tobias Boge, Lukas f. Paluch"
				+"<p><a href=\"warehouse\"><button class=\"small blue\"> warehouse</button></a></p></h:div>";
	}
	
	static String getPayButton() {
		return "<p><a href=\"pay\">"+getButton("zur Kasse","type","value")+"</a></p>";
	}
	
	static String getButton(String text, String type,String value) {
		text = (text==null)? "Button":text;
		type = (type==null)? "Button":type;
		value = (value==null)? "Button":value;
		return "<button type="+type+" value="+value+">"+text+"</button>";
	}
	
	static String getStickyFooter(String content,String backgroundColor) {
		if(backgroundColor==null || backgroundColor.isEmpty())
			backgroundColor="white";
		return "<h:div id=\"footer\" style=\"position:fixed;bottom:0px;width:1200px;z-index:99;display:table;margin: 0 auto;background-color:"+backgroundColor+";\">"+content+"</h:div>";
	}
}
