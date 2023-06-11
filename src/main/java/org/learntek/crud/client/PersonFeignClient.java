/**
 * 
 */
package org.learntek.crud.client;

import java.util.List;

import org.learntek.crud.client.model.Person;
import org.learntek.crud.client.model.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author HP
 *
 */
@FeignClient(name="person-service", url = "http://localhost:8090")
public interface PersonFeignClient {
	@GetMapping("/person/{id}")
	public Person getPersonById(@PathVariable("id") int id);
	
	@GetMapping("/person")
	public List<Person> getAllPersons();
	
	@PostMapping("/person")
	public ResponseMessage createPerson(@RequestBody Person person);
	
	@PutMapping("/person")
	public ResponseMessage updatePerson(@RequestBody Person person);
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<ResponseMessage> deletePerson(@PathVariable("id") int id);
}
