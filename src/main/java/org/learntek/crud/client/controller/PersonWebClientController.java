/**
 * 
 */
package org.learntek.crud.client.controller;

import java.util.List;

import org.learntek.crud.client.model.Person;
import org.learntek.crud.client.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author HP
 *
 */
@RestController
public class PersonWebClientController {
	@Autowired
	private WebClient webClient;
	
	@GetMapping("/person/v3/{id}")
	public ResponseEntity<?> getPersonV3(@PathVariable("id") int id){
		ClientResponse clientResponse =  webClient.get().uri("/person/"+id).exchange().block();
		ResponseEntity<Person> responseEntity = null;
		if(clientResponse.statusCode().is2xxSuccessful()) {
			Person person = clientResponse.bodyToMono(Person.class).block();
			responseEntity = new ResponseEntity<Person>(person, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/person/v3")
	public ResponseEntity<?> getAllPersonsV3(){
		ClientResponse clientResponse  =  webClient.get().uri("/person").exchange().block();
		ResponseEntity<List<Person>> responseEntity = null;
		if(clientResponse.statusCode().is2xxSuccessful()) {
			List<Person> persons = clientResponse.bodyToFlux(Person.class).collectList().block();
			responseEntity = new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/person/v3")
	public ResponseEntity<?> createPersonV3(@RequestBody Person person){
		
		ClientResponse clientResponse = webClient.post().uri("/person").bodyValue(person).exchange().block();
		
		ResponseEntity<?> responseEntity = null;
		if(clientResponse.statusCode().is2xxSuccessful()) {
			ResponseMessage responseMessage  = clientResponse.bodyToMono(ResponseMessage.class).block();
			responseEntity =  new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.CREATED);;
		}
		return responseEntity;
	}
	
	@PutMapping("/person/v3")
	public ResponseEntity<?> updatePersonV3(@RequestBody Person person){
		ClientResponse clientResponse = webClient.put().uri("/person").bodyValue(person).exchange().block();
		ResponseEntity<?> responseEntity = null;
		if(clientResponse.statusCode().is2xxSuccessful()) {
			ResponseMessage responseMessage  = clientResponse.bodyToMono(ResponseMessage.class).block();
			responseEntity =  new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);;
		}
		return responseEntity;
	}
	
	@DeleteMapping("/person/v3/{id}")
	public ResponseEntity<?> deletePersonV2(@PathVariable("id") int id){
		ClientResponse clientResponse = webClient.delete().uri("/person/"+id).exchange().block();
		ResponseEntity<?> responseEntity = null;
		if(clientResponse.statusCode().is2xxSuccessful()) {
			ResponseMessage responseMessage  = clientResponse.bodyToMono(ResponseMessage.class).block();
			responseEntity =  new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);;
		}
		return responseEntity;
	}
}
