package com.nc.hackathon.ipp.service.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.nc.hackathon.ipp.model.UserRegistration;

/**
 * CRUDRepository for entity UserRegistration
 * @author sugh1218
 *
 */
@Service
public interface RegistrationDaoService extends CrudRepository<UserRegistration, String>{

	UserRegistration findByncId(String ncid);

	
}
