package com.nc.hackathon.ipp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "demand_details")
public class Demand {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int demandId;

    @Lob
    private String responsibility;

    private Date creationDate;

    private String createdBy;

    private String projectLocation;

    private String projectName;

    private String demandTitle;

    private String domain;

    private String status;

    private int noOfApplicant;

    private double experience;

    @ElementCollection
    private List<String> skills;
}
