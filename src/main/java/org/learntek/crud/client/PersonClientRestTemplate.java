/**
 * 
 */
/*
 * package org.learntek.crud.client;
 * 
 * import java.util.List;
 * 
 * import org.learntek.crud.client.model.Person; import
 * org.learntek.crud.client.model.ResponseMessage; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.client.RestTemplate; import
 * org.springframework.web.reactive.function.client.ClientResponse; import
 * org.springframework.web.reactive.function.client.WebClient; import
 * org.springframework.web.reactive.function.client.WebClientResponseException;
 * import
 * org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
 * 
 * import reactor.core.publisher.Mono;
 * 
 *//**
	 * @author HP
	 *
	 *//*
		 * @RestController public class PersonClientRestTemplate {
		 * 
		 * @Autowired private RestTemplate restTemplate;
		 * 
		 * @Autowired private WebClient webClient;
		 * 
		 * @Autowired private PersonFeignClient personClient;
		 * 
		 * @GetMapping("/person/{id}") public ResponseEntity<?>
		 * getPerson(@PathVariable("id") int id) { ResponseEntity<Person> responseEntity
		 * = restTemplate.getForEntity("http://localhost:8090/person/{id}",
		 * Person.class, id); if (responseEntity.getStatusCode() == HttpStatus.OK) {
		 * return responseEntity; } else { ResponseMessage responseMessage = new
		 * ResponseMessage(); responseMessage.setStatusCode("400");
		 * responseMessage.setStatusMessage("Record not found in DB..........."); return
		 * new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
		 * } }
		 * 
		 * @GetMapping("/person") public ResponseEntity<List<Person>> getAllPersons() {
		 * List<Person> persons =
		 * restTemplate.getForObject("http://localhost:8090/person", List.class);
		 * ResponseEntity<List<Person>> responseEntity = new
		 * ResponseEntity<List<Person>>(persons, HttpStatus.OK); return responseEntity;
		 * }
		 * 
		 * @PostMapping("/person") public ResponseEntity<ResponseMessage>
		 * createPerson(@RequestBody Person person) { ResponseEntity<ResponseMessage>
		 * responseEntity = restTemplate.postForEntity("http://localhost:8090/person",
		 * person, ResponseMessage.class); return responseEntity; }
		 * 
		 * @PutMapping("/person") public ResponseEntity<ResponseMessage>
		 * updatePerson(@RequestBody Person person) { //
		 * restTemplate.put("http://localhost:8090/person", HttpMethod.PUT, person, //
		 * ResponseMessage.class); HttpEntity<Person> requestEntity = new
		 * HttpEntity<Person>(person); return
		 * restTemplate.exchange("http://localhost:8090/person", HttpMethod.PUT,
		 * requestEntity, ResponseMessage.class); }
		 * 
		 * @DeleteMapping("/persion/{id}") public void deletePerson(@PathVariable("id")
		 * int id) { restTemplate.delete("http://localhost:8090/person/{id}", id); }
		 * 
		 * // Using WebClient
		 * 
		 * @GetMapping("/person/v1/{id}") public ResponseEntity<?>
		 * getPersonV1(@PathVariable("id") int id) { ClientResponse clientResponse =
		 * webClient.get().uri("/person/" + id).exchange().block();
		 * System.out.println("response code : " +
		 * clientResponse.statusCode().toString()); if
		 * (clientResponse.statusCode().is2xxSuccessful()) { ResponseEntity<Person>
		 * responseEntity = new ResponseEntity<Person>(
		 * clientResponse.bodyToMono(Person.class).block(), HttpStatus.OK); return
		 * responseEntity; } else { ResponseMessage responseMessage = new
		 * ResponseMessage(); responseMessage.setStatusCode("400");
		 * responseMessage.setStatusMessage("Record not found in DB..........."); return
		 * new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
		 * } }
		 * 
		 * @GetMapping("/person/v1") public ResponseEntity<?> getAllPersonsV1() {
		 * ClientResponse clientResponse =
		 * webClient.get().uri("/person").exchange().block();
		 * ResponseEntity<List<Person>> responseEntity = null; if
		 * (clientResponse.statusCode().is2xxSuccessful()) { responseEntity = new
		 * ResponseEntity<List<Person>>(clientResponse.bodyToMono(List.class).block(),
		 * HttpStatus.OK); } return responseEntity; }
		 * 
		 * @PostMapping("/person/v1") public ResponseEntity<?>
		 * createPersonv1(@RequestBody Person person) { ClientResponse clientResponse =
		 * webClient.post().uri("/person").body(Mono.just(person),
		 * Person.class).exchange() .block(); ResponseEntity<ResponseMessage>
		 * responseEntity = null; if (clientResponse.statusCode().is2xxSuccessful()) {
		 * ResponseMessage responseMessage = new ResponseMessage();
		 * responseMessage.setStatusCode("201"); responseMessage.
		 * setStatusMessage("Person Created Successfully in DB...........");
		 * responseEntity = new ResponseEntity<ResponseMessage>(responseMessage,
		 * HttpStatus.CREATED); } return responseEntity; }
		 * 
		 * @PutMapping("/person/v1") public ResponseEntity<?>
		 * updatePersonV1(@RequestBody Person person){ ClientResponse clientResponse =
		 * webClient.put().uri("/person").body(Mono.just(person),
		 * Person.class).exchange().block(); ResponseEntity<ResponseMessage>
		 * responseEntity = null; if(clientResponse.statusCode().is2xxSuccessful()) {
		 * ResponseMessage responseMessage = new ResponseMessage();
		 * responseMessage.setStatusCode("201"); responseMessage.
		 * setStatusMessage("Person Updated Successfully in DB...........");
		 * responseEntity = new ResponseEntity<ResponseMessage>(responseMessage,
		 * HttpStatus.CREATED); } return responseEntity; }
		 * 
		 * @DeleteMapping("/person/v1/{id}") public ResponseEntity<?>
		 * deletePersonV1(@PathVariable("id") int id){ ClientResponse clientResponse =
		 * webClient.delete().uri("/person/"+id).exchange().block();
		 * ResponseEntity<ResponseMessage> responseEntity = null;
		 * if(clientResponse.statusCode().is2xxSuccessful()) { responseEntity = new
		 * ResponseEntity<ResponseMessage>(HttpStatus.NO_CONTENT); } return
		 * responseEntity; }
		 * 
		 * @GetMapping("/person/v2/{id}") public ResponseEntity<?>
		 * getPersonV2(@PathVariable("id") int id) { Person person =
		 * personClient.getPersonById(id); if (person!=null) { ResponseEntity<Person>
		 * responseEntity = new ResponseEntity<Person>(person, HttpStatus.OK); return
		 * responseEntity; } else { ResponseMessage responseMessage = new
		 * ResponseMessage(); responseMessage.setStatusCode("400");
		 * responseMessage.setStatusMessage("Record not found in DB..........."); return
		 * new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
		 * } }
		 * 
		 * @GetMapping("/person/v2") public ResponseEntity<?> getAllPersonsV2() {
		 * List<Person> persons = personClient.getAllPersons();
		 * ResponseEntity<List<Person>> responseEntity = null; if (!persons.isEmpty()) {
		 * responseEntity = new ResponseEntity<List<Person>>(persons, HttpStatus.OK); }
		 * return responseEntity; }
		 * 
		 * @PostMapping("/person/v2") public ResponseEntity<?>
		 * createPersonv2(@RequestBody Person person) { ResponseMessage responseMessage
		 * = personClient.createPerson(person); ResponseEntity<ResponseMessage>
		 * responseEntity = null; if (responseMessage.getStatusCode().equals("201")) {
		 * responseEntity = new ResponseEntity<ResponseMessage>(responseMessage,
		 * HttpStatus.CREATED); } return responseEntity; }
		 * 
		 * @PutMapping("/person/v2") public ResponseEntity<?>
		 * updatePersonV2(@RequestBody Person person){ ResponseMessage responseMessage =
		 * personClient.updatePerson(person); ResponseEntity<ResponseMessage>
		 * responseEntity = null; if(responseMessage.getStatusCode().equals("200")) {
		 * responseEntity = new ResponseEntity<ResponseMessage>(responseMessage,
		 * HttpStatus.CREATED); } return responseEntity; }
		 * 
		 * 
		 * @DeleteMapping("/person/v2/{id}") public ResponseEntity<?>
		 * deletePersonV2(@PathVariable("id") int id){ ResponseEntity<?> responseEntity
		 * = personClient.deletePerson(id);
		 * if(responseEntity.getStatusCode().is2xxSuccessful()) { responseEntity = new
		 * ResponseEntity<ResponseMessage>(HttpStatus.NO_CONTENT); } return
		 * responseEntity; } }
		 */