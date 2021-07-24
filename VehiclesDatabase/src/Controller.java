/**
 * Controller class for VehicleDAO, Vehicle, Sales
 * The controller class implements all the methods in vehicleDAO class
 * @author Qasim Awan
 * @version 1.0
 */
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Sales;
import models.Vehicle;
import models.VehicleDAO;
public class Controller {
	
	// variables used to store the vehicle data
	static int vehicle_id = -999;
	static String make;
	static String model;
	static int year;
	static int price;
	static String license_number;
	static String colour;
	static int number_doors;
	static String transmission;
	static int mileage;
	static String fuel_type;
	static int engine_size;
	static String body_style;
	static String condition;
	static String notes;
	static String message;
	
	public static void main(String[] args) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		
		// on object of VehicleDAO declared to access the methods in VehicleDAO
		VehicleDAO vehicles = new VehicleDAO();
		
		// option stores the value entered by the user
		int option = 0;
		
		// variable declared to store a single instance of the object vehicle
		Vehicle tempVehicle;
		
		// variable declared to store a singe object of object Sales
		Sales sold_vehicle;
		
		// ArrayList for Vehicle declared to store vehicle object
		ArrayList<Vehicle> col1 = new ArrayList<Vehicle>();
		
		// ArraList for Sales declared to store Sales objects
		ArrayList<Sales> sales = new ArrayList<Sales>();
		
		// creating scanner object to read data input from the user
		Scanner input = new Scanner(System.in);
		
