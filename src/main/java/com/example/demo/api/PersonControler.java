package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonControler {

    private final PersonService personService;

    @Autowired
    public PersonControler(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonbyId(@PathVariable("id") UUID uuid) {
        return personService.getPersonbyId(uuid)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonbyId(@PathVariable("id") UUID uuid) {
        personService.deletePerson(uuid);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID uuid, @Valid @NonNull @RequestBody Person personUpdate) {
        personService.updatePerson(uuid, personUpdate);
    }
}
