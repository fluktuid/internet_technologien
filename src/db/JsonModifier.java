package db;

import shared.Book;
import shared.Customer;
import shared.Order;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.AbstractDocument.Content;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * Der JsonModifier konvertiert in json und aus json
 * 
 * @author fluktuid
 *
 */
public class JsonModifier {
	static Set<Book> toBooks(String s) {
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Collection<Book>>() {
		}.getType();
		Collection<Book> coll = gson.fromJson(s, collectionType);
		TreeSet<Book> set = new TreeSet<>(new Warehouse.BookComparator());
		if (coll != null)
			set.addAll(coll);
		return set;
	}

	static <T> String toJson(Collection<T> collection) {
		return new Gson().toJson(collection);
	}
	
	static <T> String toJson(T t) {
		return new Gson().toJson(t);
	}

	static <T>Collection<Order> toOrder(String json) {
		ArrayList<Order> orders = new ArrayList<>();
		Type type = new TypeToken<T>() {
		}.getType();
		System.out.println("preSplit");
		for(String str : json.split("SPLIT")) {
			System.out.println("pastSplit");
			System.out.println(str);
			orders.add(new Gson().fromJson(str, type));
		}
		System.out.println("finished");
		return orders;
	}

	public static Book toBook(String s) {
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Book>() {
		}.getType();
		return gson.fromJson(s, collectionType);
	}

	public static String toJson(Book book) {
		String s = new Gson().toJson(book);
		System.out.println(s);
		return s;
	}
}
