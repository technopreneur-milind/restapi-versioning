package com.example.demo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ra/")

public class RaController {

	@RequestMapping({ "/**/{version:2\\.\\d+}/person/{id}", "/**/latest/person/{id}", "/**/{version:2}/person/{id}",
			"/person/{id}" })
	public ResponseEntity<Person> getPerson2(@PathVariable Integer id) {
		Person person = id == 1 ? new Person(id, "Scott2") : new Person(id, "Tiger2");
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping({ "/**/{version:2\\.\\d+}/person", "/**/{version:2}/person", "/**/latest/person", "/person" })
	public ResponseEntity<List<Person>> getAllPerson2() {
		Person person1 = new Person(1, "Scott2");
		Person person2 = new Person(2, "Tiger2");
		List<Person> personList = List.of(person1, person2);
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	@RequestMapping({ "/**/{version:1\\.\\d+}/person/{id}" })
	public ResponseEntity<Person> getPerson1(@PathVariable Integer id) {
		Person person = id == 1 ? new Person(id, "Scott1") : new Person(id, "Tiger1");
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping({ "/**/{version:1\\.\\d+}/person" })
	public ResponseEntity<List<Person>> getAllPerson1() {
		Person person1 = new Person(1, "Scott1");
		Person person2 = new Person(2, "Tiger1");
		List<Person> personList = List.of(person1, person2);
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	// Accept=application/auto1.company.app-v1+json in Postman header
	// Default also since first Match
	@RequestMapping(value = "/cn/person", produces = "application/auto1.company.app-v1+json", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPerson3() {
		Person person1 = new Person(1, "Scott3");
		Person person2 = new Person(2, "Tiger3");
		List<Person> personList = List.of(person1, person2);
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	// Accept=application/auto1.company.app-v2+json in Postman header
	@RequestMapping(value = "/cn/person", produces = "application/auto1.company.app-v2+json", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPerson4() {
		Person person1 = new Person(1, "Scott4");
		Person person2 = new Person(2, "Tiger4");
		List<Person> personList = List.of(person1, person2);
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	// AUTO1-API-VERSION=1 in Postman header
	// NO DEFAULT, Header is a MUST
	@RequestMapping(value = "/header/person", headers = "AUTO1-API-VERSION=1", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPerson5() {
		Person person1 = new Person(1, "Scott5");
		Person person2 = new Person(2, "Tiger5");
		List<Person> personList = List.of(person1, person2);
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	// AUTO1-API-VERSION=2 in Postman header
		// NO DEFAULT, Header is a MUST
	@RequestMapping(value = "/header/person", headers = "AUTO1-API-VERSION=2", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPerson6() {
		Person person1 = new Person(1, "Scott6");
		Person person2 = new Person(2, "Tiger6");
		List<Person> personList = List.of(person1, person2);
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	/*
	 * static class AppMediaType { //Make it Singleton
	 * 
	 * public static MediaType getVersionedMediaType(MediaType mediaType, String
	 * version) { String type = mediaType.getType(); String[] split =
	 * type.split("/"); return new
	 * MediaType(split[0]+"/"+"Auto1.company.app-"+version+"+"+split[1]); }
	 * 
	 * 
	 * 
	 * }
	 */

}
