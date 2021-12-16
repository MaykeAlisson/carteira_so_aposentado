package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Tipo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import static br.com.carteiradoaposentado.infra.util.UtilEnum.getEnum;
import static java.util.Objects.nonNull;


public class TipoAtivoDeserialize extends StdDeserializer<Tipo> {

    public TipoAtivoDeserialize() {
        this(null);
    }

    protected TipoAtivoDeserialize(final Class<Tipo> clazz) {
        super(clazz);
    }

    @Override
    public Tipo deserialize(
            final JsonParser jsonParser,
            final DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException {
        return nonNull(jsonParser)
                ? getEnum(Tipo.class, jsonParser.getShortValue())
                : null;
    }
}
