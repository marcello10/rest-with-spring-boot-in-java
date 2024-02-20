package br.com.marcelo.restwithspringbootinjava.services;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.exceptions.ResourceNotFoundException;
import br.com.marcelo.restwithspringbootinjava.mapper.OrikaMapper;
import br.com.marcelo.restwithspringbootinjava.mapper.PersonMapper;
import br.com.marcelo.restwithspringbootinjava.model.Person;
import br.com.marcelo.restwithspringbootinjava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;
    private Logger logger =Logger.getLogger(PersonServices.class.getName());
    public Person findById(Long id){
        logger.info("Finding one person!");

        var entity =  repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        
        return  entity;
    }
    public List<Person>  findByAll(){

        var listaPerson = repository.findAll();
        return listaPerson;
    }

    private PersonVO mockPerson(int i) {
        PersonVO person = new PersonVO();
        //person.setId(counter.incrementAndGet());
        person.setFirstName("Person name "+i);
        person.setLastName("Last name "+i);
        person.setAddress("Teresina");
        person.setGender("Male");
        return person;
    }
    public Person create(Person person) {
        logger.info("Creating one person!");
        var save = repository.save(person);
        return save;
    }
    public Person update(Person person) {
        logger.info("Updating one person!");
        var entity = repository.findById(person.getId())
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return  entity;
    }
    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        repository.delete(entity);
    }

}
