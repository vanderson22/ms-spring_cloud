package com.home.ms.hruser.resources;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.ms.hruser.entities.User;
import com.home.ms.hruser.repositories.UserRepository;

 
	

@RefreshScope // Para o actuator mudar as configurações em runtime, em todo lugar que se use configurações 
@RestController
@RequestMapping("/users")
public class UserResource {
 
	 private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserResource.class);
	//infos de contexto
	@Autowired
	private Environment env;
	
	@Autowired
	private UserRepository repository;
//	
//	@Value("${spring.application.name}")
//	private String testConfig;

 
	 
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable(name = "id") Long id) {

		logger.info("PORT = " + env.getProperty("local.server.port"));
		 
		Optional<User> opt = repository.findById(id);

		return ResponseEntity.ok(opt.orElseThrow(() -> new RuntimeException("Não foi encontrado")));
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam (name = "email") String email) {

		logger.info("PORT = " + env.getProperty("local.server.port"));

		return ResponseEntity.ok( repository.findByEmail(email));
	}

}
