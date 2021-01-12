package com.home.ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.ms.entities.Worker;


public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
