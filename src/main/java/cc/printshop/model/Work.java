package cc.printshop.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "works")
@NamedQueries({
	@NamedQuery(name = "Work.total", query = "SELECT count(w) FROM Work w"),
	@NamedQuery(name = "Work.showByClient", query = "SELECT w FROM Work w WHERE client = ?1"),
	@NamedQuery(name = "Work.requestedDateDesc", query = "SELECT w FROM Work w ORDER BY w.requestedAt DESC")
})
public class Work {
	@Id
	@GeneratedValue
	@Column(name = "id_work")
	private Long id;

	@ManyToOne
	private Client client;

	@ManyToOne
	private Employee attendant;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_work")
	private List<Item> items;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_work")
	private List<Payment> payments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date requestedAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date retiredAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getAttendant() {
		return attendant;
	}

	public void setAttendant(Employee attendant) {
		this.attendant = attendant;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Date getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}

	public Date getRetiredAt() {
		return retiredAt;
	}

	public void setRetiredAt(Date retiredAt) {
		this.retiredAt = retiredAt;
	}

	public Long getCountItems() {
		return this.items.stream().count();
	}

	public Double getSumItems() {
		return this.items.stream().mapToDouble(items -> items.getTotalPrice()).sum();
	}

	@PrePersist
	void createdAt() {
		this.requestedAt = new Date();
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
		Work other = (Work) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Work [id=" + id + ", client=" + client + ", attendant=" + attendant + ", items=" + items + ", payments="
				+ payments + ", requestedAt=" + requestedAt + ", retiredAt=" + retiredAt + "]";
	}

}
