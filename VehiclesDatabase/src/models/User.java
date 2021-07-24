package models;

/**
 * User class creates user objects
 * @author Qasim Awan
 * @version 1.0
 */
public class User {
	private int user_id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private int user_type;
	private String api_key;
	
	/**
	 * creates a new user object, which represents a user of the system
	 * @param user_id, users unique identifier
	 * @param first_name, the first name of the object user
	 * @param last_name, the last name of the object user
	 * @param username, the username of the user to login
	 * @param password, the password of the user to login
	 * @param user_type, the user_type (whether the user is admin or not)
	 */
	public User(int user_id, String first_name, String last_name, String username, String password, int user_type, String api_key)
	{
		
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.user_type = user_type;
		this.api_key = api_key;
	}

	

	/**
	 * get the id of the user object and return
	 * @return user_id, returns the user_id that identifies the user uniquely
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * sets the user_id to the parameter received
	 * @param user_id, value to set the user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * gets the first name of the user
	 * @return first_name, returns the first name of the user
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * sets the first_name to the parameter received
	 * @param first_name, value to set the first_name
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * gets the last name of the user
	 * @return last_name, returns the last anme of the user
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * sets the last name to the parameter received
	 * @param last_name, value to set the last name
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * gets the username of the user
	 * @return username, returns the username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * sets the username of the user
	 * @param username, value to set the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * gets the password of the user
	 * @return password, returns the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets the password of the user
	 * @param password, value to set the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * gets the user_type of the user e.g. admin, standard user
	 * @return user_type, returns the user_type of the system
	 */
	public int getUser_type() {
		return user_type;
	}

	/**
	 * sets the user_type of the user
	 * @param user_type, returns the user_type of the user
	 */
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
	/**
	 * gets the api_key of the user
	 * @return api_key, returns the api_key of the user
	 */
	public String getApi_key() {
		return api_key;
	}

	/**
	 * sets the api_key of the user
	 * @param api_key, value to set the api_key
	 */
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", username="
				+ username + ", password=" + password + ", user_type=" + user_type + ", api_key=" + api_key + "]";
	}
	
}