		// run the loop until the user exits
		do 
		{
			// display menu on the console
			System.out.println("------------------------\n"
					+ "Vehicle Inventory System\n"
					+ "Choose From these options\n"
					+ "------------------------\n"
					+ "1 - Retrieve all vehicles\n"
					+ "2 - Search for vehicle\n"
					+ "3 - Insert new vehicle into database\n"
					+ "4 - Update existing vehicle database\n"
					+ "5 - Delete vehicle from Database\n"
					+ "6 - Retrieve all sales\n"
					+ "7 - Retrieve sold vehicle\n"
					+ "8 - Exit");
			
			// ask user to enter the choice
			message = "Enter choice (1 - 8) > ";
			
			// store use option into variable option
			option = getIntegerInput(input, message, 1, 8);
			
			// move to next line
			System.out.println();
			
			// check the option and run the corresponding case
			switch(option)
			{
				// retrieve all vehicles
				case 1: 
					
					// retrieve vehicles calling getAllVehicles() method from Vehicle DAO class and store it into the variable col1
					col1 = vehicles.getAllVehicles();
					
					// loop through index to print all vehicles
					for(Vehicle v: col1)
					{
						System.out.println("\n------------------------------");
						
						// print the vehicle details on the console
						System.out.println(v);
					}
					break;
					
				// retrieve one vehicle using vehicle_id
				case 2: 
					System.out.println("\n------------------------------");
					System.out.println("Search for a vehicle..");
					
					// ask the user to enter the vehicle_ID
					message = "Enter vehicle ID to search for the vehicle: ";
					
					// run the get IntegerInput method to get the integer value from the user and store it into vehicle_id variable
					vehicle_id = getIntegerInput(input, message, 0, 999999999);
					
					// run the getVehicle method in vehicleDAO and store into tempVehicle variable
					tempVehicle = vehicles.getVehicle(vehicle_id);
					
					// check if a vehicle is returned
					if(tempVehicle != null)
					{
						// vehicle information is returned, print on the console
						System.out.println(tempVehicle);
					}
					else
					{
						// no vehicle was returned, display a message to the user
						System.out.println("The vehicle does not exist.");
					}
					break;
				
				// add a new vehicle into the database
				case 3:
					System.out.println("\n------------------------------");
					
					// display a message to the user to enter new vehicle details
					System.out.println("Please enter new vehicle details..");
					
					// run getVehicleData() method to get vehicle information from the user and store into the variable tempVehicle
					tempVehicle = getVehicleData(input);
					
					// run the insertVehicle method, pass the vehicle object to insert into the database
					vehicles.insertVehicle(tempVehicle);
					break;
				
				// update the details of the vehicle currently stored in the database
				case 4:
					System.out.println("\n------------------------------");
					
					// diplay a message to the user
					System.out.println("Update vehicle details..");
					
					// store the message in the variable to pass as a parameter
					message = "Enter vehicle ID to update details: ";
					
					// run the method to get an integer input from the user
					vehicle_id = getIntegerInput(input, message, 0, 999999999);
					
					// run the getVehicle() method to retrieve the vehicle
					tempVehicle = vehicles.getVehicle(vehicle_id);
					
					// check if the vehicle is retrieved
					if(tempVehicle != null)
					{
						// vehicle is retrieved, get the updated information from the user
						tempVehicle = getUpdatedVehicleData(input, tempVehicle);
						
						// run the update method to update the row in the database
						boolean done = vehicles.updateVehicle(tempVehicle, vehicle_id);
						
						// check if the row was updated successfully
						if(done)
						{
							// print the message on the console
							System.out.println("Vehicle is Updated");
						}
						else
						{
							// print the message on the console
							System.out.println("Registration Number is not valid.");
						}
					}
					else
					{
						// print the message on the console, no vehicle with such vehicle_id exists
						System.out.println("No vehicle with id " + vehicle_id + " exists.");
					}
					// reset vehicle_id
					vehicle_id = -999;
					break;
				
				// delete a record from the database
				case 5:
					System.out.println("\n------------------------------");
					
					// store the message in the variable
					message = "Please enter the vehicle id: ";
					
					// get the vehicle_id from the user
					int vehicleId = getIntegerInput(input, message, 0, 999999999);
					
					// run the query from the vehiclesDAO class
					boolean deleted = vehicles.deleteVehicle(vehicleId);
					
					// check if the vehicle is deleted using the returned boolean value
					if(deleted)
					{
						// print the message on the console
						System.out.println("Vehicles with ID = " + vehicleId + " has been deleted");
					}
					else
					{
						// print the message on the console
						System.out.println("No vehicle with ID " + vehicleId + " exists");
					}
					break;
				
				// get all the sales records from the database
				case 6:
					// call the getAllSales() method from the vehicleDAO class
					sales = vehicles.getAllSales();
					
					// loop through index to print all vehicles sales
					for(Sales s: sales)
					{
						System.out.println("\n------------------------------");
						
						// print vehicle sale record on the console
						System.out.println(s);
					}
					break;
				
				// search for vehicle sales details from the database
				case 7:
					System.out.println("\n------------------------------");
					
					// display a message
					System.out.println("Search for a sale..");
					
					// store the message into a variable
					message = "Enter vehicle ID to search for the sale: ";
					
					// get the vehicle_id from the user
					vehicle_id = getIntegerInput(input, message, 0, 999999999);
					
					// get the vehicle sale record from the database
					sold_vehicle = vehicles.getVehicleSale(vehicle_id);
					
					// check if the vehicle sale record is retrieved
					if(sold_vehicle != null)
					{
						// print the sale record on the console
						System.out.println(sold_vehicle);
					}
					else
					{
						// display a message to the user
						System.out.println("No such sale exists.");
					}
					break;
			}
			
		}
		// run the do loop until the user exits selecting 8 option
		while(option != 8);
		
		// display a message to the user
		System.out.println("Program Finished!!");
		
