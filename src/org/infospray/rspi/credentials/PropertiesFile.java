package org.infospray.rspi.credentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

	private static Properties wsProperties;


	private PropertiesFile() {
	}

	public static Properties getWsCredentials() {
		if (null == wsProperties) {
			wsProperties = new Properties();
			try {
				FileInputStream file =  new  FileInputStream("rspi.properties");
				wsProperties.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return wsProperties;
	}
	
	

}