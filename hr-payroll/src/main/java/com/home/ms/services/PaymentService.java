package com.home.ms.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.home.ms.entities.Payment;
import com.home.ms.entities.Worker;

//registrado como component do spring
//@Component
@Service
public class PaymentService {

	@Autowired
	private RestTemplate rest;

	@Value("${hr-worker.host}")
	private String url;

	public Payment getPayment(long workerId, int days) {

		// chama serviço workers

		Map<String, String> variaveis = new HashMap<>();
		variaveis.put("id", Long.toString(workerId));

	
		Worker worker = rest.getForObject(url + "/workers/{id}", Worker.class , variaveis);

		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
