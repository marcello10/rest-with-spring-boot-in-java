package br.com.marcelo.restwithspringbootinjava.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.model.Person;

import java.util.List;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    @Mapping(source = "key", target = "id")
    Person personVoToPerson(PersonVO personVO);
    @Mapping(source = "id", target = "key")
    PersonVO personToPersonVO(Person person);

}
