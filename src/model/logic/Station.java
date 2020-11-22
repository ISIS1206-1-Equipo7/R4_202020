package model.logic;

public class Station {

	// ---------
	// Atributos
	// ---------
	
	/**
	 * Tiempo en el que el usuario estuvo en la estacion
	 */
	private String time;
	
	/**
	 * id de la estacion
	 */
	private String id;
	
	/**
	 * Nombre de la estacion
	 */
	private String name;
	
	/**
	 * Latitud de la estacion
	 */
	private String latitude;
	
	/**
	 * Longitud de la estacion
	 */
	private String longitude;
	
	/**
	 * Constructor
	 */
	public Station(String time, String id, String name, String latitude, String longitude) {
		this.time = time;
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Retorna el tiempo en el que el usuario estuvo en la estacion
	 */
	public String getTime() {
		return this.time;
	}
	
	/**
	 * Retorna el id de la estacion
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * Retorna el nombre de la estacion
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Retorna la latitud de la estacion
	 */
	public String getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Retorna la longitud de la estacion
	 */
	public String getLongitude() {
		return this.longitude;
	}
}
