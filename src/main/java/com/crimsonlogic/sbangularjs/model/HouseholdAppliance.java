package com.crimsonlogic.sbangularjs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name = "HOUSE_HOLD_APP_DETAILS")
public class HouseholdAppliance implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4653250650406509842L;

	@Id  
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;
	
	@Column(name = "BRAND") 
	private String brand;
	
	@Column(name = "MODEL")   
	private String model;
	
	@Column(name = "STATUS")   
	private String status;
	
	@Column(name = "DATE_BOUGHT")   
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

	public HouseholdAppliance() {
 
    }
 
    public HouseholdAppliance(HouseholdApplianceForm householdApplianceForm) {
        this.serialNumber = householdApplianceForm.getSerialNumber();
        this.brand = householdApplianceForm.getBrand();
        this.model = householdApplianceForm.getModel();
        this.status = householdApplianceForm.getStatus();
        this.dateBought= householdApplianceForm.getDateBought();
    }
 
    public HouseholdAppliance(String serialNumber, String brand, String model, String status, Date dateBought) {
    	 this.serialNumber = serialNumber;
         this.brand = brand;
         this.model = model;
         this.status = status;
         this.dateBought= dateBought;
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
 
	/*CREATE TABLE BCTOWNER.HOUSE_HOLD_APP_DETAILS 
	(	
		SERIAL_NUMBER NUMBER NOT NULL ENABLE, 
		BRAND VARCHAR2(50 BYTE) NOT NULL ENABLE, 
		MODEL VARCHAR2(50 BYTE) NOT NULL ENABLE, 
		STATUS VARCHAR2(20 BYTE) NOT NULL ENABLE,
		DATE_BOUGHT DATE DEFAULT SYSDATE NOT NULL,
		CONSTRAINT PK_HOUSE_HOLD_APP_DETAILS PRIMARY KEY (SERIAL_NUMBER)
	) ;
	*/
}