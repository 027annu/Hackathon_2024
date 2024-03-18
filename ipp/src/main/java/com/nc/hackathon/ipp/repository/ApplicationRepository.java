package com.nc.hackathon.ipp.repository;

import com.nc.hackathon.ipp.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    public List<Application> getByEmpId(String empId); 
    
    public List<Application> getByDemandId(int demandId);
}
