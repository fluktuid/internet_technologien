package db;

import java.util.ArrayList;
import java.util.Collection;

import de.imut.ec.keyvaluestore.KeyValueStore;
import shared.Customer;
import shared.Order;

public abstract class OrderDB {
	/**
	 * Key: YHx03FkasBxSLlcIVa6ejsINz0zRZ0H6I5LJvUhW
	 */
	private static final String KEY = "YHx03FkasBxSLlcIVa6ejsINz0zRZ0H6I5LJvUhW";
	private static KeyValueStore kvs = new KeyValueStore();

	public static void addOrder(Order order) {
		String values = kvs.get(KEY);

		values = values+","+JsonModifier.toJson(order);
		kvs.put(KEY, values);
	}

	public static Collection<Order> getOrders() {
		String values = kvs.get(KEY);
		System.out.println(values);
		return JsonModifier.toOrder(values);
	}
}
