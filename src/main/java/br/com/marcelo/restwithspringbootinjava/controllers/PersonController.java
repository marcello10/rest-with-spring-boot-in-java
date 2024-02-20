package br.com.marcelo.restwithspringbootinjava.controllers;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.model.Person;
import br.com.marcelo.restwithspringbootinjava.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;
    @GetMapping(value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") Long id) throws Exception{
        return services.findById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return services.findByAll();
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) throws Exception{
        return services.create(person);
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) throws Exception{
        return services.update(person);
    }
    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}
