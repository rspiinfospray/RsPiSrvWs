package org.infospray.rspi.ws;


import javax.jws.WebService;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "org.infospray.rspi.ws.RsPiWs",serviceName = "RsPiWs")
public class RsPiWsImpl implements RsPiWs {
	
	static Logger logger = Logger.getLogger(RsPiWsImpl.class);

	@Override
	public boolean avancer() {
		logger.info("AVANCER");
		return Gpio.executeAvancer();
	}

	@Override
	public boolean reculer() {
		logger.info("RECULER");
		return Gpio.executeReculer();
	}

	@Override
	public boolean tournerGauche(long angle) {
		logger.info("TOURNER_GAUCHE : D = "  + String.valueOf(angle));
		return false;
	}

	@Override
	public boolean tournerDroite(long angle) {
		logger.info("TOURNER_DROITE : D = " + String.valueOf(angle));
		return false;
	}

	@Override
	public boolean alignerRoueMilieu() {
		logger.info("ALIGNER_ROUES_MILIEU");
		return false;
	}

	@Override
	public boolean piloteAutoOn() {
		logger.info("PILOTE_AUTO_ON");
		return false;
	}

	@Override
	public boolean piloteAutoOff() {
		logger.info("PILOTE_AUTO_OFF");
		return false;
	}

	@Override
	public boolean puissance(long puissance) {
		logger.info("PUISSANCE : P = "  + String.valueOf(puissance));
		return Gpio.executePuissance(puissance);
	}

	@Override
	public boolean stop() {
		logger.info("STOP");
		return Gpio.executeStop();
	}
	

}
