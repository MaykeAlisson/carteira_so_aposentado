package br.com.carteiradoaposentado.domain;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Document(collection = "carteira")
public class Carteira implements Serializable {

    @Id
    private String id;
    private String idUsuario;
    private Map<Tipo, Double> porcentagemTipo;
    private Map<Categoria, Double> porcentagemCategoria;
    private Map<Setor, Double> porcentagemSetor;
    private BigDecimal patrimonio;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Carteira(
            final String idUsuario,
            final Map<Tipo, Double> porcentagemTipo,
            final Map<Categoria, Double> porcentagemCategoria,
            final  Map<Setor, Double> porcentagemSetor,
            final BigDecimal patrimonio
    ) {
        this.idUsuario = idUsuario;
        this.porcentagemTipo = porcentagemTipo;
        this.porcentagemCategoria = porcentagemCategoria;
        this.porcentagemSetor = porcentagemSetor;
        this.patrimonio = patrimonio;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Map<Tipo, Double> getPorcentagemTipo() {
        return porcentagemTipo;
    }

    public void setPorcentagemTipo(Map<Tipo, Double> porcentagemTipo) {
        this.porcentagemTipo = porcentagemTipo;
    }

    public Map<Categoria, Double> getPorcentagemCategoria() {
        return porcentagemCategoria;
    }

    public void setPorcentagemCategoria(Map<Categoria, Double> porcentagemCategoria) {
        this.porcentagemCategoria = porcentagemCategoria;
    }

    public Map<Setor, Double> getPorcentagemSetor() {
        return porcentagemSetor;
    }

    public void setPorcentagemSetor(Map<Setor, Double> porcentagemSetor) {
        this.porcentagemSetor = porcentagemSetor;
    }

    public BigDecimal getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(BigDecimal patrimonio) {
        this.patrimonio = patrimonio;
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
        Carteira carteira = (Carteira) o;
        return Objects.equals(id, carteira.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
