package mmu.android.app.vehicles;

import java.io.Serializable;

/**
 * Vehicle class creates vehicle objects
 * @author Qasim Awan
 * @version 1.0
 */
public class Vehicle implements Serializable {
	private int vehicle_id;
	private String make;
	private String model;
	private int year;
	private int price;
	private String license_number;
	private String colour;
	private int number_doors;
	private String transmission;
	private int mileage;
	private String fuel_type;
	private int engine_size;
	private String body_style;
	private String condition;
	private String notes;
	private boolean sold;
	
	/**
	 * 
	 * @param vehicle_id, vehicle unique identifier
	 * @param make, make of the vehicle e.g. Audi, Mercedes, Honda etc.
	 * @param model, model of the vehicle e.g. A4, E class, Civic etc.
	 * @param year, year of the vehicle
	 * @param price, price of the vehicle
	 * @param license_number, registration number of the vehicle e.g. BB 16 ABN
	 * @param colour, colour of the vehicle
	 * @param number_doors, number of doors of the vehicle
	 * @param transmission, transmission type of the vehicle e.g. Manual, Automatic
	 * @param mileage, mileage of the vehicle
	 * @param fuel_type, fuel type of the vehicle e.g. petrol, diesel, electric etc.
	 * @param engine_size, engine size of the vehicle
	 * @param body_style, body style of the vehicle
	 * @param condition, condition of the vehicle
	 * @param notes, notes of the vehicle
	 * @param sold, sold status of the vehicle
	 */
	public Vehicle(int vehicle_id, String make, String model, int year, int price, String license_number, String colour, int number_doors,
			String transmission, int mileage, String fuel_type, int engine_size, String body_style, String condition, String notes, boolean sold){
		this.vehicle_id = vehicle_id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
		this.license_number = license_number;
		this.colour = colour;
		this.number_doors = number_doors;
		this.transmission = transmission;
		this.mileage = mileage;
		this.fuel_type = fuel_type;
		this.engine_size = engine_size;
		this.body_style = body_style;
		this.condition = condition;
		this.notes = notes;
		this.sold = sold;
	}

	/**
	 * gets the id of the vehicle object and returns
	 * @return vehicle_id, returns the vehicle_id that identifies the vehicle uniquely
	 */
	public int getVehicle_id() {
		return vehicle_id;
	}

	/**
	 * sets the vehicle id to the parameter received
	 * @param vehicle_id, value to set the vehicle_id
	 */
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	/**
	 * gets the make of the vehicle object and returns
	 * @return make, returns the make of the vehicle object
	 */
	public String getMake() {
		return make;
	}

	/**
	 * sets the make of the vehicle object to the parameter received
	 * @param make, value to set the make of the vehicle
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * gets the model of the vehicle object and returns
	 * @return model, returns the model of the vehicle object
	 */
	public String getModel() {
		return model;
	}

	/**
	 * sets the model of the vehicle object to the parameter received
	 * @param model, value to set the model of the vehicle
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * gets the year of the vehicle object and returns
	 * @return year, returns the year of the vehicle object
	 */
	public int getYear() {
		return year;
	}

	/**
	 * sets the year of the vehicle object to the parameter received
	 * @param year, value to set the year of the vehicle object
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * gets the price of the vehicle object and returns
	 * @return price, returns the price of the vehicle object
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * sets the price of the vehicle object to the parameter received
	 * @param price, value to set the price of the vehicle object
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * gets the license number of the vehicle object and returns
	 * @return license number, returns the license number of the vehicle object
	 */
	public String getLicense_number() {
		return license_number;
	}

	/**
	 * sets the license number of the vehicle object to the parameter received
	 * @param license_number, value to set the license number of the vehicle object
	 */
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	/**
	 * gets the colour of the vehicle object and returns
	 * @return colour, returns the colour of the vehicle object
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * sets the colour of the vehicle object to the parameter received
	 * @param colour, value to set the colour of the vehicle object
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * gets the numbers of doors of the vehicle object and returns
	 * @return number_doors, returns number of doors of the vehicle object
	 */
	public int getNumber_doors() {
		return number_doors;
	}

	/**
	 * sets the number of doors of the vehicle object to the parameter received
	 * @param number_doors, value to set number of doors of the vehicle object
	 */
	public void setNumber_doors(int number_doors) {
		this.number_doors = number_doors;
	}

	/**
	 * gets the transmission type of the vehicle object and returns
	 * @return transmission, returns the transmission type of the vehicle object
	 */
	public String getTransmission() {
		return transmission;
	}

	/**
	 * sets the transmission of the vehicle object to the parameter received
	 * @param transmission, value to set the transmission of the vehicle object
	 */
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	/**
	 * gets the mileage of the vehicle object and returns
	 * @return mileage, returns the mileage of the vehicle object
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * sets the mileage of the vehicle object to the parameter received
	 * @param mileage, value to set the mileage of the vehicle object
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	/**
	 * gets the fuel type of the vehicle and returns
	 * @return fuel_type, returns the mileage of the vehicle object
	 */
	public String getFuel_type() {
		return fuel_type;
	}

	/**
	 * sets the fuel type of the vehicle object to the parameter received
	 * @param fuel_type, value to set the fuel type of the vehicle object
	 */
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	/**
	 * gets the engine size of the vehicle object and returns
	 * @return engine_size, returns the engine size of the vehicle object
	 */
	public int getEngine_size() {
		return engine_size;
	}

	/**
	 * sets the engine size of the vehicle object to the parameter received
	 * @param engine_size, value to set the engine size of the vehicle object
	 */
	public void setEngine_size(int engine_size) {
		this.engine_size = engine_size;
	}

	/**
	 * gets the body style of the vehicle object and returns
	 * @return body_style. returns the body style of the vehicle object
	 */
	public String getBody_style() {
		return body_style;
	}

	/**
	 * sets the body style of the vehicle object to the parameter received
	 * @param body_style, value to set the body style of the vehicle object
	 */
	public void setBody_style(String body_style) {
		this.body_style = body_style;
	}

	/**
	 * gets the condition of the vehicle objects and returns
	 * @return condition, returns the condition of the vehicle object
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * sets the condition of the vehicle object to the parameter received
	 * @param condition,  value to set the condition of the vehicle object
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * gets the notes of the vehicle object and returns
	 * @return notes, returns the notes of the vehicle object
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * sets the notes of the vehicle object to the parameter received
	 * @param notes, value to set the notes of the vehicle object
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * gets the boolean sold value of the vehicle object and returns
	 * @return sold, returns the boolean value of sold of the vehicle object
	 */
	public boolean isSold() {
		return sold;
	}

	/**
	 * sets the boolean sold value of the vehicle object to the parameter received
	 * @param sold, value to set the boolean sold value of the vehicle object
	 */
	public void setSold(boolean sold) {
		this.sold = sold;
	}

	@Override
	public String toString() {
		return "Vehicle Id = " + vehicle_id + "\n"
				+ "Make = " + make + "\n"
				+ "Model = " + model + "\n"
				+ "Year = " + year + "\n" 
				+ "price = " + price + "\n"
				+ "License Number = " + license_number + "\n"
				+ "Colour = " + colour + "\n"
				+ "Number Doors = " + number_doors + "\n"
				+ "Transmission = " + transmission + "\n"
				+ "Mileage = " + mileage + "\n"
				+ "Fuel Type = " + fuel_type + "\n"
				+ "Engine Size = " + engine_size + "\n"
				+ "Body Style = " + body_style + "\n"
				+ "Condition = " + condition + "\n"
				+ "Notes = " + notes + "\n"
				+ "Sold = " + sold;
	}
	
}
