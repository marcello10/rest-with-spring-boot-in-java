package br.com.marcelo.restwithspringbootinjava.repository;

import br.com.marcelo.restwithspringbootinjava.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends JpaRepository<Person,Long> {

}
