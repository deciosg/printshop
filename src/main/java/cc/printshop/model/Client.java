package cc.printshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "clients")
@NamedQueries({
	@NamedQuery(name = "Client.findByCpf", query = "SELECT c FROM Client c WHERE c.cpf = ?1")
})
public class Client extends Person {
	@Column(length = 160, nullable = false, unique = true)
	@Email @Size(min = 6, max = 160)
	private String email;

	@Column(length = 14, nullable = false)
	private String landline;

	@Column(length = 15, nullable = false)
	private String cellular;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getCellular() {
		return cellular;
	}

	public void setCellular(String cellular) {
		this.cellular = cellular;
	}

	@Override
	public String toString() {
		return "Client [email=" + email + ", landline=" + landline + ", cellular=" + cellular + "]";
	}

}
