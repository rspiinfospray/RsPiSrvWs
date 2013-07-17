package org.infospray.rspi.ws;

import org.apache.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

public class Gpio {


	private Gpio() {
		// TODO Auto-generated constructor stub
	};
	
	
	static Logger logger = Logger.getLogger(Gpio.class);
	
	private static int DEFAULT_PUISSANCE = 50;

	public static boolean executeAvancer(GpioController gpio, GpioPinDigitalOutput pin5, GpioPinDigitalOutput pin4, State state){

		boolean result = false;


		try {

			// provision gpio pin #05 vers le input 1 de L293D
			pin5.low();

			// permet d'eviter un accou trop brusque lorsque le moteur recul puis avance.
			// temporise l effort
			if(state.isReculer()){
				Thread.sleep(1000);
			}
			
			// provision gpio pin #04 vers le input 2 de L293D
			pin4.high();

			// cette conbinaison de du input 1 et input 2 fait tourner le moteur dans le sens des aiguilles d' une montre

			result = true;
			state.setAvancer(true);
			state.setReculer(false);
			
			// vers le enable 1 de L293D
			SoftPwm.softPwmWrite(RaspiPin.GPIO_01.getAddress(),DEFAULT_PUISSANCE);
			

		} catch (Throwable  e) {
			result =  false;
		} 

		logger.info("executeAvancer => " + String.valueOf(result));
		return result;
	}

	public static boolean executeReculer(GpioController gpio, GpioPinDigitalOutput pin5, GpioPinDigitalOutput pin4, State state){

		boolean result = false;

		try {

			// provision gpio pin #05 vers le input 1 de L293D
			pin5.high();
			
			// permet d'eviter un accou trop brusque lorsque le moteur avance puis recule.
			// temporise l effort
			if(state.isAvancer()){
				Thread.sleep(1000);
			}

			// provision gpio pin #04 vers le input 2 de L293D
			pin4.low();

			// cette conbinaison de du input 1 et input 2 fait tourner le moteur dans le sens  inversedes aiguilles d' une montre

			result = true;
			state.setAvancer(false);
			state.setReculer(true);
			
			// vers le enable 1 de L293D
			SoftPwm.softPwmWrite(RaspiPin.GPIO_01.getAddress(),DEFAULT_PUISSANCE);

		} catch (Throwable  e) {
			result =  false;
		} 

		logger.info("executeReculer => " + String.valueOf(result));
		return result;
	}


	public static boolean executePuissance(long puissance){

		boolean result = false;

		try {

			SoftPwm.softPwmWrite(RaspiPin.GPIO_01.getAddress(),Long.valueOf(puissance).intValue());

			result = true;

		} catch (Throwable  e) {
			result =  false;
		} 


		logger.info("executePuissance => " + String.valueOf(result));
		return result;
	}

	public static boolean executeStop(GpioController gpio,GpioPinDigitalOutput pin5, GpioPinDigitalOutput pin4, State state){

		boolean result = false;

		try {


			pin5.low();

			pin4.low();

			// configure the pin shutdown behavior; these settings will be 
			// automatically applied to the pin when the application is terminated
			pin5.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);

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
