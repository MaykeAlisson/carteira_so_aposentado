package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    @Autowired
    private AtivoRepository ativoRepository;

//    public Carteira buscarCarteira(final String idUser){
//
//    }

    public void consolidarCarteira(final String idUser){
        // buscar todos ativos do usuario (tipo, categoria, qtd, valor)

    }
}
