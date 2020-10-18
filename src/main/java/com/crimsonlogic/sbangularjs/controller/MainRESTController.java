package com.crimsonlogic.sbangularjs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.sbangularjs.dao.HouseholdApplianceDAO;
import com.crimsonlogic.sbangularjs.model.HouseholdAppliance;
import com.crimsonlogic.sbangularjs.model.HouseholdApplianceForm;
  
@RestController 
public class MainRESTController {
  
    @Autowired
    private HouseholdApplianceDAO householdApplianceService;
  
  
    // URL:
    // http://localhost:8080/SomeContextPath/householdAppliances
    // http://localhost:8080/SomeContextPath/householdAppliance.xml
    // http://localhost:8080/SomeContextPath/householdAppliance.json
    @RequestMapping(value = "/householdAppliances", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<HouseholdAppliance> getHouseholdAppliances() {
        List<HouseholdAppliance> list = householdApplianceService.getAllHouseholdAppliances();
        return list;
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/householdAppliance/{serialNumber}
    // http://localhost:8080/SomeContextPath/householdAppliance/{serialNumber}.xml
    // http://localhost:8080/SomeContextPath/householdAppliance/{serialNumber}.json
    @RequestMapping(value = "/householdAppliance/{serialNumber}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public HouseholdAppliance getHouseholdAppliance(@PathVariable("serialNumber") String serialNumber) {
        return householdApplianceService.getHouseholdAppliancesBySerialNumber(serialNumber);
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/householdAppliance
    // http://localhost:8080/SomeContextPath/householdAppliance.xml
    // http://localhost:8080/SomeContextPath/householdAppliance.json
  
    @RequestMapping(value = "/householdAppliance", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public HouseholdAppliance addHouseholdAppliance(@RequestBody HouseholdApplianceForm householdApplianceForm) {
  
        System.out.println("(Service Side) Creating HouseholdAppliance with serialNumber: " + householdApplianceForm.getSerialNumber());
        HouseholdAppliance existinghHouseholdAppliance = householdApplianceService.getHouseholdAppliancesBySerialNumber(householdApplianceForm.getSerialNumber());
        HouseholdAppliance householdAppliance = null ;
        if(existinghHouseholdAppliance ==null)
        {
        	
        householdAppliance = new HouseholdAppliance();
        householdAppliance.setSerialNumber(householdApplianceForm.getSerialNumber());
    	householdAppliance.setBrand(householdApplianceForm.getBrand());
    	householdAppliance.setModel(householdApplianceForm.getModel());
    	householdAppliance.setStatus(householdApplianceForm.getStatus());
    	householdAppliance.setDateBought(householdApplianceForm.getDateBought());
    	
    	 householdApplianceService.addHouseholdAppliance(householdAppliance);
    	 
        }
        return householdAppliance;
    	
        
    }
    
    @RequestMapping(value = "/searchHouseholdAppliance", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<HouseholdAppliance> searchHouseholdAppliance(@RequestBody HouseholdApplianceForm householdApplianceForm) {
  
        System.out.println("(Service Side) search HouseholdAppliance with serialNumber: " + householdApplianceForm.getSerialNumber());
        HouseholdAppliance householdAppliance = new HouseholdAppliance();
        householdAppliance.setSerialNumber(householdApplianceForm.getSerialNumber());
    	householdAppliance.setBrand(householdApplianceForm.getBrand());
    	householdAppliance.setModel(householdApplianceForm.getModel());
    	householdAppliance.setStatus(householdApplianceForm.getStatus());
    	householdAppliance.setDateBought(householdApplianceForm.getDateBought());
    	
    	List<HouseholdAppliance> list =  householdApplianceService.findAllHouseholdAppliances(householdAppliance);
    	 
    	 return list;
        
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/householdAppliance
    // http://localhost:8080/SomeContextPath/householdAppliance.xml
    // http://localhost:8080/SomeContextPath/householdAppliance.json
    @RequestMapping(value = "/householdAppliance", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public HouseholdAppliance updateHouseholdAppliance(@RequestBody HouseholdApplianceForm householdApplianceForm) {
  
        System.out.println("(Service Side) Editing HouseholdAppliance with serialNumber: " + householdApplianceForm.getSerialNumber());
  
        HouseholdAppliance householdAppliance = householdApplianceService.getHouseholdAppliancesBySerialNumber(householdApplianceForm.getSerialNumber());
        if(householdAppliance!=null)
        {
        	householdAppliance.setSerialNumber(householdApplianceForm.getSerialNumber());
          	householdAppliance.setBrand(householdApplianceForm.getBrand());
          	householdAppliance.setModel(householdApplianceForm.getModel());
          	householdAppliance.setStatus(householdApplianceForm.getStatus());
          	householdAppliance.setDateBought(householdApplianceForm.getDateBought());
        }
        
        householdApplianceService.updateHouseholdAppliance(householdAppliance);
        return householdAppliance;
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/householdAppliance/{serialNumber}
    @RequestMapping(value = "/householdAppliance/{serialNumber}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteHouseholdAppliance(@PathVariable("serialNumber") String serialNumber) {
  
        System.out.println("(Service Side) Deleting HouseholdAppliance with serialNumber: " + serialNumber);
  
        householdApplianceService.deleteHouseholdAppliance(serialNumber);
    }
  
}