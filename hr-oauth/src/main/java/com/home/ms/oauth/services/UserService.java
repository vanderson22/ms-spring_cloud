package com.home.ms.oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.home.ms.oauth.UserFeignClient;
import com.home.ms.oauth.entities.User;

@Component
public class UserService {

	@Autowired
	UserFeignClient userFeignClient;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public User findByEmail(String email) {

		User user = userFeignClient.findByEmail(email).getBody();

		if (user == null)
			throw new IllegalArgumentException("Email é nulo");

		log.info(String.format("Email found %s", email));
		return user;
	}

}
