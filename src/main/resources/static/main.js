var app = angular.module("HouseholdApplianceManagement", []);
 
// Controller Part
app.controller("HouseholdApplianceController", function($scope, $http) {
 
 
    $scope.householdAppliances = [];
    $scope.householdApplianceForm = {
		serialNumber: "",
		brand: "",
		model: "",
		status:"",
		dateBought:""
    };
    
	var btnValue = "";
	$scope.showName = false;
    // Now load the data from server
    _refreshHouseholdApplianceData();
 
    // HTTP POST/PUT methods for add/edit householdAppliance  
    // Call: http://localhost:8080/householdAppliance
    $scope.submitHouseholdAppliance = function() {
 
        var method = "";
        var url = "";
 
        if (btnValue == "CREATE") {
            method = "POST";
            url = '/householdAppliance';
        } else if (btnValue == "UPDATE") {
            method = "PUT";
            url = '/householdAppliance';
        }
        angular.element( document.getElementById('myInput') ).prop("value", "search")
        $scope.showName = false;
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.householdApplianceForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createHouseholdAppliance = function() {
    	 btnValue = "CREATE";
    	 $scope.showName = true;
    	 angular.element( document.getElementById('myInput') ).prop("value", "create")
    	  _refreshHouseholdApplianceData();
        _clearFormData();
    	 _addRequiredAttribute();
    }
 
    // HTTP DELETE- delete householdAppliance by Id
    // Call: http://localhost:8080/householdAppliance/{serialNumber}
    $scope.deletehouseholdAppliance = function(householdAppliance) {
        $http({
            method: 'DELETE',
            url: '/householdAppliance/' + householdAppliance.serialNumber
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editHouseholdAppliance = function(householdAppliance) {
    	btnValue = "UPDATE";
    	$scope.showName = true;
    	angular.element( document.getElementById('myInput') ).prop("value", "update")
        angular.element( document.getElementById('serialNumber')).attr("disabled", "disabled");
        $scope.householdApplianceForm.serialNumber = householdAppliance.serialNumber;
        $scope.householdApplianceForm.brand = householdAppliance.brand;
        $scope.householdApplianceForm.model = householdAppliance.model;
        $scope.householdApplianceForm.status = householdAppliance.status;
        $scope.householdApplianceForm.dateBought = new Date(householdAppliance.dateBought);
        _addRequiredAttribute();
    };
 
    // Private Method  
    // HTTP GET- get all householdAppliances collection
    // Call: http://localhost:8080/householdAppliances
    function _refreshHouseholdApplianceData() {
        $http({
            method: 'GET',
            url: '/householdAppliances',
            data: angular.toJson($scope.householdApplianceForm)
        }).then(
            function(res) { // success
                $scope.householdAppliances = res.data;
                _removeRequiredAttribute();
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
    
    $scope.searchhouseholdAppliance = function() {
    	_removeRequiredAttribute();
    	angular.element( document.getElementById('serialNumber')).removeAttr("disabled"); 
        $http({
            method: 'POST',
            url: '/searchHouseholdAppliance',
            data: angular.toJson($scope.householdApplianceForm)
        }).then(
            function(res) { // success
                $scope.householdAppliances = res.data;
                if(res.data.length <= 0) // data found
            	{
                	 alert("No data found with the given serch criteria");
            	}
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
    	angular.element( document.getElementById('serialNumber')).removeAttr("disabled"); 
    	if (btnValue == "CREATE" && res.data.length  <= 0 ) 
    		{
    		 alert("Household Appliance is already exists with Serial Number: " + angular.element( document.getElementById('serialNumber')).val());
    		}
        _refreshHouseholdApplianceData();
        _clearFormData();
        btnValue = "SEARCH";
       
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
 
    // Clear the form
    function _clearFormData() {
        $scope.householdApplianceForm.serialNumber = "";
        $scope.householdApplianceForm.brand = "";
        $scope.householdApplianceForm.model = "";
        $scope.householdApplianceForm.status = "";
        $scope.householdApplianceForm.dateBought = "";
    };
    
    function _addRequiredAttribute()
    {
    	angular.element( document.getElementById('serialNumber')).prop('required', true);
    	angular.element( document.getElementById('brand')).prop('required', true);
    	angular.element( document.getElementById('model')).prop('required', true);
    	angular.element( document.getElementById('status')).prop('required', true);
    	angular.element( document.getElementById('dateBought')).prop('required', true);
    }
    function _removeRequiredAttribute()
    {
    	angular.element( document.getElementById('serialNumber')).removeAttr('required');
    	angular.element( document.getElementById('brand')).removeAttr('required');
    	angular.element( document.getElementById('model')).removeAttr('required');
    	angular.element( document.getElementById('status')).removeAttr('required');
    	angular.element( document.getElementById('dateBought')).removeAttr('required');
    }
});