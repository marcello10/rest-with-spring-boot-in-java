package br.com.marcelo.restwithspringbootinjava.services;

import br.com.marcelo.restwithspringbootinjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository repository;

    private Logger logger =Logger.getLogger(UserServices.class.getName());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one person by name "+username);
        var user = repository.findByUserName(username);
        if (user!=null){
            return user;
        }
        throw  new UsernameNotFoundException("Username "+username+" not found!");

    }
}
