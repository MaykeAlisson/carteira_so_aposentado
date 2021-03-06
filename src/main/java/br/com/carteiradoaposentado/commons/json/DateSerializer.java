package br.com.carteiradoaposentado.commons.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateSerializer  extends StdSerializer<LocalDate> {

    private static final long serialVersionUID = -441678039619274271L;

    public DateSerializer() {
        super(LocalDate.class);
    }

    public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
        String format = Objects.nonNull(value) ? DateTimeFormatter.ISO_DATE.format(value) : null;
        gen.writeString(format);
    }
}
