package br.com.carteiradoaposentado.repository;

import br.com.carteiradoaposentado.domain.Carteira;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CarteiraRepository extends MongoRepository<Carteira, String> {

    @Query("{'idUsuario' : ?0}")
    Carteira buscarPorIdUsuario(String idUser);
}
