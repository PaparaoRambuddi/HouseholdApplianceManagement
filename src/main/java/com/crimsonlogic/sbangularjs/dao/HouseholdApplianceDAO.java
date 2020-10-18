package com.crimsonlogic.sbangularjs.dao;

import java.util.List;

import com.crimsonlogic.sbangularjs.model.HouseholdAppliance;

public interface HouseholdApplianceDAO {

	
	List<HouseholdAppliance> getAllHouseholdAppliances();
	HouseholdAppliance getHouseholdAppliancesBySerialNumber(String SerialNumber);
	 
	void addHouseholdAppliance(HouseholdAppliance householdAppliance);
	void updateHouseholdAppliance(HouseholdAppliance householdAppliance);
	
	void deleteHouseholdAppliance(String serialNumber);
	
	List<HouseholdAppliance> findAllHouseholdAppliances(HouseholdAppliance householdAppliance);
}
