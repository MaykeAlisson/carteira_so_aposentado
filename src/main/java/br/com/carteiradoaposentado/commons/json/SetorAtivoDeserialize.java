package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Setor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import static br.com.carteiradoaposentado.infra.util.UtilEnum.getEnum;
import static java.util.Objects.nonNull;

public class SetorAtivoDeserialize extends StdDeserializer<Setor> {
    public SetorAtivoDeserialize() {
        this(null);
    }

    protected SetorAtivoDeserialize(final Class<Setor> clazz) {
        super(clazz);
    }

    @Override
    public Setor deserialize(
            final JsonParser jsonParser,
            final DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException {
        return nonNull(jsonParser)
                ? getEnum(Setor.class, jsonParser.getShortValue())
                : null;
    }
}
