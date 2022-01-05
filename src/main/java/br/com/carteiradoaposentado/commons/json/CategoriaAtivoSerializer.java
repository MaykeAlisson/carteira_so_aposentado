package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CategoriaAtivoSerializer extends StdSerializer<Categoria> {

    private static final long serialVersionUID = -5827735297254406566L;

    public CategoriaAtivoSerializer() {
        super(Categoria.class);
    }

    @Override
    public void serialize(
            final Categoria value,
            final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeString(value.getDescricao());
    }
}
