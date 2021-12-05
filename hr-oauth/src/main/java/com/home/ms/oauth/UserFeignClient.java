package com.home.ms.oauth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.ms.oauth.entities.User;

/**
 * Classe que serve de interface de comunicação com outro microsserviço
 * 
 * hr-user  e qual recurso ? /users
 * **/
@Component
@FeignClient(name="hr-user" , path="/users")
public interface UserFeignClient {
	
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam (name = "email") String email) ;

}
