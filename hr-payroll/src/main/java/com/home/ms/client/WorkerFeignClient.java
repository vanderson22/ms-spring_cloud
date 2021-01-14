package com.home.ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.home.ms.entities.Worker;

@Component
//@FeignClient(name= "hr-worker" , url = "localhost:8001", path="/workers") // vai para o application.properties
@FeignClient(name= "hr-worker" , path="/workers")
public interface WorkerFeignClient {

	
	//copiado de hr-worker
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable(name = "id") Long id);
}
