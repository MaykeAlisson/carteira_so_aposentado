package br.com.carteiradoaposentado.domain;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Document(collection = "ativo")
public class Ativo implements Serializable {

    @Id
    private String id;
    private String nome;
    private String idUser;
    private Categoria categoria;
    private Setor setor;
    private Long qtd;
    private Double valor;
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
            final Categoria categoria,
            final Setor setor,
            final Long qtd,
            final Double valor,
            final LocalDateTime criacao,
            final Set<Fundamento> analise
    ) {
        this.nome = nome;
        this.idUser = idUser;
        this.categoria = categoria;
        this.setor = setor;
        this.qtd = qtd;
        this.valor = valor;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EQUALS & HASCODE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BUILDER
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
