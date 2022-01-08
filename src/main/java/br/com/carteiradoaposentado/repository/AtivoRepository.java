package br.com.carteiradoaposentado.repository;

import br.com.carteiradoaposentado.domain.Ativo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AtivoRepository extends MongoRepository<Ativo, String> {

    @Query("{'idUser' : ?0, '_id': ?1}")
    Optional<Ativo> buscarPorId(String idUser, String idAtivo);

    @Query("{'idUser' : ?0}")
    Set<Ativo> buscarPorUsuario(String idUser);

    @Query("{'idUser' : ?0, 'nome': ?1}")
    Optional<Ativo> buscarPorName(String idUser, String nome);
}
