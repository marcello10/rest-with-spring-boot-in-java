package br.com.marcelo.restwithspringbootinjava.controllers;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;
    private final AtomicLong counter = new AtomicLong();
    @GetMapping(value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception{
        return services.findById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll() {
        return services.findByAll();
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person) throws Exception{
        return services.create(person);
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person) throws Exception{
        return services.update(person);
    }
    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
         services.delete(id);
         return ResponseEntity.noContent().build();
    }

}
