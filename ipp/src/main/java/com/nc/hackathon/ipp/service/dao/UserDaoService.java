package com.nc.hackathon.ipp.service.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.nc.hackathon.ipp.model.User;

/**
 * CRUDRepository for entity User
 * @author sugh1218
 *
 */
public interface UserDaoService extends CrudRepository<User, Integer> {

	public User findByncid(String id);
	
	public User findByeMail(String eMail);
	
}
