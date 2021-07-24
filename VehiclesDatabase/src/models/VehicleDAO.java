/**
 * VehicleDAO class, used to access the database to get data
 * @author Qasim Awan
 * @version 1.0
 */

package models;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleDAO {
	/**
	 * method used to create a connection to the database
	 * @return connection, returns a variable connection that stored the connection to the database
	 */
	public static Connection getDBConnection()
	{
		// declare a connection variable
		Connection connection = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			// stores the directory of the database
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
			
			// creates a connection to the database and stores it into connection variable
			connection = DriverManager.getConnection(dbURL);
			
			// returns the connection
			return connection;
		}
		catch(SQLException e)
		{
			// prints the error message
			System.out.println(e.getMessage());
		}
		// returns the connection
		return connection;
	}
	
	
	/**
	 * method used to get all the vehicle records from the database
	 * @return vehicle (ArrayList), returns ArrayList of vehicles which consists of all vehicle objects
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public ArrayList<Vehicle> getAllVehicles() throws SQLException
	{
		System.out.println("Retrieving all vehicles...");
		
		// declare
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// store the query in a string variable
		String query = "SELECT * FROM vehicles;";
		
		// declare an ArrayList of vehicles
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		
		// try the code
		try
		{
			// get the connection
			dbConnection = getDBConnection();
			
			// prepare the statement to run
			preparedStatement = dbConnection.prepareStatement(query);
			
			// print out the query on console
			System.out.println("DBQuery = " + query);
			
			//execute SQL QUERY
			result = preparedStatement.executeQuery();
			
			// loop to add vehicle objects in the ArrayList until the last index number
			while(result.next())
			{
				// store the data of the vehicle objects into the variables
				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("Notes");
				boolean sold = result.getBoolean("sold");
				
				// add the vehicle into the ArrayList vehicles
				vehicles.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style,
						condition, notes, sold));
			}
		}
		// run if there is an SQL error
		catch(SQLException e)
		{
			// print the error
			System.out.println(e.getMessage());
		}
		finally
		{
			// check if there is any result stored
			if(result != null)
			{
				// end the result and delete all the contents
				result.close();
			}
			// check if the prepared statement still exists
			if(preparedStatement != null)
			{
				// delete all the contents of the prepared statement by ending
				preparedStatement.close();
			}
			// check if the connection still exists
			if(dbConnection != null)
			{
				// delete the connection by ending
				dbConnection.close();
			}
		}
		// return the ArrayList vehicles
		return vehicles;
	}
	
	/**
	 * method used to check if the API Key used to make a request matches in the database
	 * @param apiKey, the apiKey sent by the user to make the request, used to compare with the api keys in the database
	 * @return boolean, it returns a boolean value of true or false (if the api key matches true if not false)
	 * @throws SQLException
	 */
	public boolean checkAPIKey(String apiKey) throws SQLException
	{
		// declare
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// store the query
		String selectApiKey = "SELECT api_key FROM users WHERE api_key LIKE ? ;";
		
		// declare the boolean value
		boolean ok = false;
		try
		{
			// get a connection
			dbConnection = getDBConnection();
						
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(selectApiKey);
			
			// set the string for the first value in the prepared statement
			preparedStatement.setString(1, apiKey);
			
			System.out.println("DBQuery: " + selectApiKey);
			
			// execute the query
			result = preparedStatement.executeQuery();
			
			// loop to add vehicle objects in the ArrayList until the last index number
			while(result.next())
			{
				// store the api_key retrieved from the database
				String retrievedApiKey = result.getString("api_key");
				
				// chheck if the api key matches
				if(retrievedApiKey.equals(apiKey))
				{
					// set boolean value to true
					ok = true;
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(result != null)
			{
				result.close();
			}
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// return the value
		return ok;
	}
	
	/**
	 * method used to search the vehicle between maximum and minimum price range
	 * @param min, the minimum price to search the vehicles from
	 * @param max, the maximum price to search the vehicles from
	 * @return vehicles (ArrayList), returns an arrayList of all vehicles
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public ArrayList<Vehicle> searchByPriceRange(int min, int max) throws SQLException
	{
		System.out.println("Search vehicle be price_range");
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// stores the query into the variable
		String query = "SELECT * FROM vehicles WHERE price >= ? AND price <= ?;";
		
		// prints the query on the console
		System.out.println(query);
		
		// declare an ArrayList of objects vehicles
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		
		
		try 
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setInt(1 , min);
			preparedStatement.setInt(2, max);
			
			// print the query on the console
			System.out.println("DBQuery: " + query);
			
			//execute SQL Query
			result = preparedStatement.executeQuery();
			
			// loop to add vehicle objects in the ArrayList until the last index number
			while(result.next())
			{
				// store the data of the vehicle objects into the variables
				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("Notes");
				boolean sold = result.getBoolean("sold");
				
				// add the vehicle into the ArrayList vehicles
				vehicles.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style,
						condition, notes, sold));
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(result != null)
			{
				result.close();
			}
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// return the ArrayList vehicles
		return vehicles;
	}
	
	/**
	 * method to search vehicles on the criteria of make and model
	 * @param criteria, the value of the criteria to search the vehicles from the database e.g. Mercedes
	 * @return vehicles (ArrayList), returns an arrayList of all vehicles
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public ArrayList<Vehicle> searchVehicles(String criteria) throws SQLException
	{
		System.out.println("Searching vehicles by make/model");
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// store the query to run on the database
		String query = "SELECT * FROM vehicles WHERE Make LIKE ? OR Model LIKE ? ;";
		
		// declare an ArrayList of the vehicles
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		try 
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setString(1 , criteria);
			preparedStatement.setString(2, criteria);
			
			// print out the query on the console
			System.out.println("DBQuery: " + query);
			
			//execute SQL Query
			result = preparedStatement.executeQuery();
			
			// loop to add vehicle objects in the ArrayList until the last index number
			while(result.next())
			{
				// store the data of the vehicle objects into the variables
				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("Notes");
				boolean sold = result.getBoolean("sold");
				
				// add the vehicle into the ArrayList vehicles
				vehicles.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style,
						condition, notes, sold));
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(result != null)
			{
				result.close();
			}
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// return the ArrayList vehicles
		return vehicles;
	}
	
	/**
	 * method returns a vehicle sale which is retrieved from the database using vehicle_id
	 * @param vehicle_ID, the id of the vehicle
	 * @return sold_vehicle, returns the record of the vehicle sold from the Sales table
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 * @throws ParseException, if the parsing of a variable fails, print an error (if the string fails to parse to date)
	 */
	public Sales getVehicleSale(int vehicle_ID) throws SQLException, ParseException
	{
		Sales sold_vehicle = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// store the query into the variable query
		String query = "SELECT * FROM sales WHERE vehicle_id = ?;";
		
		try 
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setInt(1, vehicle_ID);
			
			// print the query on to the console
			System.out.println("DBQuery: " + query);
			
			//execute SQL QUERY
			result = preparedStatement.executeQuery();
			
			// loop to get the vehicle sale record
			while(result.next())
			{
				// store the data of the vehicle objects into the variables
				int sale_id = result.getInt("sale_id");
				Date sold_date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("sold_date"));
				int sold_price = result.getInt("sold_price");
				String status_info = result.getString("status_info");
				int vehicle_id = result.getInt("vehicle_id");
				
				// creates a sale object
				sold_vehicle = new Sales(sale_id, sold_date, sold_price, status_info, vehicle_id);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(result != null)
			{
				result.close();
			}
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// returns the sold_vehicle sale record
		return sold_vehicle;
	}
	
	/**
	 * method used to get the vehicle record from the database
	 * @param vehicle_ID, the id of the vehicle
	 * @return vehicle, it returns a vehicle object retrieved from the database
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public Vehicle getVehicle(int vehicle_ID) throws SQLException
	{
		Vehicle vehicle = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// store the query into a string variable
		String query = "SELECT * FROM vehicles WHERE vehicle_id = ?;";
		
		try
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setInt(1, vehicle_ID);
			
			System.out.println("DBQuery: " + query);
			//Execute SQL Query
			result = preparedStatement.executeQuery();
			
			// loop to get the vehicle object
			while(result.next())
			{
				// store the data of the vehicle objects into the variables
				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("Notes");
				boolean sold = result.getBoolean("sold");
				
				// create a vehicle object and store into the vehicle variable
				vehicle = new Vehicle(vehicle_id, make, model, year, price, license_number, colour,
						number_doors,transmission, mileage, fuel_type, engine_size, body_style,condition, notes, sold);
				
			}
		}
		finally
		{
			if(result != null)
			{
				result.close();
			}
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// return the vehicle
		return vehicle;
	}
	
	/**
	 * method used to delete a vehicle sale record from the database
	 * @param vehicle_ID, the id of the vehicle
	 * @return boolean, it returns a boolean value of true or false (whether vehicle sale record is deleted or not)
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public boolean deleteVehicleSale(int vehicle_ID) throws SQLException
	{
		System.out.println("Deleting vehicle record... ");
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		// store the query into a string variable
		String query = "DELETE FROM Sales WHERE vehicle_id = ?;";
		
		try 
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setInt(1, vehicle_ID);
			System.out.println(query);
			// execute SQL query
			result = preparedStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		// check the value of the returned result
		if (result == 1) 
		{
			// returned result is 1 return true
			return true;
		} 
		else 
		{
			// returned result is not 1 return false
			return false;
		}
	}
	
	/**
	 * method used to delete a vehicle record from the database
	 * @param vehicle_ID, the id of the vehicle
	 * @return boolean, returns the value of boolean true or false (whether the vehicle is deleted or not)
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public boolean deleteVehicle(int vehicle_ID) throws SQLException
	{
		System.out.println("Deleting vehicle record... ");
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		// store the query into a string variable
		String query = "DELETE FROM vehicles WHERE vehicle_id = ?;";
		
		try
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setInt(1, vehicle_ID);
			System.out.println(query);
			
			// execute SQL query
			result = preparedStatement.executeUpdate();
		}
		finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
		// check the value of the returned result
		if (result == 1) 
		{
			// returned result is 1 return true
			return true;
		} 
		else 
		{
			// returned result is not 1 return false
			return false;
		}
	}
	
	/**
	 * method used to insert a user into the database
	 * @param user, a user object consisting of all the information to insert into the database
	 * @return boolean, returns a boolean value to determine whether the query was successful or unsuccessful
	 * @throws SQLException
	 */
	public boolean insertUser(User user) throws SQLException
	{
		// declare the variables
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		// store the query in to a variable
		String insert_user = "INSERT INTO Users (firstname, surname, username, password, user_type, api_key)"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		
		// get the connection
		dbConnection = getDBConnection();
		
		//prepare the statement
		preparedStatement = dbConnection.prepareStatement(insert_user);
		
		// retrieve the data required to execute the query
		user.setPassword(toMD5Hash(user.getPassword()));
		user.setApi_key(toMD5Hash("apikey" + user.getUsername()));
		
		// replace the query with the data to execute
		preparedStatement.setString(1, user.getFirst_name());
		preparedStatement.setString(2, user.getLast_name());
		preparedStatement.setString(3, user.getUsername());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setInt(5, 1);
		preparedStatement.setString(6, user.getApi_key());
		
		// declare a bpplean value
		boolean ok = false;
		try
		{
			// execute the query
			preparedStatement.execute();
			
			// set the boolean value to true
			ok = true;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// return the boolean value
		return ok;
	}
	
	/**
	 * method used to insert a sale record into the table Sales in the database
	 * @param vehicle_sale, the object of Sales to insert into the database
	 * @return boolean, return the value of boolean true or false (whether vehicle is inserted or not)
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public boolean insertSale(Sales vehicle_sale) throws SQLException
	{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		// store the query into a string variable
		String insert_sale = "INSERT INTO Sales (sold_date, sold_price, status_info, vehicle_id)"
				+ "VALUES (?,?,?,?);";
		// get a connection
		dbConnection = getDBConnection();
		
		// prepare the query statement in the database
		preparedStatement = dbConnection.prepareStatement(insert_sale);
		Date sold_date = vehicle_sale.getSold_date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String date_sold = dateFormat.format(sold_date);
		
		// set the values to replace with question marks in the query giving index and value
		preparedStatement.setString(1, date_sold);
		preparedStatement.setInt(2, vehicle_sale.getSold_price());
		preparedStatement.setString(3, vehicle_sale.getStatus_info());
		preparedStatement.setInt(4, vehicle_sale.getVehicle_id());
		
		// reset boolean value ok
		boolean ok = false;
		try 
		{
			System.out.println(insert_sale);
			
			//Execute the query
			preparedStatement.executeUpdate();
			ok = true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		//return the value boolean of OK
		return ok;
	}
	
	/**
	 * method to insert a vehicle record into the table vehicles in the database
	 * @param vehicle, the object vehicle to insert into the database
	 * @return boolean, returns the boolean value true or false (whether the vehicle is inserted or not)
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public boolean insertVehicle(Vehicle vehicle) throws SQLException
	{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		boolean valid = validateRegNo(vehicle.getYear(), vehicle.getLicense_number());
		
		if(valid)
		{
			// store the query into a string variable
			String insert = "INSERT INTO vehicles (make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type,"
					+ "engine_size, body_style, condition, notes, sold)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(insert);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setString(1, vehicle.getMake());
			preparedStatement.setString(2, vehicle.getModel());
			preparedStatement.setInt(3, vehicle.getYear());
			preparedStatement.setInt(4, vehicle.getPrice());
			preparedStatement.setString(5, vehicle.getLicense_number());
			preparedStatement.setString(6, vehicle.getColour());
			preparedStatement.setInt(7, vehicle.getNumber_doors());
			preparedStatement.setString(8, vehicle.getTransmission());
			preparedStatement.setInt(9, vehicle.getMileage());
			preparedStatement.setString(10, vehicle.getFuel_type());
			preparedStatement.setInt(11, vehicle.getEngine_size());
			preparedStatement.setString(12, vehicle.getBody_style());
			preparedStatement.setString(13, vehicle.getCondition());
			preparedStatement.setString(14, vehicle.getNotes());
			preparedStatement.setBoolean(15, false);
			
			// reset the boolean value of ok
			boolean ok = false;
			try 
			{
				System.out.println(insert);
				
				// execute the query
				preparedStatement.executeUpdate();
				
				// set the boolean value of OK to true
				ok = true;
			}
			catch (SQLException e)
			{
				System.out.println(e.getMessage());
			}
			finally
			{
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
				if(dbConnection != null)
				{
					dbConnection.close();
				}
			}
			// return the value of ok
			return ok;
		}
		else
		{
			// return the boolean value false
			return false;
		}
	}
	
	/**
	 * method used to updata a vehicle record in the vehicles table in the database
	 * @param vehicle,  the object vehicle to insert into the database
	 * @param vehicle_ID, the id of the vehicle
	 * @return boolean, returns the boolean value of true or false (whether the vehicle is updated or not)
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public boolean updateVehicle(Vehicle vehicle, int vehicle_ID) throws SQLException
	{
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		// store the query into a string variable
		String update = "UPDATE vehicles SET make = ?, model = ?, year = ?, " 
				+ "price = ?, license_number = ?, colour = ?, number_doors = ?, transmission = ?, mileage = ?, fuel_type = ?, engine_size = ?,"
				+ "body_style = ?, condition = ?, notes = ?, sold = ? WHERE vehicle_id = ?;";
		
		// validates the pattern of the combination of the license number and the year 
		boolean valid = validateRegNo(vehicle.getYear(), vehicle.getLicense_number());
		
		// check if the returned value is true or false
		if(valid)
		{
			try 
			{
				// get a connection
				dbConnection = getDBConnection();
				
				// prepare the query statement in the database
				preparedStatement = dbConnection.prepareStatement(update);
				
				// set the values to replace with question marks in the query giving index and value
				preparedStatement.setString(1, vehicle.getMake());
				preparedStatement.setString(2, vehicle.getModel());
				preparedStatement.setInt(3, vehicle.getYear());
				preparedStatement.setInt(4, vehicle.getPrice());
				preparedStatement.setString(5, vehicle.getLicense_number());
				preparedStatement.setString(6, vehicle.getColour());
				preparedStatement.setInt(7, vehicle.getNumber_doors());
				preparedStatement.setString(8, vehicle.getTransmission());
				preparedStatement.setInt(9, vehicle.getMileage());
				preparedStatement.setString(10, vehicle.getFuel_type());
				preparedStatement.setInt(11, vehicle.getEngine_size());
				preparedStatement.setString(12, vehicle.getBody_style());
				preparedStatement.setString(13, vehicle.getCondition());
				preparedStatement.setString(14, vehicle.getNotes());
				preparedStatement.setBoolean(15, vehicle.isSold());
				preparedStatement.setInt(16, vehicle_ID);
				System.out.println(update);
				// execute SQL update
				preparedStatement.executeUpdate();

			} 
			catch (SQLException e) 
			{

				System.out.println(e.getMessage());
				
				// return false
				return false;

			} 
			finally 
			{

				if (preparedStatement != null) 
				{
					preparedStatement.close();
				}
				if (dbConnection != null) 
				{
					dbConnection.close();
				}
			}
			// return boolean value true
			return true;
		}
		else
		{
			// return boolean value false
			return false;
		}
	}
	
	/**
	 * method used to get all the sale records from the sales table from the database
	 * @return sales (ArrayList), returns all the sales records
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 * @throws ParseException, if the parsing of a variable fails, print an error (if the string fails to parse to date)
	 */
	public ArrayList<Sales> getAllSales() throws SQLException, ParseException
	{
		System.out.println("Retrieving all Sales...");
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// store the query into a string variable
		String query = "SELECT * FROM sales;";
		
		// declare an ArrayList of the Sales records object
		ArrayList<Sales> sales = new ArrayList<>();
		
		try 
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			System.out.println("DBQuery: " + query);
			//execute SQL QUERY
			result = preparedStatement.executeQuery();
			
			// loop to get the ArrayList of records of sales
			while(result.next())
			{
				// store the information of the sales retrieved from the database into the variables
				int sale_id = result.getInt("sale_id");
				Date sold_date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("sold_date"));
				int sold_price = result.getInt("sold_price");
				String status_info = result.getString("status_info");
				int vehicle_id = result.getInt("vehicle_id");
				
				// add the object sale into the ArrayList of sales
				sales.add(new Sales(sale_id, sold_date, sold_price, status_info, vehicle_id));
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(result != null)
			{
				result.close();
			}
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// returns the ArraList of sales records
		return sales;
	}
	
	/**
	 * method used to get the user data from the users table from the database
	 * @param username, the value Username entered by the user
	 * @param password, the value Password entered by the user
	 * @return user, returns the object user
	 * @throws SQLException, if the SQL query fails, it provides information of an SQL error
	 */
	public User getUser(String username) throws SQLException
	{
		User user = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		// store the query into a string variable
		String query = "SELECT * FROM users WHERE username = ?;";
		
		try
		{
			// get a connection
			dbConnection = getDBConnection();
			
			// prepare the query statement in the database
			preparedStatement = dbConnection.prepareStatement(query);
			
			// set the values to replace with question marks in the query giving index and value
			preparedStatement.setString(1, username);
			System.out.println("DBQuery: " + query);
			//Execute SQL Query
			result = preparedStatement.executeQuery();
			
			// loop to get the record of the user
			while(result.next())
			{
				// store the data retrieved from the database of the object user into the variables
				int user_id = result.getInt("id");
				String first_name = result.getString("firstname");
				String last_name = result.getString("surname");
				String usersname = result.getString("username");
				String passwords = result.getString("password");
				int user_type = result.getInt("user_type");
				String api_key = result.getString("api_key");
				// create an object user with the data retrieved from the database
				user = new User(user_id, first_name, last_name, usersname, passwords, user_type, api_key);
				
			}
		}
		finally
		{
			if(result != null)
			{
				result.close();
			}
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
			if(dbConnection != null)
			{
				dbConnection.close();
			}
		}
		// return the object user
		return user;
	}
	
	
	/**
	 * method used to validate the registration numbers of the vehicle
	 * @param year, the year of the vehicle object
	 * @param regNo, the license plate number of the vehicle
	 * @return boolean, returns boolean value of true or false (checks whether the pattern of the license number matches the pattern for the given year)
	 */
	public boolean validateRegNo(int year, String regNo)
	{
		// creates a variable for Pattern class
		Pattern p = null;
		
		// checks the value of the year
		if(year > 2001)
		{
			// stores the pattern into the variable p
			p = Pattern.compile("[a-zA-Z]{2}[0-9]{2}[a-zA-Z]{3}");
		}
		else
		{
			// stored the pattern into the variable p
			p = Pattern.compile("[a-zA-Z]{1}[0-9]{3}[a-zA-Z]{3}");
		}
		
		// checks if the string matches the pattern that was stored into the variable p
		Matcher m = p.matcher(regNo);
		
		// stores the boolean value into the variable, true if matches, false if does not match
		boolean b = m.matches();
		
		// returns the boolean value of b
		return b;
	}
	
	/**
	 * method used to generate md5 hashes in order to genreate API keys
	 * @param string, the string to convert into a MD5 Hash
	 * @return md5Hash, it returns the generated md5Hash
	 */
	public String toMD5Hash (String string)
	{
		// declare a variable
		String md5Hash = "";
		try {
			// tell which hash version to use
			MessageDigest md5 = MessageDigest.getInstance( "MD5" );
			
			// convert the password
			md5.update( string.getBytes());
			
			// store it into the md5HasBytes as bytes
			byte [] md5HashBytes = md5.digest( string.getBytes() );
			
			// store it as a BigInteger
			BigInteger number = new BigInteger(1, md5HashBytes);
			
			// convert it to a string and store into a string variable
			md5Hash = number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return the hash string
		return md5Hash;
		
	}
}
