package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum CDA implements Constante<Integer> {

    /**
     * Qtd Parans Token
     */
    QTD_PARANS_TOKEN("Qtd Parans Token", 3),

    /**
     * Valor sub string token
     */
    SUB_STRING_TOKEN("Valor sub string token", 7);

    private final String descricao;
    private final Integer valor;

    CDA(
            final String descricao,
            final Integer valor
    ) {
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public Integer getValor() {
        return this.valor;
    }
}
