package com.home.ms.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.ms.client.WorkerFeignClient;
import com.home.ms.entities.Payment;
import com.home.ms.entities.Worker;

//registrado como component do spring
//@Component
@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(long workerId, int days) {

		// chama serviço workers

		Map<String, String> variaveis = new HashMap<>();
		variaveis.put("id", Long.toString(workerId));

	
    	Worker worker =  workerFeignClient.findById(workerId).getBody();

		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
