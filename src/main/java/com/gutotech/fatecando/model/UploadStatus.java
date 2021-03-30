package com.gutotech.fatecando.model;

public enum UploadStatus {
	WAITING_FOR_RESPONSE("Aguardando Resposta"),
	EDITABLE("Editável"), 
	EDITED("Editado"), 
	APPROVED("Aprovado"),
	DISAPPROVED("Reprovado");

	private final String displayName;

	private UploadStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
