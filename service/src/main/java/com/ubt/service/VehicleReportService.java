package com.ubt.service;

import com.ubt.model.VehicleReport;
import com.ubt.repository.VehicleReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleReportService {

    @Autowired
    private VehicleReportRepository vehicleReportRepository;

    public List<VehicleReport> getAllVehicleReports() {

        return vehicleReportRepository.findAll();
    }

    public VehicleReport getById(int id) {

        return vehicleReportRepository.findById(id);
    }

    public void deleteById(int id) {
        vehicleReportRepository.deleteById(id);
    }

    public void save(VehicleReport vr) {
        VehicleReport vehicleReport = new VehicleReport();

        vehicleReport.setDistanceTraveledInKilometers(vr.getDistanceTraveledInKilometers());
        vehicleReport.setTotalFuelSpentUntilNow(vr.getTotalFuelSpentUntilNow());
        vehicleReportRepository.save(vehicleReport);
    }
}
