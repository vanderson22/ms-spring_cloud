package com.home.ms.services;

import org.springframework.stereotype.Service;

import com.home.ms.entities.Payment;

//registrado como component do spring
//@Component
@Service
public class PaymentService {

	public Payment getPayment(long workerId, int days) {

		// chama serviço workers

		// pagamento mockado
		return new Payment("Bob", 200.0, days);
	}

}
