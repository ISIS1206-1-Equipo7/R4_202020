package model.logic;

import java.time.LocalDate;
import java.time.Period;

public class User {

	// ---------
	// Atributos
	// ---------
	
	/**
	 * ID de la bicicleta/ usuario
	 */
	private String bikeID;
	
	/**
	 * Tipo de usuario
	 */
	private String userType;
	
	/**
	 * Anno de nacimiento del usuario
	 */
	private String birthYear;
	
	/**
	 * Genero del usuario
	 */
	private String gender;
	
	/**
	 * Constructor
	 */
	public User(String bikeID, String userType, String birthYear, String gender) {
		this.bikeID = bikeID;
		this.userType = userType;
		this.userType = birthYear;
		this.gender = gender;
	}
	
	/**
	 * Retorna la edad actual del usuario
	 */
	public int getCurrentAge() {
		return (2020 - Integer.parseInt(getBirthYear()));
	}
	
	/**
	 * Retorna el ID de la bicicleta/ usuario
	 */
	public String getBikeID() {
		return this.bikeID;
	}
	
	/**
	 * Retorna el tipo de usuario
	 */
	public String getUserType() {
		return this.userType;
	}
	
	/**
	 * Retorna el anno de nacimiento del usuario
	 */
	public String getBirthYear() {
		return this.birthYear;
	}
	
	/**
	 * Retorna el genero del usuario
	 */
	public String getGender() {
		return this.gender;
	}
}
