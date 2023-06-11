/**
 * 
 */
package org.learntek.crud.client.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author HP
 *
 */
@Configuration
//@EnableFeignClients(basePackages = "org.learntek.crud.client")
public class AppConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*
	 * @Bean public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 * builder.setConnectTimeout(Duration.ofMillis(3000));
	 * builder.setReadTimeout(Duration.ofMillis(3000)); return builder.build(); }
	 */

	/*
	 * @Bean public RestTemplate restTemplate() { SimpleClientHttpRequestFactory
	 * factory = new SimpleClientHttpRequestFactory();
	 * factory.setConnectTimeout(30000); factory.setReadTimeout(30000); return new
	 * RestTemplate(factory); }
	 */
	
	@Bean
	public WebClient webClient() {
		// WebClient.create();
		return WebClient.create("http://localhost:8090");
		
	}
}
