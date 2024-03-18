package com.nc.hackathon.ipp.model.constant;

import java.util.*;

/**
 * This class will contain all static data model which changes rarely.
 * @author sugh1218
 *
 */
public interface ConstantData {

	
	class ROLES {
		
		static String BLANK = "";
		static String ASE = "Asistant System Engineer";
		static String SE = "System Engineer";
		static String SSE = "Senior System Engineer";
		static String SA = "Software Analyst";
		static String DL = "Development Lead";
		static String SDL ="Senior Dev Lead";
		static String TM = "Technical Manager";
		static String PM = "Project Manager";
		static String STL = "Senior Technical Manager";
		static String HS = "Head of Sector";
		static String HD = "Head Of Division";
		static String DIR = "Technical Director";
		
		static String BA ="Business Analyst";
		static String SBA ="Senior Business Analyst";
		static String LBA ="Lead Business Analyst";
		static String SABA ="Solution Archeticect";

		static String QA ="Quality Analyst";
		static String SQA ="Senior Quality Analyst";
		static String LQA ="Lead Quality Analyst";
		static String QAM ="QA Manager";
		static String SAM ="Senior QA Manager";
		static String QAD = "QA Director";

		public static List<String> getAllRoles() {
			
			return List.of(BLANK, ASE, SE, SSE, SA, DL, SDL, TM, PM, STL, HS, HD, DIR, BA, SBA, LBA, SABA, QA, SQA, LQA, QAM, SAM, QAD);
		}
		
		
		public static Map<String, String> USER_ACCESS_ROLE = new HashMap<>();

		
		public static Map<String, String> getUserAccessLevel() {
			
			USER_ACCESS_ROLE.put(ASE, "USER");
			USER_ACCESS_ROLE.put(SE, "USER");
			USER_ACCESS_ROLE.put(SSE, "USER");
			USER_ACCESS_ROLE.put(SA, "USER");
			USER_ACCESS_ROLE.put(DL, "SUPERUSER");
			USER_ACCESS_ROLE.put(SDL, "SUPERUSER");
			USER_ACCESS_ROLE.put(TM, "SUPERUSER");
			USER_ACCESS_ROLE.put(PM, "SUPERUSER");
			USER_ACCESS_ROLE.put(STL, "SUPERUSER");
			USER_ACCESS_ROLE.put(HS, "SUPERUSER");
			USER_ACCESS_ROLE.put(HD, "SUPERUSER");
			USER_ACCESS_ROLE.put(DIR, "SUPERUSER");
			
			USER_ACCESS_ROLE.put(BA, "USER");
			USER_ACCESS_ROLE.put(SBA, "USER");
			USER_ACCESS_ROLE.put(LBA, "SUPERUSER");
			USER_ACCESS_ROLE.put(SABA , "SUPERUSER");
			USER_ACCESS_ROLE.put(QA, "USER");
			USER_ACCESS_ROLE.put(SQA, "SUPERUSER");
			USER_ACCESS_ROLE.put(QAM, "SUPERUSER");
			USER_ACCESS_ROLE.put(QAD, "SUPERUSER");
			
			
			return USER_ACCESS_ROLE;
		}
		
	}
	
	
	class PROJECTS {
		
		static String BLANK = "";
		static String P1 = "Project One";
		static String P2 = "Project Two";
		static String P3 = "Apna O2";
		static String P4 = "T Mobile NL";
		static String P5 = "NU-Day";

		public static List<String> getAllProjects() {
			
			return List.of(BLANK, P1, P2, P3, P4, P5);
		}
	}

	
	class EDUCATION {
		
		static String BLANK = "";
		static String E1 = "B. Tech";
		static String E2 = "M. tech";
		static String E3 = "MBA";
		static String E4 = "MCA";
		static String E5 = "BCA";
		static String E6 = "CA";
		static String E7 = "BBA";

		public static List<String> getAllEducations() {
			
			return List.of(BLANK, E1, E2, E3, E4, E5, E6, E7);
		}
	}
	
	
	class SKILLS {
		
		static String BLANK = "";
		static String S1 = "Java";
		static String S2 = "Spring MVC";
		static String S3 = "Spring Boot";
		static String S4 = "MicroServices";
		static String S5 = "Kafka";
		static String S6 = "Couch Base";
		static String S7 = "DevOps";
		static String S8 = "Kubernets";
		static String S9 = "Amazon Web Services";
		static String S10 = "Docker";
		static String S11 = "Unix";
		static String S12 = "Linux Admin";
		static String S13 = "Project Management";
		static String S14 = "Test Automation";
		
		public static List<String> getAllSkills() {
			
			return List.of(BLANK, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14);
		}
	}
	
	
	
}
