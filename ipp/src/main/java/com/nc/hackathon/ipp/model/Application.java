package com.nc.hackathon.ipp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int applicationId;

	private String empId;

	private int demandId;

	private String status;

	private Date appliedDate;

	private String jobTitle;

	private String domain;

	private String applicantFeedback;
}
