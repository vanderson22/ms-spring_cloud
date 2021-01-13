package com.home.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	/**
	 * Singleton restTemplate
	 */
	@Bean
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}
}
