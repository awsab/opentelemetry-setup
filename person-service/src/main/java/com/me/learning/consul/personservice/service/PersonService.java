/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 08/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package com.me.learning.consul.personservice.service;

import com.me.learning.consul.personservice.dto.PersonRequest;
import com.me.learning.consul.personservice.dto.PersonResponse;
import com.me.learning.consul.personservice.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    private static final List<Person> personList = List.of (
            new Person (1L, "John", "Doe", "john.doe@doe.com", "1234567890", "123 Main St", "New York", "USA", 25),
            new Person (2L, "Jane", "Smith", "jane.smith@email.com", "2345678901", "456 Oak Ave", "Los Angeles", "USA", 30),
            new Person (3L, "Alice", "Johnson", "alice.johnson@email.com", "3456789012", "789 Pine Rd", "Chicago", "USA", 28),
            new Person (4L, "Bob", "Williams", "bob.williams@email.com", "4567890123", "321 Maple St", "Houston", "USA", 35),
            new Person (5L, "Charlie", "Brown", "charlie.brown@email.com", "5678901234", "654 Cedar Ave", "Phoenix", "USA", 22),
            new Person (6L, "Diana", "Miller", "diana.miller@email.com", "6789012345", "987 Birch Rd", "Philadelphia", "USA", 27),
            new Person (7L, "Ethan", "Davis", "ethan.davis@email.com", "7890123456", "159 Spruce St", "San Antonio", "USA", 31),
            new Person (8L, "Fiona", "Garcia", "fiona.garcia@email.com", "8901234567", "753 Willow Ave", "San Diego", "USA", 29),
            new Person (9L, "George", "Martinez", "george.martinez@email.com", "9012345678", "852 Aspen Rd", "Dallas", "USA", 33),
            new Person (10L, "Hannah", "Lopez", "hannah.lopez@email.com", "0123456789", "951 Elm St", "San Jose", "USA", 26)
    );


    public PersonResponse getPersonByPersonId (Long personId) {
        log.info ("Fetching person with ID: {}", personId);
        return personList.stream ()
                .filter (person -> person.getPersonId ().equals (personId))
                .findFirst ()
                .map (person -> new PersonResponse (
                        person.getPersonId (),
                        person.getFirstName (),
                        person.getLastName (),
                        person.getEmail (),
                        person.getPhoneNumber (),
                        person.getAddress (),
                        person.getCity (),
                        person.getCountry (),
                        person.getAge ()
                ))
                .orElseThrow (() -> new RuntimeException ("Person not found with ID: " + personId));
    }

    public PersonResponse createPerson (PersonRequest personRequest) {
        log.info ("Creating person with request: {}", personRequest);
        Long newPersonId = personList.stream ()
                .mapToLong (Person::getPersonId)
                .max ()
                .orElse (0L) + 1;

        Person newPerson = new Person (
                newPersonId,
                personRequest.getFirstName (),
                personRequest.getLastName (),
                personRequest.getEmail (),
                personRequest.getPhoneNumber (),
                personRequest.getAddress (),
                personRequest.getCity (),
                personRequest.getCountry (),
                personRequest.getAge ()
        );

        personList.add (newPerson);

        return new PersonResponse (
                newPerson.getPersonId (),
                newPerson.getFirstName (),
                newPerson.getLastName (),
                newPerson.getEmail (),
                newPerson.getPhoneNumber (),
                newPerson.getAddress (),
                newPerson.getCity (),
                newPerson.getCountry (),
                newPerson.getAge ()
        );
    }


    public List<PersonResponse> getAllPersons () {
        log.info ("Fetching all persons");
        return personList.stream ()
                .map (person -> new PersonResponse (
                        person.getPersonId (),
                        person.getFirstName (),
                        person.getLastName (),
                        person.getEmail (),
                        person.getPhoneNumber (),
                        person.getAddress (),
                        person.getCity (),
                        person.getCountry (),
                        person.getAge ()
                ))
                .toList ();
    }
}
