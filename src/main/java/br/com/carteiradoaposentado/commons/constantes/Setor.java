package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum Setor implements Constante<Short> {

    /**
     * Tecnologia
     */
    TECNOLOGIA( "Tecnologia", (short) 1 ),

    /**
     * Consumo
     */
    CONSUMO( "Consumo", (short) 2 ),

    /**
     * Consumo cíclico
     */
    CONSUMO_CICLICO( "Consumo cíclico", (short) 3 ),

    /**
     * Financeiro
     */
    FINANCEIRO( "Financeiro", (short) 4 ),

    /**
     * Saúde
     */
    SAUDE( "Saúde", (short) 5 ),

    /**
     * Materiais Básicos
     */
    MATERIAIS_BASICOS( "Materiais Básicos", (short) 6 ),

    /**
     * Utilidade pública
     */
    UTILIDADE_PUBLICA( "Utilidade pública", (short) 7 ),

    /**
     * Shoppings
     */
    FII_SHOPPINGS( "FII Shoppings", (short) 8 ),

    /**
     * Lajes Corporativas
     */
    FII_LAJES_CORPORATIVAS( "FII Lajes Corporativas", (short) 9 ),

    /**
     * Galpões Industriais
     */
    FII_GALPOES( "FII Galpões Industriais", (short) 10 ),

    /**
     * Hotéis
     */
    FII_HOTEIS( "FII Hotéis", (short) 11 ),

    /**
     * Fundos
     */
    FII_FUNDOS( "FII Fundos", (short) 12 ),

    /**
     * Papel
     */
    FII_PAPEL( "FII Papel", (short) 13 ),

    /**
     * Outros
     */
    OUTROS( "Outros", (short) 14 )

    ;

    private final String descricao;
    private final Short valor;

    Setor(
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
