package org.infospray.rspi.ws;

public class State {

	private boolean avancer;
	
	private boolean reculer;
	
	private boolean stop;
	
	private boolean droite;
	
	private boolean gauche;

	public boolean isAvancer() {
		return avancer;
	}

	public void setAvancer(boolean avancer) {
		this.avancer = avancer;
	}

	public boolean isReculer() {
		return reculer;
	}

	public void setReculer(boolean reculer) {
		this.reculer = reculer;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isDroite() {
		return droite;
	}

	public void setDroite(boolean droite) {
		this.droite = droite;
	}

	public boolean isGauche() {
		return gauche;
	}

	public void setGauche(boolean gauche) {
		this.gauche = gauche;
	}
	
	
	
	
}
