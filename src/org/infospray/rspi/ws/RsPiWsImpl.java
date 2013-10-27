package org.infospray.rspi.ws;


import javax.jws.WebService;
import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;



@WebService(endpointInterface = "org.infospray.rspi.ws.RsPiWs",serviceName = "RsPiWs")
public class RsPiWsImpl implements RsPiWs {
	
	static Logger logger = Logger.getLogger(RsPiWsImpl.class);
	
	private GpioController gpio =  null;

	// puissance
	private GpioPinDigitalOutput pin5 = null;
	private GpioPinDigitalOutput pin4 = null;
	
	// direction
	private GpioPinDigitalOutput pin0 = null;
	private GpioPinDigitalOutput pin2 = null;
	
	private State state;
	
	public RsPiWsImpl() {
		// create gpio controller
		this.gpio = GpioFactory.getInstance();

		this.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.HIGH);
		
		//pour la puissance
		this.pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);
		this.pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,PinState.LOW);
		
		// pour la direction
		this.pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.LOW);
		this.pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,PinState.LOW);

		com.pi4j.wiringpi.Gpio.wiringPiSetup();
		// setup PWM for Motor A
		// le pin #1 pour pi4j est en definitif le pin 18 visiblement le seul a pouvoir faire du pwm.
		SoftPwm.softPwmCreate(RaspiPin.GPIO_01.getAddress(), 0, 100);

		
		this.state = new State();
	}

	@Override
	public boolean avancer() {
		logger.info("AVANCER");
		return Gpio.executeAvancer(this.gpio, this.pin5, this.pin4, this.state);
	}

	@Override
	public boolean reculer() {
		logger.info("RECULER");
		return Gpio.executeReculer(this.gpio, this.pin5, this.pin4, this.state);
	}

	@Override
	public boolean tournerGauche(long angle) {
		logger.info("TOURNER_GAUCHE");
		return Gpio.executeGauche(this.pin2,this.state);
	}

	@Override
	public boolean tournerDroite(long angle) {
		logger.info("TOURNER_DROIT");
		return Gpio.executeDroite(this.pin0, this.state);
	}

	@Override
	public boolean alignerRoueMilieu() {
		logger.info("ALIGNER_ROUES_MILIEU");
		return Gpio.executeAlignerRoue(this.pin2,this.pin0,this.state);
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
		return Gpio.executeStop(this.gpio, this.pin5, this.pin4, this.state);
	}
	

}
