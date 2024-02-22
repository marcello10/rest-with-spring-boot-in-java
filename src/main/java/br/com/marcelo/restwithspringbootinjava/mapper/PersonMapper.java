package br.com.marcelo.restwithspringbootinjava.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.marcelo.restwithspringbootinjava.data.vo.v1.PersonVO;
import br.com.marcelo.restwithspringbootinjava.model.Person;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(source = "key", target = "id")
    Person personVoToPerson(PersonVO personVO);
    @Mapping(source = "id", target = "key")
    PersonVO personToPersonVO(Person person);

}
