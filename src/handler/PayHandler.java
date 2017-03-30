package handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import shared.Book;
import shared.Customer;

@ManagedBean(name = "payHandler")
@SessionScoped
public class PayHandler {
	
	/**
	 * der aktuelle Kunde
	 */
	private Customer currentCustomer;
	
	/**
	 * die gewählte Zahlungsmethode
	 */
	public String payMethod;

	/**
	 * Die Map enthält alle PaymentMethoden und das zugehörige Kürzel.
	 */
	private static Map<String, Object> payMethods = new TreeMap<String, Object>();
	
	/**
	 * füllt die TreeMap {@link handler.PayHandler.PayMethods}
	 */
	static {
		payMethods.put("Mastercard", "mc");
		payMethods.put("Visa", "v");
		payMethods.put("Bitcoin", "btc");
		payMethods.put("Vorkasse per Post", "vk");
		payMethods.put("Rechnung", "r");
	}

	/**
	 * Referenz auf @see {@link #cartHandler}
	 */
	@ManagedProperty(value = "#{cartHandler}")
	private CartHandler cartHandler;

	/**
	 * Setter für {@link PayHandler#cartHandler}
	 * 
	 * @param cartHandler
	 */
	public void setCartHandler(CartHandler cartHandler) {
		this.cartHandler = cartHandler;
	}

	/**
	 * gibt alle Bücher im Cart zurück
	 * 
	 * @return alle Bücher im Cart
	 */
	public ArrayList<Book> getBooks() {
		Collection<Book> books = cartHandler.getBooks();
		return new ArrayList<>(books);
	}

	/**
	 * Speichert die Kundendaten in {@link PayHandler#payMethod}
	 * 
	 * @param name
	 *            Nachname
	 * @param prename
	 *            Vorname
	 * @param street
	 *            Straße
	 * @param adressAdd
	 *            Adresszusatz
	 * @param plz
	 *            Postleitzahl
	 * @param city
	 *            Stadt
	 * @param country
	 *            Land
	 */
	public String checkPay(String name, String prename, String street, String adressAdd, String plz, String city,
			String country) {
		System.out.println(name + prename + street + adressAdd + plz + city + country);
		currentCustomer = new Customer(name, prename, street, adressAdd, plz, city, country,
				payMethods.entrySet().stream().filter(e -> Objects.equals(e.getValue(), payMethod))
						.map(Map.Entry::getKey).collect(Collectors.toList()).get(0));
		// (drüber) fügt den key anhand des Value ein
		return "/pay2.jsf";
	}

	/**
	 * Gibt alle Zahlungsmethoden zurück
	 * 
	 * @return eine Map aus <NameZahlungsweise,Kürzel>
	 */
	public Map<String, Object> getPayMethodsValue() {
		return payMethods;
	}

	/**
	 * gibt die gewählte Zahlungsmethode zurück
	 * 
	 * @return gewählte Zahlungsmethode
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * setzt die gewählte Zahlungsmethode
	 * 
	 * @param payMethod
	 *            gewählte Zahlungsmethode
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * gibt den aktuellen Kunden aus
	 * 
	 * @return der aktuelle Kunde
	 */
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

}
