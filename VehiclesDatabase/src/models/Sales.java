/**
 * Sales class creates sale objects of the vehicle
 * @author Qasim Awan
 * @version 1.0
 */

package models;

import java.util.Date;

public class Sales {
	
	private int sale_id;
	private Date sold_date;
	private int sold_price;
	private String status_info;
	private int vehicle_id;
	
	/**
	 * Creates a new Sales objects, which represents the sale of a vehicle
	 * @param sale_id, sale identifier
	 * @param sold_date, the date of the vehicle sold
	 * @param sold_price, the price at which the vehicle sold
	 * @param status_info, to store the status of the vehicle, e.g. sold
	 * @param vehicle_id, vehicle identifier
	 */
	public Sales(int sale_id, Date sold_date, int sold_price, String status_info, int vehicle_id)
	{
		
		this.sale_id  = sale_id;
		this.sold_date = sold_date;
		this.sold_price = sold_price;
		this.status_info = status_info;
		this.vehicle_id = vehicle_id;
	}
	
	/**
	 * get the sale_id of the vehicle object and returns
	 * @return sale_id, returns the sale identifier
	 */
	public int getSale_id() {
		return sale_id;
	}
	
	/**
	 * sets the sale id of the vehicle object
	 * @param sale_id, the new sale_id to update the vehicle sale_id
	 */
	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}
	
	/**
	 * gets the sold date of the vehicle and returns
	 * @return sold_date, returns the date the vehicle is sold
	 */
	public Date getSold_date() {
		return sold_date;
	}

	/**
	 * sets the sold date of the vehicle object
	 * @param sold_date, date passed to set the sold date of the vehicle
	 */
	public void setSold_date(Date sold_date) {
		this.sold_date = sold_date;
	}
	
	/**
	 * gets the sold price of the vehicle
	 * @return sold_price, returns the sold price of the vehicle
	 */
	public int getSold_price() {
		return sold_price;
	}
	
	/**
	 * sets the sold price to the parameter received
	 * @param sold_price, value to set the sold price of the vehicle object
	 */
	public void setSold_price(int sold_price) {
		this.sold_price = sold_price;
	}
	
	/**
	 * gets the status information of the vehicle sold
	 * @return status_info, return the status of the vehicle e.g. sold, returned
	 */
	public String getStatus_info() {
		return status_info;
	}
	
	/**
	 * sets the status of the vehicle sold to the parameter received
	 * @param status_info, value to set the status
	 */
	public void setStatus_info(String status_info) {
		this.status_info = status_info;
	}
	
	/**
	 * gets the vehicle_id of the sale record
	 * @return	vehicle_id, returns the id of the vehicle sold
	 */
	public int getVehicle_id() {
		return vehicle_id;
	}
	
	/**
	 * sets the vehicle_id to the parameter received
	 * @param vehicle_id, value to set the vehicle_id
	 */
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	
	@Override
	// returns the string
	public String toString() {
		return "Sale Id = " + sale_id + "\n"
				+ "Sold Date = " + sold_date + "\n"
				+ "Sold Price = " + sold_price + "\n"
				+ "Status Info = " + status_info + "\n" 
				+ "Vehicle Id = " + vehicle_id;
		}
	
	
}
