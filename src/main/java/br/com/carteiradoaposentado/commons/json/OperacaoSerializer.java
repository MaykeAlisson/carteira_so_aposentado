package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Operacao;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OperacaoSerializer extends StdSerializer<Operacao> {

    private static final long serialVersionUID = 7059927509929509653L;

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
