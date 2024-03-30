package br.com.marcelo.restwithspringbootinjava.unittests.mapper.mocks;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.BookVO;
import br.com.marcelo.restwithspringbootinjava.model.Book;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAutor("Some Author" + number);
        book.setDataLancamento(LocalDate.of(1999, Month.APRIL,4));
        book.setPreco(25D);
        book.setTitulo("Some Title" + number);
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setId(number.longValue());
        book.setAutor("Some Author" + number);
        book.setDataLancamento(LocalDate.now());
        book.setPreco(25D);
        book.setTitulo("Some Title" + number);
        return book;
    }

}