		// close the input object of the scanner class
		input.close();
	}
	
	/**
	 * Method to get the vehicle information from the user
	 * @param kb, input object of the scanner class
	 * @return vehicle object is returned
	 */
	static Vehicle getVehicleData(Scanner kb)
	{
		// display the message on the console
		System.out.print("Enter vehicle make: ");
		// get the input from the user
		make = kb.next();
		
		// display the message on the console
		System.out.print("Enter vehicle model: ");
		// get the input from the user
		model = kb.next();
		
		// display the message on the console
		message = "Enter vehicle year (1950 - 2019): ";
		// get the vehicle year from the user
		year = getIntegerInput(kb, message, 1950, 2019);
		
		// display the message on the console
		message = "Enter vehicle price: ";
		// get the vehicle price from the user
		price = getIntegerInput(kb, message, 0, 999999999);
		
		// display the message on the console
		System.out.print("Enter vehicle license number: ");
		// get the input from the user
		license_number = kb.next();
		
		// display the message on the console
		System.out.print("Enter vehicle colour: ");
		// get the input from the user
		colour = kb.next();
		
		// display the message on the console
		message = "Enter number of doors: ";
		// get the number of doors of the vehicle from the user
		number_doors = getIntegerInput(kb, message, 1, 7);
		
		// display the message on the console
		System.out.print("Enter transmission type(manual/automatic): ");
		// get the input from the user
		transmission = kb.next();
		
		// display the message on the console
		message = "Enter mileage: ";
		// get the mileage of the vehicle from the user
		mileage = getIntegerInput(kb, message, 0, 999999999);
		
		// display the message on the console
		System.out.print("Enter fuel type(petrol/diesel/hybrid/electric): ");
		// get the input from the user
		fuel_type = kb.next();
		
		// display the message on the console
		message = "Enter engine size (cc): ";
		// get the engine size of the vehicle from the user
		engine_size = getIntegerInput(kb, message, 200, 999999999);
		
		// display the message on the console
		System.out.print("Enter body style (hatchback/estate/SUV/MVP Coupe): ");
		// get the input from the user
		body_style = kb.next();
		
		// display the message on the console
		System.out.print("Enter condition: ");
		// get the input from the user
		condition = kb.next();
		
		// display the message on the console
		System.out.print("Enter notes (special features such as satnav): ");
		// get the input from the user
		notes = kb.next();
		
		// set the sold status of the vehicle to false
		boolean sold = false;
		
		// stores the vehicle object into the declared vehicle variable
		Vehicle vehicle = new Vehicle(vehicle_id ,make, model, year ,price, license_number, colour, number_doors,
				transmission, mileage, fuel_type, engine_size, body_style, condition, notes, sold);
		
		// returns the vehicle object
		return vehicle;
	}
	
	/**
	 * 
	 * @param kb, input object of the scanner class
	 * @param vehicle object is passed as a parameter
	 * @return vehicle object is returned
	 */
	static Vehicle getUpdatedVehicleData(Scanner kb, Vehicle tempVehicle)
	{
		// declare a variable
		int choice;
		
		// run the loop until the user exits
		do 
		{
			// display the menu to the user
			System.out.println("\n\n------------------------\n"
					+ "Choose the field you would like to change\n"
					+ "1 - Vehicle make: " + tempVehicle.getMake() + "\n"
					+ "2 - Vehicle model: " + tempVehicle.getModel() + "\n"
					+ "3 - Vehicle year: " + tempVehicle.getYear() + "\n"
					+ "4 - Vehicle price: " + tempVehicle.getPrice() + "\n"
					+ "5 - Vehicle license number: " + tempVehicle.getLicense_number() + "\n"
					+ "6 - Vehicle colour: " + tempVehicle.getColour() + "\n"
					+ "7 - Vehicle number of doors: " + tempVehicle.getNumber_doors() + "\n"
					+ "8 - Vehicle transmission: " + tempVehicle.getTransmission() + "\n"
					+ "9 - Vehicle mileage: " + tempVehicle.getMileage() + "\n"
					+ "10 - Vehicle fuel type: " + tempVehicle.getFuel_type() + "\n"
					+ "11 - Vehicle engine size: " + tempVehicle.getEngine_size() + "\n"
					+ "12 - Vehicle body style: " + tempVehicle.getBody_style() + "\n"
					+ "13 - Vehicle condition: " + tempVehicle.getCondition() + "\n"
					+ "14 - Vehicle notes: " + tempVehicle.getNotes() + "\n"
					+ "15 - Save");
			
			// store the message to the user
			message ="Enter choice (1 - 15) > ";
			
			// get the menu option chosen by the user
			choice = getIntegerInput(kb, message, 0, 15);
			
			// check the option chosen by the user
			switch(choice)
			{
				// get the make of the vehicle from the user
				case 1:
					System.out.print("Enter vehicle make: ");
					// set the make of the vehicle to the value entered by the user
					tempVehicle.setMake(kb.next());
					break;
				
				// get the model from the user
				case 2:
					System.out.print("Enter vehicle model: ");
					// set the model of the vehicle to the value entered by the user
					tempVehicle.setModel(kb.next());
					break;
				
				// get the year from the user
				case 3:
					message = "Enter vehicle year (1950 - 2019): ";
					// set the year of the vehicle to the value enterd by the user
					tempVehicle.setYear(getIntegerInput(kb, message, 1950, 2019));
					break;
					
				// get the price from the user
				case 4:
					message = "Enter vehicle price: ";
					// set the vehicle price to the value entered by the user
					tempVehicle.setPrice(getIntegerInput(kb, message, 0, 999999999));
					break;
				
				// get the license number from the user
				case 5:
					System.out.print("Enter vehicle license number: ");
					// set the lisence number to the value entered by the user
					tempVehicle.setLicense_number(kb.next());
					break;
				
				// get the colour from the user
				case 6:
					System.out.print("Enter vehicle colour: ");
					// set the colour to the value entered by the user
					tempVehicle.setColour(kb.next());
					break;
				
				// get the number of doors from the user
				case 7:
					message = "Enter number of doors: ";
					// set the number of doors of the vehicle to the value entered by the user
					tempVehicle.setNumber_doors(getIntegerInput(kb, message, 1, 7));
					break;
				
				// get the transmission type from the user
				case 8:
					System.out.print("Enter transmission type(manual/automatic): ");
					// set the transmission type to the value entered by the user
					tempVehicle.setTransmission(kb.next());
					break;
				
				// get the mileage of the vehicle from the user
				case 9:
					message = "Enter mileage: ";
					// set the mileage of the vehicle to the value entered by the user
					tempVehicle.setMileage(getIntegerInput(kb, message, 0, 999999999));
					break;
					
				case 10:
					System.out.print("Enter fuel type(petrol/diesel/hybrid/electric): ");
					tempVehicle.setFuel_type(kb.next());
					break;
				
				// get the engine size of the vehicle from the user
				case 11:
					message = "Enter engine size (cc): ";
					// set the engine size of the vehicle to the value entered by the user
					tempVehicle.setEngine_size(getIntegerInput(kb, message, 200, 999999999));
					break;
				
				// get the body style of the vehicle from the user
				case 12:
					System.out.print("Enter body style (hatchback/estate/SUV/MVP Coupe): ");
					// set the body style of the vehicle to the value entered by the user
					tempVehicle.setBody_style(kb.next());
					break;
				
				// get the status of the condition of the vehicle from the user
				case 13:
					System.out.print("Enter condition: ");
					// set the condition status of the vehicle to the value entered by the user
					tempVehicle.setCondition(kb.next());
					break;
				
				// get the notes of the vehicle from the user
				case 14:
					System.out.print("Enter notes (special features such as satnav): ");
					// set the notes of the vehicle to the value entered by the value
					tempVehicle.setNotes(kb.next());
					break;
			}
		}
		// run the do loop until the user exits by selecting the value 15
		while(choice != 15);
		
		// store all the data received from the do loop into the vehicle instance
		tempVehicle = new Vehicle(tempVehicle.getVehicle_id() , tempVehicle.getMake(), tempVehicle.getModel(), tempVehicle.getYear() ,tempVehicle.getPrice(), 
				tempVehicle.getLicense_number(), tempVehicle.getColour(), tempVehicle.getNumber_doors(), tempVehicle.getTransmission(), tempVehicle.getMileage(), 
				tempVehicle.getFuel_type(), tempVehicle.getEngine_size(), tempVehicle.getBody_style(), tempVehicle.getCondition(), tempVehicle.getNotes(), tempVehicle.isSold());
		
		// return the vehicle object
		return tempVehicle;
	}
	
	/**
	 * 
	 * @param kb, input object of the scanner class
	 * @param message, message passed to display to the user on the console
	 * @param min, the minimum value the user can input
	 * @param max, the maximum value the user can input
	 * @return value, the number value entered by the user
	 */
	static int getIntegerInput(Scanner kb,String message, int min, int max)
	{
		// declare the variable to store the input from the user
		int value;
		
		// run the loop until the user enters a valid number
		do 
		{
			// display the message to the user
			System.out.print(message);
			
			// check if the value entered is a number
			while(!kb.hasNextInt())
			{
				// display a message to the user
				System.out.print("The input is invalid! Please enter a number: ");
				kb.next();
			}
			// value entered is an integer, store it into the variable value
			value = kb.nextInt();
		}
		// run the do loop until the user enters a valid number, a number between max and min
		while(value < min || value > max);
		
		// return the value entered by the user.
		return value;
	}
	
}
