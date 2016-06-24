package cc.printshop.model;

public enum StatusPayment {
	PENDING ("Pendente"),
	RECEIVED ("Recebido"),
	REVERSAL ("Estorno");
	
	private String description;
	
	private StatusPayment(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
