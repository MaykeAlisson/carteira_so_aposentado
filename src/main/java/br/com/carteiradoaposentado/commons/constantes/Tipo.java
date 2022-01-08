package br.com.carteiradoaposentado.commons.constantes;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public enum Tipo implements Constante<Short> {

    /**
     * Fii
     */
    FII("FII", (short) 1),

    /**
     * Ação
     */
    ACAO("Ação", (short) 2),

    /**
     * stocks
     */
    STOCKS("STOCKS", (short) 3),

    /**
     * Renda fixa
     */
    RENDA_FIXA("Renda Fixa", (short) 4),

    /**
     * ETF
     */
    ETF("Exchange Traded Fund (ETF)", (short) 6),

    /**
     * Fundo de Investimento
     */
    FUNDO_DE_INVESTIMENTO("Fundo de Investimento", (short) 7),

    /**
     * Cripto Moeda
     */
    CRIPTO("Cripto Moeda", (short) 8);

    private final String descricao;
    private final Short valor;

    Tipo(
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
