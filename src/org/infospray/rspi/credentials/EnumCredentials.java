package org.infospray.rspi.credentials;

public enum EnumCredentials {

	

	WS_URL("ws.url");

	
	private final String libelle;

	public String getLibelle() {
		return libelle;
	}

	private EnumCredentials(String libelle) {
		this.libelle = libelle;
	}

}
