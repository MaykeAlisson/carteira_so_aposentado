package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum Setor implements Constante<String> {

    /**
     * Tecnologia
     */
    TECNOLOGIA( "Tecnologia", "TECNOLOGIA" ),

    /**
     * Consumo
     */
    CONSUMO( "Consumo", "CONSUMO" ),

    /**
     * Consumo cíclico
     */
    CONSUMO_CICLICO( "Consumo cíclico", "CONSUMO_CICLICO" ),

    /**
     * Financeiro
     */
    FINANCEIRO( "Financeiro", "FINANCEIRO" ),

    /**
     * Saúde
     */
    SAUDE( "Saúde", "SAUDE" ),

    /**
     * Materiais Básicos
     */
    MATERIAIS_BASICOS( "Materiais Básicos", "MATERIAIS_BASICOS" ),

    /**
     * Utilidade pública
     */
    UTILIDADE_PUBLICA( "Utilidade pública", "UTILIDADE_PUBLICA" ),

    /**
     * Outros
     */
    OUTROS( "Outros", "OUTROS" )

    ;

    private final String descricao;
    private final String valor;

    Setor(
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
