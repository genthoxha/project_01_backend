package com.ubt.repository;

import com.ubt.model.Order;
import com.ubt.model.VehicleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleReportRepository extends JpaRepository<VehicleReport, Integer> {
    VehicleReport findById(int id);
}
