package com.ubt.app.controller;

import com.ubt.app.util.Utils;
import com.ubt.model.Driver;
import com.ubt.model.Server;
import com.ubt.model.Vehicle;
import com.ubt.model.VehicleReport;
import com.ubt.service.VehicleReportService;
import com.ubt.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    public static final Logger logger = LoggerFactory.getLogger(WorkScheduleController.class);
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleReportService vehicleReportService;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> getDriverReports(){
        logger.info("List all vehicles");
        List<Vehicle> reports = vehicleService.getAll();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicle-reports", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleReport>> getVehicleReports(){
        logger.info("List all vehicle reports");
        List<VehicleReport> reports = vehicleReportService.getAllVehicleReports();
        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @RequestMapping(value="/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@RequestParam("id") int id) {
        logger.info("Fetching & Deleting Server with id {}", id);
        Vehicle vehicle = vehicleService.getById(id);
        if (vehicle == null) {
            logger.error("Unable to delete. Vehicle with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to delete. Server with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        vehicleService.deleteById(id);
        return new ResponseEntity<Server>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/createVehicle", method = RequestMethod.POST)
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle, UriComponentsBuilder uriCBuilder) {
        logger.info("Creating Vehicle: {}", vehicle);

        if (vehicleService.getById(vehicle.getId()) != null) {
            logger.error("Server with id:" + vehicle.getId() + " already exist.");
            return new ResponseEntity<>(new Utils
                    ("Unable to create server with id:" + vehicle.getId() + " exist."),
                    HttpStatus.CONFLICT);
        }
        vehicleService.save(vehicle);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCBuilder.path("/vehicles/{id}").buildAndExpand(vehicle.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle vehicle) {
        logger.info("Updating Vehicle with id {}", id);
        Vehicle currentVehicle = vehicleService.getById(id);

        if (currentVehicle == null) {
            logger.error("Unable to update. Vehicle with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to upate. Vehicle with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentVehicle.setName(vehicle.getName());
        currentVehicle.setModel(vehicle.getModel());
        currentVehicle.setFuelType(vehicle.getFuelType());

        vehicleService.save(currentVehicle);
        return new ResponseEntity<>(currentVehicle, HttpStatus.OK);
    }



}
