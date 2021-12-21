package br.com.carteiradoaposentado.domain;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.json.CategoriaAtivoSerializer;
import br.com.carteiradoaposentado.commons.json.SetorAtivoSerializer;
import br.com.carteiradoaposentado.commons.json.TipoAtivoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "ativo")
public class Ativo implements Serializable {

    @Id
    private String id;
    private String nome;
    private String idUser;
    private Tipo tipo;
    private Categoria categoria;
    private Setor setor;
    private Long qtd;
    private BigDecimal valor;
    private Long porcentagem;
    private String observacao;
    private LocalDateTime criacao;
    private Set<Fundamento> analise;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Ativo(
            final String nome,
            final String idUser,
            final Tipo tipo,
            final Categoria categoria,
            final Setor setor,
            final Long qtd,
            final BigDecimal valor,
            final Long porcentagem,
            final String observacao,
            final LocalDateTime criacao,
            final Set<Fundamento> analise
    ) {
        this.nome = nome;
        this.idUser = idUser;
        this.tipo = tipo;
        this.categoria = categoria;
        this.setor = setor;
        this.qtd = qtd;
        this.valor = valor;
        this.porcentagem = porcentagem;
        this.observacao = observacao;
        this.criacao = criacao;
        this.analise = analise;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS / SETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @JsonSerialize(using = TipoAtivoSerializer.class)
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @JsonSerialize(using = CategoriaAtivoSerializer.class)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @JsonSerialize(using = SetorAtivoSerializer.class)
    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Long getQtd() {
        return qtd;
    }

    public void setQtd(Long qtd) {
        this.qtd = qtd;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Long porcentagem) {
        this.porcentagem = porcentagem;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDateTime criacao) {
        this.criacao = criacao;
    }

    public Set<Fundamento> getAnalise() {
        return analise;
    }

    public void setAnalise(Set<Fundamento> analise) {
        this.analise = analise;
    }

    public BigDecimal getValorTotal(){
        return getValor().multiply( new BigDecimal(getQtd()));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EQUALS & HASCODE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ativo ativo = (Ativo) o;
        return Objects.equals(id, ativo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MÉTODOS AUXILIARES
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateAnalises(final Fundamento fundamento) {
        this.getAnalise().removeIf(fund -> Objects.equals(fund.getMes(), fundamento.getMes()));
        this.getAnalise().add(fundamento);
    }

    public static Map<Tipo, BigDecimal> agruparPorTipo(
            final Set<Ativo> ativos
    ) {
        return ativos.stream().collect( Collectors.groupingBy( Ativo::getTipo, Collectors.reducing( BigDecimal.ZERO,
                Ativo::getValorTotal,
                BigDecimal::add ) ) );
    }

    public static Map<Categoria, BigDecimal> agruparPorCategoria(
            final Set<Ativo> ativos
    ) {
        return ativos.stream().collect( Collectors.groupingBy( Ativo::getCategoria, Collectors.reducing( BigDecimal.ZERO,
                Ativo::getValorTotal,
                BigDecimal::add ) ) );
    }

    public static Map<Setor, BigDecimal> agruparPorSetor(
            final Set<Ativo> ativos
    ) {
        return ativos.stream().collect( Collectors.groupingBy( Ativo::getSetor, Collectors.reducing( BigDecimal.ZERO,
                Ativo::getValorTotal,
                BigDecimal::add ) ) );
    }

    public static Map<Tipo, Long> agruparTipoQtd(
            final Set<Ativo> ativos
    ) {
        return ativos.stream().collect(Collectors.groupingBy(Ativo::getTipo,
               Collectors.reducing(0L, Ativo::getQtd, Long::sum) ));
    }

    public static Map<String, BigDecimal> agruparNomeAtivoValor(
            final Set<Ativo> ativos
    ) {
         return ativos.stream().collect(Collectors.groupingBy(Ativo::getNome, Collectors.reducing( BigDecimal.ZERO,
                Ativo::getValorTotal,
                BigDecimal::add)));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BUILDER
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
