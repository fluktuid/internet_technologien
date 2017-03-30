package shared;

import com.google.gson.annotations.SerializedName;

/**
 * @author fluktuid
 *
 */
public class Customer {
	@SerializedName("n")
	private String name;
	@SerializedName("pr")
	private String prename;
	@SerializedName("s")
	private String street;
	@SerializedName("a")
	private String adressAdd;
	@SerializedName("o")
	private String plz;
	@SerializedName("ct")
	private String city;
	@SerializedName("co")
	private String country;
	@SerializedName("pm")
	private String paymentMethod;

	public Customer(String name, String prename, String street, String adressAdd, String plz, String city,
			String country, String paymentMethod) {
		this.name = name;
		this.prename = prename;
		this.street = street;
		this.adressAdd = adressAdd;
		this.plz = plz;
		this.city = city;
		this.country = country;
		this.paymentMethod = paymentMethod;
	}

	public Customer(String name, String prename, String street, String plz, String city, String country) {
		this.name = name;
		this.prename = prename;
		this.street = street;
		this.plz = plz;
		this.city = city;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public String getPreName() {
		return prename;
	}

	public String getStreet() {
		return street;
	}

	public String getAdressAdd() {
		return adressAdd;
	}

	public String getPlz() {
		return plz;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
}
