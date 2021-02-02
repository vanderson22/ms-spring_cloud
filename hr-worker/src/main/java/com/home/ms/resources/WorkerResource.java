package com.home.ms.resources;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.ms.entities.Worker;
import com.home.ms.repositories.WorkerRepository;

@RestController
@RequestMapping("/workers")
public class WorkerResource {
 
	 private static final Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerResource.class);
	//infos de contexto
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerRepository repository;
	
	@Value("${test.config}")
	private String testConfig;

	
	@GetMapping(value="/configs")
	public ResponseEntity<Void> getConfig() {

		logger.info("testConfig = " + testConfig);
		
		return ResponseEntity
								.noContent()
							    .build();
		}


	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {

		List<Worker> list = repository.findAll();

		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable(name = "id") Long id) {

		logger.info("PORT = " + env.getProperty("local.server.port"));
		 
		Optional<Worker> opt = repository.findById(id);

		return ResponseEntity.ok(opt.orElseThrow(() -> new RuntimeException("Não foi encontrado")));
	}

}
