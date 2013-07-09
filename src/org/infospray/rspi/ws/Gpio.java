package org.infospray.rspi.ws;

import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

public class Gpio {


	private Gpio() {
		// TODO Auto-generated constructor stub
	};
	
	
	static Logger logger = Logger.getLogger(Gpio.class);

	public static boolean executeAvancer(GpioController gpio, GpioPinDigitalOutput pin1, GpioPinDigitalOutput pin4){

		boolean result = false;


		try {


			// provision gpio pin #01 vers le input 1 de L293D
			pin1.low();

			// provision gpio pin #04 vers le input 2 de L293D
			pin4.high();

			// cette conbinaison de du input 1 et input 2 fait tourner le moteur dans le sens des aiguilles d' une montre

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} 

		logger.info("executeAvancer => " + String.valueOf(result));
		return result;
	}

	public static boolean executeReculer(GpioController gpio, GpioPinDigitalOutput pin1, GpioPinDigitalOutput pin4){

		boolean result = false;

		try {

			// provision gpio pin #01 vers le input 1 de L293D
			pin1.high();

			// provision gpio pin #04 vers le input 2 de L293D
			pin4.low();

			// cette conbinaison de du input 1 et input 2 fait tourner le moteur dans le sens  inversedes aiguilles d' une montre

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} 

		logger.info("executeReculer => " + String.valueOf(result));
		return result;
	}


	public static boolean executePuissance(GpioController gpio, long puissance, GpioPinDigitalOutput pin1){

		boolean result = false;

		try {

			// le pulse devrait moduler la vitesse du moteur
			pin1.pulse(puissance, true); // set second argument to 'true' use a blocking call

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} 

		
		logger.info("executePuissance => " + String.valueOf(result));
		return result;
	}

	public static boolean executeStop(GpioController gpio,GpioPinDigitalOutput pin1, GpioPinDigitalOutput pin4){

		boolean result = false;

		try {


			pin1.low();

			pin4.low();

			// configure the pin shutdown behavior; these settings will be 
			// automatically applied to the pin when the application is terminated
			pin1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);

			// configure the pin shutdown behavior; these settings will be 
			// automatically applied to the pin when the application is terminated
			pin4.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);

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

		logger.info("executeStop => " + String.valueOf(result));
		return result;
	}



}
