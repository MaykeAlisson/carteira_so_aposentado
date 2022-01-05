package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Tipo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class TipoAtivoSerializer extends StdSerializer<Tipo> {

    private static final long serialVersionUID = 4352371060898387977L;

    public TipoAtivoSerializer() {
        super(Tipo.class);
    }

    @Override
    public void serialize(
            final Tipo value,
            final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeString(value.getDescricao());
    }
}
