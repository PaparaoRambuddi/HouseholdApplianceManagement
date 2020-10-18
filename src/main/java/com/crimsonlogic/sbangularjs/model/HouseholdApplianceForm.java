package com.crimsonlogic.sbangularjs.model;

import java.util.Date;

public class HouseholdApplianceForm {
    
		
	private String serialNumber;
	private String brand;
	private String model;
	private String status;
	private Date dateBought;

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the dateBought
	 */
	public Date getDateBought() {
		return dateBought;
	}

	/**
	 * @param dateBought the dateBought to set
	 */
	public void setDateBought(Date dateBought) {
		this.dateBought = dateBought;
	}
}
