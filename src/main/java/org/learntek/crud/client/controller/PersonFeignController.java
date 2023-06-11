/**
 * 
 */
package org.learntek.crud.client.controller;

import java.util.List;

import org.learntek.crud.client.PersonFeignClient;
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

/**
 * @author HP
 *
 */
@RestController
public class PersonFeignController {
	@Autowired
	private PersonFeignClient feignClient;
	
	@GetMapping("/person/v2/{id}")
	public ResponseEntity<?> getPersonV2(@PathVariable("id") int id){
		Person person = feignClient.getPersonById(id);
		ResponseEntity<?> responseEntity = null;
		if(person!=null) {
			responseEntity = new ResponseEntity<Person>(person, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/person/v2")
	public ResponseEntity<?> getAllPersonsV2(){
		List<Person> persons = feignClient.getAllPersons();
		ResponseEntity<?> responseEntity = null;
		if(!persons.isEmpty()) {
			responseEntity = new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping("/person/v2")
	public ResponseEntity<?> createPersonV2(@RequestBody Person person){
		ResponseMessage responseMessage =  feignClient.createPerson(person);
		ResponseEntity<?> responseEntity = null;
		if(responseMessage.getStatusCode().equals("201")) {
			responseEntity =  new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.CREATED);;
		}
		return responseEntity;
	}
	
	@PutMapping("/person/v2")
	public ResponseEntity<?> updatePersonV2(@RequestBody Person person){
		ResponseMessage responseMessage = feignClient.updatePerson(person);
		ResponseEntity<?> responseEntity = null;
		if(responseMessage.getStatusCode().equals("200")) {
			responseEntity =  new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);;
		}
		return responseEntity;
	}
	
	@DeleteMapping("/person/v2/{id}")
	public ResponseEntity<?> deletePersonV2(@PathVariable("id") int id){
		ResponseEntity<ResponseMessage> responseEntity = feignClient.deletePerson(id);
		return responseEntity;
	}
}
