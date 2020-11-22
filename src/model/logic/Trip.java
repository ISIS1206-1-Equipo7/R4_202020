package model.logic;

public class Trip {
	
	// ---------
	// ATRIBUTOS
	// ---------
	
	/**
	 * Estacion inicial
	 */
	private Station iStation;
	
	/**
	 * Estacion final
	 */
	private Station fStation;
	
	/**
	 * Duracion del viaje
	 */
	private String tripDuration;
	
	/**
	 * Usuario
	 */
	private User user;
	
	/**
	 * Constructor
	 */
	public Trip(Station iStation, Station fStation, String tripDuration, User user) {
		this.iStation = iStation;
		this.fStation = fStation;
		this.tripDuration = tripDuration;
		this.user = user;
	}
	
	/**
	 * Retorna la estacion inicial
	 */
	public Station getInitialStation() {
		return this.iStation;
	}
	
	/**
	 * Retorna la estacion final
	 */
	public Station getFinalStation() {
		return this.fStation;
	}
	
	/**
	 * Retorna la duracion del viaje
	 */
	public String getTripDuration() {
		return this.tripDuration;
	}
	
	/**
	 * Retorna el id de la bicicleta/ usuario
	 */
	public User getUser() {
		return this.user;
	}
}
