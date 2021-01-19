package com.home.ms.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.ms.entities.Payment;
import com.home.ms.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;

	@HystrixCommand(fallbackMethod = "getPaymentIfFail")// Tolerância a falhas
	@GetMapping(path = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
		Payment payment = service.getPayment(workerId, days);

		return ResponseEntity.ok(payment);

	}
	
	public ResponseEntity<Payment> getPaymentIfFail(  Long workerId,   Integer days) {
		Payment payment =  new Payment("Default", 400.0, days); // um mock se falhar 

		return ResponseEntity.ok(payment);

	}

}
