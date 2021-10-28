package br.com.carteiradoaposentado.repository;

import br.com.carteiradoaposentado.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    

}
