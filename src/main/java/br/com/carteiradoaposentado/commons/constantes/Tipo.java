package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum Tipo implements Constante<String> {

    /**
     * Fii
     */
    FUNDO_IMOBILIARIO( "Fundo Imobiliario", "FII" ),

    /**
     * Ação
     */
    ACAO( "Ação", "ACAO" ),

    /**
     * stocks
     */
    STOCKS( "STOCKS", "STOCKS" ),

    /**
     * Renda fixa
     */
    RENDA_FIXA( "Renda Fixa", "RENDA_FIXA" ),

    /**
     * Caixa
     */
    CAIXA( "Reserva de Oportunidade", "CAIXA" ),

    /**
     * ETF
     */
    ETF( "Exchange Traded Fund (ETF)", "ETF" ),

    /**
     * Fundo de Investimento
     */
    FUNDO_DE_INVESTIMENTO( "Fundo de Investimento", "FUNDO_DE_INVESTIMENTO" ),

    /**
     * Cripto Moeda
     */
    CRIPTO_MOEDA( "Cripto Moeda", "CRIPTO_MOEDA" ),

    /**
     * Tpb
     */
    TPB( "Tiro Porrada e Bomba", "TPB" )

    ;

    private final String descricao;
    private final String valor;

    Tipo(
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
