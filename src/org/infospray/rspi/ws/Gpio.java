package org.infospray.rspi.ws;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Gpio {


	private Gpio() {
		// TODO Auto-generated constructor stub
	};

	public static boolean executeAvancer(){

		boolean result = false;

		GpioController gpio =  null;

		try {

			// create gpio controller
			gpio = GpioFactory.getInstance();

			// provision gpio pin #01 vers le input 1 de L293D
			gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);

			// provision gpio pin #00 vers le input 2 de L293D
			gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.HIGH);

			// cette conbinaison de du input 1 et input 2 fait tourner le moteur dans le sens des aiguilles d' une montre

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} 

		return result;
	}
	
	public static boolean executeReculer(){

		boolean result = false;

		GpioController gpio =  null;

		try {

			// create gpio controller
			gpio = GpioFactory.getInstance();

			// provision gpio pin #01 vers le input 1 de L293D
			gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.HIGH);

			// provision gpio pin #00 vers le input 2 de L293D
			gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.LOW);

			// cette conbinaison de du input 1 et input 2 fait tourner le moteur dans le sens  inversedes aiguilles d' une montre

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} 

		return result;
	}
	
	
	public static boolean executePuissance(long puissance){

		boolean result = false;

		GpioController gpio =  null;

		try {

			// create gpio controller
			gpio = GpioFactory.getInstance();

			// provision gpio pin #02 vers le enable1 de L293D permet de faire du PWM du pulse.
			final GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.HIGH);

			// le pulse devrait moduler la vitesse du moteur
			pin02.pulse(puissance, true); // set second argument to 'true' use a blocking call

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} 

		return result;
	}
	
	public static boolean executeStop(){

		boolean result = false;

		GpioController gpio =  null;

		try {

			// create gpio controller
			gpio = GpioFactory.getInstance();

			// stop all GPIO activity/threads by shutting down the GPIO controller
			// (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
			gpio.shutdown();

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} finally{
			if(gpio != null){
				gpio.shutdown();
			}
		}

		return result;
	}



}
