package cc.printshop.model;

public enum StatusItem {
	REQUESTED ("Solicitado"),
	IN_ANALYSIS ("Em analise"),
	REJECTED ("Refugado"),
	CANCELED ("Cancelado"),
	APPROVED ("Aprovado"),
	IN_PRODUCTION ("Em producao"),
	AVAILABLE_FOR_REMOVED ("Disponivel para retirada"),
	REMOVED ("Retirado"),
	BLOCKED ("Bloqueado") ;
	
	private String description;

	private StatusItem(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
