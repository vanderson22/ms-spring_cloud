package com.home.ms.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private WorkerRepository repository;

	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {

		List<Worker> list = repository.findAll();

		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable(name = "id") Long id) {

		Optional<Worker> opt = repository.findById(id);

		return ResponseEntity.ok(opt.orElseThrow(() -> new RuntimeException("Não foi encontrado")));
	}

}
