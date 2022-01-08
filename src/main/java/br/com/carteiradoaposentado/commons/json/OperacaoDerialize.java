package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Operacao;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import static br.com.carteiradoaposentado.infra.util.UtilEnum.getEnum;
import static java.util.Objects.nonNull;

public class OperacaoDerialize extends StdDeserializer<Operacao> {

    private static final long serialVersionUID = -800380959200120544L;

    public OperacaoDerialize() {
        this(null);
    }

    protected OperacaoDerialize(final Class<Operacao> clazz) {
        super(clazz);
    }

    @Override
    public Operacao deserialize(
            final JsonParser jsonParser,
            final DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException {
        return nonNull(jsonParser)
                ? getEnum(Operacao.class, jsonParser.getValueAsString())
                : null;
    }
}
