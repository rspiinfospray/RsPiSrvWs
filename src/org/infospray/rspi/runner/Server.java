package org.infospray.rspi.runner;


import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.infospray.rspi.credentials.Credentials;
import org.infospray.rspi.ws.RsPiWsImpl;




public class Server {

	static Logger logger = Logger.getLogger(Server.class);

	public Server() throws Exception {

		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Starting Server");
		
		RsPiWsImpl rspiWs = new RsPiWsImpl();
		String address =  Credentials.getWebServiceUrl();
		logger.info(address);
		Endpoint.publish(address, rspiWs);


	}

}
