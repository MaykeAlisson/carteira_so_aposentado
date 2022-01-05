package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import static br.com.carteiradoaposentado.infra.util.UtilEnum.getEnum;
import static java.util.Objects.nonNull;

public class CategoriaAtivoDeserialize extends StdDeserializer<Categoria> {

    private static final long serialVersionUID = 559749590998722306L;

    public CategoriaAtivoDeserialize() {
        this(null);
    }

    protected CategoriaAtivoDeserialize(final Class<Categoria> clazz) {
        super(clazz);
    }

    @Override
    public Categoria deserialize(
            final JsonParser jsonParser,
            final DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException {
        return nonNull(jsonParser)
                ? getEnum(Categoria.class, jsonParser.getShortValue())
                : null;
    }
}
