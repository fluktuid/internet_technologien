package handler;

import java.io.IOException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import db.OrderDB;
import shared.Customer;
import shared.Order;

@ManagedBean(name = "pay2Handler")
@RequestScoped
public class Pay2Handler {
	@ManagedProperty(value = "#{payHandler.currentCustomer}")
	private Customer customer;
	
	@ManagedProperty(value="#{cartHandler}")
	private CartHandler cartHandler;
	
	public void setCartHandler(CartHandler cartHandler) {
		this.cartHandler = cartHandler;
	}

	public String getName() {
		return customer.getName();
	}

	public String getPrename() {
		return customer.getPreName();
	}

	public String getCity() {
		return customer.getCity();
	}

	public String getPaymentMethod() {
		return customer.getPaymentMethod();
	}

	public String getStreet() {
		return customer.getStreet();
	}

	public String getAdressAdd() {
		return customer.getAdressAdd();
	}

	public String getPlz() {
		return customer.getPlz();
	}

	public String getCountry() {
		return customer.getCountry();
	}

	/**
	 * setzt den Kunden
	 * @param customer der Kunde
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * platziert die Bestellung und speichert sie
	 */
	public String checkout() {
		System.out.println("checked out");
		OrderDB.addOrder(new Order(customer,cartHandler.getMapBooks(),new Date()));
		cartHandler.clean();
		return "/pay3.jsf";
	}

	//TODO: anders lösen
	public String back() {
		System.out.println("back");
		return "/pay1.jsf";
	}


	/**
	 * gibt den CheckoutText abhängig von der gewählten Zahlungsmethode zurück
	 * @return CheckoutText
	 */
	public String getCheckoutText() {
		switch (customer.getPaymentMethod()) {
		case "Mastercard":
		case "Visa":
			return "Im Warten auf den Kontoauszug";
		case "Bitcoin":
			return "In Erwartung auf einen volleren Wallet";
		case "Vorkasse per Post":
		default:
			return "In Erwartung auf Ihren Brief";
		case "Rechnung":
			return "Nicht ernsthaft? Wir sollen zuerst handeln? Sie sind sicher schon voller Erwartung auf den Gerichtsvollzieher.";
		}
	}

}
