package br.com.carteiradoaposentado.commons.json;

import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

import static br.com.carteiradoaposentado.infra.util.UtilConstante.getValor;

public class SetorAtivoSerializer extends StdSerializer<Setor> {

    public SetorAtivoSerializer() {

        super( Setor.class );
    }

    @Override
    public void serialize(
            final Setor value,
            final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeString( value.getDescricao() );
    }
}