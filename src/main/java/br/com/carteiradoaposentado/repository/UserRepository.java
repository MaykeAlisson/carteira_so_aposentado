package br.com.carteiradoaposentado.repository;

import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'idUser' : ?0}")
    Optional<User> buscarPorEmail(final String email);

}
