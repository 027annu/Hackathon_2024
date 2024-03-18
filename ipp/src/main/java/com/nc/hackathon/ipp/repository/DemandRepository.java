package com.nc.hackathon.ipp.repository;

import com.nc.hackathon.ipp.model.Application;
import com.nc.hackathon.ipp.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
    public List<Demand> getByCreatedBy(String empId);
}
