package br.com.marcelo.restwithspringbootinjava.services;

import br.com.marcelo.restwithspringbootinjava.controllers.BookController;
import br.com.marcelo.restwithspringbootinjava.data.vo.v1.BookVO;
import br.com.marcelo.restwithspringbootinjava.exceptions.RequiredObjectIsNullException;
import br.com.marcelo.restwithspringbootinjava.exceptions.ResourceNotFoundException;
import br.com.marcelo.restwithspringbootinjava.mapper.BookMapper;
import br.com.marcelo.restwithspringbootinjava.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {

    @Autowired
    BookRepository repository;
    BookMapper bookMapper = BookMapper.INSTANCE;
    private Logger logger =Logger.getLogger(BookServices.class.getName());
    public BookVO findById(Long id) throws Exception {
        logger.info("Finding one book!");

        var entity =  repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        var vo = bookMapper.bookToBookVO(entity);
        //var vo = personMapper.personToPersonVO(entity);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }
    public List<BookVO>  findByAll(){
        logger.info("Finding all books!");
        var listaBook = repository.findAll();
        List<BookVO> booksVOList = new ArrayList<>();
        listaBook.forEach(book -> {
            BookVO bookVO = bookMapper.bookToBookVO(book);
            try {
                bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getId())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            booksVOList.add(bookVO);
        });
        return booksVOList;
    }
    public BookVO create(BookVO bookVO) throws Exception {
        if(bookVO == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one book!");
        var book = bookMapper.bookVOToBook(bookVO);
        var save = repository.save(book);
        var vo = bookMapper.bookToBookVO(save);
        //var vo = personMapper.personToPersonVO(save);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }
    public BookVO update(BookVO bookVO) throws Exception {
        if( bookVO== null) throw new RequiredObjectIsNullException();
        logger.info("Updating one book!");
        var book = bookMapper.bookVOToBook(bookVO);
        var entity = repository.findById(book.getId())
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        entity.setAutor(book.getAutor());
        entity.setDataLancamento(book.getDataLancamento());
        entity.setPreco(book.getPreco());
        entity.setTitulo(book.getTitulo());
        var save  =repository.save(entity);
        var vo = bookMapper.bookToBookVO(save);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }
    public void delete(Long id) {
        logger.info("Deleting one book!");
        var entity = repository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("No records found for this ID!")
                );
        repository.delete(entity);
    }

}
