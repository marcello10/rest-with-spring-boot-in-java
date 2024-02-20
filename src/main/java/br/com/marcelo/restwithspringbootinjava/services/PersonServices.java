package br.com.marcelo.restwithspringbootinjava.services;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.exceptions.ResourceNotFoundException;
import br.com.marcelo.restwithspringbootinjava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;
    private Logger logger =Logger.getLogger(PersonServices.class.getName());
    public PersonVO findById(Long id){
        logger.info("Finding one person!");

        PersonVO person = new PersonVO();

        return repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
    }
    public List<PersonVO>  findByAll(){
        return repository.findAll();
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
    public PersonVO create(PersonVO person) {
        logger.info("Creating one person!");
        return repository.save(person);
    }
    public PersonVO update(PersonVO person) {
        logger.info("Updating one person!");
        var entity = repository.findById(person.getId())
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(entity);
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
