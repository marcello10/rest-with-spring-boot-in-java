package br.com.marcelo.restwithspringbootinjava.mapper;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.BookVO;
import br.com.marcelo.restwithspringbootinjava.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book bookVOToBook(BookVO bookVO);
    BookVO bookToBookVO(Book book);

}
