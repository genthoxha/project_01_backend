package com.ubt.service;

import com.ubt.model.Vehicle;
import com.ubt.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle getById(int id) {
        return vehicleRepository.findById(id);
    }

    public void deleteById(int id) {
        vehicleRepository.deleteById(id);
    }



    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }


}