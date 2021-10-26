package br.com.carteiradoaposentado.repository;

import br.com.carteiradoaposentado.domain.Ativo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AtivoRepository extends MongoRepository<Ativo, String> {

    @Query("{'idUser' : ?0}")
    Set<Ativo> buscarPorUsuario(final String idUser);
}
