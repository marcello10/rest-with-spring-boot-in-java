package br.com.marcelo.restwithspringbootinjava.repository;

import br.com.marcelo.restwithspringbootinjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User WHERE u.userName = :userName")
    User findByUserName(@Param("userName") String userName);


}
