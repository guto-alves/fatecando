package com.gutotech.fatecando.model;

public enum UploadStatus {
	WAITING_FOR_RESPONSE("Aguardando Resposta"), 
	APPROVED("Aprovado"),
	DISAPPROVED("Reprovado"),
	EDITABLE("Editável");

	private final String displayName;

	private UploadStatus(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

}
