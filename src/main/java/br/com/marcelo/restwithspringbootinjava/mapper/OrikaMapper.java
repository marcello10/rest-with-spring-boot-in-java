package br.com.marcelo.restwithspringbootinjava.mapper;


import java.util.ArrayList;
import java.util.List;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaMapper {
    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    public static <O,D> D parseObject(O origin, Class<D> destination){
        return mapperFactory.getMapperFacade().map(origin, destination);
    }
    public static <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destList = new ArrayList<D>();
        for (O o : origin) {
            destList.add(mapperFactory.getMapperFacade().map(o, destination));
        }
        return destList;
    }
}
