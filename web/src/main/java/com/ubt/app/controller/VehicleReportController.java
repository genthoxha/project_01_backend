package com.ubt.app.controller;


import com.ubt.app.util.Utils;
import com.ubt.model.Order;
import com.ubt.model.VehicleReport;
import com.ubt.service.VehicleReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/vehicle_reports")
public class VehicleReportController {
    public static final Logger logger = LoggerFactory.getLogger(VehicleReportController.class);

    @Autowired
    private VehicleReportService vehicleReportService;

    @GetMapping("/getAll")
    public ResponseEntity<List<VehicleReport>> listAllVehicleReports() {
        logger.info("List all vehicle reports");
        List<VehicleReport> vehicleReports = vehicleReportService.getAllVehicleReports(); //.getAll();
        if (vehicleReports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicleReports, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehicleReport> getVehicleReport(@PathVariable("id") int id) {
        logger.info("Get vehicle report with id: "+id);
        // service + repository help web to provide data from database
        VehicleReport vehicleReport = vehicleReportService.getById(id);
        if (vehicleReport == null) {
            logger.error("Vehicle report with id:"+id+" doesnt exist.");
        }
        return new ResponseEntity<>(vehicleReport, HttpStatus.OK);
    }
    @PostMapping("/createVehicleReport")
    public ResponseEntity<?> createVehicleReport(@RequestBody VehicleReport vehicleReport, UriComponentsBuilder uriCBuilder) {
        logger.info("Creating vehicle report: {}", vehicleReport);

        if (vehicleReportService.getById(vehicleReport.getId()) != null) {
            logger.error("VehicleReport with id:"+ vehicleReport.getId()+" already exist.");
            return new ResponseEntity<>(new Utils
                    ("Unable to create vehicle report with id:" + vehicleReport.getId() + " exist."),
                    HttpStatus.CONFLICT);
        }
        vehicleReportService.save(vehicleReport);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCBuilder.path("/api/order/{id}").buildAndExpand(vehicleReport.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicleReport(@PathVariable("id") int id, @RequestBody VehicleReport vehicleReport) {
        logger.info("Updating vehicleReport with id {}", id);
        VehicleReport currentVehicleReport = vehicleReportService.getById(id);

        if (currentVehicleReport == null) {
            logger.error("Unable to update. VehicleReport with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to update. VehicleReport with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentVehicleReport.setDistanceTraveledInKilometers(vehicleReport.getDistanceTraveledInKilometers());
        currentVehicleReport.setTotalFuelSpentUntilNow(vehicleReport.getTotalFuelSpentUntilNow());
        return new ResponseEntity<>(currentVehicleReport, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleReport(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting vehicle report with id {}", id);
        VehicleReport vehicleReport = vehicleReportService.getById(id);
        if (vehicleReport == null) {
            logger.error("Unable to delete. VehicleReport with id {} not found.", id);
            return new ResponseEntity<>(new Utils("Unable to delete. VehicleReport with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        vehicleReportService.deleteById(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }



}
