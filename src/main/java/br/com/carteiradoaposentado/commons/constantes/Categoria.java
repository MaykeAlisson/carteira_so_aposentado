package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum Categoria implements Constante<String> {

    /**
     * Caixa
     */
    CAIXA( "Reserva de Oportunidade", "CAIXA" ),

    /**
     * By Road
     */
    BY_ROAD( "By Road", "BY_ROAD" ),

    /**
     * Tpb
     */
    TPB( "Tiro Porrada e Bomba", "TPB" )

    ;

    private final String descricao;
    private final String valor;

    Categoria(
            final String descricao,
            final String valor
    ) {
        this.descricao = descricao;
        this.valor = valor;
    }


    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public String getValor() {
        return this.valor;
    }
}
