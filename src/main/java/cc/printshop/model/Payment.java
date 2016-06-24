package cc.printshop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue
	@Column(name = "id_payment")
	private Long id;

	@Column
	private Double value;

	@Column
	private Date paidIn;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusPayment status;

	public Payment() {
		super();
	}

	public Payment(Double value, StatusPayment status) {
		super();
		this.value = value;
		this.status = status;
	}

	public Payment(Double value, Date paidIn, StatusPayment status) {
		super();
		this.value = value;
		this.paidIn = paidIn;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getPaidIn() {
		return paidIn;
	}

	public void setPaidIn(Date paidIn) {
		this.paidIn = paidIn;
	}

	public StatusPayment getStatus() {
		return status;
	}

	public void setStatus(StatusPayment status) {
		this.status = status;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", value=" + value + ", paidIn=" + paidIn + ", status=" + status + "]";
	}

}
