package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum Categoria implements Constante<Short> {

    /**
     * Caixa
     */
    CAIXA( "Reserva de Oportunidade", (short) 1 ),

    /**
     * By Road
     */
    BY_ROAD( "By Road", (short) 2 ),

    /**
     * Tpb
     */
    TPB( "Tiro Porrada e Bomba", (short) 3 )

    ;

    private final String descricao;
    private final Short valor;

    Categoria(
            final String descricao,
            final Short valor
    ) {
        this.descricao = descricao;
        this.valor = valor;
    }


    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public Short getValor() {
        return this.valor;
    }
}
