package cc.printshop.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	@Column(length = 160, nullable = false)
	@NotNull @Max(value = 160)
	private String street;

	@Column
	private Integer number;

	@Column(length = 60)
	@Max(value = 60)
	private String complement;

	@Column(length = 80, nullable = false)
	@NotNull @Max(value = 80)
	private String borough;

	@Column(length = 80, nullable = false)
	@NotNull @Max(value = 80)
	private String city;

	@Column(length = 2, nullable = false)
	@Size(min = 2, max = 2)
	private String state;

	@Column(length = 9)
	private String postalCode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getBorough() {
		return borough;
	}

	public void setBorough(String borough) {
		this.borough = borough;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", number=" + number + ", complement=" + complement + ", borough="
				+ borough + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + "]";
	}

}
