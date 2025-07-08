/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 08/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package com.me.learning.consul.personservice.controller;

import com.me.learning.consul.personservice.dto.PersonRequest;
import com.me.learning.consul.personservice.dto.PersonResponse;
import com.me.learning.consul.personservice.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/person")
@Slf4j
public class PersonController {

    private final PersonService personService;

    public PersonController (PersonService personService) {
        this.personService = personService;
    }

    @GetMapping ("/{personId}")
    public ResponseEntity<PersonResponse> getPersonByPersonId(@PathVariable Long personId){
        log.info ("Fetching person with ID: {}", personId);
        return ResponseEntity.ok (personService.getPersonByPersonId (personId));
    }

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(PersonRequest personRequest){
        log.info ("Creating person with request: {}", personRequest);
        return ResponseEntity.ok (personService.createPerson (personRequest));
    }


    @GetMapping("/all")
    public ResponseEntity<List<PersonResponse>> getAllPersons(){
        log.info ("Fetching all persons");
        return ResponseEntity.ok(personService.getAllPersons());
    }
}
