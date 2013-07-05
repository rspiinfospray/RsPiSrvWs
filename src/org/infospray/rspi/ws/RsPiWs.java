package org.infospray.rspi.ws;

import javax.jws.WebService;

@WebService
public interface RsPiWs {
	
	public boolean	avancer();
	
	public boolean	reculer();
	
	public boolean	puissance(long ms);
	
	public boolean	stop();
	
	public boolean	tounerGauche(long angle);
	
	public boolean	tournerDroite(long angle);
	
	public boolean	alignerRoueMilieu();
	
	public boolean	piloteAutoOn();
	
	public boolean	piloteAutoOff();

}
