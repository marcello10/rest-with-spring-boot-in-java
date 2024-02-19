package br.com.marcelo.restwithspringbootinjava.controllers;

import br.com.marcelo.restwithspringbootinjava.exceptions.UnsupportedMathOperatioExcepition;
import br.com.marcelo.restwithspringbootinjava.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static br.com.marcelo.restwithspringbootinjava.converters.NumberConverter.convertToDouble;
import static br.com.marcelo.restwithspringbootinjava.converters.NumberConverter.isNumeric;

@RestController
public class PersonController {
    private final AtomicLong counter = new AtomicLong();
    private SimpleMath math  = new SimpleMath();
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
    method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo)
    throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
        {
            throw new UnsupportedMathOperatioExcepition("Please set a numeric value!");
        }
        return math.sum(convertToDouble(numberOne),convertToDouble(numberTwo));
    }
    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double sub(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo)
            throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
        {
            throw new UnsupportedMathOperatioExcepition("Please set a numeric value!");
        }
        return math.sub(convertToDouble(numberOne),convertToDouble(numberTwo));
    }
    @RequestMapping(value = "/multi/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double multi(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo)
            throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
        {
            throw new UnsupportedMathOperatioExcepition("Please set a numeric value!");
        }
        return math.multi(convertToDouble(numberOne),convertToDouble(numberTwo));
    }
    @RequestMapping(value = "/div/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double div(@PathVariable(value = "numberOne") String numberOne,
                        @PathVariable(value = "numberTwo") String numberTwo)
            throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
        {
            throw new UnsupportedMathOperatioExcepition("Please set a numeric value!");
        }
        return math.div(convertToDouble(numberOne),convertToDouble(numberTwo));
    }
    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double mean(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo)
            throws Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
        {
            throw new UnsupportedMathOperatioExcepition("Please set a numeric value!");
        }
        return math.mean(convertToDouble(numberOne),convertToDouble(numberTwo));
    }
    @RequestMapping(value = "/sqrt/{numberOne}",
            method = RequestMethod.GET)
    public Double sqrt(@PathVariable(value = "numberOne") String numberOne)
            throws Exception{
        if(!isNumeric(numberOne))
        {
            throw new UnsupportedMathOperatioExcepition("Please set a numeric value!");
        }
        Double a = convertToDouble(numberOne);
        if(a<0)
            throw new UnsupportedMathOperatioExcepition("Please set a numeric value non negative!");
        return math.sqrt(a);
    }


}
