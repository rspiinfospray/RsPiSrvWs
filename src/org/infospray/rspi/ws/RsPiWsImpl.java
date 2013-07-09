package org.infospray.rspi.ws;


import javax.jws.WebService;
import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;



@WebService(endpointInterface = "org.infospray.rspi.ws.RsPiWs",serviceName = "RsPiWs")
public class RsPiWsImpl implements RsPiWs {
	
	static Logger logger = Logger.getLogger(RsPiWsImpl.class);
	
	private GpioController gpio =  null;
	private GpioPinDigitalOutput pin1 = null;
	private GpioPinDigitalOutput pin4 = null;
	
	public RsPiWsImpl() {
		// create gpio controller
		this.gpio = GpioFactory.getInstance();
		this.pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);
		this.pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);		
	}

	@Override
	public boolean avancer() {
		logger.info("AVANCER");
		return Gpio.executeAvancer(this.gpio, this.pin1, this.pin4);
	}

	@Override
	public boolean reculer() {
		logger.info("RECULER");
		return Gpio.executeReculer(this.gpio, this.pin1, this.pin4);
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
		return Gpio.executePuissance(this.gpio,puissance, this.pin1);
	}

	@Override
	public boolean stop() {
		logger.info("STOP");
		return Gpio.executeStop(this.gpio, this.pin1, this.pin4);
	}
	

}
