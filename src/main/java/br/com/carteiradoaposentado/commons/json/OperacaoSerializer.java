package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Operacao;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OperacaoSerializer extends StdSerializer<Operacao> {

    public OperacaoSerializer() {
        super(Operacao.class);
    }

    @Override
    public void serialize(
            final Operacao value,
            final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeString(value.getDescricao());
    }
}