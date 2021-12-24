package br.com.carteiradoaposentado.repository;

import br.com.carteiradoaposentado.domain.Lancamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LancamentoRepository extends MongoRepository<Lancamento, String> {

    @Query("{'idUser' : ?0}")
    Set<Lancamento> findAll(String idUser);

    @Query("{'idUser' : ?0, '_id': ?1}")
    Optional<Lancamento> buscarPorId(String idUser, String id);
}
