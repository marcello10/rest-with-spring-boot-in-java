package br.com.marcelo.restwithspringbootinjava.services;

import br.com.marcelo.restwithspringbootinjava.controllers.PersonController;
import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.exceptions.RequiredObjectIsNullException;
import br.com.marcelo.restwithspringbootinjava.exceptions.ResourceNotFoundException;
import br.com.marcelo.restwithspringbootinjava.mapper.PersonMapper;
import br.com.marcelo.restwithspringbootinjava.model.Person;
import br.com.marcelo.restwithspringbootinjava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;
    PersonMapper personMapper = PersonMapper.INSTANCE;
    private Logger logger =Logger.getLogger(PersonServices.class.getName());
    public PersonVO findById(Long id) throws Exception {
        logger.info("Finding one person!");

        var entity =  repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        var vo = personMapper.personToPersonVO(entity);
        //var vo = personMapper.personToPersonVO(entity);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }
    public List<PersonVO>  findByAll(){
        var listaPerson = repository.findAll();
        List<PersonVO> personVOList = new ArrayList<>();
        listaPerson.forEach(person -> {
            PersonVO personVO = personMapper.personToPersonVO(person);
            try {
                personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            personVOList.add(personVO);
        });
        return personVOList;
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
    public PersonVO create(PersonVO personVO) throws Exception {
        if(personVO == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one person!");
        var save = repository.save(personMapper.personVoToPerson(personVO));
        var vo = personMapper.personToPersonVO(save);
        //var vo = personMapper.personToPersonVO(save);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public PersonVO update(PersonVO personVO) throws Exception {
        if(personVO == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one person!");
        Person person = personMapper.personVoToPerson(personVO);
        var entity = repository.findById(person.getId())
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var save  =repository.save(entity);
        var vo = personMapper.personToPersonVO(save);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
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
