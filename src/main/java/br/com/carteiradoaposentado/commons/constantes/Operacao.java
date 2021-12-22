package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum Operacao implements Constante<String> {

    /**
     * Compra
     */
    COMPRA("Compra", "Compra"),

    /**
     * Venda
     */
    VENDA("Venda", "Venda");

    private final String descricao;
    private final String valor;

    Operacao(
            final String descricao,
            final String valor
    ){
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
