package com.home.ms.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.ms.hruser.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);


}
