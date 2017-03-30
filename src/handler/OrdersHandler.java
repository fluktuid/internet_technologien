package handler;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import db.OrderDB;
import shared.Order;

@ManagedBean(name = "ordersHandler")
@RequestScoped
public class OrdersHandler {

	/**
	 * gibt eine Liste aller Bestellungen zurück
	 * 
	 * @return Liste aller Bestellungen
	 */
	public ArrayList<Order> getOrders() {
		for (Order o : OrderDB.getOrders()) {
			if(o== null) {
				System.out.println("null");
			} else {
				System.out.println(o.getName()+o.getSum());
			}
		}
		return new ArrayList<>(OrderDB.getOrders());
	}

	/**
	 * gibt die Gesamtkosten einer Bestellung zurück
	 * 
	 * @param order,
	 *            welche verwendet werden soll
	 * @return Gesamtkosten
	 */
	public double getSum(Order order) {
		return order == null ? 0 : order.getSum();
	}
}
