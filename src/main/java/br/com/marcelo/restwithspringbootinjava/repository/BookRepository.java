package br.com.marcelo.restwithspringbootinjava.repository;

import br.com.marcelo.restwithspringbootinjava.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
