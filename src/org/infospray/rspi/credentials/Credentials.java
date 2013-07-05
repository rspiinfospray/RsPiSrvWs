package org.infospray.rspi.credentials;



public   class Credentials {
	
	
	private Credentials() {
		super();
	}




	public static String getWebServiceUrl() {
		return PropertiesFile.getWsCredentials().getProperty(EnumCredentials.WS_URL.getLibelle());
	}


	
	
}
