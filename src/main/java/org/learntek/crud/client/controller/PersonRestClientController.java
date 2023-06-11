/**
 * 
 */
package org.learntek.crud.client.controller;

import java.util.List;

import org.learntek.crud.client.model.Person;
import org.learntek.crud.client.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author HP
 *
 */
@RestController
public class PersonRestClientController {
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/person/v1/{id}")
	public ResponseEntity<?> getPersonV1(@PathVariable("id") int id){
		System.out.println("v1 API is called");
		ResponseEntity<Person> responseEntity = restTemplate.getForEntity("http://localhost:8090/person/{id}", Person.class,id);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity;
		}
		else {
			String errorMessage = "Some Problem , plz try after sometime..........";
			ResponseEntity<String> errorResponse = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
	}
	
	@GetMapping("/person/v1")
	public ResponseEntity<?> getAllPersonsv1(){
		ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8090/person", List.class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity;
		}
		else {
			String errorMessage = "Some Problem , plz try after sometime..........";
			ResponseEntity<String> errorResponse = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
	}
	
	@PostMapping("/person/v1")
	public ResponseEntity<?> createPersonV1(@RequestBody Person person){
		ResponseEntity<ResponseMessage> responseEntity = restTemplate.postForEntity("http://localhost:8090/person", person, ResponseMessage.class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity;
		}
		else {
			String errorMessage = "Some Problem , plz try after sometime..........";
			ResponseEntity<String> errorResponse = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
	}
	
	@PutMapping("/person/v1")
	public ResponseEntity<?> updatePersonV1(@RequestBody Person person){
		//restTemplate.put("http://localhost:8090/person",person);
		HttpEntity<Person> httpEntity = new HttpEntity<Person>(person);
		ResponseEntity<ResponseMessage> responseEntity = restTemplate.exchange("http://localhost:8090/person", HttpMethod.PUT, httpEntity, ResponseMessage.class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity;
		}
		else {
			String errorMessage = "Some Problem , plz try after sometime..........";
			ResponseEntity<String> errorResponse = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
	}
	
	@DeleteMapping("/person/v1/{id}")
	public ResponseEntity<?> deletePersonv1(@PathVariable("id") int id){
		//restTemplate.delete("http://localhost:8090/person/{id}", id);
		ResponseEntity<?> responseEntity = restTemplate.exchange("http://localhost:8090/person/{id}", HttpMethod.DELETE, null, Void.class, id);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseEntity;
		}
		else {
			String errorMessage = "Some Problem , plz try after sometime..........";
			ResponseEntity<String> errorResponse = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
	}
}
