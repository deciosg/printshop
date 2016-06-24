package cc.printshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "items")
@NamedQueries({
	@NamedQuery(name = "Item.countByRole", query = "SELECT count(i) FROM Item i WHERE status = ?1")
})
public class Item {
	@Id @GeneratedValue
	@Column(name = "id_item")
	private Long id;

	@Column(nullable = false)
	@NotNull
	private Integer quantity;

	@Column(nullable = false)
	@NotNull
	private Double price;

	@Column(nullable = false, length = 160)
	@Size(min = 6, max = 160)
	private String name;

	@Column(length = 200)
	private String description;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusItem status;
	
	@Column
	private String statusReason;
	
	@ManyToOne
	private Employee design;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusItem getStatus() {
		return status;
	}

	public void setStatus(StatusItem status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public Employee getDesign() {
		return design;
	}

	public void setDesign(Employee design) {
		this.design = design;
	}

	public Double getTotalPrice() {
		return this.quantity * this.price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", quantity=" + quantity + ", price=" + price + ", name=" + name + ", description="
				+ description + ", status=" + status + ", statusReason=" + statusReason + ", design=" + design + "]";
	}

}
